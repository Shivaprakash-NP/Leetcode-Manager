# Maximum Score From Removing Substrings: LeetCode Problem Explanation

## 1. Problem Understanding

The problem "Maximum Score From Removing Substrings" asks us to find the maximum score achievable by repeatedly removing occurrences of "ab" or "ba" from a given string `s`.  Removing "ab" earns a score of `x`, and removing "ba" earns a score of `y`.  The goal is to maximize the total score by strategically removing these substrings.

## 2. Approach / Intuition

The solution employs a greedy approach using two stacks. The core idea is to process the string twice, prioritizing different substring removals each time.

* **First Pass (solve function):** This pass prioritizes removing one type of substring (e.g., "ab" if x > y).  It iterates through the string, using a stack to track characters. If it encounters a "c1c2" (e.g., "ab"), it pops `c1` from the stack, indicating the removal, and adds the corresponding score (v1).  Characters that don't contribute to removals are pushed onto the stack.

* **Second Pass (solve function):** This pass processes the remaining characters (those left in the stack from the first pass).  It uses a second stack and prioritizes removing the other type of substring (e.g., "ba" if x > y). This ensures we consider all possible removals.

* **maximumGain function:** This function determines which substring ("ab" or "ba") should be prioritized in the first pass based on which has a higher score (x or y).  This is crucial because the order of removal can significantly impact the final score.


This greedy approach works because removing the higher-scoring substring first leads to an optimal solution.  Consider that removing a "ba" might prevent the opportunity to remove future "ab" substrings, and vice-versa.  Prioritizing the substring with higher value maximizes the score.

## 3. Data Structures and Algorithms

* **Stack:** Two stacks are used to efficiently track characters and manage removals.  Stacks are appropriate here because they allow for LIFO (Last-In, First-Out) operations, which is exactly what we need to handle substring removals.
* **Greedy Algorithm:** The overall strategy is a greedy algorithm because we make the locally optimal choice (removing the highest-scoring substring) at each step, aiming for a globally optimal solution.


## 4. Code Walkthrough

**`solve(String s, char c1, char c2, int v1, int v2)`:**

*   The function takes the string `s`, characters `c1` and `c2` defining the substring to prioritize, and their respective scores `v1` and `v2`.
*   It initializes a stack `st` and a score variable `g`.
*   The loop iterates through the characters of `s`.  If `c2` is encountered and the stack's top is `c1`, it means the substring `c1c2` is found.  `c1` is popped, and `v1` is added to the score `g`.  Otherwise, the character is pushed onto the stack.
*   A second stack `st1` is used to process the remaining characters from the first stack. This second pass accounts for any remaining potential removals after the initial prioritization.
*   The function returns the total score `g`.


**`maximumGain(String s, int x, int y)`:**

*   This function acts as the main entry point.
*   It compares `x` and `y` to determine which substring ("ab" or "ba") should be prioritized in the `solve` function.
*   It calls the `solve` function with appropriate parameters and returns the maximum score.

## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the length of the string `s`. The string is traversed at most twice (once for each stack). Each stack operation takes constant time.

* **Space Complexity:** O(N) in the worst-case scenario. This is because the stacks could potentially store up to all the characters from the string if no removals occur. In the best case it would be O(1).


The solution is efficient because it performs a linear scan of the string, leading to a linear time complexity.  The space used is proportional to the length of the string in the worst case.  The algorithm's efficiency makes it suitable for handling relatively long input strings.
