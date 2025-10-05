## Pacific Atlantic Water Flow - Detailed Explanation

### 1. Problem Understanding:

The problem presents a matrix (2D array) representing land heights. We need to find all the cells in the matrix from which water can flow to both the Pacific Ocean (left and top edges) and the Atlantic Ocean (right and bottom edges).  Water can only flow from a cell to an adjacent cell (up, down, left, right) if the adjacent cell's height is less than or equal to the current cell's height.  The goal is to return a list of coordinates (row, column) representing the cells that can reach both oceans.

### 2. Approach / Intuition:

The core idea is to use a Breadth-First Search (BFS) algorithm to determine which cells can reach each ocean separately.  Instead of starting from the cells themselves and trying to see if they can reach both oceans, we start from the oceans and move *inward* to see which cells can reach *them*.

Here's the breakdown:

1.  **Reverse Thinking:**  It's easier to find cells that can reach an ocean than to prove that a cell can reach *both* oceans directly.  We reverse the direction of flow.  Instead of simulating water flowing *from* a cell, we simulate water flowing *to* a cell from the ocean.

2.  **BFS from Oceans:**  We perform two separate BFS traversals: one starting from the Pacific Ocean edges (top and left) and another starting from the Atlantic Ocean edges (bottom and right).

3.  **Reachable Sets:** Each BFS determines a set of cells that can reach the corresponding ocean.  A cell can reach an ocean if there's a path of non-increasing heights from that cell to the ocean edge.

4.  **Intersection:**  Finally, we find the intersection of the two sets of reachable cells.  Any cell present in both sets is a cell from which water can flow to both the Pacific and Atlantic Oceans.

**Why BFS?**

BFS is well-suited for this problem because it systematically explores the grid layer by layer. This ensures that we find the shortest "path" (in terms of number of steps) from the ocean to each reachable cell. It's also efficient for determining reachability.

### 3. Data Structures and Algorithms:

*   **2D Array `heights[][]`:** Represents the land heights.
*   **Set `P` (Pacific) and `A` (Atlantic):**  Stores the coordinates (as strings "row col") of cells that can reach the Pacific and Atlantic oceans, respectively.  Sets are used for efficient `contains` checks.
*   **Queue `Pq` (Pacific) and `Aq` (Atlantic):**  Used for BFS traversals, storing coordinates (int array of size 2) of cells to visit.
*   **Boolean 2D Array `Pvis[][]` (Pacific) and `Avis[][]` (Atlantic):** Tracks visited cells during the BFS traversals to prevent cycles and redundant processing.
*   **BFS (Breadth-First Search):** The core algorithm for exploring the grid and determining reachability from the oceans.

### 4. Code Walkthrough:

```java
class Solution {
    private void bfs(int[][] hei, Set<String> set, Queue<int[]> q, boolean[][] vis) {

        int m = hei.length;
        int n = hei[0].length;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();
            set.add(p[0]+" "+p[1]); //Mark current cell as visited by adding to the set.

            for(int[] d : dir) {
                int i = p[0]+d[0];
                int j = p[1]+d[1];
                if(i>=0 && j>=0 && i<m && j<n && !vis[i][j] && hei[p[0]][p[1]] <= hei[i][j]) {
                    q.offer(new int[]{i, j});
                    vis[i][j] = true;
                }
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        Set<String> P = new HashSet<>();
        Set<String> A = new HashSet<>();

        Queue<int[]> Pq = new LinkedList<>();
        Queue<int[]> Aq = new LinkedList<>();

        boolean[][] Pvis = new boolean[m][n];
        boolean[][] Avis = new boolean[m][n];

        for(int i = 0; i<m; i++) {
            Pq.offer(new int[]{i, 0});
            Aq.offer(new int[]{i, n-1});
            Pvis[i][0] = true;
            Avis[i][n-1] = true;
        }

        for(int i = 0; i<n; i++) {
            Pq.offer(new int[]{0, i});
            Aq.offer(new int[]{m-1, i});
            Pvis[0][i] = true;
            Avis[m-1][i] = true;
        }

        bfs(heights, P, Pq, Pvis);
        bfs(heights, A, Aq, Avis);

        List<List<Integer>> ans = new ArrayList<>();
        for(String p : A) {
            if(P.contains(p)) {
                String[] s = p.split(" ");
                List<Integer> t = new ArrayList<>();
                t.add(Integer.parseInt(s[0]));
                t.add(Integer.parseInt(s[1]));
                ans.add(t);
            }
        }        

        return ans;
    }
}
```

