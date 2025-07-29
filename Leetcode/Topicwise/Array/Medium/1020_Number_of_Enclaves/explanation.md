## LeetCode: Number of Enclaves - Detailed Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the number of "enclaves" in a given grid. An enclave is a group of 1s that are completely surrounded by 0s (or the grid's boundaries).  We need to count only those 1s that are not connected to the boundary of the grid.

**2. Approach / Intuition:**

The solution employs a Depth-First Search (DFS) algorithm to identify and mark all 1s connected to the boundary.  The core idea is that if a '1' is reachable from the boundary, it's not an enclave.  By performing DFS from all boundary 1s, we effectively "flood-fill" and remove all such 1s.  Whatever 1s remain after this process are the enclaves.  We then simply count these remaining 1s.  This approach is efficient because it avoids redundant checks and directly targets the relevant parts of the grid.

**3. Data Structures and Algorithms:**

* **Data Structures:** The input `grid` is a 2D array (matrix) representing the grid.  No other significant data structures are used beyond the implicit call stack of the recursive DFS function.
* **Algorithms:**  The primary algorithm is Depth-First Search (DFS), a graph traversal algorithm.  We also use a simple iterative approach for counting the remaining 1s.

**4. Code Walkthrough:**

* **`dfs(int i, int j, int[][] mat)`:** This is a recursive helper function.
    * It takes the row (`i`), column (`j`), and the grid (`mat`) as input.
    * **Base Case:** If `i` or `j` is out of bounds, or if the current cell (`mat[i][j]`) is not a 1, it returns (stops the recursion).
    * **Marking Visited:** If the current cell is a 1, it's marked as -1 to indicate that it's been visited and is reachable from the boundary (this avoids revisiting it and ensures correctness).
    * **Recursive Calls:** It recursively calls itself for the four neighboring cells (up, down, left, right).

* **`numEnclaves(int[][] grid)`:** This is the main function.
    * It gets the dimensions (`n`, `m`) of the grid.
    * **Boundary DFS:** It iterates through the top and bottom rows, and then the leftmost and rightmost columns. For each cell containing a 1 on the boundary, it calls `dfs` to mark all connected 1s as -1.
    * **Counting Enclaves:** It iterates through the entire grid again. This time, it counts all cells with a value of 1. These are the 1s that were not reachable from the boundary, i.e., the enclaves.
    * **Return Value:** It returns the `count` of remaining 1s.


**5. Time and Space Complexity:**

* **Time Complexity:** O(M * N), where M and N are the dimensions of the grid.  This is because the DFS function visits each cell at most once, and the initial boundary iteration and final counting loop are also linear in the size of the grid.

* **Space Complexity:** O(M * N) in the worst case.  This is due to the implicit space used by the recursive call stack during the DFS. In the worst-case scenario (where most of the grid consists of 1s), the depth of the recursion could be proportional to the grid's dimensions.  However, if the grid is sparse or contains many 0s, the space complexity will be much lower.  The space used by the grid itself is not considered part of the algorithm's space complexity.


**Improvements:**

While the provided code is functional, using an iterative DFS (using a stack) would improve space complexity in the worst case by avoiding potential stack overflow issues for very large grids.  The iterative approach avoids the implicit space usage of recursion.
