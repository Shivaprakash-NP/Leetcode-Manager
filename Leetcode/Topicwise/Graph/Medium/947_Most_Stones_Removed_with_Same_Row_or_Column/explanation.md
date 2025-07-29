## LeetCode: Most Stones Removed with Same Row or Column - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum number of stones we can remove from a given set of stones on a 2D plane such that after removing stones, no two remaining stones share the same row or column.  Each stone is represented by its coordinates (x, y).

**2. Approach / Intuition:**

The solution uses Union-Find (Disjoint Set Union) to efficiently determine the number of connected components among the stones.  The core idea is to treat each row and each column as a node in a graph.  A stone at (x, y) creates an edge between row y and column x.  If two stones share a row or a column, they belong to the same connected component.  The maximum number of stones we can remove is equal to the total number of stones minus the number of connected components.  This is because at least one stone must remain in each connected component.

This approach is chosen because Union-Find is highly efficient in determining connected components in a graph, especially when dealing with a large number of nodes and edges.  It has a near-linear time complexity for the union and find operations.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `par`: An integer array representing the parent node in the Union-Find data structure. `par[i] = i` initially indicates that node `i` is its own parent.
    * `size`: An integer array storing the size of each connected component in the Union-Find data structure.
    * `HashSet<Integer>`: Used to store the unique connected components found.

* **Algorithms:**
    * **Union-Find (Disjoint Set Union):**  Used to efficiently manage the connected components.
    * **Path Compression:** Used in the `find` function to optimize the search for the root of a connected component.
    * **Union by Rank (or Size):** Used in the `union` function to balance the tree structure of the connected components, ensuring near-constant time complexity for the find operation.


**4. Code Walkthrough:**

* **Initialization:**
    * `r` and `c` determine the maximum row and column indices.
    * `par` and `size` arrays are initialized for Union-Find with size `r+c+2` (to accommodate all rows and columns).  We add 2 to handle potential off-by-one errors.
    * Each element in `par` is initially set to itself, indicating that each node is its own component.
    * Each element in `size` is initially 1, representing the size of a singleton component.


* **Union Operation:**
    * The loop iterates through each stone `s`.
    * `a` represents the row index, and `b` represents the column index plus `r+1`, effectively mapping rows and columns to disjoint ranges within the `par` array.
    * `union(a, b)` merges the row and column components using Union-Find.


* **Finding Connected Components:**
    * The `HashSet set` stores the unique root nodes of each connected component.
    * For each stone, the root nodes of its row and column components (`find(par[a])` and `find(par[b])`) are added to the `set`.


* **Result Calculation:**
    * The number of removable stones is the total number of stones minus the number of distinct connected components found (the size of the `set`).


* **`find(int a)`:**
    * This function finds the root node of the component containing node `a` using path compression for optimization.


* **`union(int a, int b)`:**
    * This function performs the union of two components, using union by size (or rank) for efficiency.


**5. Time and Space Complexity:**

* **Time Complexity:** O(Nα(N)), where N is the number of stones, and α(N) is the inverse Ackermann function, which is practically a constant for all realistic input sizes.  The Union-Find operations (union and find) with path compression and union by size have an amortized time complexity of nearly O(1) per operation.

* **Space Complexity:** O(R + C), where R is the maximum row index and C is the maximum column index. This is the space required for the `par` and `size` arrays.  The HashSet's space complexity is also bounded by O(R+C).

In summary, this solution efficiently solves the problem using Union-Find, achieving a near-linear time complexity, making it suitable for handling a large number of stones. The space complexity is also reasonably efficient, depending linearly on the maximum row and column indices.
