## LeetCode: All Nodes Distance K in Binary Tree - Solution Explanation

**1. Problem Understanding:**

Given a binary tree `root`, a target node `target`, and an integer `k`, find all nodes in the tree that are exactly distance `k` away from the target node.  The distance is the number of edges between two nodes.

**2. Approach / Intuition:**

This solution uses a two-step Breadth-First Search (BFS) approach.  The first BFS constructs a parent-child map of the entire tree. This map is crucial because it allows us to traverse upwards (to parent nodes) when searching for nodes at distance `k` from the target.  A naive approach only using direct child links would miss nodes that are k-distance away via parent nodes.

The second BFS starts from the target node and explores nodes at increasing distances. We use a `visited` list (`vis`) to prevent cycles and ensure we don't revisit nodes. The search continues until the distance `k` is reached. All nodes encountered at distance `k` are added to the result list.  BFS is chosen because it guarantees finding nodes at the shortest distance first and naturally lends itself to level-order traversal.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `HashMap<TreeNode, TreeNode> map`:  Stores the parent-child relationships in the tree.  Keys are child nodes, values are their parent nodes.
    * `ArrayList<TreeNode> vis`: Keeps track of visited nodes to avoid cycles in the second BFS.
    * `Queue<TreeNode> q`:  Used for the BFS traversals.
    * `ArrayList<Integer> ans`: Stores the results (node values at distance k).

* **Algorithms:**
    * Breadth-First Search (BFS): Used twice – once to build the parent-child map and again to find nodes at distance k.

**4. Code Walkthrough:**

* **Lines 1-6:**  Standard TreeNode definition.

* **Lines 8-14:** This section performs the first BFS to create the `map`. It iterates through the tree level by level, adding each node and its parent to the `map`.

* **Lines 16-18:** Initialization for the second BFS. `q` is initialized with the target node, and `dis` tracks the current distance from the target.

* **Lines 20-37:**  This is the second BFS. The outer `while` loop iterates until the distance `k` is reached. The inner loop processes nodes at the current distance.  For each node, it adds its children (if not visited) and its parent (if not visited) to the queue. The `vis` list ensures that nodes are not processed multiple times.


* **Lines 21-23:** If the distance `k` is reached, all nodes currently in the queue are at distance `k`, so their values are added to the `ans` list.

* **Line 39:** The list of node values at distance `k` is returned.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Both BFS traversals visit each node at most once.  Building the `map` and the second search are both linear.

* **Space Complexity:** O(N) in the worst case. This is due to the `map` which could store all N nodes (in a skewed tree), the queue `q` which might hold all nodes at some point in BFS and the `vis` list which will contain at most all nodes.


**Improvements:**

The code is quite efficient.  One minor optimization might be to directly store node values in `vis` instead of TreeNode objects to slightly reduce memory usage,  but the asymptotic complexity remains the same.  The use of a HashMap for parent tracking is efficient for navigating both up and down the tree.
