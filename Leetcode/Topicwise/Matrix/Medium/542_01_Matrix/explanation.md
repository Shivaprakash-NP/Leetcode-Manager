## LeetCode 01 Matrix: Detailed Solution Explanation

**1. Problem Understanding:**

The "01 Matrix" problem asks us to find the shortest distance from each cell containing a `1` to the nearest cell containing a `0` in a given binary matrix.  The distance is measured in terms of the number of steps, where each step can be either up, down, left, or right. The result should be a matrix of the same size where each element represents this shortest distance.


**2. Approach / Intuition:**

This solution uses Breadth-First Search (BFS) to efficiently find the shortest distances.  BFS is ideal for finding the shortest path in an unweighted graph because it explores the graph level by level.

The intuition is as follows:

1. **Initialization:** We start by identifying all cells containing `0`s. These are our starting points for the BFS traversal.  They are added to a queue and marked as visited.

2. **BFS Traversal:**  We process the queue level by level.  For each cell encountered, we explore its four neighbors (up, down, left, right).

3. **Distance Calculation:** If a neighbor is a `1` and hasn't been visited yet, its distance from the nearest `0` is one more than the distance of the current cell. We update the matrix with this distance, add the neighbor to the queue, and mark it as visited.

4. **Termination:** The BFS continues until the queue is empty, meaning all reachable cells (cells with `1`s) have been processed and assigned their shortest distances.


This approach is chosen over other methods like Dijkstra's algorithm (which handles weighted graphs) because the problem inherently deals with unweighted distances (each step has a cost of 1). BFS provides a simpler and more efficient solution in this context.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `Queue<int[]> q`: A queue to store the coordinates of cells to be processed using BFS.  `int[]` represents the (row, column) coordinates of a cell.
    * `boolean[][] vis`: A 2D boolean array to keep track of visited cells, preventing cycles and redundant processing.
    * `int[][] mat`: The input and output matrix itself.
    * `int[][] dir`: A 2D array to store the four possible movement directions (up, down, left, right).

* **Algorithms:**
    * **Breadth-First Search (BFS):**  The core algorithm used to traverse the matrix and find the shortest distances.



**4. Code Walkthrough:**

* **Initialization (lines 1-13):**
    * `n` and `m` store the dimensions of the matrix.
    * `q` is initialized as a `LinkedList` based Queue.
    * `vis` is a boolean matrix initialized to `false` for all cells, indicating initially that no cells have been visited.
    * The code iterates through the matrix and adds all cells with value `0` to the queue `q` and marks them as visited in the `vis` matrix.


* **BFS Traversal (lines 15-27):**
    * `dir` stores the four possible directions of movement.
    * The `while` loop continues as long as the queue `q` is not empty.
    * Inside the loop, `p` gets the coordinates of a cell from the front of the queue.
    * The inner `for` loop iterates over the four directions.
    * `ni` and `nj` calculate the coordinates of the neighbor in the current direction.
    * The `if` condition checks if the neighbor is within the matrix bounds and hasn't been visited.
    * If the neighbor is valid and unvisited, its distance is updated in `mat`, it's added to the queue, and marked as visited in `vis`.

* **Return (line 29):**
    * The updated `mat` (containing the shortest distances) is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n*m), where 'n' and 'm' are the dimensions of the matrix. This is because each cell might be visited and processed once in the BFS traversal in the worst-case scenario.

* **Space Complexity:** O(n*m) in the worst case. This is due to the space used by the queue `q` and the visited matrix `vis`. In the worst-case scenario (a matrix filled with 1s except for one 0), the queue could potentially hold all the cells in the matrix.  The `vis` matrix also uses O(n*m) space.  The input matrix itself is not considered in space complexity analysis because it's considered part of the input.

In summary, this solution efficiently solves the 01 Matrix problem using a straightforward BFS approach, resulting in a time and space complexity that is linear to the size of the input matrix.
