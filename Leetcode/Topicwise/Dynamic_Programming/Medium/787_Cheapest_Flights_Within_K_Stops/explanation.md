# Cheapest Flights Within K Stops: A Detailed Explanation

## 1. Problem Understanding

The problem asks us to find the price of the cheapest flight from a source airport (`src`) to a destination airport (`dst`), with at most `k` stops allowed.  We are given the number of airports (`n`), a list of flights with their costs (`flights`), the source and destination airports, and the maximum number of stops allowed. The output should be the minimum cost, or -1 if no such flight exists.

## 2. Approach / Intuition

This problem is a classic shortest path problem with a constraint on the number of stops.  We can't use Dijkstra's algorithm directly because it doesn't inherently track the number of stops.  Instead, we use a Breadth-First Search (BFS) approach.  BFS naturally explores nodes level by level, which corresponds to the number of stops in our case.  By keeping track of the number of stops visited, we ensure that we don't exceed the allowed `k` stops.

We choose BFS because it guarantees finding the shortest path in terms of the number of edges (stops) *if the edge weights are non-negative*, which is the case here.  Dijkstra's algorithm would be more efficient for general shortest paths, but wouldn't neatly handle the stop constraint.

## 3. Data Structures and Algorithms

* **Data Structures:**
    * `ArrayList<int[]>[] adj`: An adjacency list to represent the graph of flights. Each index represents an airport, and the list at that index contains pairs of `[neighbor airport, cost]` for flights originating from that airport.
    * `Queue<int[]> q`: A queue to implement the BFS algorithm.  Each element in the queue is an array `[stops, airport, cost]`.
    * `int[] dis`: An array to store the minimum cost to reach each airport. Initialized with `Integer.MAX_VALUE` to represent infinity.

* **Algorithms:**
    * **Breadth-First Search (BFS):**  We use BFS to explore the graph level by level, limiting the exploration to at most `k+1` levels (0 stops to k stops).
    * **Graph Representation (Adjacency List):**  The adjacency list efficiently stores the flight connections.


## 4. Code Walkthrough

1. **Initialization:**
   - `adj`: Creates an adjacency list representing the flight network.  `adj[i]` holds the list of destinations and costs reachable from airport `i`.
   - `dis`: An array to keep track of the minimum cost to reach each airport. Initially, all costs are set to `Integer.MAX_VALUE` (infinity).

2. **Building the Adjacency List:**
   - The code iterates through the `flights` array. For each flight `[u, v, c]`, it adds a new entry `[v, c]` to `adj[u]`, representing a flight from airport `u` to airport `v` with cost `c`.

3. **BFS Traversal:**
   - `q.offer(new int[]{0, src, 0})`:  Starts the BFS by adding the source airport (`src`) to the queue. The initial number of stops is 0, and the cost is 0.
   - The `while(!q.isEmpty())` loop continues as long as there are airports to process in the queue.
   - `int[] fp = q.poll()`: Retrieves the next airport to process from the queue. `fp[0]` is the number of stops made, `fp[1]` is the airport, and `fp[2]` is the cost to reach that airport.
   - `if(stop>k) continue`: If the number of stops exceeds `k`, this path is not considered, and the next airport is processed.
   - The inner loop iterates through the neighbors (`nei`) of the current airport `u`.
   - `if(dist+c < dis[v])`:  If a cheaper path to airport `v` is found, the cost `dis[v]` is updated, and the new path is added to the queue.

4. **Result:**
   - `return (dis[dst]==Integer.MAX_VALUE)?-1:dis[dst];`: After processing all airports, the minimum cost to reach the destination `dst` is returned. If `dis[dst]` is still `Integer.MAX_VALUE`, it means no path exists, so -1 is returned.


## 5. Time and Space Complexity

* **Time Complexity:** O(E + N*k), where E is the number of edges (flights) and N is the number of nodes (airports).  In the worst case, we might explore all edges multiple times (up to k+1 times), hence the E * (k+1) factor.  The N factor comes from the fact that we visit each node at most once. Because k is much smaller than N, we approximate this as O(Ek).

* **Space Complexity:** O(E + N). The space is dominated by the adjacency list (`adj`), which can store up to E edges, and the `dis` array which stores N elements. The queue in BFS has a maximum size proportional to the branching factor of the graph and the depth of search (k). In the worst case, this is O(Ek), but since k is typically small, it's usually dominated by the adjacency list size.  Thus, O(E+N) is a reasonable approximation.
