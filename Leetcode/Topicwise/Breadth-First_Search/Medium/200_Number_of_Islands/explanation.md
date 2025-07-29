## LeetCode: Number of Islands - Solution Explanation

**1. Problem Understanding:**

The "Number of Islands" problem asks us to count the number of distinct islands in a given grid.  The grid is represented as a 2D array of characters, where '1' represents land and '0' represents water.  An island is a group of connected '1's (horizontally or vertically).

**2. Approach / Intuition:**

The solution employs a Breadth-First Search (BFS) algorithm to traverse and count the islands.  The core idea is to iterate through each cell of the grid. If a cell contains land ('1') and hasn't been visited yet, we've found a new island. We then use BFS to explore all connected land cells belonging to that island, marking them as visited to prevent recounting.  This ensures that each island is counted only once. BFS is chosen because it efficiently explores all connected components in a graph-like structure (the grid in this case).

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `char[][] grid`:  A 2D character array representing the grid of land ('1') and water ('0').
    * `boolean[][] vis`: A 2D boolean array to keep track of visited cells.  This prevents revisiting cells and ensures that we don't overcount islands.
    * `Queue<int[]> q`: A queue used for the BFS traversal. It stores the coordinates (`int[]`) of cells to be visited.
    * `int[][] dir`: A 2D array storing the four possible directions (up, down, left, right) for exploring neighboring cells.


* **Algorithms:**
    * **Breadth-First Search (BFS):**  Used to explore all connected land cells within an island.
    * **Iteration:**  The solution iterates through the grid to find unvisited land cells, initiating a BFS for each new island found.


**4. Code Walkthrough:**

```java
class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0; // Initialize the count of islands
        int n = grid.length; // Number of rows
        int m = grid[0].length; // Number of columns
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // Directions: down, up, right, left
        boolean[][] vis = new boolean[n][m]; // Visited array

        for(int i = 0; i<n; i++) { // Iterate through rows
            for(int j = 0; j<m; j++) { // Iterate through columns
                if(grid[i][j] == '1' && !vis[i][j]) { // Found unvisited land
                    ans++; // Increment island count
                    Queue<int[]> q = new LinkedList<>(); // Create a queue for BFS
                    q.offer(new int[]{i, j}); // Add the starting cell to the queue
                    vis[i][j] = true; // Mark the cell as visited
                    while(!q.isEmpty()) { // BFS traversal
                        int[] p = q.poll(); // Get the next cell from the queue

                        for(int[] d : dir) { // Explore neighboring cells
                            int ni = p[0]+d[0]; // Calculate the coordinates of the neighbor
                            int nj = p[1]+d[1];
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == '1' && !vis[ni][nj]) { // Check bounds and if it's land and unvisited
                                q.offer(new int[]{ni, nj}); // Add neighbor to queue
                                vis[ni][nj] = true; // Mark neighbor as visited
                            }
                        }
                    }
                }
            }
        }

        return ans; // Return the total number of islands
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(M * N), where M and N are the number of rows and columns in the grid, respectively.  This is because we visit each cell at most once.  The BFS operation within each island takes time proportional to the size of that island. In the worst case, the entire grid is a single island, leading to O(M * N) time for BFS.

* **Space Complexity:** O(M * N) in the worst case. This is due to the `vis` array which stores the visited status of each cell, and the queue in BFS, which can hold up to all cells in the grid in the worst case (a single large island).  The `dir` array is of constant size and doesn't affect the overall space complexity significantly.
