### Problem Understanding

The problem asks us to find the *second minimum time* required to travel from a starting node (node 1) to a destination node (node `n`) in a given undirected graph.

Here are the key details:
*   The graph has `n` nodes and `edges`.
*   Each edge traversal takes a fixed `time` duration.
*   There are traffic lights at each node. The lights cycle with a period of `2 * change`.
    *   For the first `change` minutes of a cycle (i.e., `t % (2 * change) < change`), the light is green.
    *   For the next `change` minutes (i.e., `t % (2 * change) >= change`), the light is red.
*   If you arrive at a node during a red light, you must wait until the light turns green before you can proceed.

Essentially, we need to find two things:
1.  The path from node 1 to node `n` that uses the second fewest number of edges.
2.  Calculate the total time for this path, considering the traffic light rules.

### Approach / Intuition

The core idea is to adapt a Breadth-First Search (BFS) algorithm to find not just the shortest path (in terms of edges), but also the *second shortest path* (also in terms of edges) to every node in the graph. Once we have the second shortest path length (in edges) to the destination node `n`, we can then calculate the actual time taken, factoring in the traffic light delays.

Here's why this approach works:
1.  **Edge Count First:** Since each edge takes a fixed `time`, and traffic light delays depend on the *total elapsed time*, the number of edges directly influences the total time. A path with fewer edges will generally lead to less total time (though traffic lights can complicate this). Finding the second shortest path in terms of edges is a good starting point.
2.  **Modified BFS for K-Shortest Paths:** A standard BFS finds the shortest path in an unweighted graph. To find the second shortest path, we need to store two minimum path lengths (number of edges) for each node `v`:
    *   `d1[v]`: The minimum number of edges to reach node `v`.
    *   `d2[v]`: The second minimum number of edges to reach node `v`.
    We initialize `d1` and `d2` to a very large value (infinity). During the BFS, when we explore from `u` to `v` and `u` was reached in `cnt` edges:
    *   If `cnt` is less than `d1[v]`, we've found a new shortest path to `v`. The old `d1[v]` becomes the new `d2[v]`, and `d1[v]` is updated to `cnt`. We then add `v` to the queue to explore further.
    *   If `cnt` is greater than `d1[v]` but less than `d2[v]`, we've found a new second shortest path to `v`. `d2[v]` is updated to `cnt`. We then add `v` to the queue to explore further.
    This ensures that we explore paths in increasing order of edge count, effectively finding the first and second minimums.
3.  **Time Calculation with Traffic Lights:** After determining `d2[n]` (the second minimum number of edges to reach `n`), we simulate the journey edge by edge.
    *   We maintain a `current_time`.
    *   For each edge, we first check the traffic light status at the `current_time`.
    *   The traffic light cycle is `2 * change`. Let `c = 2 * change`.
    *   The time within the current cycle is `r = current_time % c`.
    *   If `r < change`, the light is green. We simply add `time` to `current_time`.
    *   If `r >= change`, the light is red. We must wait until the light turns green. The waiting time will be `c - r`. After waiting, we add `time` for the edge traversal.

### Data Structures and Algorithms

*   **Adjacency List:** `ArrayList<Integer>[] adj` is used to represent the graph. Each index `i` stores a list of nodes adjacent to node `i`. This is efficient for sparse graphs.
*   **Arrays for Distances:**
    *   `int[] d1`: Stores the minimum number of edges to reach each node from the source (node 1).
    *   `int[] d2`: Stores the second minimum number of edges to reach each node from the source (node 1).
*   **Queue:** `Queue<Integer> q` (implemented with `LinkedList`) is used for the BFS traversal. It stores nodes to be visited.
*   **Breadth-First Search (BFS) variant:** The core algorithm is a modified BFS. It explores the graph level by level, where each level corresponds to an increasing number of edges from the source. The modification allows it to track two distinct shortest path lengths.

### Code Walkthrough

```java
class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // 1. Graph Initialization (Adjacency List)
        ArrayList<Integer>[] adj = new ArrayList[n+1]; // Adjacency list for n nodes (1-indexed)
        for(int i = 1; i<=n; i++) adj[i] = new ArrayList<>(); // Initialize each list

        for(int[] p : edges) { // Populate adjacency list from given edges
            int u = p[0];
            int v = p[1];
            adj[u].add(v); // Add edge u-v
            adj[v].add(u); // Add edge v-u (undirected graph)
        }

        // 2. Distance Arrays Initialization
        int[] d1 = new int[n+1]; // Stores first minimum edge count to reach node i
        int[] d2 = new int[n+1]; // Stores second minimum edge count to reach node i
        Arrays.fill(d1, Integer.MAX_VALUE); // Initialize with max value (infinity)
        Arrays.fill(d2, Integer.MAX_VALUE); // Initialize with max value (infinity)

        // 3. BFS Setup
        Queue<Integer> q = new LinkedList<>();
        int cnt = 1; // 'cnt' represents the current path length in terms of edges
        q.offer(1); // Start BFS from node 1

        // 4. Modified BFS Loop
        while(!q.isEmpty()) {
            int size = q.size(); // Get current level size to process all nodes at 'cnt' edges
            for(int i = 0; i<size; i++) {
                int u = q.poll(); // Dequeue current node
                for(int v : adj[u]) { // Explore neighbors of u
                    // Case 1: Found a new shortest path to v
                    if(cnt < d1[v]) {
                        d2[v] = d1[v]; // Old shortest path becomes second shortest
                        d1[v] = cnt;   // Update shortest path
                        q.offer(v);    // Enqueue v to explore further
                    } 
                    // Case 2: Found a new second shortest path to v (must be distinct from d1[v])
                    else if(cnt > d1[v] && cnt < d2[v]) {
                        d2[v] = cnt;   // Update second shortest path
                        q.offer(v);    // Enqueue v to explore further
                    }
                }
            }
            cnt++; // Increment edge count