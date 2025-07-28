### 1. Intuition

The problem asks for the minimum cost to reach the bottom-right corner of a grid from the top-left corner.  Each cell in the grid suggests a preferred direction (1: right, 2: left, 3: down, 4: up). Moving in the preferred direction is free; moving in any other direction costs 1.  Imagine this as a maze where some paths are "easier" (cost 0) than others (cost 1).  We need to find the cheapest route.  This suggests a shortest path algorithm, and because we can prioritize free moves, Dijkstra's algorithm (or a variation of it) is a good candidate.  This solution uses a deque to simulate a priority queue, allowing faster processing of zero-cost moves.


### 2. Approach

The solution employs a modified Dijkstra's algorithm using a deque (`ArrayDeque`) to efficiently manage the priority queue.

1. **Initialization:**  A distance matrix (`distance`) is created to store the minimum cost to reach each cell. It's initialized with `Integer.MAX_VALUE` except for the starting cell (0,0), which has a distance of 0. A deque `q` is initialized with the starting cell (0,0,0), representing (distance, row, column).  A `map` is used to translate the grid's direction values (1-4) into corresponding coordinate offsets.

2. **Iteration:** The `while` loop iterates as long as the deque is not empty.  In each iteration:
    - The cell with the minimum distance (`p`) is dequeued.
    - For each of the four possible directions (`dir`), the algorithm explores adjacent cells.
    - **Cost Calculation:** The cost (`c`) of moving to the neighbor is 0 if the move aligns with the preferred direction of the current cell, and 1 otherwise.
    - **Distance Update:** If the calculated distance to the neighbor (`dis + c`) is less than the current minimum distance stored in `distance[ni][nj]`, the distance is updated.
    - **Enqueueing:** The neighbor is added to the deque.  Crucially, cells reached by following the preferred direction (`c == 0`) are added to the *front* of the deque (higher priority), while others are added to the *end*. This prioritizes zero-cost movements, mimicking the behavior of a priority queue while leveraging the deque's efficient insertion at both ends.

3. **Result:** Finally, `distance[n-1][m-1]` (the minimum cost to reach the bottom-right cell) is returned.


### 3. Data Structures

- **`grid` (2D array):**  The input grid containing preferred movement directions.
- **`map` (2D array):** A lookup table that maps the direction values (1-4) in `grid` to their corresponding row and column offsets. This improves readability and efficiency.
- **`distance` (2D array):** A distance matrix storing the minimum cost to reach each cell from the starting point.  Initialized to infinity except for the start.
- **`q` (Deque):**  A double-ended queue acting as a priority queue. Elements are stored as `int[]{distance, row, column}`. The deque's ability to efficiently add elements to both ends is crucial for prioritizing zero-cost moves.
- **`dir` (2D array):** An array of direction vectors representing movements (up, down, left, right).


### 4. Complexity Analysis

- **Time Complexity:** O(n*m), where 'n' and 'm' are the dimensions of the grid.  In the worst case, each cell might be visited and processed once.  Enqueueing and dequeuing from the deque takes O(1) time.

- **Space Complexity:** O(n*m).  The dominant space usage comes from the `distance` matrix, which stores the distances for all cells in the grid. The deque's size in the worst case could be O(n*m) but is generally smaller. The other arrays (`map`, `dir`) are of constant size.
