### Problem Understanding

The problem "Delete Columns to Make Sorted" asks us to consider an array of strings, `strs`, as if they form a grid or matrix of characters. For example, if `strs = ["cba", "daf", "ghi"]`, it can be visualized as:

```
c d g
b a h
a f i
```

We need to determine the minimum number of columns that must be deleted so that the remaining columns are sorted non-decreasingly. A column is considered "sorted non-decreasingly" if, when read from top to bottom, each character is less than or equal to the character immediately below it. For example, in the visualization above:
*   Column 0 (`c`, `b`, `a`) is *not* sorted because `c > b` and `b > a`.
*   Column 1 (`d`, `a`, `f`) is *not* sorted because `d > a`.
*   Column 2 (`g`, `h`, `i`) *is* sorted because `g <= h` and `h <= i`.

The goal is to count how many columns are *not* sorted in this manner. Each unsorted column must be deleted to satisfy the condition, and since the sorted status of one column does not affect another, simply counting them gives the minimum deletions.

### Approach / Intuition

The core idea is straightforward: iterate through each column one by one and check if it satisfies the non-decreasing order condition. If a column violates this condition at any point (i.e., `strs[j-1].charAt(col_idx) > strs[j].charAt(col_idx)` for any adjacent rows `j-1` and `j`), then that column is deemed "unsorted" and must be deleted. We increment a counter for such columns. Once a column is identified as unsorted, there's no need to check further rows within that same column; we can immediately move on to the next column.

This approach works because:
1.  The sorted status of each column is independent. Deleting one column doesn't change whether another column is sorted or not.
2.  We are looking for the *minimum* number of deletions. If a column is unsorted, it *must* be deleted. If it's sorted, it *can* be kept. Thus, simply counting all unsorted columns gives the minimum.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `String[] strs`: An array of strings, representing the input grid of characters.
*   **Algorithms:**
    *   **Nested Loops:** Two nested `for` loops are used to iterate through the columns and then through the rows within each column.
    *   **Character Comparison:** Basic comparison operators (`>`) are used to check the lexicographical order of characters.
    *   **Early Exit (`break`):** An optimization to stop checking rows for a column as soon as a violation is found, improving efficiency in cases where violations occur early.

### Code Walkthrough

```java
class Solution {
    public int minDeletionSize(String[] strs) {
        // 1. Determine the number of columns.
        // All strings in 'strs' are guaranteed to have the same length.
        // So, we can take the length of the first string (strs[0]).
        int n = strs[0].length(); // 'n' represents the number of columns.

        // 2. Initialize a counter for the number of columns that need to be deleted.
        int cnt = 0;

        // 3. Outer loop: Iterate through each column.
        // 'i' represents the current column index, from 0 to n-1.
        for(int i = 0; i < n; i++) {
            // 4. Inner loop: Iterate through the rows for the current column 'i'.
            // We start 'j' from 1 because we need to compare strs[j-1] with strs[j].
            // 'j' goes from 1 up to strs.length - 1 (the last valid row index).
            for(int j = 1; j < strs.length; j++) {
                // 5. Character comparison: Check if the current column 'i' is unsorted.
                // We compare the character at column 'i' in the previous row (j-1)
                // with the character at column 'i' in the current row (j).
                if(strs[j-1].charAt(i) > strs[j].charAt(i)) {
                    // 6. If a violation is found (character in previous row is greater),
                    // this column is unsorted and must be deleted.
                    cnt++; // Increment the deletion counter.

                    // 7. Optimization: Once a column is found to be unsorted,
                    // there's no need to check the remaining rows for this column.
                    // We can break out of the inner loop and move to the next column.
                    break;
                }
            }
        }

        // 8. Return the total count of unsorted columns found.
        return cnt;
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   Let `R` be the number of strings (rows) in the input `strs` (i.e., `strs.length`).
    *   Let `C` be the length of each string (columns) (i.e., `strs[0].length()`).
    *   The outer loop iterates `C` times, once for each column.
    *   The inner loop iterates at most `R-1` times (from `j=1` to `R-1`), comparing characters in adjacent rows. In the worst case (e.g., all columns are sorted, or the violation always occurs at the last possible row), the inner loop runs for its full duration.
    *   Inside the inner loop, `charAt(i)` is an O(1) operation in Java.
    *   Therefore, the total time complexity is proportional to `C * R`.
    *   **Time Complexity: O(C * R)**

*   **Space Complexity:**
    *   The solution uses a few integer variables (`n`, `cnt`, `i`, `j`) to store the column count, deletion count, and loop indices.
    *   These variables consume a constant amount of extra space, regardless of the input size.
    *   No auxiliary data structures (like arrays, lists, maps) are created that scale with the input size.
    *   **Space Complexity: O(1)**