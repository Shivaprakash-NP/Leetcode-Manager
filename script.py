import os
import shutil
import time
import subprocess
import requests
import json
from datetime import datetime
import google.generativeai as genai
from dotenv import load_dotenv

# --- CONFIGURATION ---

# The root folder for all your LeetCode solutions.
# The script will create this folder if it doesn't exist.
LEETCODE_ROOT_FOLDER = "Leetcode"

# The number of new problems to process in a single run of the script.
# Set this to your desired batch size (e.g., 20).
# To process all available new submissions, set this to 0.
MAX_PROBLEMS_PER_RUN = 20

# --- SCRIPT START ---


def setup_environment():
    """
    Loads environment variables from a .env file and configures the Gemini API.
    Returns the LeetCode session and CSRF token.
    """
    load_dotenv()

    # Load LeetCode credentials
    session = os.getenv("LEETCODE_SESSION")
    csrftoken = os.getenv("CSRFTOKEN")
    if not session or not csrftoken:
        print("🔴 ERROR: LEETCODE_SESSION and/or CSRFTOKEN not found in .env file.")
        print("Please make sure you have a .env file in the same directory with your credentials.")
        exit(1)

    # Load and configure Gemini API
    api_key = os.getenv("GEMINI_API_KEY")
    if not api_key:
        print("🔴 ERROR: GEMINI_API_KEY not found in .env file.")
        exit(1)
    try:
        genai.configure(api_key=api_key)
        print("✅ Gemini API configured successfully.")
    except Exception as e:
        print(f"🔴 ERROR: Could not configure Gemini API: {e}")
        exit(1)

    return session, csrftoken

def get_question_details(title_slug, headers):
    """
    Fetches detailed information about a question using the LeetCode GraphQL API.
    """
    graphql_query = {
        "query": """
            query questionData($titleSlug: String!) {
                question(titleSlug: $titleSlug) {
                    questionId
                    questionFrontendId
                    title
                    difficulty
                    topicTags {
                        name
                    }
                }
            }
        """,
        "variables": {"titleSlug": title_slug}
    }
    try:
        res = requests.post("https://leetcode.com/graphql", json=graphql_query, headers=headers, timeout=10)
        res.raise_for_status() # Raise an exception for bad status codes (4xx or 5xx)
        return res.json()["data"]["question"]
    except requests.exceptions.RequestException as e:
        print(f"🔴 ERROR: Failed to fetch details for {title_slug} using GraphQL. Error: {e}")
        return None

def get_gemini_explanation(code_content, qn_name, lang):
    """
    Uses the Gemini API to generate an explanation for the given code.
    """
    try:
        # Using a recent and capable model.
        model = genai.GenerativeModel('gemini-2.0-flash')
        prompt = f"""
        You are an expert LeetCode problem solver and explainer.
        Given the following {lang} code for a LeetCode problem titled "{qn_name}",
        provide a detailed explanation in Markdown format covering:

        1.  **Problem Understanding:** Briefly restate the problem in your own words.
        2.  **Approach / Intuition:** Explain the high-level strategy and the core logic behind the solution. Why was this approach chosen?
        3.  **Data Structures and Algorithms:** Name the key data structures and algorithms used.
        4.  **Code Walkthrough:** Go through the code section-by-section, explaining what each part does.
        5.  **Time and Space Complexity:** Analyze the time and space complexity of the solution and explain why.

        Here is the code:
        ```{lang}
        {code_content}
        ```
        """
        response = model.generate_content(prompt)
        return response.text
    except Exception as e:
        print(f"      🔴 ERROR generating explanation for '{qn_name}': {e}")
        return f"Error generating explanation: {e}"

def run_git_commands(commit_message):
    """
    Runs git add, commit, and push commands to update the GitHub repository.
    """
    try:
        print("\n🚀 Pushing changes to GitHub...")
        subprocess.run(["git", "add", "."], check=True)
        subprocess.run(["git", "commit", "-m", commit_message], check=True)
        subprocess.run(["git", "push"], check=True)
        print("✅ Successfully pushed to GitHub!")
    except subprocess.CalledProcessError as e:
        print(f"🔴 ERROR: A Git command failed: {e}")
        print("Please ensure you are in a Git repository and have the correct permissions.")
    except FileNotFoundError:
        print("🔴 ERROR: 'git' command not found. Is Git installed and in your system's PATH?")

