## LeetCode: Critical Connections in a Network - Solution Explained

**1. Problem Understanding:**

The problem asks us to find all the "critical connections" in a network represented as an undirected graph. A critical connection is an edge whose removal would increase the number of connected components in the graph.  In simpler terms, if you remove a critical connection, you break the network into separate parts.


**2. Approach / Intuition:**

This solution uses Tarjan's algorithm to find bridges (critical connections) in an undirected graph.  Tarjan's algorithm is efficient because it leverages Depth-First Search (DFS) to explore the graph and track the "low" value for each node. The low value of a node represents the lowest time-stamp reachable from that node, either directly or through back-edges.  If the low value of a child node is greater than the time-stamp of its parent node during DFS, it indicates that there's no path from the child node back to the parent node other than the direct edge between them. This edge is a bridge, or a critical connection.

This approach was chosen because Tarjan's algorithm is specifically designed for finding bridges in linear time, which is optimal for this problem.  Other approaches might be less efficient.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<List<Integer>> ans`: Stores the list of critical connections (edges). Each inner list represents an edge (a pair of nodes).
    * `int[] time`: Stores the discovery time (DFS order) of each node.
    * `int[] low`: Stores the lowest discovery time reachable from each node.
    * `ArrayList<Integer>[] adj`: An adjacency list representation of the graph.  It efficiently stores the neighbors of each node.

* **Algorithms:**
    * **Depth-First Search (DFS):**  The core algorithm used to explore the graph.
    * **Tarjan's Algorithm:** A specific algorithm for finding bridges (critical connections) in a graph, built upon DFS.


**4. Code Walkthrough:**

* **`criticalConnections(int n, List<List<Integer>> connections)`:** This is the main function.
    * It initializes `time`, `low`, and `adj`.  `time` is filled with -1 initially (indicating unvisited nodes). `low` is initialized with a very large value. `adj` is created as an adjacency list from the `connections`.
    * It then calls `dfs(-1, 0)` to start the DFS traversal from node 0.
    * Finally, it returns the `ans` list containing the critical connections.

* **`dfs(int par, int node)`:** This is the recursive DFS function.
    * `time[node] = t; low[node] = t; t++;`: It assigns the current time-stamp to both `time` and `low` of the current node and increments `t`.
    * `for(int v : adj[node])`: It iterates through the neighbors (`v`) of the current node (`node`).
    * `if(v == par) continue;`: It skips the parent node to avoid going back up the tree immediately.
    * `if(time[v] == -1)`: If the neighbor is unvisited:
        * `dfs(node, v);`: Recursively calls `dfs` on the neighbor.
        * `low[node] = Math.min(low[v], low[node]);`: Updates the `low` value of the current node with the minimum of its current `low` value and the `low` value of its child. This accounts for back-edges from descendants.
        * `if(low[v] > time[node])`: If the `low` value of the child is greater than the `time` value of the parent, it means this edge is a critical connection. The edge is then added to the `ans` list.
    * `else low[node] = Math.min(low[node], time[v]);`: If the neighbor is already visited, it updates the `low` value of the current node considering the `time` value of the neighbor (potentially finding a shorter path back to an ancestor).

**5. Time and Space Complexity:**

* **Time Complexity: O(V + E)**, where V is the number of vertices (nodes) and E is the number of edges in the graph.  This is because the DFS visits each vertex and edge exactly once.

* **Space Complexity: O(V + E)**. This is dominated by the space used by the adjacency list (`adj`), which stores all edges and the `time` and `low` arrays, which store information about each vertex.  The call stack during the recursive DFS also contributes to space complexity but is bounded by the height of the graph (which is at most V).

The algorithm is therefore considered linear in terms of the size of the input graph.
