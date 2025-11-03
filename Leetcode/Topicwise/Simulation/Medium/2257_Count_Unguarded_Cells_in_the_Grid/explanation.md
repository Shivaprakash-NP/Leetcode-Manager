### Problem Understanding

The problem asks us to count the number of "unguarded" cells in a grid. We are given the dimensions of the grid (`m` rows, `n` columns), and the coordinates of "guards" and "walls".

Here's how guarding works:
*   Guards project their "sight" in all four cardinal directions: up, down, left, and right.
*   A guard's sight extends until it hits one of three things:
    1.  Another guard.
    2.  A wall.
    3.  The boundary of the grid.
*   Any cell that falls within a guard's line of sight is considered "guarded".
*   Cells occupied by guards or walls are not considered "unguarded" in the final count; we are only interested in empty cells that are not covered by any guard's sight.

Our goal is to find the total count of empty cells that are *not* guarded by any guard.

### Approach / Intuition

The core idea is to simulate the guarding process by iterating through the grid and marking cells that are under a guard's sight. Since guards project sight in four directions, we need to perform checks for each direction.

Instead of iterating from each guard outwards (which could be complex due to overlapping sight lines and blocking), a more efficient approach is to iterate through the grid cells in each of the four cardinal directions.

Here's the intuition:

1.  **Initialize the Grid:** Create a 2D array representing the grid. Mark guards and walls with distinct values. Empty cells can be marked with `0`.
2.  **Mark Guarded Cells:**
    *   **Row-wise (Left to Right):** For each row, iterate from left to right. Maintain a `seen` flag. If we encounter a guard, set `seen` to `true`. If we encounter a wall, set `seen` to `false` (as walls block sight). If `seen` is `true` and the current cell is empty, it means this cell is guarded by a guard to its left. Mark it as guarded.
    *   **Row-wise (Right to Left):** Similarly, for each row, iterate from right to left. If `seen` is `true` and the current cell is empty, it's guarded by a guard to its right. Mark it.
    *   **Column-wise (Top to Bottom):** For each column, iterate from top to bottom. If `seen` is `true` and the current cell is empty, it's guarded by a guard above it. Mark it.
    *   **Column-wise (Bottom to Top):** For each column, iterate from bottom to top. If `seen` is `true` and the current cell is empty, it's guarded by a guard below it. Mark it.
3.  **Count Unguarded Cells:** After performing all four passes, iterate through the grid one last time. Any cell that was initially empty (`0`) and was *not* marked as guarded in any of the four passes will still have its initial `0` value. Count these cells.

