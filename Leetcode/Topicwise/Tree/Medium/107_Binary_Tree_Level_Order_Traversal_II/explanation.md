## LeetCode: Binary Tree Level Order Traversal II - Solution Explanation

**1. Problem Understanding:**

The problem asks us to perform a level-order traversal of a binary tree, but with a twist: the result should be a list of lists, where the innermost lists represent levels of the tree, ordered from bottom (leaves) to top (root).  Essentially, we need to reverse the standard level-order traversal output.


**2. Approach / Intuition:**

The most efficient approach to solve this problem is using Breadth-First Search (BFS) with a queue.  BFS naturally processes nodes level by level. To achieve the bottom-up order, we use a `LinkedList` which provides efficient `addFirst()` operation for inserting levels at the beginning of the result list.  This avoids the need for reversing the list at the end, improving efficiency.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `Queue<TreeNode>`:  A queue is used to implement BFS, storing nodes to be visited level by level.  `LinkedList` is used here as it offers efficient `offer` (enqueue) and `poll` (dequeue) operations.
    * `LinkedList<List<Integer>> ans`: A linked list is used to store the levels of the tree, allowing for efficient insertion at the beginning (`addFirst()`). This directly generates the bottom-up order.
    * `ArrayList<Integer> val`: An ArrayList stores the node values for each level.  ArrayList is chosen for its efficient element addition (`add()`).

* **Algorithms:**
    * Breadth-First Search (BFS): A graph traversal algorithm that explores nodes level by level.


**4. Code Walkthrough:**

* **`levelOrderBottom(TreeNode root)`:** This is the main function that performs the level-order traversal.
* **`LinkedList<List<Integer>> ans = new LinkedList<>();`:** Initializes an empty linked list to store the results (levels of the tree).
* **`if (root == null) return ans;`:** Handles the base case where the root is null (empty tree).
* **`Queue<TreeNode> q = new LinkedList<>();`:** Creates a queue to store nodes for BFS.
* **`q.offer(root);`:** Adds the root node to the queue to start the traversal.
* **`while(!q.isEmpty())`:** The main loop continues as long as there are nodes in the queue.
* **`int n = q.size();`:** Gets the number of nodes at the current level. This is crucial because we process all nodes at the same level before moving to the next.
* **`List<Integer> val = new ArrayList<>();`:** Creates a new list to store the values of nodes at the current level.
* **`for(int i = 0 ; i<n ; i++)`:** Iterates through all nodes at the current level.
* **`TreeNode node = q.poll();`:** Removes and gets the next node from the queue.
* **`val.add(node.val);`:** Adds the node's value to the current level's list.
* **`if(node.left != null) q.offer(node.left);`:** Adds the left child to the queue if it exists.
* **`if(node.right != null) q.offer(node.right);`:** Adds the right child to the queue if it exists.
* **`ans.addFirst(val);`:** Adds the current level's list of values to the *beginning* of the result list, ensuring bottom-up order.
* **`return ans;`:** Returns the final list of lists.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Each node is visited and processed exactly once.
* **Space Complexity:** O(W), where W is the maximum width (number of nodes) of the tree.  In the worst case (a complete binary tree), W can be proportional to N, leading to O(N) space complexity. The queue holds nodes from at most one level at a time, and the `ans` list stores all levels, whose total size in the worst case is proportional to the number of nodes in the tree.


This solution efficiently uses BFS and a `LinkedList` to achieve the desired bottom-up level order traversal without needing a separate reversal step, making it optimal for this problem.
