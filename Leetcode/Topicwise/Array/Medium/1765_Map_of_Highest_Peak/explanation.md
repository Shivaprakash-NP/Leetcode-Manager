## LeetCode: Map of Highest Peak - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the height of each cell in a grid, where `1` represents a water cell and `0` represents a land cell. The height of a land cell is the shortest distance to any water cell.  Water cells have a height of 0.


**2. Approach / Intuition:**

This problem can be efficiently solved using Breadth-First Search (BFS).  We start by adding all water cells to a queue. Then, we process cells level by level, increasing the height by 1 for each neighboring land cell as we move away from the water cells. This ensures we find the shortest distance to water for every land cell.  BFS guarantees that we explore cells in increasing order of distance from the water sources.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] isWater`:  The input grid representing water (1) and land (0) cells.
    * `boolean[][] vis`: A boolean array to keep track of visited cells, preventing cycles and ensuring each cell is processed only once.
    * `Queue<int[]> q`: A queue to store cells to be processed using BFS.  Each element in the queue is an array `[row, column]` representing a cell's coordinates.
    * `int[][] ans`: The output grid storing the height of each cell.

* **Algorithms:**
    * **Breadth-First Search (BFS):**  A graph traversal algorithm used to explore the grid level by level, starting from the water cells.


**4. Code Walkthrough:**

```java
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        int[][] ans = new int[n][m];

        // Initialize the queue with water cells and mark them as visited.
        for(int i = 0; i<n ; i++) {
            for(int j = 0; j<m ; j++) {
                if(isWater[i][j] == 1) {
                    vis[i][j] = true;
                    q.offer(new int[]{i, j});
                }
            }
        }

        // BFS traversal
        while(!q.isEmpty()) {
            int[] node = q.poll(); // Get the next cell from the queue
            int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Directions: up, down, right, left

            // Explore neighbors
            for(int[] d : dir) {
                int ni = node[0]+d[0]; //Calculate neighbor's coordinates
                int nj = node[1]+d[1];
                // Check if the neighbor is within bounds, unvisited, and a land cell
                if(ni>=0 && nj>=0 && ni<n && nj<m && !vis[ni][nj]) {
                    vis[ni][nj] = true; // Mark as visited
                    ans[ni][nj] = ans[node[0]][node[1]] + 1; //Set height - one more than the current cell
                    q.offer(new int[]{ni, nj}); // Add neighbor to the queue
                }
            }
        }
        return ans;
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N*M), where N and M are the dimensions of the grid.  Each cell is visited and processed at most once during the BFS traversal.

* **Space Complexity:** O(N*M) in the worst case. This is due to the `vis` array and the queue `q`.  In the worst-case scenario (a grid entirely of land cells), the queue could contain all cells at some point.  However,  the space used by the `ans` array is also O(N*M), but it is part of the output and not considered extra space used by the algorithm.


The solution efficiently solves the problem by leveraging the properties of BFS to find the shortest distances from water cells to all other cells.  The use of the `vis` array prevents redundant processing and guarantees a correct and efficient solution.
