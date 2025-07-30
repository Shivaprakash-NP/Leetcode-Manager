## Maximal Rectangle Problem Explanation

### 1. Problem Understanding:

The "Maximal Rectangle" problem asks us to find the area of the largest rectangle containing only '1's within a given 2D binary matrix (containing only '0's and '1's). The rectangle's sides must be parallel to the matrix's sides.

### 2. Approach / Intuition:

The solution leverages the concept of the "Largest Rectangle in Histogram" problem. The core idea is to treat each row of the matrix as the base of a histogram and calculate the height of each bar in the histogram based on consecutive '1's stacked on top of each other vertically.

Here's the breakdown:

1.  **Building Histograms:**  The `maximalRectangle` function first transforms the input character matrix (`char[][] matrix`) into an integer matrix (`int[][] mat`).  Each element `mat[i][j]` represents the height of a bar in a histogram, calculated by summing consecutive '1's vertically up to that row. If `matrix[i][j]` is '0', `mat[i][j]` becomes 0, effectively breaking the consecutive '1's.

2.  **Largest Rectangle in Histogram for Each Row:** For each row in the `mat` matrix (which represents a histogram), the `maxrect` function calculates the largest rectangular area that can fit within that histogram.

3.  **Finding the Maximum:** The `maximalRectangle` function iterates through all the rows (histograms) and finds the maximum rectangular area among all of them.  This maximum area is the answer to the original problem.

**Why this approach?**

The "Largest Rectangle in Histogram" is a well-known problem that can be solved efficiently using a stack.  By transforming the 2D matrix into a series of histograms, we can reuse this efficient algorithm to find the maximum rectangular area within each histogram (row). Combining the results from each row allows us to find the maximal rectangle in the original matrix.

### 3. Data Structures and Algorithms:

*   **`int[][] mat`:**  A 2D integer array to store the heights of the histograms represented by each row.
*   **`Stack<Integer> st`:** A stack is used in `maxrect` to efficiently find the "Previous Smaller Element" (PSE) and "Next Smaller Element" (NSE) for each bar in the histogram.
*   **`int[] pse`:**  An array to store the index of the Previous Smaller Element for each bar in the histogram.
*   **`int[] nse`:**  An array to store the index of the Next Smaller Element for each bar in the histogram.
*   **Algorithms:**
    *   **Stack-based algorithm for Largest Rectangle in Histogram:** Used in the `maxrect` function to determine the largest rectangle for a given histogram.
    *   **Dynamic Programming (Implicit):** The conversion of the character matrix to the integer matrix can be seen as a form of dynamic programming, where `mat[i][j]`'s value depends on `mat[i-1][j]`.

### 4. Code Walkthrough:

**`maxrect(int[] hei)` Function:**

This function calculates the largest rectangle area in a given histogram represented by the `hei` array.

1.  **Initialization:**
    *   `n = hei.length;`: Stores the number of bars in the histogram.
    *   `pse = new int[n];`:  Array to store indices of Previous Smaller Elements.
    *   `nse = new int[n];`:  Array to store indices of Next Smaller Elements.
    *   `Stack<Integer> st = new Stack<>();`:  A stack to keep track of bar indices.

2.  **Finding Previous Smaller Element (PSE):**
    *   The first `for` loop iterates through the `hei` array from left to right.
    *   `while(!st.isEmpty() && hei[st.peek()] >= hei[i]) st.pop();`:  While the stack is not empty and the height of the bar at the top of the stack is greater than or equal to the current bar's height (`hei[i]`), pop elements from the stack. This ensures that we find the closest element to the left which is strictly smaller.
    *   `pse[i] = (st.isEmpty())?-1:st.peek();`:  If the stack is empty, it means there is no smaller element to the left, so `pse[i]` is set to -1. Otherwise, `pse[i]` is set to the index of the top element of the stack (the previous smaller element).
    *   `st.push(i);`:  Push the current bar's index onto the stack.

