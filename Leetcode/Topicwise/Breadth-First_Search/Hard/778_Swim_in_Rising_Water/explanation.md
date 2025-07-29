## LeetCode: Swim in Rising Water - Solution Explained

**1. Problem Understanding:**

The problem presents a square grid where each cell represents a height of water.  We start at the top-left corner (0,0) and want to reach the bottom-right corner (n-1, n-1).  At each step, we can move up, down, left, or right to an adjacent cell. The water level is constantly rising, and the maximum height we encounter during our journey determines the time it takes to reach the destination. The goal is to find the minimum time required to reach the bottom-right corner.


**2. Approach / Intuition:**

This solution uses Dijkstra's algorithm to find the shortest path in a weighted graph.  We treat each cell in the grid as a node in the graph, and the weight of an edge between two adjacent cells is the maximum of the water levels in those two cells. Dijkstra's algorithm efficiently finds the shortest path (minimum time) from the source (top-left) to the destination (bottom-right) in a graph with non-negative edge weights.  This approach is chosen because it effectively handles the "rising water" constraint by considering the maximum height encountered along any path.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `grid`: A 2D integer array representing the water heights in the grid.
    * `dis`: A 2D integer array storing the minimum time to reach each cell from the source.  It's initialized with `Integer.MAX_VALUE` to represent infinity.
    * `PriorityQueue<int[]> q`: A priority queue storing cells to visit, prioritized by the minimum time to reach them. Each element is an array `[cost, row, col]`.
    * `dir`: A 2D integer array representing the four possible movement directions (up, down, left, right).

* **Algorithms:**
    * **Dijkstra's Algorithm:**  The core algorithm used to find the shortest path in the weighted graph represented by the grid.
    * **Priority Queue:** Used to efficiently manage the cells to visit, always selecting the cell with the minimum time to reach next.


**4. Code Walkthrough:**

* **Initialization:**
    * `n`: Stores the size of the grid (assuming a square grid).
    * `dis`: Initialized with `Integer.MAX_VALUE` to track the minimum time to reach each cell.
    * `q`: A priority queue is created and the starting cell `(0,0)` with its initial cost (`grid[0][0]`) is added.
    * `dir`: Defines the four cardinal directions for movement.

* **Dijkstra's Algorithm (while loop):**
    * The loop continues until the priority queue is empty.
    * `q.poll()`:  Retrieves the cell with the minimum cost from the priority queue.
    * The code iterates through the four neighboring cells (`for(int[] d : dir)`).
    * `ni`, `nj`: Calculate the row and column index of the neighbor.
    * **Boundary Check:** Ensures the neighbor is within the grid boundaries.
    * `nc`: Calculates the maximum cost to reach the neighbor (`Math.max(cost, grid[ni][nj])`).
    * **Relaxation:** If the calculated cost (`nc`) is less than the current minimum cost to reach the neighbor (`dis[ni][nj]`), it means we've found a shorter path.  The `dis` array is updated, and the neighbor is added to the priority queue.

* **Result:**
    * `dis[n-1][n-1]`: After the loop completes, `dis[n-1][n-1]` contains the minimum time required to reach the bottom-right corner. This value is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N^2 log N), where N is the size of the grid.  The priority queue operations (offer and poll) take O(log N) time, and we visit each cell at most once.  The nested loops iterate through at most 4*N^2 cells.

* **Space Complexity:** O(N^2). The `dis` array and the priority queue both require O(N^2) space in the worst case.  The priority queue could potentially hold all cells at once.


In summary, this Java code provides an efficient and optimized solution to the "Swim in Rising Water" problem using Dijkstra's algorithm with a priority queue to manage the cells to visit, resulting in a time complexity of O(N^2 log N) and a space complexity of O(N^2). The code is well-structured and easy to understand, showcasing a good understanding of graph algorithms and their implementation in Java.
