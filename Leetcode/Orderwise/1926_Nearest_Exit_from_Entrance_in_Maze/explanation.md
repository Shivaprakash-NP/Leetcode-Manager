```markdown
## Nearest Exit from Entrance in Maze

### 1. Problem Understanding:

The problem asks us to find the shortest path (number of steps) from a given entrance in a maze to the nearest exit. The maze is represented by a 2D character array where '.' represents an empty cell and '+' represents a wall. An exit is defined as an empty cell located on the border of the maze that is *different* from the entrance. The entrance itself does not count as an exit unless it is also on the border. If no such path exists, return -1.

### 2. Approach / Intuition:

The problem can be effectively solved using a variation of Dijkstra's algorithm. The maze can be viewed as a graph where each empty cell is a node, and adjacent empty cells have an edge between them. We want to find the shortest distance from the entrance node to the nearest exit node. Dijkstra's algorithm is well-suited for finding the shortest path in a graph with non-negative edge weights (in this case, each step has a weight of 1).

The original code attempts to use Dijkstra but contains key errors:
1.  **Entrance is not considered:** the code initializes all distances to max values, then runs dijkstra, but marks the entrance as Integer.MAX_VALUE if it's on the border, failing to recognize it as a possible exit *during* the dijkstra search.
2.  **Logic Error:** The code assumes that only the border cells are potential exits, but the entrance itself might be a valid exit if it's on the border.
3.  **Inefficient Use of PriorityQueue:** The use of `PriorityQueue` is not correct. This approach should use BFS.

The correct algorithm to be used is BFS because the weights are uniform (each move is a unit cost).
Here's the correct, fixed strategy:

1.  **Initialization:** Create a queue to store the cells to visit, starting with the entrance. Mark the entrance as visited (e.g., by changing its value in the maze).
2.  **BFS Traversal:** While the queue is not empty:
    *   Dequeue a cell and its distance from the entrance.
    *   For each of its neighbors (up, down, left, right):
        *   If the neighbor is within the maze bounds, is an empty cell, and has not been visited:
            *   If the neighbor is on the border of the maze and is *not* the initial entrance position, it's an exit. Return the distance to this neighbor + 1 (the cost of the current step).
            *   Otherwise, enqueue the neighbor with an incremented distance. Mark the neighbor as visited.
3.  **No Exit Found:** If the queue becomes empty without finding an exit, return -1.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `char[][] maze`: The input maze.
    *   `int[] entrance`: The coordinates of the entrance.
    *   `Queue<int[]>`: A queue (typically implemented as a `LinkedList`) to store the cells to visit during the BFS traversal, along with their distances from the entrance.  Each element in the queue is an `int[]` of length 3: `[row, col, distance]`.

*   **Algorithms:**
    *   **Breadth-First Search (BFS):** Used to explore the maze layer by layer, guaranteeing that the first exit found will be the nearest one.

### 4. Code Walkthrough:

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int startRow = entrance[0];
        int startCol = entrance[1];

        // Queue for BFS: {row, col, distance}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});

        // Mark the entrance as visited
        maze[startRow][startCol] = '+'; // Mark as visited

        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            // Explore neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the neighbor is within bounds and is an empty cell
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && maze[newRow][newCol] == '.') {
                    // Check if it's an exit (on the border)
                    if (newRow == 0 || newRow == m - 1 || newCol == 0 || newCol == n - 1) {
                        return distance + 1; // Found the nearest exit
                    }

                    // Enqueue the neighbor and mark it as visited
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                    maze[newRow][newCol] = '+'; // Mark as visited
                }
            }
        }

        // No exit found
        return -1;
    }
}
```

*   **`nearestExit(char[][] maze, int[] entrance)`:** The main function that takes the maze and entrance as input.
*   **`int m = maze.length; int n = maze[0].length;`:** Gets the dimensions of the maze.
*   **`Queue<int[]> queue = new LinkedList<>();`:** Initializes a queue to store cells to visit.
*   **`queue.offer(new int[]{startRow, startCol, 0});`:** Adds the entrance to the queue with a distance of 0.
*   **`maze[startRow][startCol] = '+';`:** Marks the entrance as visited.  This is crucial to avoid revisiting the entrance.
*   **`int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};`:** Defines the possible movement directions (up, down, left, right).
*   **`while (!queue.isEmpty()) { ... }`:** The main BFS loop.
*   **`int[] current = queue.poll();`:** Retrieves the next cell from the queue.
*   **`for (int[] dir : directions) { ... }`:** Iterates through the possible directions.
*   **`int newRow = row + dir[0]; int newCol = col + dir[1];`:** Calculates the coordinates of the neighbor.
*   **`if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && maze[newRow][newCol] == '.') { ... }`:** Checks if the neighbor is within the maze bounds and is an empty cell ('.').
*   **`if (newRow == 0 || newRow == m - 1 || newCol == 0 || newCol == n - 1) { return distance + 1; }`:**  Crucially, checks *before* enqueueing if the neighbor is an exit (on the border). If so, the shortest distance has been found.
*   **`queue.offer(new int[]{newRow, newCol, distance + 1});`:** Adds the neighbor to the queue.
*   **`maze[newRow][newCol] = '+';`:** Marks the neighbor as visited.
*   **`return -1;`:** If the loop completes without finding an exit, it means there is no path to an exit, so -1 is returned.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(M * N), where M is the number of rows and N is the number of columns in the maze. In the worst case, we might visit every cell in the maze once.
*   **Space Complexity:** O(M * N) in the worst case. This is because the queue might contain all the empty cells in the maze if the maze is largely empty and the entrance is far from the exits. Also, the implicit space used by marking cells as visited in the maze itself is O(M*N).