3.  **Finding Next Smaller Element (NSE):**
    *   `st.clear();`: Clear the stack before finding NSE.
    *   The second `for` loop iterates through the `hei` array from right to left.
    *   `while(!st.isEmpty() && hei[st.peek()] >= hei[i]) st.pop();`: Similar logic as PSE, but iterates from right to left to find the next smaller element.
    *   `nse[i] = (st.isEmpty())?n:st.peek();`:  If the stack is empty, it means there is no smaller element to the right, so `nse[i]` is set to `n` (representing the boundary). Otherwise, `nse[i]` is set to the index of the top element of the stack (the next smaller element).
    *   `st.push(i);`:  Push the current bar's index onto the stack.

4.  **Calculating Maximum Area:**
    *   `int max = 0;`: Initialize the maximum area to 0.
    *   The third `for` loop iterates through the `hei` array.
    *   `int h = hei[i];`: Gets the height of the current bar.
    *   `int w = (nse[i]-pse[i]-1);`: Calculates the width of the rectangle that can be formed with the current bar as its height. The width is determined by the distance between the NSE and PSE indices, minus 1 (to exclude the PSE and NSE themselves).
    *   `max = Math.max(max , h*w);`: Updates the `max` area if the calculated area is larger.

5.  **Return Maximum Area:**
    *   `return max;`: Returns the maximum rectangular area found in the histogram.

**`maximalRectangle(char[][] matrix)` Function:**

This function calculates the maximal rectangular area in the given binary matrix.

1.  **Initialization:**
    *   `int n = matrix.length;`:  Gets the number of rows in the matrix.
    *   `int m = matrix[0].length;`: Gets the number of columns in the matrix.
    *   `int[][] mat = new int[n][m];`: Creates an integer matrix of the same dimensions as the input matrix.

2.  **Building the Histograms:**
    *   The first `for` loop initializes the first row of the `mat` matrix based on the first row of the input `matrix`.  If `matrix[0][i]` is '0', `mat[0][i]` is set to 0; otherwise, it's set to 1.
    *   The nested `for` loops iterate through the remaining rows and columns of the input `matrix`.
    *   `int v = (matrix[j][i]=='0')?0:1;`: Sets `v` to 0 if the corresponding element in `matrix` is '0', and to 1 otherwise.
    *   `mat[j][i] = (mat[j-1][i]+v)*v;`:  Calculates the height of the bar at `mat[j][i]`.  If `v` is 0, then `mat[j][i]` becomes 0, breaking the consecutive '1's. If `v` is 1, then `mat[j][i]` is the sum of the value above it (`mat[j-1][i]`) and 1, effectively counting consecutive '1's vertically.

3.  **Finding the Maximum Area:**
    *   `int max = 0;`: Initializes the maximum area to 0.
    *   The enhanced `for` loop iterates through each row (`hei`) in the `mat` matrix (each row representing a histogram).
    *   `max = Math.max(max , maxrect(hei));`:  Calls the `maxrect` function to find the maximum rectangular area in the current histogram and updates the `max` area if the result is larger.

4.  **Return Maximum Area:**
    *   `return max;`: Returns the overall maximum rectangular area found in the matrix.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   `maxrect(int[] hei)`: O(n), where n is the length of the histogram.  The loops iterate through the histogram once for PSE, once for NSE, and once for area calculation. The stack operations (push and pop) take amortized O(1) time.
    *   `maximalRectangle(char[][] matrix)`: O(m\*n), where n is the number of rows and m is the number of columns in the matrix.
        *   Building the `mat` matrix: O(m\*n).
        *   Iterating through rows and calling `maxrect`: O(n \* m), because `maxrect` is O(m) and it's called n times.

    *   Therefore, the overall time complexity is **O(m\*n)**.

*   **Space Complexity:**
    *   `maxrect(int[] hei)`: O(n) due to the `pse`, `nse` arrays, and the stack.
    *   `maximalRectangle(char[][] matrix)`: O(m\*n) due to the `mat` matrix.  Additionally `maxrect` uses O(m) space. Since O(m\*n) dominates O(m), the space complexity remains **O(m\*n)**.
