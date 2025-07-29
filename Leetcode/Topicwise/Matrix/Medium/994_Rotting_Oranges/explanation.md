## LeetCode: Rotting Oranges - Solution Explanation

**1. Problem Understanding:**

The problem "Rotting Oranges" asks us to determine the minimum number of minutes it takes for all fresh oranges in a grid to rot.  A rotten orange at position (i,j) can infect its adjacent fresh oranges (horizontally or vertically) in one minute.  The grid is represented by a 2D integer array where 0 represents an empty cell, 1 represents a fresh orange, and 2 represents a rotten orange.  If it's impossible for all fresh oranges to rot, we return -1.


**2. Approach / Intuition:**

This solution uses Breadth-First Search (BFS) to simulate the rotting process.  BFS is ideal because it explores the grid level by level, ensuring that we find the minimum time required for all oranges to rot.  The algorithm starts by adding all initially rotten oranges to a queue.  In each iteration (representing a minute), we process all oranges currently in the queue, infecting their adjacent fresh oranges. We keep track of the number of fresh oranges (`fre`). If at the end of the process there are still fresh oranges left, it means some oranges are unreachable, and we return -1. Otherwise, we return the number of minutes (`t`).

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `Queue<int[]> q`: A queue is used to store the coordinates of rotten oranges to be processed. Each element is an integer array `[row, column]`.
    * `int[][] grid`: A 2D array representing the grid of oranges.
* **Algorithms:**
    * **Breadth-First Search (BFS):**  The core algorithm used to simulate the rotting process level by level.


**4. Code Walkthrough:**

* **Initialization:**
    * `Queue<int[]> q = new LinkedList<>();`:  Initializes an empty queue to store the coordinates of rotten oranges.
    * `int fre = 0;`:  Counts the total number of fresh oranges.
    * The nested loops iterate through the grid:
        * If `grid[i][j] == 2`, the orange is rotten, and its coordinates are added to the queue.
        * If `grid[i][j] == 1`, it's a fresh orange, so `fre` is incremented.
    * `if(fre == 0) return 0;`: If there are no fresh oranges, we immediately return 0.
    * `int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};`: Defines the four possible directions (up, down, left, right) to check for adjacent oranges.

* **BFS Process:**
    * `int t = -1;`: Initializes the time counter (`t`).
    * `while(!q.isEmpty())`: The main BFS loop continues as long as there are rotten oranges in the queue.
    * `t++;`: Increments the time counter for each iteration (minute).
    * `int size = q.size();`: Stores the size of the queue to process all oranges added in the previous minute. This ensures that oranges added in the current iteration are processed only in the next iteration
    * The inner loop iterates through the oranges in the current level of the queue (`size`).
        * `int[] p = q.poll();`: Removes an orange from the queue.
        * The nested loop iterates through the four directions:
            * `int ni = d[0]+p[0]; int nj = d[1]+p[1];`: Calculates the coordinates of the adjacent orange.
            * The `if` condition checks if the adjacent orange is within the grid bounds (`ni>=0 && nj>=0 && ni<n && nj<m`) and is fresh (`grid[ni][nj]==1`).
            * If the conditions are met:
                * `grid[ni][nj] = 2;`: The fresh orange rots.
                * `q.add(new int[]{ni, nj});`: The rotten orange is added to the queue for processing in the next iteration.
                * `fre--;`: Decrements the count of fresh oranges.

* **Result:**
    * `return (fre==0)?t:-1;`: If all fresh oranges have rotted (`fre == 0`), the function returns the time `t`; otherwise, it returns -1.

**5. Time and Space Complexity:**

* **Time Complexity:** O(M * N), where M is the number of rows and N is the number of columns in the grid.  We visit each cell at most once.  The BFS process iterates through each cell at most once.

* **Space Complexity:** O(M * N) in the worst case. The queue can hold all the oranges in the grid if all of them are rotten.  The space used by the grid itself is also O(M * N).

In summary, the provided Java code efficiently solves the "Rotting Oranges" problem using Breadth-First Search, providing a clear and concise solution with optimal time and space complexity.
