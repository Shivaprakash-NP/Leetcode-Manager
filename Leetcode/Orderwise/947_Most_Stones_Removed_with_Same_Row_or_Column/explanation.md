## LeetCode: Most Stones Removed with Same Row or Column - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the maximum number of stones we can remove from a given set of stones on a 2D plane such that no two remaining stones share the same row or column.  Essentially, we want to find the minimum number of stones that form a connected component where each stone connects to another via the same row or column.

**2. Approach / Intuition:**

This solution uses a Union-Find (Disjoint Set Union) algorithm.  The core idea is to treat each row and each column as a node in a graph. A stone at (x, y) creates an edge between the node representing row `y` and the node representing column `x`.  The goal is to find the number of connected components in this graph. Each connected component represents a set of stones that cannot be removed without violating the constraint. The number of stones removed is the total number of stones minus the number of connected components.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `par[]`: Parent array for the Union-Find data structure.  `par[i]` stores the parent node of node `i`.
    * `size[]`: Array to store the size of each connected component in the Union-Find data structure.
    * `HashSet<Integer>`:  Used to efficiently store and check for unique connected component representatives.
* **Algorithms:**
    * **Union-Find (Disjoint Set Union):**  Used to efficiently determine connected components in the graph.
    * **Path Compression (in `find()`):** Optimization to improve the efficiency of the Union-Find algorithm.
    * **Union by Rank (in `union()`):**  Optimization to improve the efficiency of the Union-Find algorithm by always attaching the smaller tree to the larger tree.


**4. Code Walkthrough:**

* **Initialization:**
    * `r` and `c` find the maximum row and column indices.
    * `par` and `size` arrays are initialized to represent the Union-Find data structure. Each node initially forms its own component (`par[i] = i`, `size[i] = 1`).  The size of the arrays is `r + c + 2` because we need to represent all rows and columns (0-indexed) and these are mapped to different indices in the union-find structure.

* **Union Operations:**
    * The code iterates through each stone `s`. It maps each stone's row index (`s[1]`) and its column index (`s[0]`) to unique indices in the `par` array using a simple mapping. It then performs a `union` operation between the row and column nodes, effectively connecting them.

* **Find Connected Components:**
    * After the union operations, we need to determine the number of connected components. It iterates through each stone again, finds the representative (root) of each row and column using `find()`, and adds it to a `HashSet`.  The `HashSet` ensures that only unique representatives are counted.

* **Result:**
    * The function returns `stones.length - set.size()`. This is because the number of connected components represents the minimum number of stones that cannot be removed. Subtracting this from the total number of stones gives the maximum number of removable stones.

* **`find(int a)`:** This function performs path compression in the Union-Find. It recursively finds the root node of `a` and updates the parent of `a` to point directly to the root.

* **`union(int a, int b)`:** This function performs a union operation in the Union-Find. It finds the root nodes of `a` and `b` using `find()`. If they are different, it merges their components by setting the parent of the smaller component to the root of the larger component.


**5. Time and Space Complexity:**

* **Time Complexity:** O(Nα(N)), where N is the number of stones, and α(N) is the inverse Ackermann function, which is practically a constant for all practical input sizes.  The Union-Find operations with path compression and union by rank have this amortized time complexity.

* **Space Complexity:** O(N), where N is the number of stones, due to the `par` and `size` arrays, which are proportional to the maximum row + column indices. The `HashSet` has a space complexity also proportional to N in the worst case.

In summary, this solution efficiently solves the problem using a well-optimized Union-Find algorithm, achieving a near-linear time complexity for most practical inputs.
