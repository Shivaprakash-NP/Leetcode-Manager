## LeetCode: Is Graph Bipartite? - Solution Explained

**1. Problem Understanding:**

The problem asks whether a given graph can be divided into two disjoint sets (let's call them Set A and Set B) such that every edge connects a node in Set A to a node in Set B (and no two nodes within the same set are connected).  In other words, can we color the nodes of the graph using only two colors (e.g., 1 and 2) such that no two adjacent nodes have the same color?  If such a coloring is possible, the graph is bipartite; otherwise, it's not.

**2. Approach / Intuition:**

The solution employs Depth-First Search (DFS) with a coloring strategy.  We use a `vis` array to track the color of each node (0 for unvisited, 1 for color A, 2 for color B).  The `dfs` function recursively explores the graph, assigning colors to nodes. If we encounter a node that's already visited and has the same color as the current node, it means we've found an adjacent pair with the same color, violating the bipartite condition, and we return `false`.  Otherwise, we assign the opposite color to the neighbor and continue the recursion.  If the DFS completes without finding such a conflict for all nodes, the graph is bipartite.

This approach is chosen because DFS efficiently explores all connected components of the graph, ensuring that all nodes are checked for bipartite properties.  A Breadth-First Search (BFS) could also be used, achieving the same result.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] adj`: Adjacency matrix representing the graph.  `adj[i]` contains a list of nodes adjacent to node `i`.
    * `int[] vis`: Array to store the color of each node (0: unvisited, 1: color A, 2: color B).

* **Algorithms:**
    * Depth-First Search (DFS):  Used to explore the graph and assign colors to nodes.


**4. Code Walkthrough:**

* **`dfs(int node, int c, int[][] adj, int[] vis)`:**
    * Takes the current `node`, its assigned `color` (`c`), the adjacency matrix `adj`, and the visited array `vis` as input.
    * `vis[node] = c;`: Assigns color `c` to the current node.
    * The loop iterates through the neighbors (`v`) of the current node.
        * `if(vis[v] != 0)`: If the neighbor is already visited, check for color conflict. If `vis[v] == c`, it's a conflict, so return `false`.
        * `else`: If the neighbor is unvisited, recursively call `dfs` with the neighbor, assigning the opposite color (`(c==1)?2:1`).  The `cur` boolean variable ensures that the function returns `false` if any recursive call finds a conflict.
    * `return cur;`: Returns `true` if no conflict was found in this subtree, `false` otherwise.

* **`isBipartite(int[][] graph)`:**
    * Initializes `vis` array with 0s (unvisited).
    * Iterates through each node (`i`).
        * `if(vis[i] == 0)`: If the node is unvisited (belongs to a new connected component), call `dfs` to color it and its connected component.  If `dfs` returns `false`, the graph isn't bipartite, so return `false`.
    * `return true;`: If all nodes are processed without conflicts, the graph is bipartite.

**5. Time and Space Complexity:**

* **Time Complexity:** O(V + E), where V is the number of vertices (nodes) and E is the number of edges in the graph.  This is because DFS visits each node and edge exactly once (in the worst case).

* **Space Complexity:** O(V).  The space is dominated by the `vis` array, which stores the color of each node. The recursion stack in DFS could also take up to O(V) space in the worst case (a deeply nested graph).  The adjacency matrix itself is not considered part of the algorithm's space complexity as it's given as input.


This detailed explanation provides a comprehensive understanding of the provided Java code for determining if a graph is bipartite.  The use of DFS with a clever coloring scheme efficiently solves the problem with optimal time and space complexity.
