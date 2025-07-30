## LeetCode Problem: Calculate Score After Performing Instructions

This document explains the provided Java code solution for the LeetCode problem "Calculate Score After Performing Instructions".

### 1. Problem Understanding:

The problem presents two arrays: `instructions` (String array) and `values` (integer array) of the same length. We start at index 0 and follow the instructions. If the instruction at the current index `i` is "add", we add `values[i]` to our score `s` and move to the next index (i.e., `i++`). Otherwise (if the instruction is not "add"), we jump forward by `values[i]` indices (i.e., `i += values[i]`). We continue this process until we either go out of bounds of the array or revisit an index we've already visited (to avoid infinite loops). The goal is to calculate and return the final score `s`.

### 2. Approach / Intuition:

The core idea is to simulate the execution of the instructions, keeping track of the current index and the accumulated score.  We iterate based on the instructions and values. The key to preventing infinite loops is to detect revisits. We use a `chk` array to mark visited indices. If we encounter an index we've already visited, it indicates a loop, and we terminate the execution.

The approach is chosen because it directly implements the problem description.  It's a straightforward simulation technique.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `int[] chk`: An integer array used as a boolean array to mark visited indices.  A value of 1 indicates the index has been visited; 0 indicates it hasn't.
*   **Algorithms:**
    *   **Simulation:** The main algorithm is a simulation of the instruction execution, incrementing the index based on the instructions and values.
    *   **Visited Node Detection:** The `chk` array implements a simple form of cycle detection by marking visited nodes to avoid infinite loops.

### 4. Code Walkthrough:

```java
class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int i = 0; // Initialize the starting index to 0.
        long s = 0; // Initialize the score to 0.  Using 'long' to avoid potential integer overflow.
        int[] chk = new int[values.length]; // Create a boolean array to track visited indices.

        while(i>=0 && i<values.length) // Loop until we go out of bounds (i < 0 or i >= values.length)
        {
            if(chk[i] == 1) break; // If the current index has already been visited, exit the loop.
            chk[i] = 1; // Mark the current index as visited.

            if(instructions[i].equals("add")) // Check if the instruction at the current index is "add".
            {
                s+=values[i]; // Add the value at the current index to the score.
                i++; // Move to the next index.
            }
            else // If the instruction is not "add" (implicitly implying another instruction).
            {
                i+=values[i]; // Jump forward by the value at the current index.
            }
        }
        return s; // Return the calculated score.
    }
}
```

**Line-by-line explanation:**

1.  `class Solution {`: Defines the Solution class.
2.  `public long calculateScore(String[] instructions, int[] values) {`: Defines the `calculateScore` method, which takes the instructions and values arrays as input and returns a `long` representing the calculated score. The return type is `long` to handle potential overflow if the sum of values becomes large.
3.  `int i = 0;`: Initializes the index `i` to 0, representing the starting position in the arrays.
4.  `long s = 0;`: Initializes the score `s` to 0.  The type is `long` for potential overflow.
5.  `int[] chk = new int[values.length];`: Creates an integer array `chk` of the same length as the `values` array. This array is used to keep track of visited indices to prevent infinite loops.
6.  `while(i>=0 && i<values.length)`: The main loop. It continues as long as the index `i` is within the bounds of the array (i.e., greater than or equal to 0 and less than the length of the `values` array).
7.  `if(chk[i] == 1) break;`: This condition checks if the current index `i` has already been visited. If it has (i.e., `chk[i]` is 1), it means we're in a loop, so we break out of the `while` loop.
8.  `chk[i] = 1;`: Marks the current index `i` as visited by setting `chk[i]` to 1.
9.  `if(instructions[i].equals("add"))`: Checks if the instruction at the current index `i` is equal to the string "add".
10. `s+=values[i];`: If the instruction is "add", the value at the current index `i` in the `values` array is added to the score `s`.
11. `i++;`: After adding the value to the score, the index `i` is incremented by 1 to move to the next index.
12. `else`: If the instruction is not "add" (implicitly another kind of instruction), the following block is executed.
13. `i+=values[i];`: The index `i` is incremented by the value at the current index `i` in the `values` array. This represents a jump to a new index.
14. `return s;`: After the loop finishes (either by going out of bounds or by encountering a visited index), the method returns the final calculated score `s`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the length of the `instructions` and `values` arrays. In the worst-case scenario, we might visit each index once before either going out of bounds or detecting a cycle.
*   **Space Complexity:** O(N), due to the `chk` array, which stores visited indices.  The size of this array is proportional to the input array size.
