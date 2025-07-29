## LeetCode: Max Area of Island - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum area of an island in a given grid.  The grid represents a map where '1' represents land and '0' represents water. An island is a group of connected '1's (horizontally or vertically). The area of an island is the total number of '1's in that island.  We need to determine the largest such area.


**2. Approach / Intuition:**

The solution employs a Breadth-First Search (BFS) algorithm to traverse each island and calculate its area.  We iterate through the grid.  Whenever we encounter a '1' (land) that hasn't been visited, we initiate a BFS starting from that point. The BFS explores all connected land cells, marking them as visited to avoid recounting.  During the BFS, we count the number of cells visited (the area of the island). We keep track of the maximum area encountered so far. This approach is chosen because BFS efficiently explores all connected components of the graph (island) in a systematic way.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] grid`:  A 2D array representing the map of land and water.
    * `boolean[][] vis`: A 2D boolean array to keep track of visited cells.  This prevents revisiting cells and ensures correct area calculation.
    * `Queue<int[]> q`: A queue used in the BFS algorithm to store coordinates of cells to be visited.
    * `int[][] dir`: A 2D array storing the four possible directions (up, down, left, right) to explore neighboring cells.

* **Algorithms:**
    * **Breadth-First Search (BFS):**  Used to explore all connected land cells in an island.
    * **Iteration:** Used to traverse the entire grid.


**4. Code Walkthrough:**

* **Initialization:**
    * `n` and `m` store the dimensions of the grid.
    * `vis` is initialized as a 2D boolean array, all set to `false`, indicating no cells are initially visited.
    * `dir` array defines the four directions for moving to adjacent cells.
    * `ans` stores the maximum area found so far, initialized to 0.

* **Outer Loop:** The nested `for` loops iterate through each cell of the grid.

* **Island Detection:** `if(grid[i][j] == 1 && !vis[i][j])` checks if the current cell is land ('1') and hasn't been visited. If true, a new island is found.

* **BFS:**
    * A new queue `q` is created for the BFS.
    * `cursum` tracks the area of the current island.
    * The starting cell's coordinates are added to the queue, and it's marked as visited.
    * The `while` loop continues as long as the queue is not empty.
    * Inside the loop:
        * `p = q.poll()` retrieves the next cell to explore.
        * `cursum` is incremented.
        * The inner `for` loop iterates through the four directions.
        * `ni` and `nj` calculate the coordinates of the neighboring cell in each direction.
        * The `if` condition checks if the neighbor is within the grid bounds, is land ('1'), and hasn't been visited. If true, the neighbor is added to the queue and marked as visited.

* **Maximum Area Update:** After processing an island, `ans = Math.max(ans, cursum);` updates `ans` to the maximum of the current maximum area and the area of the just-processed island.

* **Return Value:** Finally, the function returns `ans`, the maximum area of any island.


**5. Time and Space Complexity:**

* **Time Complexity:** O(M * N), where M and N are the dimensions of the grid.  Each cell is visited at most once during the BFS traversal.  The overall time is dominated by the grid traversal.

* **Space Complexity:** O(M * N) in the worst-case scenario. This is because the visited array `vis` requires space proportional to the grid size.  In the worst case, the queue in BFS could also hold up to all the cells in the largest island if it is a fully connected island.  However, the space used by the queue is typically much smaller than the grid size unless the largest island occupies a significant portion of the grid.
