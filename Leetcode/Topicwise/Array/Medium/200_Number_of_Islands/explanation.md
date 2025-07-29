## LeetCode: Number of Islands - Detailed Solution Explanation

**1. Problem Understanding:**

The "Number of Islands" problem asks us to count the number of distinct islands in a given 2D binary matrix (represented as a `char[][]`).  A '1' represents land, and a '0' represents water.  Islands are connected horizontally or vertically (not diagonally).  We need to determine the number of separate groups of connected '1's.


**2. Approach / Intuition:**

This solution uses Breadth-First Search (BFS) to efficiently count the islands.  The core idea is:

* **Iteration:** We iterate through each cell of the grid.
* **Island Detection:** If a cell contains '1' (land) and hasn't been visited yet, we've found a new island.
* **BFS Traversal:**  We use BFS to explore all connected land cells belonging to this island.  This ensures we don't recount connected land cells as separate islands.
* **Visited Tracking:** A `boolean[][] vis` array keeps track of visited cells to avoid revisiting and infinite loops.
* **Island Count:** Each time we encounter an unvisited '1', we increment the `ans` (island count) and perform BFS to mark all cells in that island as visited.

This BFS approach is chosen because it systematically explores all connected components, preventing double-counting and ensuring we cover all parts of each island.  Depth-First Search (DFS) could also be used, but BFS often provides better performance for this type of problem in terms of memory usage on very large grids due to its queue-based nature.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `char[][] grid`:  The input 2D grid representing the map of land and water.
    * `boolean[][] vis`: A 2D boolean array to track visited cells.  This prevents redundant processing.
    * `Queue<int[]> q`: A queue used in the BFS algorithm to store coordinates of cells to be visited.  `int[]` represents the (row, column) coordinates of a cell.
    * `int[][] dir`: A 2D array to efficiently store the four possible directions (up, down, left, right) for movement during BFS.

* **Algorithms:**
    * **Breadth-First Search (BFS):** Used to explore all connected components (islands) efficiently.
    * **Iteration:**  Nested loops iterate through each cell in the grid.


**4. Code Walkthrough:**

```java
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0; // Initialize island count
        int n = grid.length; // Rows
        int m = grid[0].length; // Columns
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // Directions (up, down, left, right)
        boolean[][] vis = new boolean[n][m]; // Visited array

        for(int i = 0; i<n; i++) { // Iterate through rows
            for(int j = 0; j<m; j++) { // Iterate through columns
                if(grid[i][j] == '1' && !vis[i][j]) { // Found unvisited land
                    ans++; // Increment island count
                    Queue<int[]> q = new LinkedList<>(); // Create BFS queue
                    q.offer(new int[]{i, j}); // Add starting cell
                    vis[i][j] = true; // Mark as visited
                    while(!q.isEmpty()) { // BFS traversal
                        int[] p = q.poll(); // Get next cell

                        for(int[] d : dir) { // Explore adjacent cells
                            int ni = p[0]+d[0]; // New row
                            int nj = p[1]+d[1]; // New column
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == '1' && !vis[ni][nj]) { // Check bounds and if it's land and unvisited
                                q.offer(new int[]{ni, nj}); // Add to queue
                                vis[ni][nj] = true; // Mark as visited
                            }
                        }
                    }
                }
            }
        }

        return ans; // Return the total island count
    }
}
```


**5. Time and Space Complexity:**

* **Time Complexity:** O(M * N), where M is the number of rows and N is the number of columns in the grid.  In the worst-case scenario (a grid entirely filled with '1's), we visit each cell exactly once.  The BFS operation within each island takes time proportional to the size of the island. However, each cell is visited at most once overall.

* **Space Complexity:** O(M * N) in the worst case. This is due to the `vis` array which stores visited information for each cell.  The queue used in BFS can also consume space proportional to the size of the largest island, but this is still bounded by O(M * N) in the worst case.  The `dir` array is constant space.
