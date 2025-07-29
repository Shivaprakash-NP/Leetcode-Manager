## LeetCode: Surrounded Regions Explained

**1. Problem Understanding:**

The problem "Surrounded Regions" asks us to modify a given 2D board containing 'X' and 'O' characters.  We need to capture all 'O's that are not connected to a boundary (top, bottom, left, or right edge) and replace them with 'X's.  'O's that are connected to the boundary, either directly or indirectly through other 'O's, should remain unchanged.


**2. Approach / Intuition:**

The solution employs a Depth-First Search (DFS) algorithm to identify and mark 'O's connected to the boundary.  The core idea is that any 'O' that can be reached from the boundary cannot be surrounded.

The algorithm first iterates through the boundary of the board.  If it finds an 'O', it initiates a DFS traversal from that point.  During the DFS, all connected 'O's are marked with a temporary character ('S' in this case).  After exploring all boundary-connected 'O's, the algorithm iterates through the board again. Any remaining 'O's (which were not marked 'S') are surrounded and are changed to 'X', while the temporary 'S' markers are reverted back to 'O'.

This approach is efficient because it avoids checking every 'O' individually. It focuses only on 'O's accessible from the boundary, significantly reducing the search space.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is a 2D character array (`char[][]`) to represent the board.  The `int[]` array is used to represent the coordinates (row, column) during the DFS traversal.
* **Algorithms:** The core algorithm is Depth-First Search (DFS), a graph traversal algorithm used to explore all connected components of 'O's starting from the boundary.


**4. Code Walkthrough:**

* **`dfs(int[] ind, char[][] mat)`:** This is the recursive DFS function.  It takes the coordinates (`ind`) and the board (`mat`) as input.
    * It checks if the current coordinates are within the bounds of the board. If not, it returns.
    * If the current cell contains 'O', it changes it to 'S', marking it as visited and connected to the boundary.
    * It then recursively calls itself for the adjacent cells (right, left, down, up).

* **`solve(char[][] board)`:** This is the main function.
    * It gets the dimensions of the board.
    * It iterates over the top and bottom rows, and left and right columns of the board. If it encounters an 'O', it initiates a DFS traversal from that position using `dfs()`. This ensures that all 'O's connected to the boundary are marked with 'S'.
    * Finally, it iterates through the entire board. If it finds an 'O', it replaces it with 'X' (surrounded 'O's). If it finds an 'S', it replaces it with 'O' (boundary-connected 'O's).

**5. Time and Space Complexity:**

* **Time Complexity:** O(M*N), where 'M' is the number of rows and 'N' is the number of columns in the board.  The algorithm visits each cell at most once during the initial boundary check and the final replacement process. The DFS calls only visit cells connected to the boundary.  Therefore, the overall complexity is dominated by the traversal of the entire board.

* **Space Complexity:** O(M*N) in the worst case. This is because the space usage is primarily determined by the recursion depth of the DFS. In the worst-case scenario (a board filled entirely with 'O's), the recursion depth could reach O(M*N).  In addition to this, the implicit space used by the recursive call stack contributes to this complexity.  However, if the board contains many 'X's or isolated 'O's, the space complexity will be much lower.


This solution efficiently solves the Surrounded Regions problem using a clever combination of DFS and a temporary marker to distinguish between surrounded and boundary-connected 'O's.  The use of DFS allows for a concise and relatively easy-to-understand solution.
