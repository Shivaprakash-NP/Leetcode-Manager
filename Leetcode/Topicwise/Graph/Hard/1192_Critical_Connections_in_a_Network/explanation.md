## Critical Connections in a Network: Detailed Solution Explanation

**1. Problem Understanding:**

The problem asks us to find all the "critical connections" in a network represented as an undirected graph. A critical connection is an edge whose removal would increase the number of connected components in the graph.  In other words, it's an edge that is essential for maintaining connectivity between certain nodes.


**2. Approach / Intuition:**

This solution uses Tarjan's algorithm for finding articulation points (nodes whose removal increases the number of connected components) and bridges (edges whose removal increases the number of connected components).  We adapt it to directly find the bridges (critical connections).

The core idea is a depth-first search (DFS) that tracks two values for each node:

* `time[node]`: The discovery time of the node during the DFS.
* `low[node]`: The lowest discovery time reachable from the node, either directly or through back edges.

An edge (u, v) is a critical connection if `low[v] > time[u]` (or equivalently, `low[u] > time[v]`). This condition implies that there's no back edge from the subtree rooted at `v` that can reach a node with a discovery time earlier than or equal to `time[u]`.  Therefore, removing the edge (u, v) would disconnect the subtree rooted at `v` from the rest of the graph.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<List<Integer>> ans`: Stores the list of critical connections (edges).
    * `int[] time`: Array to store the discovery time of each node.
    * `int[] low`: Array to store the lowest discovery time reachable from each node.
    * `ArrayList<Integer>[] adj`: Adjacency list representation of the graph.
* **Algorithms:**
    * **Depth-First Search (DFS):**  Used to traverse the graph and compute `time` and `low` values.
    * **Tarjan's Algorithm (adapted):**  The core algorithm for finding bridges (critical connections) using DFS and the `time` and `low` values.


**4. Code Walkthrough:**

* **`Solution` class:** This class encapsulates the solution.
* **`ans`, `time`, `low`, `t`, `adj`:** Member variables to store results, discovery times, lowest reachable times, a time counter, and the adjacency list, respectively.
* **`dfs(par, node)`:** This recursive function performs the DFS.
    * `time[node] = t; low[node] = t; t++;`: Assigns the current time to `time[node]` and `low[node]`, then increments the time counter.
    * `for(int v : adj[node])`: Iterates through neighbors `v` of `node`.
    * `if(v == par) continue;`: Avoids going back to the parent node.
    * `if(time[v] == -1)`: If `v` is not visited, recursively call `dfs(node, v)`.
        * `low[node] = Math.min(low[v], low[node]);`: Updates `low[node]` to the minimum of `low[v]` and `low[node]`.  This is crucial for detecting back edges.
        * `if(low[v] > time[node])`: If this condition is true, edge (node, v) is a critical connection.  It's added to `ans`.
    * `else low[node] = Math.min(low[node], time[v]);`: If `v` is already visited (back edge), update `low[node]`.
* **`criticalConnections(int n, List<List<Integer>> connections)`:** This function sets up the graph and calls DFS.
    * Initializes `time`, `low`, and `adj`.
    * Builds the adjacency list from `connections`.
    * Calls `dfs(-1, 0)` to start DFS from node 0.
    * Returns `ans`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(V + E), where V is the number of vertices (nodes) and E is the number of edges in the graph.  This is because the DFS visits each vertex and edge once.
* **Space Complexity:** O(V + E).  The space is dominated by the adjacency list (`adj`), which can store up to O(V + E) elements.  The `time` and `low` arrays take O(V) space, and the `ans` list can have at most O(E) elements in the worst case.


In summary, this Java code provides an efficient and well-structured solution to the "Critical Connections in a Network" problem using Tarjan's algorithm. The use of an adjacency list and careful implementation of the DFS makes the algorithm achieve optimal time and space complexity.
