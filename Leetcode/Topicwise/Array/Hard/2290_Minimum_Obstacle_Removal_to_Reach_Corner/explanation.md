## Minimum Obstacle Removal to Reach Corner - LeetCode Solution Explained

**1. Problem Understanding:**

The problem asks us to find the minimum number of obstacles we need to remove to reach the bottom-right corner of a grid from the top-left corner.  Each cell in the grid represents a cost (obstacle) – a value of 0 means no obstacle, and a value greater than 0 represents the cost of removing that obstacle. We can move up, down, left, or right in the grid.

**2. Approach / Intuition:**

This solution uses a variation of Dijkstra's algorithm, specifically a priority queue implementation using a deque.  Dijkstra's algorithm is well-suited for finding the shortest path in a weighted graph, which is exactly what we have here.  The grid represents the graph, and the obstacle values represent the weights of the edges.  We use a deque (double-ended queue) to maintain a priority queue; elements with lower cost are placed at the front and processed first (similar to a min-heap).

The algorithm efficiently explores the grid, prioritizing cells with lower cumulative obstacle removal costs. By placing elements with equal cost at the beginning of the queue, we can explore all equal-cost paths effectively.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] grid`:  A 2D array representing the grid with obstacle costs.
    * `int[][] vis`: A 2D array to store the minimum cost to reach each cell. Initialized with `Integer.MAX_VALUE` to represent infinity.
    * `Deque<int[]> q`: A deque (double-ended queue) acting as a priority queue to store cells to be visited, ordered by their cumulative cost. Each element in the queue is an array `[cost, row, column]`.
    * `int[][] dir`: A 2D array storing the four possible movement directions (up, down, left, right).

* **Algorithms:**
    * **Dijkstra's Algorithm (modified):** The core algorithm used to find the shortest path.
    * **Breadth-First Search (BFS) elements:** The deque usage incorporates elements of BFS, allowing for exploration of equal-cost paths efficiently.

**4. Code Walkthrough:**

* **Initialization:**
    * `INF = Integer.MAX_VALUE`: Sets a large value to represent infinity for unvisited cells.
    * `n`, `m`: Store the dimensions of the grid.
    * `vis`: A 2D array is created and initialized with infinity values, representing the minimum cost to reach each cell.
    * `dir`:  Defines the four possible movement directions.
    * `q`:  A deque is initialized, adding the starting cell `[0, 0, 0]` (cost 0, row 0, column 0).

* **Main Loop (`while(!q.isEmpty())`):**
    * The loop iterates until the deque is empty. In each iteration:
        * `p = q.removeFirst()`: The cell with the lowest cost is retrieved from the front of the deque.
        * The code then iterates through the four directions (`for(int[] d : dir)`).
        * For each neighboring cell:
            * `ni`, `nj`: Calculate the coordinates of the neighbor.
            * `if(ni>=0 && nj>=0 && ni<n && nj<m)`: Checks if the neighbor is within the grid boundaries.
            * `nc = dis + grid[ni][nj]`: Calculates the new cost to reach the neighbor.
            * `if(nc < vis[ni][nj])`:  If the new cost is less than the current minimum cost to reach the neighbor, it updates the minimum cost (`vis[ni][nj] = nc`) and adds the neighbor to the deque.  The placement in the deque determines the exploration priority:
                * `q.addFirst(new int[]{nc, ni, nj})`: If the new cost is equal to the current cost (`nc == dis`), it's added to the front to maintain the priority of equal-cost paths.
                * `q.addLast(new int[]{nc, ni, nj})`: Otherwise, it's added to the end.

* **Return Value:**
    * `return vis[n-1][m-1]`: After the loop completes, the minimum cost to reach the bottom-right cell (`vis[n-1][m-1]`) is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N*M*log(N*M)), where N and M are the dimensions of the grid.  The log factor comes from adding and removing elements from the deque, which acts like a priority queue. In the worst case, we might visit every cell once.

* **Space Complexity:** O(N*M). The space is dominated by the `vis` array and the deque `q`. In the worst case, the deque can contain all cells of the grid.

The algorithm is efficient because it avoids revisiting cells with higher costs once a lower-cost path has been found.  The use of a deque (double-ended queue) for the priority queue provides a time-efficient way to manage the exploration of the cells, prioritizing those with lower costs.
