## LeetCode: Number of Operations to Make Network Connected - Solution Explained

**1. Problem Understanding:**

The problem asks us to determine the minimum number of cables needed to connect all `n` computers in a network.  We are given a list of existing connections between computers.  A connection is represented as an edge between two computers. If it's impossible to connect all computers, we return -1.

**2. Approach / Intuition:**

This problem can be efficiently solved using the Union-Find (Disjoint Set) algorithm.  The core idea is to represent each computer as a node in a graph.  Connections between computers are edges in this graph.  The Union-Find algorithm helps us efficiently determine the number of connected components in the graph.  Each connected component represents a group of computers that are already connected.  The minimum number of cables needed is equal to the number of connected components minus 1 (because each cable connects two components, effectively reducing the number of components by one).

We choose this approach because Union-Find is highly optimized for this type of connectivity problem, offering near-constant time complexity for most operations.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `parent[]`: An integer array used to store the parent of each node in the Union-Find data structure.  `parent[i]` represents the parent node of node `i`.  If `parent[i] == i`, then node `i` is the root of its component.
    * `size[]`: An integer array used to store the size of each component. This is used for union-by-rank optimization.
* **Algorithms:**
    * **Union-Find (Disjoint Set):**  This algorithm is used to efficiently manage the connected components.  It consists of two main operations: `find()` (to find the root of a component) and `union()` (to merge two components).
    * **Path Compression (within `find()`):**  This optimization technique flattens the tree structure in the Union-Find data structure, improving the efficiency of future `find()` operations.
    * **Union by Rank (within `union()`):** This optimization attaches the smaller tree to the root of the larger tree, improving the overall height of the tree structure and maintaining efficiency.


**4. Code Walkthrough:**

* **`makeConnected(int n, int[][] connections)`:** This is the main function.
    * `if(n-1 > connections.length) return -1;`: This checks if there are enough connections to potentially connect all computers. If not, it's impossible to connect them all, so -1 is returned.
    * `parent = new int[n]; size = new int[n];`: Initializes the `parent` and `size` arrays for the Union-Find data structure.
    * `for(int i = 0; i<n; i++) { parent[i] = i; size[i] = 1; }`: Initializes each node to be its own parent (representing separate components initially) and sets each component's size to 1.
    * `int ans = n;`: Initializes the number of connected components to `n` (each node is initially its own component).
    * `for(int[] c : connections) { ... }`: Iterates through the connections.
    * `if(union(u, v)) ans--;`:  Attempts to unite the two nodes (`u` and `v`) using the `union()` function. If the union is successful (meaning they were in different components), the number of components is decremented.
    * `return ans-1;`: Returns the number of connected components minus 1.

* **`find(int u)`:** This function finds the root of the component containing node `u` using path compression.

* **`union(int a, int b)`:** This function merges the components containing nodes `a` and `b` using union-by-rank.  It returns `true` if the union was successful and `false` otherwise.


**5. Time and Space Complexity:**

* **Time Complexity:** O(Eα(E)), where E is the number of edges (connections) and α is the inverse Ackermann function.  In practice, α(E) is a very slowly growing function, essentially constant for all practical input sizes, so the time complexity can be considered O(E).  The `find()` and `union()` operations are amortized O(α(E)) each.

* **Space Complexity:** O(N), where N is the number of nodes (computers).  This is due to the space used by the `parent` and `size` arrays.

The use of path compression and union by rank significantly optimizes the Union-Find algorithm, resulting in near-linear time complexity for most practical inputs.  The space complexity is linear because we need to store the parent and size of each node.
