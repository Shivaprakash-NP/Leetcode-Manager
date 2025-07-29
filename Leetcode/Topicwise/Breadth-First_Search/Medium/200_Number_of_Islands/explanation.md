## LeetCode: Number of Islands - Detailed Solution Explanation

**1. Problem Understanding:**

The "Number of Islands" problem asks us to count the number of distinct islands in a given 2D binary matrix (represented as a `char[][]` grid).  A '1' represents land, and a '0' represents water.  An island is a group of connected '1's (horizontally or vertically).

**2. Approach / Intuition:**

This solution uses Breadth-First Search (BFS) to efficiently count the islands.  The core idea is to iterate through each cell of the grid. If we encounter a '1' (land) that hasn't been visited, we've found a new island.  We then use BFS to explore all connected land cells belonging to that island, marking them as visited to prevent recounting.  Once the BFS for that island is complete, we increment the island count.

BFS was chosen because it systematically explores all connected components in a graph (in this case, the graph is implicitly defined by the grid and connections between adjacent land cells).  Depth-First Search (DFS) could also be used, but BFS is often preferred for its simpler implementation in this context.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `char[][] grid`:  The input 2D grid representing the map of land and water.
    * `boolean[][] vis`: A 2D boolean array to keep track of visited cells. This prevents revisiting cells and ensures we don't overcount islands.
    * `Queue<int[]> q`: A queue used for the BFS traversal.  It stores the coordinates (`int[]`) of cells to be visited.
    * `int[][] dir`: A 2D array storing the four possible directions (up, down, left, right) to explore neighboring cells.

* **Algorithms:**
    * **Breadth-First Search (BFS):** Used to explore all connected land cells within an island.
    * **Iteration:** Used to traverse the grid cell by cell.


**4. Code Walkthrough:**

```java
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0; // Initialize the island count
        int n = grid.length; // Number of rows
        int m = grid[0].length; // Number of columns
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // Directions: down, up, right, left
        boolean[][] vis = new boolean[n][m]; // Visited array

        for(int i = 0; i<n; i++) { // Iterate through rows
            for(int j = 0; j<m; j++) { // Iterate through columns
                if(grid[i][j] == '1' && !vis[i][j]) { // Found unvisited land
                    ans++; // Increment island count
                    Queue<int[]> q = new LinkedList<>(); // Create a queue for BFS
                    q.offer(new int[]{i, j}); // Add the current land cell to the queue
                    vis[i][j] = true; // Mark the cell as visited
                    while(!q.isEmpty()) { // BFS traversal
                        int[] p = q.poll(); // Get the next cell from the queue

                        for(int[] d : dir) { // Explore neighbors
                            int ni = p[0]+d[0]; // New row index
                            int nj = p[1]+d[1]; // New column index
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == '1' && !vis[ni][nj]) { // Check boundaries and if it's unvisited land
                                q.offer(new int[]{ni, nj}); // Add neighbor to queue
                                vis[ni][nj] = true; // Mark neighbor as visited
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

* **Time Complexity:** O(M * N), where M and N are the number of rows and columns in the grid, respectively.  This is because we visit each cell at most once.  The BFS operation within each island takes at most O(M*N) in the worst case (a single large island), but this is amortized across all islands.

* **Space Complexity:** O(M * N) in the worst-case scenario.  This is primarily due to the `vis` array used to track visited cells.  In the worst case (all cells are land), the queue used in BFS can also reach a size of O(M*N), although it's generally much smaller in practice.

In summary, this solution provides an efficient way to solve the Number of Islands problem using BFS and a visited array to prevent redundant exploration and ensure accurate island counting. The time and space complexity are both linear with respect to the size of the input grid.
