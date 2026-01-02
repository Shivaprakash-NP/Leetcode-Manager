### Problem Understanding

The problem asks us to find the *latest day* by which we can still cross a `row x col` grid from the top row to the bottom row. Initially, the entire grid is land. We are given a list `cells`, where `cells[i]` represents the coordinates `(r, c)` of the cell that turns into water on day `i+1`. This means on day 1, `cells[0]` turns water; on day 2, `cells[1]` turns water, and so on. We can only move horizontally or vertically through land cells.

Essentially, we need to find the maximum `k` such that if cells `cells[0]` through `cells[k-1]` have turned into water, there is still a path of land cells connecting any cell in the first row to any cell in the last row.

### Approach / Intuition

The key insight for solving this problem efficiently lies in the monotonic nature of the "crossability" condition.
1.  **Monotonicity:** If it's possible to cross the grid on `day X`, it must also be possible to cross on any `day < X`. This is because on an earlier day, *fewer* cells would have turned into water, meaning there are at least as many land cells available for a path.
2.  **Monotonicity (Reverse):** Conversely, if it's *not* possible to cross on `day X`, it will also *not* be possible on any `day > X`. On a later day, *more* cells would have turned into water, potentially blocking any existing paths or making new ones impossible.

This monotonic property is a strong indicator that **binary search** can be used on the number of days. We can binary search for the `latestDay` (our answer).

**Binary Search Strategy:**
*   The search space for the day is from `1` (the earliest possible day) to `row * col` (the latest possible day, when all cells have turned water).
*   For a given `mid` day in our binary search, we need a helper function `is(mid_day)` that checks if it's possible to cross the grid on `mid_day`.
*   If `is(mid_day)` returns `true` (crossing is possible), it means `mid_day` is a potential answer, and we should try to find an even *later* day. So, we update our answer and search in the upper half (`[mid_day + 1, right]`).
*   If `is(mid_day)` returns `false` (crossing is not possible), it means `mid_day` is too late, and we need to search for an *earlier* day in the lower half (`[left, mid_day - 1]`).

**`is(mid_day)` Helper Function:**
This function determines if a path exists from the top row to the bottom row on a given `mid_day`.
1.  **Grid State:** On `mid_day`, all cells `cells[0]` through `cells[mid_day-1]` are water. All other cells are land.
2.  **Connectivity Check:** We need to perform a graph traversal algorithm, like **Breadth-First Search (BFS)** or Depth-First Search (DFS), to check for connectivity.
    *   Start a BFS from all land cells in the first row.
    *   During the BFS, we can only move to adjacent cells that are within bounds, have not been visited yet, and are *still land* on `mid_day`.
    *   A cell `(r, c)` is land on `mid_day` if it turns into water on a day *after* `mid_day` (i.e., `map[r][c] > mid_day`).
    *   If the BFS reaches any cell in the last row, then a path exists, and the function returns `true`.
    *   If the BFS completes without reaching the last row, no path exists, and the function returns `false`.

To efficiently check if a cell is land or water on `mid_day`, we first pre-process the `cells` array into a `map` grid. `map[r][c]` will store the day on which cell `(r, c)` turns into water.

### Data Structures and Algorithms

1.  **Algorithms:**
    *   **Binary Search:** Applied to the range of possible days to find the latest day.
    *   **Breadth-First Search (BFS):** Used within the `is` function to check connectivity between the top and bottom rows.

2.  **Data Structures:**
    *   `int[][] map`: A 2D array (grid) of size `(row+1) x (col+1)`. `map[r][c]` stores the day when the cell `(r, c)` turns into water. This allows for `O(1)` lookup to determine if a cell is land or water on a given day `m`. We use 1-indexed coordinates for convenience, matching the problem's `cells` input.
    *   `Queue<int[]> q`: A standard queue (implemented with `LinkedList`) used for the BFS traversal. It stores `int[]` arrays representing `[row, col]` coordinates.
    *   `boolean[][] vis`: A 2D boolean array of size `(row+1) x (col+1)` used in BFS to keep track of visited cells, preventing cycles and redundant processing.

### Code Walkthrough

```java
class Solution {
    int[][] map; // Stores the day each cell turns into water

    // Helper function to check if it's possible to cross on a given day 'm'
    private boolean is(int m, int row, int col) {
        Queue<int[]> q = new LinkedList<>(); // BFS queue
        boolean[][] vis = new boolean[row+1][col+1]; // Visited array for BFS

        // Initialize BFS: Add all land cells from the first row to the queue
        for(int i = 1; i<=col; i++) {
            // A cell (1, i) is land on day 'm' if it turns water AFTER day 'm'
            if(map[1][i] > m) { 
                vis[1][i] = true; // Mark as visited
                q.offer(new int[]{1, i}); // Add to queue
            }
        }

        // Directions for 4-way movement (up, down, left, right)
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Perform BFS
