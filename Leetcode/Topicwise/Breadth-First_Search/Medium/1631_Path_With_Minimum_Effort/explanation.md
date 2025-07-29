## LeetCode: Path With Minimum Effort - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the path from the top-left corner to the bottom-right corner of a matrix (grid) with minimum *effort*.  The effort of a path is defined as the maximum absolute difference between adjacent cell heights along that path.  We need to find the path that minimizes this maximum difference.


**2. Approach / Intuition:**

This solution utilizes Dijkstra's algorithm with a priority queue to find the path with minimum effort.  Instead of minimizing the total cost (like in a standard shortest path problem), we minimize the *maximum* cost along any path.

Dijkstra's algorithm is suitable because it efficiently finds the shortest paths from a single source node (top-left corner) to all other nodes in a graph with non-negative edge weights.  In this case, our "edge weights" represent the effort between adjacent cells.  The priority queue ensures that we always explore the nodes with the lowest maximum effort encountered so far, guaranteeing we find the optimal path.


**3. Data Structures and Algorithms:**

* **Priority Queue (PriorityQueue):**  Used to store nodes (cells) to be visited, prioritized by the maximum effort encountered so far along the path to that node.  This allows us to explore nodes with lower maximum effort first.
* **2D Array (vis):** A 2D array to store the minimum effort required to reach each cell from the starting point. It acts as a distance array in Dijkstra's Algorithm.  Initially filled with `Integer.MAX_VALUE` to represent infinity.
* **2D Array (heights):** Input matrix representing the heights of cells.
* **Dijkstra's Algorithm:** The core algorithm used to find the minimum effort path.


**4. Code Walkthrough:**

* **Initialization:**
    * `n`, `m`: Store the dimensions of the input `heights` matrix.
    * `vis`: A 2D array to store the minimum effort to reach each cell. Initialized with `Integer.MAX_VALUE`.
    * `dir`: A 2D array representing the four possible directions (up, down, left, right) to move from a cell.
    * `q`: A priority queue storing cells (represented as `[effort, row, col]`), prioritized by effort. The starting cell `(0, 0)` with effort 0 is added to the queue.

* **Dijkstra's Algorithm (while loop):**
    * The loop continues until the priority queue is empty.
    * `q.poll()`:  Retrieves the cell with the minimum effort from the priority queue.
    * Iteration over neighbors: The code iterates through the four neighboring cells using the `dir` array.
    * `ncost`: Calculates the maximum effort required to reach the neighbor (`ni`, `nj`) considering the current maximum effort (`cost`) and the absolute difference in heights between the current cell and the neighbor.
    * Relaxation: If the calculated effort (`ncost`) to reach the neighbor is less than the current minimum effort stored in `vis[ni][nj]`, it means we've found a better path to the neighbor.  We update `vis[ni][nj]` with `ncost` and add the neighbor to the priority queue.

* **Result:**
    * After the loop finishes, `vis[n-1][m-1]` contains the minimum effort required to reach the bottom-right cell, which is returned as the result.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N * M * log(N * M)). The priority queue operations (insertion and extraction) take O(log(N * M)) time, and we visit each cell at most once.  Therefore, the overall time complexity is dominated by the priority queue operations.

* **Space Complexity:** O(N * M). The space is primarily used by the `vis` array to store the minimum effort to reach each cell and the priority queue, which in the worst case can hold all cells.  The `dir` array is of constant size.


In summary, this solution efficiently solves the "Path With Minimum Effort" problem by cleverly adapting Dijkstra's algorithm to handle the maximum effort constraint using a priority queue. The use of a priority queue ensures optimality by always exploring the most promising paths first.
