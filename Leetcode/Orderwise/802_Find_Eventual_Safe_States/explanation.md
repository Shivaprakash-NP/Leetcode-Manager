## LeetCode: Find Eventual Safe States - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find all the nodes in a directed graph that are "safe." A node is considered safe if there is no path from that node to a cycle in the graph.  In simpler terms, starting from a safe node, you can never end up in a loop.


**2. Approach / Intuition:**

The solution uses Topological Sort to identify eventual safe states. The core idea is that a node is safe if and only if it's part of a topological ordering of the graph.  Nodes involved in cycles cannot be part of a topological ordering.

We construct a reversed graph where an edge (u, v) in the original graph becomes (v, u) in the reversed graph.  Then, we perform topological sort on the reversed graph.  Nodes with an in-degree of 0 in the reversed graph are the starting points for the topological sort. The nodes that are included in the topological sort are the eventual safe nodes.  Because nodes involved in a cycle in the original graph will have a cycle in the reversed graph, therefore they won't be included in the topological sort.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<Integer>[] adj`: An adjacency list representing the reversed graph.  This is used for efficient neighbor lookups.
    * `Queue<Integer> q`: A queue for Breadth-First Search (BFS) used in the topological sort.
    * `List<Integer> topo`: A list to store the topological ordering of nodes.
    * `int[] ind`: An array to store the in-degrees of each node in the reversed graph.

* **Algorithms:**
    * **Topological Sort:**  A standard algorithm for ordering nodes in a directed acyclic graph (DAG).  It's used here to identify safe nodes.
    * **Breadth-First Search (BFS):** Used as the implementation of the topological sort.


**4. Code Walkthrough:**

```java
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        ArrayList<Integer>[] adj = new ArrayList[n]; // Initialize adjacency list for reversed graph
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS (topological sort)
        List<Integer> topo = new ArrayList<>(); // List to store topological order
        int[] ind = new int[n]; // Array to store in-degrees of nodes in reversed graph

        for(int i = 0 ; i<n ; i++) adj[i] = new ArrayList<>(); // Initialize each adjacency list entry.

        // Create reversed graph and compute in-degrees
        for(int i = 0; i<n ; i++) {
            int[] v = graph[i];
            for(int node : v) {
                adj[node].add(i); // Add reverse edge
                ind[i]++; // Increment in-degree of node i
            }
        }

        // Find nodes with in-degree 0 (starting points for topological sort)
        for(int i = 0 ; i < n ; i++) {
            if(ind[i] == 0) q.offer(i);
        }

        // Topological sort using BFS
        while(!q.isEmpty()) {
            int node = q.poll();

            for(int v : adj[node]) {
                ind[v]--; // Decrement in-degree of neighbors
                if(ind[v] == 0) q.offer(v); // Add neighbor to queue if in-degree becomes 0
            }

            topo.add(node); // Add node to topological order
        }

        Collections.sort(topo); // Sort the result for consistency (optional)

        return topo;
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(V + E), where V is the number of vertices (nodes) and E is the number of edges in the graph. This is because we iterate through the graph once to create the reversed graph and compute in-degrees, and then we perform a BFS (topological sort) which also takes O(V + E) time.

* **Space Complexity:** O(V + E).  The space is dominated by the adjacency list (`adj`), which stores the reversed graph, and the queue `q`, which can hold at most all the nodes in the worst case. The `topo` list and `ind` array also consume O(V) space.  In total O(V + E) space is needed.


In summary, this solution efficiently determines eventual safe states in a directed graph using a clever application of topological sort and a reversed graph representation.  The time and space complexities are optimal for this type of problem.
