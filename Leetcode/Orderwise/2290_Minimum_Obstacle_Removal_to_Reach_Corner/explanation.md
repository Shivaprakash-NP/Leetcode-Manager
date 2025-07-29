## Minimum Obstacle Removal to Reach Corner - LeetCode Solution Explanation

**1. Problem Understanding:**

The problem asks to find the minimum number of obstacles you need to remove to reach the bottom-right corner (target) of a grid from the top-left corner (starting point).  Obstacles are represented by '1's in the grid, and empty spaces are represented by '0's.  Removing an obstacle costs 1 unit.  The goal is to find the minimum cost path to the target.


**2. Approach / Intuition:**

This solution uses a variation of Dijkstra's algorithm employing a deque (double-ended queue) for efficient priority management.  Dijkstra's algorithm is well-suited for finding the shortest path in a weighted graph, which this problem can be modeled as.  The weights represent the cost (number of obstacles encountered).

The deque is used to maintain a priority queue: elements with lower cost are placed at the front (using `addFirst`), while elements with higher cost are added to the back (using `addLast`). This prioritizes exploring paths with fewer obstacles first, which leads to finding the minimum cost path more quickly than a standard BFS.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] grid`: The input grid representing the map.
    * `int[][] vis`: A 2D array to store the minimum cost to reach each cell. Initialized with `Integer.MAX_VALUE` to represent infinity.
    * `Deque<int[]> q`: A double-ended queue (deque) acting as a priority queue to store cells to be visited. Each element in the queue is an array `[cost, row, column]`.
    * `int[][] dir`: A 2D array storing the four possible movement directions (up, down, left, right).

* **Algorithms:**
    * **Dijkstra's Algorithm (modified):**  The core algorithm used to find the shortest path.  The modification using a deque improves efficiency by prioritizing lower-cost paths.
    * **Breadth-First Search (BFS) elements:** The underlying traversal is similar to BFS, but with the priority queue optimization.


**4. Code Walkthrough:**

* **Initialization:**
    * `INF = Integer.MAX_VALUE;`: Sets a large value to represent infinity for the cost.
    * `n` and `m`: Store the dimensions of the grid.
    * `vis`: A 2D array is initialized with infinity for all cells, representing the initial minimum cost to reach each cell.
    * `dir`: An array defining the four cardinal directions for movement.
    * `q`: A deque is created, adding the starting point `(0, 0)` with a cost of `grid[0][0]` (0 or 1).

* **Main Loop (`while(!q.isEmpty())`):**
    * It iterates through the queue until it's empty.
    * `p = q.removeFirst();`:  Retrieves the cell with the lowest cost from the front of the deque.
    * Nested loop (`for(int[] d : dir)`): Iterates through the four neighboring cells.
    * **Boundary and Cost Check:** It checks if the neighbor is within the grid boundaries.  `nc` calculates the cost to reach the neighbor.
    * **Update Minimum Cost (`if(nc < vis[ni][nj])`):**  If a lower cost path to the neighbor is found, the `vis` array is updated.
    * **Queue Management:**  If the cost to reach the neighbor is the same as the current cost (`nc == dis`), it means the neighbor is on the same cost level, so it's added to the front of the queue. Otherwise, (if the cost increased due to an obstacle) it's added to the end.

* **Result:**
    * `return vis[n-1][m-1];`: Returns the minimum cost to reach the bottom-right corner.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N*M log(N*M)).  While the deque offers optimization over a standard min-heap in Dijkstra's, in the worst case (where you might need to visit every cell), it would approach the complexity of Dijkstra's using a min-heap.  The log factor comes from the implicit priority queue operations (though not explicitly using a `PriorityQueue`, the deque operations perform a similar task).

* **Space Complexity:** O(N*M). This is due to the `vis` array, which stores the minimum cost to reach each cell in the grid.  The deque's space usage is at most O(N*M) in the worst case, but is typically much smaller.


In summary, this solution efficiently solves the "Minimum Obstacle Removal to Reach Corner" problem using a deque-optimized Dijkstra's algorithm to find the shortest path with the minimum obstacle removal cost. The use of a deque helps to optimize the order in which nodes are explored, leading to faster convergence in many cases compared to a standard BFS.