def get_processed_slugs(root_folder):
    """Scans the local repository to find which problems have already been processed."""
    processed_slugs = set()
    if not os.path.exists(root_folder):
        return processed_slugs

    for root, _, files in os.walk(root_folder):
        # Look for the new markdown file
        if "question_link.md" in files:
            try:
                with open(os.path.join(root, "question_link.md"), 'r', encoding='utf-8') as f:
                    line = f.readline().strip()
                    # Extracts URL from markdown link format like [Text](URL)
                    if line.startswith('[') and '](' in line and line.endswith(')'):
                        url_start = line.find('](') + 2
                        url_end = line.rfind(')')
                        url = line[url_start:url_end]
                        if "/problems/" in url:
                            slug = url.split('/problems/')[1].split('/')[0]
                            processed_slugs.add(slug)
            except Exception as e:
                print(f"⚠️ Could not read or parse question_link.md in {root}: {e}")
    return processed_slugs

def main():
    """
    Main function to drive the script.
    """
    session, csrftoken = setup_environment()

    headers = {
        "authority": "leetcode.com",
        "accept": "application/json, text/plain, */*",
        "accept-language": "en-US,en;q=0.9",
        "cookie": f"LEETCODE_SESSION={session}; csrftoken={csrftoken};",
        "referer": "https://leetcode.com/submissions/",
        "origin": "https://leetcode.com",
        "x-csrftoken": csrftoken,
        "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36",
    }

    # Initialize folders
    orderwise_path = os.path.join(LEETCODE_ROOT_FOLDER, "Orderwise")
    topicwise_path = os.path.join(LEETCODE_ROOT_FOLDER, "Topicwise")
    os.makedirs(orderwise_path, exist_ok=True)
    os.makedirs(topicwise_path, exist_ok=True)

    # Load existing problems to avoid re-processing
    processed_problem_slugs = get_processed_slugs(LEETCODE_ROOT_FOLDER)
    print(f"Found {len(processed_problem_slugs)} problems already processed in local folders.")

    new_solutions_added_to_repo = []
    processed_this_run_count = 0
    
    # Mapping LeetCode lang to file extensions
    lang_to_ext = {
        'python3': '.py', 'python': '.py', 'java': '.java', 'cpp': '.cpp',
        'c': '.c', 'javascript': '.js', 'typescript': '.ts', 'golang': '.go',
        'rust': '.rs', 'kotlin': '.kt', 'swift': '.swift', 'csharp': '.cs'
    }

    # --- Fetch and Process Submissions ---
    offset = 150
    limit = 20 # Fetch submissions in pages of 20. This is the API page size.
    print(f"\n🔍 Fetching latest accepted submissions from LeetCode...")
    
    while True:
        # Check if we have reached the processing limit for this run
        if MAX_PROBLEMS_PER_RUN > 0 and processed_this_run_count >= MAX_PROBLEMS_PER_RUN:
            print(f"\n🏁 Reached the processing limit of {MAX_PROBLEMS_PER_RUN} problems for this run.")
            break

        url = f"https://leetcode.com/api/submissions/?offset={offset}&limit={limit}"
        try:
            res = requests.get(url, headers=headers, timeout=10)
            res.raise_for_status()
            submissions_page = res.json().get("submissions_dump", [])
        except requests.exceptions.RequestException as e:
            print(f"🔴 ERROR: Failed to fetch submissions page (offset={offset}). Error: {e}")
            break

        if not submissions_page:
            print("✅ No more submissions found. Reached the end of your submission history.")
            break

        print(f"   -> Fetched page with {len(submissions_page)} submissions (Offset: {offset}). Processing...")

        for sub in submissions_page:
            # Re-check the limit at the start of each submission processing
            if MAX_PROBLEMS_PER_RUN > 0 and processed_this_run_count >= MAX_PROBLEMS_PER_RUN:
                break

            title_slug = sub["title_slug"]
            
            # Process only unique, accepted submissions that haven't been processed before
            if sub["status_display"] == "Accepted" and title_slug not in processed_problem_slugs:
                
                print(f"✨ Found new accepted solution for '{sub['title']}'. Processing...")
                
                # 1. Get detailed info (this is the GraphQL call)
                details = get_question_details(title_slug, headers)
                if not details:
                    print(f"   🔴 Skipping '{sub['title']}' due to failure in fetching question details.")
                    processed_problem_slugs.add(title_slug) # Avoid retrying this slug
                    continue

                qn_number = details.get("questionFrontendId")
                qn_name_raw = details.get("title")
                
                if not qn_number:
                    print(f"   ⚠️ Skipping '{qn_name_raw}' - Could not determine question number.")
                    processed_problem_slugs.add(title_slug)
                    continue
                
                # 2. Generate Explanation
                print(f"   🤖 Generating explanation for '{qn_name_raw}'...")
                explanation_content = get_gemini_explanation(sub["code"], qn_name_raw, sub["lang"])
                
                # 3. *** ATOMICITY CHECK ***
                # Only proceed to create folders and files if the explanation was generated successfully.
                if "Error generating explanation" in explanation_content:
                    print(f"   🔴 Skipping '{qn_name_raw}' because explanation generation failed.")
                    processed_problem_slugs.add(title_slug) # Avoid retrying this slug
                    continue

                # --- All data is ready, now prepare file paths and content ---
                qn_difficulty = details.get("difficulty", "N/A")
                topic_tags = [tag['name'] for tag in details.get("topicTags", [])]
                qn_name_formatted = qn_name_raw.replace(" ", "_").replace("/", "_").replace("(", "").replace(")", "")
                folder_name = f"{int(qn_number):03d}_{qn_name_formatted}"
                file_extension = lang_to_ext.get(sub["lang"], '.txt')
                code_filename = f"{folder_name}{file_extension}"
                explanation_filename = "explanation.md"
                question_link_filename = "question_link.md"
                question_url = f"https://leetcode.com/problems/{title_slug}/"
                markdown_link = f"[View Problem on LeetCode]({question_url})"

                # --- Create Directory Structures and Save Files ---
                all_dirs_to_create = []
                
                # A. Orderwise Path
                orderwise_problem_dir = os.path.join(orderwise_path, folder_name)
                all_dirs_to_create.append(orderwise_problem_dir)

                # B. Topicwise Paths
                if topic_tags:
                    for tag in topic_tags:
                        tag_folder = tag.replace(' ', '_').replace('/', '_')
                        topic_difficulty_path = os.path.join(topicwise_path, tag_folder, qn_difficulty)
                        all_dirs_to_create.append(os.path.join(topic_difficulty_path, folder_name))
                else:
                    no_topic_path = os.path.join(topicwise_path, "No_Topic", "Uncategorized")
                    all_dirs_to_create.append(os.path.join(no_topic_path, folder_name))

                # Create all directories and write all files
                print(f"   ✍️  Saving files for '{qn_name_raw}'...")
                for problem_dir in all_dirs_to_create:
                    os.makedirs(problem_dir, exist_ok=True)
                    with open(os.path.join(problem_dir, code_filename), 'w', encoding='utf-8') as f:
                        f.write(sub["code"])
                    with open(os.path.join(problem_dir, question_link_filename), 'w', encoding='utf-8') as f:
                        f.write(markdown_link)
                    with open(os.path.join(problem_dir, explanation_filename), 'w', encoding='utf-8') as f:
                        f.write(explanation_content)

                print(f"   ✅ Successfully saved '{qn_name_raw}'.")
                new_solutions_added_to_repo.append(f"{qn_number}. {qn_name_raw}")
                processed_problem_slugs.add(title_slug) # Mark as processed
                processed_this_run_count += 1
                time.sleep(1) # Add a small delay to avoid overwhelming the Gemini API

        offset += limit
        time.sleep(1.5) # Be polite to the LeetCode API

    # --- Final Git Push ---
    if new_solutions_added_to_repo:
        print("\n-----------------------------------------")
        print("Finished processing. New solutions added to repository:")
        for name in new_solutions_added_to_repo:
            print(f"- {name}")
        
        commit_message = f"feat: Add {len(new_solutions_added_to_repo)} new LeetCode solutions\n\n" + "\n".join([f"- {name}" for name in new_solutions_added_to_repo])
        run_git_commands(commit_message)
    else:
        print("\n✅ No new accepted submissions found. Your repository is up to date!")

if __name__ == "__main__":
    main()