*   **`bfs(int[][] hei, Set<String> set, Queue<int[]> q, boolean[][] vis)` function:**
    *   `hei`: The heights matrix.
    *   `set`:  The set to store reachable cells (either P or A).
    *   `q`: The queue for BFS (either Pq or Aq).
    *   `vis`: The visited array (either Pvis or Avis).

    1.  `int m = hei.length; int n = hei[0].length;`: Gets the dimensions of the matrix.
    2.  `int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};`: Defines the four possible directions (down, up, right, left) to move to adjacent cells.
    3.  `while(!q.isEmpty()) { ... }`:  The main BFS loop.
    4.  `int[] p = q.poll();`:  Retrieves the next cell from the queue.
    5.  `set.add(p[0]+" "+p[1]);`: Adds the cell's coordinates (converted to a string) to the reachable set. We use a string because a 2D array cannot be a set element.
    6.  `for(int[] d : dir) { ... }`: Iterates through the four possible directions.
    7.  `int i = p[0]+d[0]; int j = p[1]+d[1];`: Calculates the coordinates of the adjacent cell.
    8.  `if(i>=0 && j>=0 && i<m && j<n && !vis[i][j] && hei[p[0]][p[1]] <= hei[i][j]) { ... }`:  This is the crucial condition.  It checks:
        *   `i>=0 && j>=0 && i<m && j<n`:  If the adjacent cell is within the bounds of the matrix.
        *   `!vis[i][j]`:  If the adjacent cell has not been visited yet.  This prevents cycles.
        *   `hei[p[0]][p[1]] <= hei[i][j]`: If the water *can* flow from the adjacent cell to the current cell.  Remember, we're reversing the flow direction.  So, we're checking if the current cell's height is *less than or equal to* the adjacent cell's height.  If this is true, it means water *can flow from* the neighbor *to* the ocean, and we are traversing "upstream".
    9.  `q.offer(new int[]{i, j});`: Adds the adjacent cell to the queue for further exploration.
    10. `vis[i][j] = true;`: Marks the adjacent cell as visited.

*   **`pacificAtlantic(int[][] heights)` function:**

    1.  `int m = heights.length; int n = heights[0].length;`: Gets the dimensions of the matrix.
    2.  `Set<String> P = new HashSet<>(); Set<String> A = new HashSet<>();`: Initializes the sets to store reachable cells for the Pacific and Atlantic oceans.
    3.  `Queue<int[]> Pq = new LinkedList<>(); Queue<int[]> Aq = new LinkedList<>();`: Initializes the queues for BFS for the Pacific and Atlantic oceans.
    4.  `boolean[][] Pvis = new boolean[m][n]; boolean[][] Avis = new boolean[m][n];`: Initializes the visited arrays for the Pacific and Atlantic oceans.
    5.  The next two `for` loops initialize the queues and visited arrays by adding all the cells along the borders of the matrix to their respective oceans' queues. This is where we begin our BFS from the edges.
    6.  `bfs(heights, P, Pq, Pvis);`: Performs the BFS for the Pacific Ocean.
    7.  `bfs(heights, A, Aq, Avis);`: Performs the BFS for the Atlantic Ocean.
    8.  `List<List<Integer>> ans = new ArrayList<>();`: Creates a list to store the final answer (coordinates of cells reachable from both oceans).
    9.  `for(String p : A) { ... }`: Iterates through the cells reachable from the Atlantic Ocean.
    10. `if(P.contains(p)) { ... }`: Checks if the current cell is also reachable from the Pacific Ocean (i.e., if it's in the intersection of the two sets).
    11. If a cell is reachable from both oceans, its coordinates are added to the `ans` list.
    12. `return ans;`: Returns the list of coordinates.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(m \* n), where *m* is the number of rows and *n* is the number of columns in the matrix.  This is because:
    *   We visit each cell at most once during each of the two BFS traversals. The `vis` array ensures this.
    *   The operations inside the BFS loop (queue operations, set lookups, comparisons) take O(1) time on average.
    *   The final loop to find the intersection of the sets also takes O(m\*n) in the worst case.
*   **Space Complexity:** O(m \* n) in the worst case.  This is due to:
    *   The `Pvis` and `Avis` boolean arrays, which have dimensions m x n.
    *   The `P` and `A` sets, which in the worst case, could store all m \* n cells.
    *   The `Pq` and `Aq` queues, which in the worst case, could also hold all m \* n cells.

In summary, the solution employs BFS to efficiently explore the matrix and determine reachability from the Pacific and Atlantic oceans, taking O(m*n) time and space.
