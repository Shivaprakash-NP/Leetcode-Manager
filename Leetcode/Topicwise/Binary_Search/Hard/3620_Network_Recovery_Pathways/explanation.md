## LeetCode Problem: Network Recovery Pathways

**1. Problem Understanding:**

The problem asks us to find the maximum minimum edge weight along a path from node 0 to node `n-1` in a network, given that some nodes might be offline.  We are given a list of edges with their weights, a boolean array indicating which nodes are online, and a maximum total path weight `k`.  The solution needs to return the maximum minimum edge weight that allows a path from node 0 to node `n-1` with a total weight less than or equal to `k`.


**2. Approach / Intuition:**

The solution employs a binary search on the minimum edge weight.  For a given minimum edge weight `m`, we perform a Breadth-First Search (BFS) using a priority queue to check if a path exists from node 0 to node `n-1` where all edge weights are at least `m` and the total path weight is less than or equal to `k`. This BFS uses Dijkstra's algorithm to find the shortest path using a min heap to speed up finding the next node to visit.

This approach is efficient because checking for path existence with a given minimum weight is relatively fast using BFS. The binary search helps to efficiently find the maximum possible minimum edge weight.  We start with a range from 0 to the maximum edge weight and iteratively narrow it down until we find the optimal value.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<int[]>[] adj`: An adjacency list representing the graph. Each element is a list of edges connected to a node, where each edge is represented as an integer array `[neighbor_node, edge_weight]`.
    * `PriorityQueue<int[]> q`: A priority queue to store nodes to visit during BFS, prioritizing nodes with shorter distances. Each element is an array `[distance, node_index]`.
    * `long[] dis`: An array to store the shortest distances from node 0 to all other nodes.
    * `boolean[] online`: A boolean array indicating whether each node is online.

* **Algorithms:**
    * **Breadth-First Search (BFS) with Dijkstra's Optimization:**  The `bfs` function uses a priority queue-based BFS (essentially Dijkstra's algorithm) to efficiently find the shortest path.
    * **Binary Search:** The `findMaxPathScore` function uses binary search to find the maximum minimum edge weight.


**4. Code Walkthrough:**

* **`findMaxPathScore(int[][] edges, boolean[] online, long k)`:**
    * This function initializes the adjacency list `adj` from the input `edges`.
    * It finds the `maxw` (maximum edge weight) to define the search space for binary search.
    * It performs a binary search using `bfs` to find the maximum acceptable minimum edge weight.
    * It returns the maximum minimum edge weight `ans`.

* **`bfs(ArrayList<int[]>[] adj, boolean[] online, long k, int minw)`:**
    * This function performs a BFS to check if a path exists from node 0 to node `n-1` with a minimum edge weight of `minw` and total weight less than or equal to `k`.
    * It initializes a priority queue `q` with the starting node (0, 0).
    * It uses `dis` array to track the shortest distances.
    * It iteratively explores nodes, considering only edges with weight >= `minw` and online nodes.
    * It returns `true` if a path to the destination node (`n-1`) is found, otherwise `false`.

**5. Time and Space Complexity:**

* **Time Complexity:**
    * The `bfs` function has a time complexity of O(E log V), where E is the number of edges and V is the number of vertices (nodes). This is because of the priority queue operations in the BFS.
    * The binary search in `findMaxPathScore` takes O(log W) iterations, where W is the maximum edge weight.
    * Therefore, the overall time complexity is O(E log V log W).

* **Space Complexity:**
    * The adjacency list `adj` takes O(E) space.
    * The `dis` array and the priority queue `q` take O(V) space.
    * The overall space complexity is O(E + V).


In summary, the solution efficiently utilizes a combination of binary search and a priority queue-based BFS to solve the problem with a time complexity of O(E log V log W) and space complexity of O(E + V).  The use of Dijkstra's algorithm within the BFS ensures efficient exploration of the graph.
