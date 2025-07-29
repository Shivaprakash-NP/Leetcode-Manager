## LeetCode: Find the City With the Smallest Number of Neighbors at a Threshold Distance

**1. Problem Understanding:**

The problem asks us to find the city with the fewest number of cities within a given distance threshold.  We're given a number of cities (`n`), connections between them represented as weighted edges (`edges`), and a maximum distance (`distanceThreshold`).  The goal is to identify the city that has the smallest number of other cities reachable within the specified distance threshold.


**2. Approach / Intuition:**

The solution uses Floyd-Warshall algorithm to find the shortest paths between all pairs of cities.  Floyd-Warshall is chosen because it efficiently computes shortest paths for all city pairs in a graph with positive or zero edge weights. After computing the shortest paths, we iterate through each city, counting the number of cities reachable within the `distanceThreshold`. The city with the minimum count is the solution.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `map[][]`: A 2D array (adjacency matrix) to store the shortest distances between all pairs of cities.  `map[i][j]` represents the shortest distance between city `i` and city `j`.  A large value (`INF`) represents unreachable cities.
* **Algorithms:**
    * **Floyd-Warshall Algorithm:**  Used to find the shortest paths between all pairs of vertices in a weighted graph.
    * **Iteration:**  Iterating through cities to count neighbors within the threshold.


**4. Code Walkthrough:**

* **Initialization:**
    ```java
    int[][] map = new int[n][n];
    int INF = 100000000; 

    for(int i = 0; i<n; i++) {
        Arrays.fill(map[i], INF);
        map[i][i] = 0;
    }
    ```
    This section initializes a square matrix `map` of size `n x n` with a large value (`INF`) representing infinity for all pairs of cities. The diagonal is set to 0 because the distance from a city to itself is 0.

* **Edge Processing:**
    ```java
    for(int[] e : edges) {
        int u = e[0];
        int v = e[1];
        int w = e[2];
        map[u][v] = w;
        map[v][u] = w;
    }
    ```
    This part populates the `map` with the initial distances given in the `edges` array. Each edge `e` contains the source city (`u`), destination city (`v`), and the weight (`w`).  The matrix is updated for both directions (undirected graph).


* **Floyd-Warshall:**
    ```java
    for(int v = 0; v<n; v++) {
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                map[i][j] = Math.min(map[i][v]+map[v][j], map[i][j]);
            }
        }
    }
    ```
    This is the core of the Floyd-Warshall algorithm. It iterates through all possible intermediate vertices (`v`) to find the shortest path between each pair of cities (`i` and `j`). `Math.min` ensures we keep the shortest path found so far.


* **Finding the City:**
    ```java
    int ans = 0;
    int rec = Integer.MAX_VALUE;
    for(int i = 0; i<n; i++) {
        int c = 0;
        for(int j = 0; j<n; j++) {
            if(i!=j && map[i][j] <= distanceThreshold) c++;
        }
        if(c <= rec) {
            rec = c;
            ans = i;
        }
    }
    return ans;
    ```
    This section iterates through each city (`i`). For each city, it counts the number of cities (`c`) reachable within the `distanceThreshold`. The city with the minimum count (`rec`) is stored in `ans`, which is ultimately returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n³), dominated by the Floyd-Warshall algorithm, which has cubic time complexity. The final iteration to count neighbors is O(n²).  However, O(n³) is the dominant factor.

* **Space Complexity:** O(n²), due to the `map` matrix which stores shortest path distances between all pairs of cities.  The space used by other variables is negligible compared to the matrix.


The solution is efficient for moderately sized graphs. For extremely large graphs, more advanced algorithms might be necessary to reduce the cubic time complexity.
