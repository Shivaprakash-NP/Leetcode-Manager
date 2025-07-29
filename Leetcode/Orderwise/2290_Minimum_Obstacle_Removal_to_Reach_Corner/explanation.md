## Minimum Obstacle Removal to Reach Corner - LeetCode Solution Explained

**1. Problem Understanding:**

The problem asks us to find the minimum number of obstacles we need to remove to reach the bottom-right corner of a grid from the top-left corner.  Each cell in the grid represents either an empty space (0) or an obstacle (1).  Removing an obstacle counts as one step. We can move up, down, left, or right.

**2. Approach / Intuition:**

The solution uses a variation of Dijkstra's algorithm, specifically a priority queue (implemented using a deque) to efficiently find the shortest path.  Dijkstra's algorithm is well-suited for finding the shortest path in a weighted graph, where the weights represent the cost of traversing an edge. In this case, the weights are the obstacles (0 cost for empty cell, 1 cost for obstacle).

A simple BFS wouldn't work optimally because it doesn't prioritize paths with fewer obstacles. We use a deque to maintain a priority.  Cells with fewer accumulated obstacles are added to the front (higher priority), ensuring that we explore paths with fewer obstacles first. This prioritization allows us to find the minimum number of obstacles efficiently.

**3. Data Structures and Algorithms:**

* **Deque (ArrayDeque):**  Used as a priority queue to store cells to visit.  Cells with fewer accumulated obstacle costs are prioritized.
* **2D Array (vis):** Stores the minimum cost to reach each cell. Initialized with `Integer.MAX_VALUE` to represent infinity.
* **2D Array (grid):** Input grid representing the obstacles.
* **Array (dir):** Stores the four possible directions (up, down, left, right).
* **Dijkstra's Algorithm (modified):** The core algorithm used to find the shortest path with minimum obstacle removal.

**4. Code Walkthrough:**

* **Initialization:**
    * `INF = Integer.MAX_VALUE`: Sets a large value to represent infinity for the minimum cost.
    * `n`, `m`: Store the dimensions of the grid.
    * `vis`: A 2D array to store the minimum cost to reach each cell. Initialized with `INF`.
    * `dir`: An array defining the four possible movement directions.
    * `q`: A deque initialized with the starting cell `{0, 0, 0}`, representing {cost, row, column}.

* **Main Loop (while !q.isEmpty()):**
    * `p = q.removeFirst()`: Removes the cell with the minimum cost from the deque.
    * Iteration through directions:
        * `ni`, `nj`: Calculate the coordinates of the neighboring cell.
        * Boundary check: Ensures the neighboring cell is within the grid bounds.
        * `nc`: Calculates the accumulated cost to reach the neighboring cell.
        * Cost update: If `nc < vis[ni][nj]`, it means a shorter path to the neighbor is found.
            * If `nc == dis`, it means the neighbor is reached without removing any obstacle, therefore adding it to the front of the queue to maintain priority order.
            * Otherwise, `nc > dis`, implying an obstacle was removed, so the neighbor is added to the end of the queue.
        * `vis[ni][nj] = nc`: Updates the minimum cost to reach the neighboring cell.

* **Result:** `vis[n-1][m-1]`: Returns the minimum cost to reach the bottom-right corner.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N*M*log(N*M)). The outer `while` loop iterates at most O(N*M) times.  The `for` loop iterates four times for each cell. Adding and removing from the deque takes O(log(N*M)) time in the worst case due to the priority queue operations.

* **Space Complexity:** O(N*M).  The `vis` array and the deque have a space complexity of O(N*M) in the worst case, where N and M are the dimensions of the grid.  The `dir` array is of constant size.

In summary, the solution efficiently finds the minimum number of obstacles to remove using a modified Dijkstra's algorithm with a priority queue implemented using a deque, guaranteeing the optimal solution. The algorithm prioritizes paths with fewer obstacles removed, leading to an efficient exploration of the grid.
