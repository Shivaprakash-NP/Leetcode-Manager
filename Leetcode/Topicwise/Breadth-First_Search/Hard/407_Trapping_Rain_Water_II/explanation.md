```markdown
## Trapping Rain Water II

### 1. Problem Understanding:

The problem asks us to calculate the amount of water that can be trapped in a 2D elevation map after it rains. The elevation map is represented by a 2D array of non-negative integers, where each integer represents the height of a unit cell. Water can be trapped if it's surrounded by higher elevation cells. Unlike the 1D "Trapping Rain Water" problem, water can now flow in four directions (up, down, left, and right).

### 2. Approach / Intuition:

The core idea is based on the "Shortest Path" like algorithms and the concept that water level is determined by the *boundary* of the elevation map. We treat the border cells as the starting points. The height of the water at each cell is determined by the minimum height of its surrounding boundary cells. We can use a priority queue (min-heap) to maintain the cells on the boundary, sorted by their height.

Here's the breakdown:

1.  **Initialization:**
    *   Add all border cells to a priority queue `q`. The priority queue stores cell information as `[height, row, col]` and orders them by height (smallest first).
    *   Mark the border cells as visited in a `vis` array.

2.  **Iteration:**
    *   While the priority queue is not empty:
        *   `poll` the cell with the smallest height (`ch`) from the queue. This represents the current water level limit for neighboring unvisited cells.
        *   For each unvisited neighbor of the current cell:
            *   Calculate the amount of trapped water: `Math.max(0, ch - heightMap[neighbor_row][neighbor_col])`.  If the current boundary cell is higher than the neighbor, then the difference is the amount of water trapped at this neighbor. If the boundary cell is lower or the same height, than no water is trapped.
            *   Update the priority queue by adding the neighbor to it. The height that will be added to the priority queue must be the `Math.max(ch, heightMap[neighbor_row][neighbor_col])`. This ensures that the next round of exploration uses the correct water level limit. If the neighbor is taller than the current boundary, the neighbor's height becomes the new boundary height. If the boundary height is taller than the neighbor, the boundary height remains the limit.
            *   Mark the neighbor as visited.

3.  **Result:**  Accumulate the trapped water for each cell, and the final sum is the total trapped water.

**Why this approach?**
*   By starting from the boundary and expanding inwards, we ensure that we are always considering the lowest possible water level that can be contained.
*   Using a priority queue guarantees that we process the lowest boundary cells first, which is crucial for determining the water level accurately.
*   The visited array avoids cycles and ensures that we only process each cell once.

### 3. Data Structures and Algorithms:

*   **PriorityQueue (Min-Heap):** Used to store the cells on the boundary, sorted by their height.  This ensures we process the cell with the lowest height first.
*   **2D Array (int[][]):** `heightMap` stores the elevation map. `vis` stores the visited status of each cell.
*   **Array (int[]):** Used to represent a cell in the priority queue (height, row, column). `dir` represents the four possible directions to move.
*   **Algorithm:**  A variation of Dijkstra's algorithm is implicitly used to explore the map based on the heights of the boundary cells.

### 4. Code Walkthrough:

```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[][] vis = new int[m][n];

        // PriorityQueue to store cells, sorted by height (min-heap)
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Add border cells to the priority queue and mark them as visited
        for(int i = 0; i<m; i++) {
            q.offer(new int[]{heightMap[i][0], i, 0}); // Left border
            q.offer(new int[]{heightMap[i][n-1], i, n-1}); // Right border
            vis[i][0] = 1;
            vis[i][n-1] = 1;
        }

        for(int i = 1; i<n-1; i++) {
            q.offer(new int[]{heightMap[0][i], 0, i}); // Top border
            q.offer(new int[]{heightMap[m-1][i], m-1, i}); // Bottom border
            vis[0][i] = 1;
            vis[m-1][i] = 1;
        }

        int ans = 0; // Stores the total trapped water
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0,-1}}; // Directions to move (up, down, right, left)

        // While the priority queue is not empty
        while(!q.isEmpty()) {
            int[] p = q.poll(); // Get the cell with the smallest height
            int ch = p[0]; // Get the height of that cell

            // Explore neighbors of the current cell
            for(int[] d : dir) {
                int i = d[0]+p[1]; // Neighbor's row
                int j = d[1]+p[2]; // Neighbor's column

                // Check if the neighbor is within bounds and not visited
                if(i<m && j<n && i>=0 && j>=0 && vis[i][j] != 1) {
                    // Calculate trapped water at the neighbor
                    ans += Math.max(0, ch-heightMap[i][j]);

                    // Add the neighbor to the priority queue
                    // The height is the maximum of the current height and the neighbor's height
                    q.offer(new int[]{Math.max(ch, heightMap[i][j]), i, j});

                    // Mark the neighbor as visited
                    vis[i][j] = 1;
                }
            }
        }

        return ans; // Return the total trapped water
    }
}
```

### 5. Time and Space Complexity:

*   **Time Complexity:** O(M * N * log(M * N)), where M is the number of rows and N is the number of columns in the `heightMap`.
    *   Adding all border cells to the priority queue takes O(M + N) time initially.
    *   In the worst case, we might visit all the cells in the `heightMap`, i.e., M \* N cells.
    *   For each cell, we perform a `poll` operation from the priority queue, which takes O(log(M * N)) time. We also iterate through the four neighbours which takes O(1) time.
    *   Therefore the time complexity becomes O(M * N * log(M * N)).

*   **Space Complexity:** O(M * N)
    *   The `vis` array takes O(M * N) space.
    *   The priority queue `q` can, in the worst case, store all the cells of the `heightMap`, taking O(M * N) space.
