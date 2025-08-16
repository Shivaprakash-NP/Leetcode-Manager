## Minimum Cost Path with Edge Reversals: A Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find the minimum cost to reach node `n-1` from node `0` in a directed graph.  The graph is represented by a set of edges, where each edge `(u, v, w)` indicates a directed edge from node `u` to node `v` with cost `w`.  Crucially, we can traverse an edge in the reverse direction, but this incurs double the cost (2*w).  If no path exists, we return -1.

**2. Approach / Intuition:**

The solution employs Dijkstra's algorithm to find the shortest path in a graph with non-negative edge weights.  Because traversing an edge in reverse doubles the cost, we effectively create a new directed edge for each existing edge, representing the reverse traversal with adjusted cost. Dijkstra's algorithm is ideal here because it efficiently finds the shortest path from a single source node to all other nodes in a graph with non-negative edge weights.  This ensures that we will find the minimum cost path, considering both forward and reverse edge traversals.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<int[]>[] adj`: An adjacency list representing the graph.  Each element is an array list of `int[]` arrays, where each inner array represents an edge `[neighbor_node, edge_cost]`.
    * `int[] vis`: An array to store the minimum cost to reach each node from the source. Initialized with `Integer.MAX_VALUE` to represent infinity.
    * `PriorityQueue<int[]> q`: A priority queue to efficiently manage nodes to visit, ordered by their minimum cost.

* **Algorithms:**
    * **Dijkstra's Algorithm:**  A greedy algorithm used to find the shortest paths from a single source node to all other nodes in a graph with non-negative weights.


**4. Code Walkthrough:**

* **Initialization:**
    * `int[] vis = new int[n]; Arrays.fill(vis, Integer.MAX_VALUE);`:  Initializes the `vis` array, representing the minimum cost to reach each node.  `Integer.MAX_VALUE` signifies infinity.
    * `ArrayList<int[]>[] adj = new ArrayList[n]; ...`: Creates an adjacency list to represent the graph.
    * The loop populates the adjacency list with both forward and reverse edges, adjusting costs as required.

* **Dijkstra's Algorithm Implementation:**
    * `PriorityQueue<int[]> q = ...`: Creates a priority queue ordered by the minimum cost.
    * `q.offer(new int[]{0, 0}); vis[0] = 0;`: Adds the starting node (0) with cost 0 to the priority queue.
    * `while(!q.isEmpty())`: The main loop of Dijkstra's algorithm.
    * `int[] p = q.poll();`:  Retrieves the node with the smallest minimum cost from the priority queue.
    * `for(int[] nei : adj[u])`: Iterates through the neighbors of the current node.
    * `if(cost+w < vis[v])`:  If a shorter path is found to a neighbor, update `vis[v]` and add the neighbor to the priority queue.

* **Result:**
    * `return vis[n-1] == Integer.MAX_VALUE?-1:vis[n-1];`: Returns the minimum cost to reach node `n-1` or -1 if no path exists.


**5. Time and Space Complexity:**

* **Time Complexity:** O(E log V), where E is the number of edges and V is the number of vertices.  This is because Dijkstra's algorithm with a priority queue has this time complexity.  Creating the adjacency list is O(E).

* **Space Complexity:** O(V + E). This is due to the adjacency list (`adj`), which stores all edges, and the `vis` array, which stores the minimum cost for each node.  The priority queue, in the worst case, can hold all vertices.


In summary, this Java code efficiently solves the "Minimum Cost Path with Edge Reversals" problem using Dijkstra's algorithm and appropriate data structures. The time and space complexities are optimal for this type of graph problem.
