## LeetCode: Count Islands With Total Value Divisible by K

**1. Problem Understanding:**

The problem asks us to count the number of islands in a given grid where the sum of all cell values within each island is divisible by a given integer `k`.  An island is a group of connected cells (horizontally or vertically) containing non-zero values.

**2. Approach / Intuition:**

The solution employs a Breadth-First Search (BFS) algorithm to traverse each island.  We iterate through the grid. When we encounter a non-zero cell that hasn't been visited, we initiate a BFS traversal to explore the entire island. During the BFS, we accumulate the sum of cell values within that island.  Finally, we check if the total sum is divisible by `k`. If it is, we increment the island count.  This approach is efficient because it avoids revisiting cells and processes each island only once.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] grid`:  A 2D array representing the grid of cell values.
    * `boolean[][] vis`: A 2D boolean array to track visited cells, preventing cycles and redundant calculations.
    * `Queue<int[]> q`: A queue used in the BFS algorithm to store coordinates of cells to be visited.
    * `int[][] dir`: A 2D array storing the four possible directions (up, down, left, right) for movement in the grid.

* **Algorithms:**
    * **Breadth-First Search (BFS):**  Used to explore connected components (islands) in the grid.
    * **Iteration:** The main loops iterate through the grid to find unvisited non-zero cells, starting points for BFS.


**4. Code Walkthrough:**

* **Initialization:**
    * `n` and `m` store the dimensions of the grid.
    * `ans` is initialized to 0 and will store the count of islands with sums divisible by `k`.
    * `vis` is a boolean matrix initialized to `false`, marking all cells as unvisited.
    * `dir` stores the four directional offsets for BFS traversal.


* **Outer Loops:** The nested `for` loops iterate through each cell of the grid.


* **Island Detection:**
    * `if(grid[i][j] != 0 && !vis[i][j])`: This condition checks if the current cell has a non-zero value and hasn't been visited. If true, it indicates the start of a new island.


* **BFS Traversal:**
    * A queue `q` is created to perform BFS.
    * `sum` is initialized to accumulate the island's cell values.
    * The starting cell is added to the queue and marked as visited. Its value is added to `sum`.
    * The `while` loop continues as long as the queue is not empty.
    * In each iteration, a cell is dequeued (`q.poll()`).
    * The inner loop iterates through the four directions.
    * For each neighbor, it checks if it's within the grid boundaries, has a non-zero value, and hasn't been visited. If all conditions are met, the neighbor is added to the queue, marked as visited, and its value is added to `sum`.


* **Divisibility Check:**
    * `if(sum%k == 0) ans++;`: After exploring the entire island, this line checks if the accumulated `sum` is divisible by `k`. If so, the `ans` counter is incremented.


* **Return Value:** The function returns the final count of islands (`ans`).


**5. Time and Space Complexity:**

* **Time Complexity:** O(N*M), where N and M are the dimensions of the grid.  In the worst case (a single large island), the BFS might visit every cell once. The outer loops iterate through all cells.

* **Space Complexity:** O(N*M) in the worst case.  This is primarily due to the `vis` matrix used to track visited cells and the queue in BFS, which in the worst case could hold all cells of the grid if there's a single large island spanning the entire grid.  The `dir` array is of constant size and doesn't contribute significantly to space complexity.