This approach works because each pass correctly identifies all cells guarded from a specific direction (e.g., from the left). By performing all four passes, we ensure that every cell that *can* be guarded from any direction is marked. Cells that remain `0` are truly unguarded.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[][] grid`: A 2D integer array of size `m x n` is used to represent the grid.
        *   `0`: Represents an empty, potentially unguarded cell.
        *   `1`: Represents a guard.
        *   `2`: Represents a wall.
        *   `-1`: Represents an empty cell that has been identified as guarded. (This is a choice; any distinct negative value would work).
*   **Algorithms:**
    *   **Grid Traversal:** The solution uses multiple linear traversals of the grid:
        *   Row-by-row, left-to-right.
        *   Row-by-row, right-to-left.
        *   Column-by-column, top-to-bottom.
        *   Column-by-column, bottom-to-top.
    *   **State Machine (Implicit):** The `boolean seen` flag acts as a simple state machine, tracking whether a guard has been encountered in the current line of sight and whether that sight is currently unobstructed.

### Code Walkthrough

```java
class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // 1. Initialize the grid
        int[][] grid = new int[m][n];
        int cnt = 0; // Counter for unguarded cells

        // 2. Mark guards and walls in the grid
        // Guards are marked with '1'
        for(int[] v : guards) grid[v[0]][v[1]] = 1;
        // Walls are marked with '2'
        for(int[] v : walls) grid[v[0]][v[1]] = 2;

        // 3. First two passes: Row-wise (Left-to-Right and Right-to-Left)
        for(int i = 0; i<m; i++) { // Iterate through each row
            boolean seen = false; // Flag to track if a guard has been seen in the current line of sight

            // Pass 1: Left-to-Right
            for(int j = 0; j<n; j++) { // Iterate columns from left to right
                if(grid[i][j] == 1) { // If a guard is found
                    seen = true; // Sight begins
                } else if(grid[i][j] == 2) { // If a wall is found
                    seen = false; // Sight is blocked
                } else if(seen) { // If it's an empty cell AND a guard has been seen
                    grid[i][j] = -1; // Mark this cell as guarded
                }
            }

            seen = false; // Reset 'seen' flag for the reverse pass

            // Pass 2: Right-to-Left
            for(int j = n-1; j>=0; j--) { // Iterate columns from right to left
                if(grid[i][j] == 1) { // If a guard is found
                    seen = true; // Sight begins
                } else if(grid[i][j] == 2) { // If a wall is found
                    seen = false; // Sight is blocked
                } else if(seen) { // If it's an empty cell AND a guard has been seen
                    grid[i][j] = -1; // Mark this cell as guarded
                }
            }
        }

        // 4. Next two passes: Column-wise (Top-to-Bottom and Bottom-to-Top)
        for(int j = 0; j<n; j++) { // Iterate through each column
            boolean seen = false; // Reset 'seen' flag for the current column

            // Pass 3: Top-to-Bottom
            for(int i = 0; i<m; i++) { // Iterate rows from top to bottom
                if(grid[i][j] == 1) { // If a guard is found
                    seen = true; // Sight begins
                } else if(grid[i][j] == 2) { // If a wall is found
                    seen = false; // Sight is blocked
                } else if(seen) { // If it's an empty cell AND a guard has been seen
                    grid[i][j] = -1; // Mark this cell as guarded
                }
            }

            seen = false; // Reset 'seen' flag for the reverse pass

            // Pass 4: Bottom-to-Top
            for(int i = m-1; i>=0; i--) { // Iterate rows from bottom to top
                if(grid[i][j] == 1) { // If a guard is found
                    seen = true; // Sight begins
                } else if(grid[i][j] == 2) { // If a wall is found
                    seen = false; // Sight is blocked
                } else if(seen) { // If it's an empty cell AND a guard has been seen
                    grid[i][j] = -1; // Mark this cell as guarded
                }
            }
        }
        
        // 5. Count unguarded cells
        // Iterate through the final grid and count cells that are still '0'
        for(int[] v : grid) {
            for(int vv : v) {
                if(vv == 0) { // If the cell is still '0', it means it's empty and not guarded
                    cnt++; // Increment the unguarded count
                }
            }
        }

        return cnt; // Return the total count of unguarded cells
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   **Grid Initialization:** Creating the `m x n` grid takes `O(m * n)` time.
    *   **Marking Guards and Walls:** Iterating through the `guards` array (let `G` be the number of guards) and `walls` array (let `W` be the number of walls) takes `O(G + W)` time. In the worst case, `G` and `W` can be up to `m * n`.
    *   **Four Traversal Passes:**
        *   The two row-wise passes (left-to-right and right-to-left) iterate through each cell once per pass. This is `2 * O(m * n)`.
        *   The two column-wise passes (top-to-bottom and bottom-to-top) also iterate through each cell once per pass. This is `2 * O(m * n)`.
        *   Total for traversals: `4 * O(m * n) = O(m * n)`.
    *   **Counting Unguarded Cells:** The final iteration through the grid to count `0`s takes `O(m * n)` time.
    *   **Overall:** Combining all steps, the dominant factor is the grid traversals. Thus, the total time complexity is `O(m * n)`.

*   **Space Complexity:**
    *   **`grid` array:** A 2D integer array of size `m x n` is created to store the state of each cell. This requires `O(m * n)` space.
    *   **Other variables:** A few integer and boolean variables are used, taking `O(1)` space.
    *   **Overall:** The total space complexity is `O(m * n)`.