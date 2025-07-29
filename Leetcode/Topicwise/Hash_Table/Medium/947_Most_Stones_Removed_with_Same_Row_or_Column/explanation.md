## LeetCode: Most Stones Removed with Same Row or Column - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum number of stones we can remove from a grid such that no two remaining stones share the same row or column.  Each stone is represented by its coordinates (row, column).

**2. Approach / Intuition:**

The solution employs a Union-Find algorithm to efficiently determine the number of connected components among stones.  The core idea is to treat each row and each column as a node in a graph.  If two stones share a row or column, their corresponding row and column nodes are connected.  The number of stones that can be removed is equal to the total number of stones minus the number of connected components.  By using Union-Find, we efficiently determine the connected components, avoiding redundant checks.

This approach is chosen because Union-Find excels at efficiently managing connectivity in a graph, making it well-suited for this problem's requirements.  A brute-force approach would be computationally expensive for larger inputs.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `par`: An integer array representing the parent node in the Union-Find data structure.  `par[i]` stores the parent of node `i`.
    * `size`: An integer array storing the size of each connected component (number of nodes in the subtree rooted at that node).
    * `Set<Integer>`:  A HashSet to efficiently store and check for unique connected component representatives.

* **Algorithms:**
    * **Union-Find:**  A disjoint-set data structure used to efficiently track connected components.
    * **Path Compression (in `find` method):** Optimizes Union-Find by flattening the tree structure during the search for a root node.
    * **Union by Rank (in `union` method):** Optimizes Union-Find by always attaching the smaller tree to the larger one, improving overall efficiency.


**4. Code Walkthrough:**

* **Initialization:**
    * `r` and `c` find the maximum row and column indices among the stones.
    * `par` and `size` arrays are initialized to represent the Union-Find data structure. Each node initially forms its own connected component with parent set to itself and size 1.  The size of the arrays is `r + c + 2` to account for all rows and columns (0-indexed).  We add 2 to accommodate for potential 0-indexed rows and columns.

* **Union of Rows and Columns:**
    * The code iterates through each stone `s`.
    * It maps each stone's row (`s[1]`) and column (`s[0]`) to unique indices in the `par` array.  The column index is offset by `r + 1` to avoid conflict with row indices.
    * `union(a, b)` connects the row and column nodes for the stone.

* **Finding Connected Components:**
    * A `HashSet` `set` is used to collect the unique representatives (roots) of connected components.  For each stone, `find(par[a])` and `find(par[b])` determine the root of its row and column components using path compression.

* **Calculating Removable Stones:**
    * The number of removable stones is the total number of stones minus the number of unique connected components found (size of `set`).

* **Helper Functions:**
    * `find(a)`: Implements path compression for efficient root finding in Union-Find.
    * `union(a, b)`: Implements Union-by-Rank for efficient merging of components in Union-Find.

**5. Time and Space Complexity:**

* **Time Complexity:** O(Nα(N)), where N is the number of stones, and α(N) is the inverse Ackermann function, which is practically a constant for all realistic inputs.  The Union-Find operations (find and union) with path compression and union by rank have an amortized time complexity of nearly O(1) per operation.

* **Space Complexity:** O(R + C), where R is the maximum row index and C is the maximum column index. This is due to the size of the `par` and `size` arrays, which are proportional to the number of rows and columns.  The `set` has a space complexity of O(number of connected components), which is at most O(R+C).


In summary, this solution efficiently solves the "Most Stones Removed with Same Row or Column" problem using the Union-Find algorithm with optimizations, providing a time-efficient solution for a potentially large number of stones.
