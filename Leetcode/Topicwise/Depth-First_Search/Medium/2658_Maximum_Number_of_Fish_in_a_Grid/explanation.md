## Maximum Number of Fish in a Grid: A Detailed Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum number of fish that can be collected from a grid.  The grid represents a pond where each cell contains a certain number of fish (a positive integer).  Fish in adjacent cells (horizontally or vertically) are considered to be part of the same school.  We need to find the maximum number of fish within a single school.


**2. Approach / Intuition:**

The solution employs a Breadth-First Search (BFS) algorithm to explore connected components (fish schools) within the grid.  We iterate through each cell of the grid. If a cell contains fish (value > 0), we initiate a BFS traversal starting from that cell.  During the traversal, we sum up the number of fish in the connected component and mark visited cells as -1 to prevent revisiting.  The maximum sum encountered across all connected components represents the maximum number of fish in a single school.  This approach is chosen because BFS efficiently explores all connected cells in a graph (or, in this case, grid) in a level-by-level manner.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] grid`: A 2D array representing the pond, storing the number of fish in each cell.
    * `Queue<int[]> q`: A queue used for the BFS traversal, storing the coordinates of cells to be visited.
* **Algorithms:**
    * **Breadth-First Search (BFS):**  Used to explore connected components of fish in the grid.
    * **Iteration:** Used to iterate through the grid cells.


**4. Code Walkthrough:**

* **Initialization:**
    * `n` and `m` store the dimensions of the grid.
    * `dir` is a 2D array representing the four directions (up, down, left, right).
    * `ans` stores the maximum number of fish found so far, initialized to 0.

* **Outer Loop:** The nested `for` loops iterate through each cell `(i, j)` of the grid.

* **Inner Loop (Fish Check):**
    * `if(grid[i][j] > 0)`: This condition checks if the current cell contains fish.

* **BFS Traversal:**
    * A `Queue<int[]> q` is created to store cell coordinates for BFS.
    * `sum` keeps track of the total number of fish in the current school.
    * `grid[i][j] = -1`: The current cell is marked as visited.
    * `while(!q.isEmpty())`: The BFS continues until the queue is empty.
    * `int[] p = q.poll()`: The next cell to visit is dequeued.
    * The inner loop iterates through the four directions (`for(int[] d : dir)`).
    * `ni` and `nj` calculate the coordinates of the neighboring cell.
    * `if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] >0)`: This condition checks if the neighbor is within the grid bounds and contains fish (not yet visited).
    * `sum+=grid[ni][nj]`: Fish count is added to the total.
    * `grid[ni][nj] = -1`: Neighbor is marked as visited.
    * `q.offer(new int[]{ni, nj})`: The neighbor is added to the queue.

* **Update Maximum:**
    * `ans = Math.max(ans, sum)`: The maximum fish count is updated.

* **Return Value:**
    * `return ans`: The maximum number of fish in a single school is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N*M), where N and M are the dimensions of the grid.  In the worst-case scenario, we might visit each cell once (if all cells are connected). The BFS operation takes O(number of cells visited), which is at most O(N*M).

* **Space Complexity:** O(N*M) in the worst case,  This is due to the queue used in the BFS. In the worst case, the queue could hold all the cells in the grid if there's one giant connected component.  The space used for the `grid` itself is also O(N*M).

The space complexity can be improved slightly by using a different marking strategy that doesn't modify the original input grid (e.g., using a separate visited array), but the asymptotic complexity remains the same.
