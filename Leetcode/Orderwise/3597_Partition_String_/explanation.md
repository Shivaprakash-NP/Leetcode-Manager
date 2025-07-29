## LeetCode: Partition String - Solution Explanation

**1. Problem Understanding:**

The problem asks us to partition a given string `s` into a list of substrings.  The crucial constraint is that each substring must be unique; no substring can be a duplicate of a previously seen substring. The solution should return a list of these unique substrings.

**2. Approach / Intuition:**

The solution employs a greedy approach. It iterates through the string, building substrings one character at a time.  Each new character is appended to the current substring.  Before adding a character, it checks if the resulting substring is already present in a `HashSet` (`seen`). If it's unique, the substring is added to the result list, marked as seen, and the process continues with the next character. If it's not unique, the current substring (without the last character) is considered a partition, added to the result, and a new substring starts with the character that caused the duplication.  This ensures that we always have unique substrings in the partition.  A greedy approach is suitable here because finding the optimal partition (e.g., minimizing the number of partitions) is not explicitly required;  the problem only focuses on ensuring uniqueness.

**3. Data Structures and Algorithms:**

* **HashSet (`seen`):** Used to efficiently track seen substrings.  HashSets provide O(1) average time complexity for `contains()` and `add()` operations.
* **ArrayList (`result`):** Stores the resulting list of unique substrings. ArrayLists offer efficient appending of elements.
* **StringBuilder (`sb`):** Used to efficiently build substrings by appending characters.  `StringBuilder` is more efficient than repeatedly concatenating strings.
* **Greedy Algorithm:** The algorithm makes locally optimal choices (adding characters to a substring until a duplicate is found) without considering the global optimum (which might be a different partition).


**4. Code Walkthrough:**

* **Initialization:** A `HashSet` `seen` to track unique substrings, an `ArrayList` `result` to store the output, and an integer `i` (index) to iterate through the input string are initialized.
* **Outer `while` loop:** This loop iterates through the string until the end (`i < n`).
* **Inner `while` loop:** This loop builds the current substring character by character.
* **`sb.append(s.charAt(i))`:** Appends the current character to the `StringBuilder`.
* **`segment = sb.toString()`:** Converts the `StringBuilder` to a string for checking uniqueness.
* **`!seen.contains(segment)`:** Checks if the substring is unique.
* **If unique:** The substring is added to `result` and `seen`, the index `i` is incremented, and the inner loop breaks to start a new substring.
* **If not unique:** Only the outer loop's index `i` is incremented, continuing the search for a unique substring starting from the current position.  This effectively creates a new partition.
* **Return `result`:** After processing the entire string, the function returns the list of unique substrings.

**5. Time and Space Complexity:**

* **Time Complexity:** O(n*k), where n is the length of the string and k is the maximum length of a repeated substring. In the worst case, the inner loop might iterate up to the length of the string multiple times to find unique substrings.  In the best-case scenario (all characters are unique), it's simply O(n). However, we can consider the average case complexity to be O(n) due to the efficient use of a HashSet for uniqueness checks.
* **Space Complexity:** O(n) in the worst case, primarily due to the `seen` HashSet and the `result` ArrayList.  In the worst-case scenario, every substring can be of length 1 (if all characters are unique), leading to a space complexity proportional to n.  The StringBuilder has negligible space complexity compared to the HashSet and ArrayList.

The provided solution is efficient and well-structured for this problem. The use of a HashSet significantly improves the performance of the uniqueness check, making the average-case time complexity linear.  The space complexity is also linear, which is reasonable given the nature of the problem.
