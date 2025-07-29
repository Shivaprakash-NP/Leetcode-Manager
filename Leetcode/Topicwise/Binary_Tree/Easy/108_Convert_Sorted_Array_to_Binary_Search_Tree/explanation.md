## LeetCode: Convert Sorted Array to Binary Search Tree - Solution Explained

**1. Problem Understanding:**

The problem asks us to convert a sorted integer array into a balanced Binary Search Tree (BST).  A BST is a tree where the value of each node is greater than all values in its left subtree and less than all values in its right subtree.  The goal is to create a reasonably balanced tree to avoid skewed trees which could lead to poor performance in BST operations.


**2. Approach / Intuition:**

The solution uses a recursive Depth-First Search (DFS) approach. The core idea is to efficiently find the middle element of the sorted array, use it as the root of the BST, and recursively build the left and right subtrees using the left and right halves of the array, respectively.  This approach is chosen because it naturally leads to a balanced (or close to balanced) BST. Picking the middle element ensures that we distribute nodes relatively evenly across the left and right subtrees, preventing skewed trees.  Other approaches might lead to unbalanced trees depending on the input data.


**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure is a `TreeNode` which represents a node in the binary search tree. The input is a sorted integer array.
* **Algorithms:** The core algorithm is recursion (DFS).  `Arrays.copyOfRange()` is used to create subarrays efficiently.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, containing a value (`val`), a left child (`left`), and a right child (`right`).

* **`dfs(int[] arr)` function:** This is a recursive helper function that does the actual work of constructing the BST.

    * **`int n = arr.length;`**: Gets the length of the input array.
    * **`if(0 == n) return null;`**: Base case: If the array is empty, return `null` (no tree).
    * **`if(1 == n) return new TreeNode(arr[0]);`**: Base case: If the array has one element, create a single-node tree with that element.
    * **`int m = n/2;`**: Finds the middle index of the array. Integer division ensures that the middle element is chosen correctly even for odd-length arrays.
    * **`TreeNode node = new TreeNode(arr[m]);`**: Creates a new tree node using the middle element as its value. This will be the root of the subtree.
    * **`node.left = dfs(Arrays.copyOfRange(arr , 0 , m));`**: Recursively calls `dfs` on the left half of the array (from index 0 to `m-1`) to build the left subtree.  `Arrays.copyOfRange` creates a new array efficiently.
    * **`node.right = dfs(Arrays.copyOfRange(arr , m+1 , n));`**: Recursively calls `dfs` on the right half of the array (from index `m+1` to `n-1`) to build the right subtree.
    * **`return node;`**: Returns the newly created node (root of the current subtree).

* **`sortedArrayToBST(int[] nums)` function:** This is the main function that calls the recursive `dfs` function with the input array.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the input array.  This is because each element in the array is visited and processed exactly once during the recursive calls.  The creation of subarrays using `Arrays.copyOfRange` takes linear time in the worst case, but this is dominated by the recursive calls.

* **Space Complexity:** O(N) in the worst case.  This is due to the recursive call stack. In the worst case (a completely unbalanced tree), the depth of the recursion can be N.  However, due to our approach which aims for balanced tree, the space complexity is typically O(log N) for a balanced tree, but O(N) in the worst-case scenario of an unbalanced tree (though our algorithm tries to avoid this).  Additionally, the `Arrays.copyOfRange` method uses extra space proportional to the size of the subarrays created during each recursive call. This contributes to the overall space complexity.  Therefore, while practically O(log N) due to the balanced tree construction, the worst-case theoretical space complexity remains O(N).
