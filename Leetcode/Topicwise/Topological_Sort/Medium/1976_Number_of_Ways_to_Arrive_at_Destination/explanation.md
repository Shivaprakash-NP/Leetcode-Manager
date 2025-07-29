## LeetCode: Number of Ways to Arrive at Destination - Detailed Explanation

**1. Problem Understanding:**

The problem asks to find the number of different ways to reach the destination node (node `n-1`) from the source node (node `0`) in a weighted, undirected graph, such that the total weight of the path is minimal.  Multiple paths with the same minimum weight are allowed, and we need to count all such paths.


**2. Approach / Intuition:**

The problem can be efficiently solved using Dijkstra's algorithm with a slight modification to count paths.  Dijkstra's algorithm finds the shortest paths from a single source node to all other nodes in a graph. We augment it to keep track of the number of ways to reach each node with the shortest distance found so far.

We choose Dijkstra's algorithm (implemented using a priority queue) because it's the most efficient algorithm for finding shortest paths in a weighted graph when negative edge weights are not present.  Other algorithms like Bellman-Ford are less efficient in this case.

The key idea is to maintain two arrays: `dis` (shortest distance to each node) and `way` (number of ways to reach each node with the shortest distance). When we discover a shorter path to a node, we update its shortest distance and the number of ways to reach it. If we discover a path with the *same* shortest distance, we add the number of ways to reach the current node to the number of ways to reach the newly discovered node (modulo `MOD` to avoid integer overflow).


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<long[]>[] adj`: An adjacency list to represent the graph. Each entry is a list of edges connected to a node, where each edge is represented as `{neighbor_node, weight}`. Using `long` to prevent integer overflow for weights.
    * `long[] dis`: An array to store the shortest distance from the source node to each node. Initialized to `Long.MAX_VALUE`.
    * `long[] way`: An array to store the number of ways to reach each node with the shortest distance. Initialized to `0`.
    * `PriorityQueue<long[]> q`: A priority queue to store nodes to be processed, prioritized by their shortest distance from the source.
* **Algorithms:**
    * **Dijkstra's Algorithm:**  Used to find the shortest paths from the source node to all other nodes.
    * **Modulo Arithmetic:** Used to prevent integer overflow when counting paths.


**4. Code Walkthrough:**

* **Lines 4-8:** Initialize the adjacency list `adj` from the given `roads` input.  The graph is undirected, so we add edges in both directions. Note the use of `long` to prevent integer overflow with large weights.
* **Lines 10-13:** Initialize the `dis` and `way` arrays.
* **Lines 15-18:** Initialize the priority queue `q` with the source node (0,0), meaning distance 0 and node 0.
* **Lines 21-33:** This is the main Dijkstra's algorithm loop.
    * `q.poll()`: Gets the node with the smallest distance.
    * The inner loop iterates through the neighbors of the current node `u`.
    * `if(d+dd == dis[(int)v]) way[(int)v]=(way[(int)v]+way[(int)u])%MOD;`: If a path with the same minimum distance is found, we add the number of ways to reach the current node to the number of ways to reach the neighbor (modulo `MOD`).
    * `if(d+dd < dis[(int)v])`: If a shorter path is found, update the shortest distance and the number of ways.
* **Line 35:** Return the number of ways to reach the destination node `n-1`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(E log V), where E is the number of edges and V is the number of vertices in the graph. This is because the priority queue operations (insertion and extraction) take O(log V) time, and we iterate through all edges in the worst case.
* **Space Complexity:** O(V + E), where V is the number of vertices and E is the number of edges. This is due to the space used by the adjacency list, `dis` array, `way` array, and priority queue.  In the worst case the priority queue will contain all vertices.

The use of a priority queue in Dijkstra's algorithm makes this solution efficient for large graphs, significantly better than a naive breadth-first search approach which would not utilize the weights of the edges effectively. The space complexity is linear with the input size.
