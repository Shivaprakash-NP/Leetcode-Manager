## LeetCode: Binary Tree Zigzag Level Order Traversal - Solution Explained

**1. Problem Understanding:**

The problem asks us to traverse a binary tree level by level, but with a twist: the order of elements within each level alternates between left-to-right and right-to-left.  The result should be a list of lists, where each inner list represents a level in the tree.

**2. Approach / Intuition:**

This solution uses a Breadth-First Search (BFS) algorithm implemented with a queue.  BFS ensures that we process nodes level by level. To achieve the zigzag pattern, a boolean flag `ltor` (left-to-right) is toggled after each level.  If `ltor` is true, elements are added to the `sub` list in the order they are dequeued from the queue. If `ltor` is false, elements are added to the beginning of `sub` using `sub.addFirst()`, effectively reversing the order for that level.

This approach was chosen because BFS is inherently well-suited for level-order traversal, and the `ltor` flag elegantly handles the zigzag requirement without needing complex recursion or extra data structures.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `Queue<TreeNode> q`:  A queue to store nodes for BFS traversal.  `LinkedList` is used for efficient `add` and `poll` operations.
    * `List<List<Integer>> ans`: A list of lists to store the level-order traversal result.
    * `List<Integer> sub`: A list to temporarily store the nodes of a single level.
* **Algorithms:**
    * Breadth-First Search (BFS):  To traverse the tree level by level.
    * List manipulation: `add()` and `addFirst()` are used to control the order of elements in each level.

**4. Code Walkthrough:**

* **`List<List<Integer>> zigzagLevelOrder(TreeNode root)`:** This is the main function that performs the zigzag level order traversal.

* **`List<List<Integer>> ans = new ArrayList<>();`:** Initializes an empty list of lists to store the final result.

* **`if(root == null) return ans;`:** Handles the base case where the root is null; an empty list is returned.

* **`Queue<TreeNode> q = new LinkedList<>(); q.add(root);`:** Initializes a queue and adds the root node to it, starting the BFS.

* **`boolean ltor = true;`:** Initializes a boolean flag to true, indicating left-to-right traversal for the first level.

* **`while(!q.isEmpty()) { ... }`:** This loop continues as long as the queue is not empty.

* **`int n = q.size();`:** Gets the number of nodes in the current level. This is crucial because we want to process all nodes at the current level before moving to the next.

* **`List<Integer> sub = new ArrayList<>();`:** Creates a new list to hold the values for the current level.

* **`for(int i = 0 ; i<n ; i++) { ... }`:** This loop iterates through all nodes in the current level.

* **`if(q.peek().left != null) q.add(q.peek().left);`**  **`if(q.peek().right != null) q.add(q.peek().right);`:** Adds the left and right children (if they exist) to the queue for processing in the next level.

* **`if(ltor) sub.add(q.poll().val); else sub.addFirst(q.poll().val);`:** This is the core of the zigzag logic.  If `ltor` is true, the value of the dequeued node is added to the end of `sub`. Otherwise, it's added to the beginning, reversing the order.

* **`ans.add(sub);`:** Adds the completed level list (`sub`) to the result list (`ans`).

* **`ltor = !ltor;`:** Toggles the `ltor` flag for the next level.

* **`return ans;`:** Returns the final list of lists.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree. Each node is visited and processed exactly once.

* **Space Complexity:** O(W), where W is the maximum width of the binary tree. In the worst case (a complete binary tree), W can be equal to N, the number of nodes. This is due to the queue used in BFS, which can hold up to W nodes at a time.  The space used by the `ans` list is also proportional to N, but it doesn't dominate the space complexity.


This solution provides an efficient and concise way to solve the "Binary Tree Zigzag Level Order Traversal" problem.  The use of BFS and the `ltor` flag makes the code easy to understand and maintain.
