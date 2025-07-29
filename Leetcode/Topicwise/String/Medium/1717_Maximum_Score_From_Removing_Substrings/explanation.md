# Maximum Score From Removing Substrings: A Detailed Explanation

## 1. Problem Understanding

The problem "Maximum Score From Removing Substrings" asks us to find the maximum score achievable by removing substrings "ab" or "ba" from a given string `s`.  Removing "ab" gives a score of `x`, and removing "ba" gives a score of `y`.  The goal is to maximize the total score by strategically removing these substrings.  The order of removal matters, as removing one substring might create opportunities for removing others.


## 2. Approach / Intuition

The solution employs a greedy approach using two stacks. The core idea is to process the string twice.  The first pass removes as many "ab" or "ba" substrings as possible in a specific order (determined by comparing x and y). The second pass processes the remaining characters to capture any remaining pairs.

The approach is greedy because it focuses on removing the highest-scoring substring pairs at each step. It prioritizes either "ab" or "ba" depending on which yields a higher score.  This greedy approach is effective because removing one pair can reveal opportunities to remove other pairs later.


## 3. Data Structures and Algorithms

* **Stack:**  The solution uses two stacks (`st` and `st1`) to efficiently track the characters and remove matching pairs.  Stacks are well-suited for this task because they allow for LIFO (Last-In, First-Out) operations, mirroring the way substrings are removed.
* **Greedy Algorithm:** The overall algorithm is a greedy algorithm, prioritizing the removal of higher-value pairs at each step.



## 4. Code Walkthrough

**`solve(String s, char c1, char c2, int v1, int v2)` function:**

This function is the core logic, taking the string, the characters forming the substring ('a' and 'b'), and their respective scores as input.

1. **Initialization:** `g` (score) is initialized to 0, and a stack `st` is created to store characters.

2. **First Pass:** The code iterates through the string. If the stack is not empty and the current character `c` is `c2` and the top of the stack is `c1`, it means we found a pair (`c1c2`).  The top element is popped from the stack, and the corresponding score (`v1`) is added to `g`. Otherwise, the current character is pushed onto the stack.

3. **Second Pass:** A new stack `st1` is created. The elements from `st` (remaining characters) are popped and processed similarly to the first pass. This pass accounts for pairs that might have been missed in the first pass due to the order of characters.

4. **Return Value:** The function returns the total score `g`.

**`maximumGain(String s, int x, int y)` function:**

This function acts as a dispatcher.

1. **Comparison:** It compares `x` and `y` to determine which pair ("ab" or "ba") should be prioritized in the `solve` function. This is crucial because the order matters for achieving the maximal score.

2. **Function Call:** It calls the `solve` function with the appropriate parameters, ensuring the higher-scoring pair is processed first.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the length of the string. The string is iterated through twice in the worst case. Stack operations (push and pop) take constant time, so the overall time complexity remains linear.

* **Space Complexity:** O(N) in the worst case. The stacks `st` and `st1` can store up to N characters if no pairs are found.  In the best-case scenario (all pairs are found), the space complexity is O(1).  Therefore, the space complexity is dominated by the size of the stack, resulting in a linear space complexity.
