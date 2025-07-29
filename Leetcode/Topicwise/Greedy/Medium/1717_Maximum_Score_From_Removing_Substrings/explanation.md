## LeetCode: Maximum Score From Removing Substrings - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum score achievable by repeatedly removing substrings "ab" or "ba" from a given string `s`.  Removing "ab" gives a score of `x`, and removing "ba" gives a score of `y`. The goal is to maximize the total score.  The order of removals matters, as removing one substring might create opportunities to remove others later.


**2. Approach / Intuition:**

The solution employs a greedy approach with two passes using stacks.  The core idea is that we can maximize the score by prioritizing the removal of the substring that yields a higher score.  We achieve this by iteratively removing either "ab" or "ba" based on whether `x` (score for "ab") or `y` (score for "ba") is larger.

The `solve` function performs this greedy removal. It processes the string once to remove as many high-value substrings as possible in one direction (e.g., "ab" if `x > y`). Then, it reverses the process on the remaining string to handle remaining substrings that might have become available after the first pass. This two-pass approach ensures that we consider all possibilities for maximizing the score.

The `maximumGain` function simply determines which substring ("ab" or "ba") yields the higher score and calls the `solve` function accordingly, ensuring that the higher-scoring substring is prioritized.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is a `Stack`. Stacks are ideal for this problem because they allow us to efficiently remove substrings from the end.  The LIFO (Last-In, First-Out) property of a stack directly corresponds to the order of character removal in the string.
* **Algorithms:** The algorithm used is a greedy algorithm with two passes. It's not a standard algorithm like Dijkstra's or mergesort but rather a custom greedy strategy built around stack operations.


**4. Code Walkthrough:**

* **`solve(String s, char c1, char c2, int v1, int v2)`:** This function is the core of the solution.
    * `int g = 0;`: Initializes the total score `g` to 0.
    * `Stack<Character> st = new Stack<>();`: Creates a stack to store characters while iterating through the string.
    * `for(char c : s.toCharArray())`: Iterates through each character of the input string.
    * `if(!st.isEmpty() && c==c2 && st.peek()==c1)`: Checks if the stack is not empty and if the current character `c` and the top of the stack form a substring ("ab" or "ba", based on `c1` and `c2`). If so, it pops the top element ( `c1`), removes the substring, and adds the corresponding score (`v1`) to `g`.
    * `else { st.push(c); }`: Otherwise, the character is pushed onto the stack.
    * The second stack (`st1`) and the subsequent `while` loop perform the same operation on the remaining characters in the stack `st`.  This second pass ensures we capture any substrings that might become available after the first pass.
* **`maximumGain(String s, int x, int y)`:** This function acts as an entry point.
    * `if(x>y) return solve(s, 'a', 'b', x, y);`: If `x` (score for "ab") is greater than `y`, it calls `solve` to prioritize removing "ab".
    * `else return solve(s, 'b', 'a', y, x);`: Otherwise, it prioritizes removing "ba".


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the string `s`.  The code iterates through the string twice in the worst case (once for each stack). Each stack operation (push and pop) takes O(1) time.
* **Space Complexity:** O(N) in the worst case. The stacks can potentially store all the characters of the string if no substrings are found.  In the best-case scenario (all substrings are removed quickly), the space complexity would be much lower, but we analyze for the worst case.


In summary, this solution efficiently solves the "Maximum Score From Removing Substrings" problem using a greedy approach with stacks. The two-pass strategy coupled with stack-based character processing ensures that the maximum possible score is achieved. The time and space complexity are linear, making it an optimal solution for this problem.
