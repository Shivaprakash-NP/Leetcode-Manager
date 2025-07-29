## Maximum Number of Fish in a Grid - LeetCode Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum number of fish we can collect from a grid.  The grid represents a sea where each cell contains a certain number of fish (a positive integer).  Adjacent cells (horizontally or vertically) with fish are considered connected.  We can collect all fish in a connected component. The goal is to find the maximum number of fish in any single connected component.

**2. Approach / Intuition:**

The solution employs a Breadth-First Search (BFS) algorithm to traverse connected components of fish.  We iterate through each cell of the grid. If a cell contains fish (value > 0), we initiate a BFS starting from that cell. The BFS explores all adjacent cells containing fish, summing up the fish count as it goes.  After exploring a connected component, we mark the visited cells with -1 to prevent revisiting. The maximum sum encountered across all connected components is the final answer. This approach is efficient because it avoids redundant exploration of the same connected component.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] grid`: A 2D array representing the grid of fish.
    * `int[][] dir`: A 2D array storing the four possible directions (up, down, left, right) for movement in the grid.
    * `Queue<int[]> q`: A queue used in the BFS algorithm to store the coordinates of cells to be visited.
* **Algorithms:**
    * **Breadth-First Search (BFS):**  Used to explore all connected components of fish.
    * **Iteration:** Used to traverse the grid and initiate BFS from each cell containing fish.


**4. Code Walkthrough:**

* **Initialization:**
    * `n` and `m` store the dimensions of the grid.
    * `dir` stores the directions for movement in the grid (up, down, left, right).
    * `ans` stores the maximum number of fish collected so far, initialized to 0.

* **Outer Loop (Nested For Loops):**
    * The nested loops iterate through each cell `(i, j)` of the grid.

* **Fish Check:**
    * `if(grid[i][j] > 0)`: This condition checks if the current cell contains fish.

* **BFS Initialization:**
    * `sum`: A variable to store the total number of fish in the current connected component.
    * `q`: A queue to store cells for BFS, initialized with the current cell `(i, j)`.
    * `sum += grid[i][j]`: Adds the fish count in the current cell to `sum`.
    * `grid[i][j] = -1`: Marks the current cell as visited.

* **BFS Traversal (while loop):**
    * `while(!q.isEmpty())`: Continues as long as there are cells in the queue.
    * `int[] p = q.poll()`: Retrieves the next cell from the queue.
    * **Inner Loop (for each direction):**
        * Iterates through all four directions (`dir`).
        * `ni` and `nj` calculate the coordinates of the adjacent cell in the current direction.
        * **Boundary and Fish Check:**
            * `if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] >0)`: Checks if the adjacent cell is within the grid bounds and contains fish.
        * **If Fish Found:**
            * `sum += grid[ni][nj]`: Adds the fish count to `sum`.
            * `grid[ni][nj] = -1`: Marks the adjacent cell as visited.
            * `q.offer(new int[]{ni, nj})`: Adds the adjacent cell to the queue.

* **Update Maximum:**
    * `ans = Math.max(ans, sum)`: Updates `ans` with the maximum fish count found so far.

* **Return:**
    * `return ans`: Returns the maximum number of fish collected.

**5. Time and Space Complexity:**

* **Time Complexity:** O(n*m), where 'n' and 'm' are the dimensions of the grid. Each cell is visited at most once during the iteration, and the BFS operation for each connected component takes time proportional to the size of the component. In the worst case, the entire grid forms a single connected component.

* **Space Complexity:** O(n*m) in the worst-case scenario. The space is primarily used by the queue in BFS. In the worst case, the queue can hold all the cells in the grid if there's one giant connected component.  The `grid` itself takes O(n*m) space. Therefore, the space complexity is dominated by the queue and the grid.

