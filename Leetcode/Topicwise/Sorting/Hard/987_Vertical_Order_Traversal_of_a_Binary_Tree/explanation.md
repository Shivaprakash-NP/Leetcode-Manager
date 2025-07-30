## LeetCode: Vertical Order Traversal of a Binary Tree - Solution Explained

**1. Problem Understanding:**

The problem asks us to traverse a binary tree and return a list of lists representing the nodes' values arranged vertically.  Nodes at the same vertical level are grouped together in a sublist, and the order within each sublist is determined by the node's level from top to bottom (and if there's a tie, by their value).


**2. Approach / Intuition:**

The solution uses a Depth-First Search (DFS) approach combined with a `TreeMap` to efficiently manage the vertical ordering.  A `TreeMap` is chosen because it automatically maintains sorted keys (vertical levels), making it easy to retrieve the vertically ordered nodes later.

The core logic involves a recursive helper function (`assign`) that traverses the tree. For each node, it calculates its vertical level (`i`) and level from top to bottom (`j`). It stores the node's value and its level information (`{j, node.val}`)  in the `TreeMap` using the vertical level (`i`) as the key.  Nodes at the same vertical level are added to the same ArrayList within the `TreeMap`.

Finally, the main function iterates through the `TreeMap`'s sorted keys (vertical levels), sorts the ArrayLists at each level based on the level from the top and then adds the node values to the result list.

This approach is efficient because it leverages the properties of the `TreeMap` to implicitly sort the vertical levels, and the use of DFS makes the traversal systematic and easy to implement recursively.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`:  Represents a node in the binary tree.
    * `TreeMap<Integer, ArrayList<int[]>> map`: Stores nodes organized by their vertical level (`Integer` key).  Each value is an `ArrayList` of `int[]`, where `int[0]` is level from top and `int[1]` is node's value. This allows sorting within each vertical level.
    * `ArrayList<List<Integer>> ans`: The final result list containing vertically ordered sublists.
* **Algorithms:**
    * **Depth-First Search (DFS):**  Used to recursively traverse the binary tree.
    * **Sorting:** `Collections.sort` is used to sort the nodes within each vertical level based on their level from top to bottom.


**4. Code Walkthrough:**

* **`Comparator<int[]> comp`:** Defines a custom comparator to sort `int[]` arrays (representing level from top and value) first by level from top and then by value if levels are equal.

* **`TreeMap<Integer, ArrayList<int[]>> map`:**  This `TreeMap` is the core data structure.  It stores the nodes grouped by their vertical coordinates.  The key is the horizontal coordinate (vertical level `i`), and the value is a list of nodes at that level, sorted by their level from the top (`j`).

* **`assign(TreeNode node, int i, int j)`:** This recursive function performs a depth-first traversal of the tree.
    * `if (node == null) return;`: Base case for recursion.
    * `map.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{j, node.val});`:  Adds the node's level from top (`j`) and value to the `ArrayList` associated with its vertical level (`i`). `computeIfAbsent` ensures that an `ArrayList` is created for a given vertical level if it doesn't exist yet.
    * `assign(node.left, i - 1, j + 1);`: Recursive call for the left child (moves one level left and one level down).
    * `assign(node.right, i + 1, j + 1);`: Recursive call for the right child (moves one level right and one level down).


* **`verticalTraversal(TreeNode root)`:** This function orchestrates the process.
    * `List<List<Integer>> ans = new ArrayList<>();`: Initializes the result list.
    * `assign(root, 0, 0);`: Starts the recursive traversal from the root node with initial vertical level 0 and level from top 0.
    * The loop iterates through the sorted keys (vertical levels) of the `TreeMap`.
    * `Collections.sort(map.get(v), comp);`: Sorts the nodes at each vertical level using the custom comparator.
    * A sublist is created and added to the result `ans`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the number of nodes in the tree.  The DFS traversal takes O(N) time. Sorting each level's nodes takes O(k log k) time where k is the number of nodes at that level.  In the worst case (a completely skewed tree), k could be equal to N, resulting in O(N log N) overall.  The `TreeMap` operations (insertion and retrieval) have logarithmic time complexity.

* **Space Complexity:** O(N). In the worst case (a completely skewed tree), the height of the tree is N, and the `TreeMap` may store up to N entries. The recursive call stack during DFS could also take up to O(N) space.  The result list `ans` can also have up to N elements in the worst case.


This solution provides an efficient and well-structured approach to solving the "Vertical Order Traversal of a Binary Tree" problem. The use of a `TreeMap` significantly simplifies the sorting aspect, leading to a clear and concise solution.
