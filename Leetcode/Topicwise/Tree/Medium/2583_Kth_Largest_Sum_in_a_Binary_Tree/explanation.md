## LeetCode: Kth Largest Sum in a Binary Tree - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the kth largest sum among all levels of a binary tree.  A level's sum is the sum of all node values at that level.  If there are fewer than `k` levels, we return -1.

**2. Approach / Intuition:**

The solution uses a level-order traversal (Breadth-First Search) to calculate the sum of each level in the binary tree.  Instead of storing all level sums and then sorting them to find the kth largest, we utilize a min-heap (PriorityQueue in Java) to efficiently maintain only the k largest sums encountered so far. This significantly reduces the space complexity and improves performance compared to storing all sums.

We choose this approach because:

* **Level-order traversal** naturally processes nodes level by level, allowing us to compute level sums directly.
* **Min-heap (PriorityQueue)** ensures that we only keep track of the `k` largest sums seen.  If a new level sum is larger than the smallest sum in the heap (the root of the min-heap), we replace the smallest element with the new sum, maintaining the property that the heap always contains the `k` largest sums.


**3. Data Structures and Algorithms:**

* **Queue (LinkedList):** Used for level-order traversal (BFS) of the binary tree.  It stores nodes waiting to be processed.
* **PriorityQueue (Min-Heap):**  Used as a min-heap to efficiently store and manage the `k` largest level sums encountered so far.  It provides O(log k) time complexity for insertion and deletion of the smallest element.
* **Breadth-First Search (BFS):** The algorithm uses BFS to traverse the binary tree level by level.


**4. Code Walkthrough:**

```java
class Solution {
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q = new LinkedList<>(); // Queue for BFS
        q.offer(root); // Start with the root node
        PriorityQueue<Long> pq = new PriorityQueue<>(); // Min-heap to store k largest sums

        while(!q.isEmpty()) { // Continue until all levels are processed
            int n = q.size(); // Number of nodes at the current level
            long sum = 0; 
            for(int i = 0 ; i<n ; i++) { // Process each node at the current level
                TreeNode node = q.poll();
                sum += node.val; // Add node value to the level sum
                if(node.left != null) q.offer(node.left); // Add children to the queue
                if(node.right != null) q.offer(node.right);
            }
            pq.offer(sum); // Add the level sum to the min-heap
            while(pq.size() > k) pq.poll(); // Maintain only k largest sums in the heap
        }

        return pq.size() == k ? pq.peek() : -1; // Return kth largest sum or -1 if less than k levels
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N log k), where N is the number of nodes in the binary tree.  BFS takes O(N) time.  The operations on the min-heap (insertion and deletion) take O(log k) time each, and we perform these operations at most N times (once for each level).

* **Space Complexity:** O(k + W), where k is the value of input parameter `k` and W is the maximum width of the binary tree.  The space is dominated by the PriorityQueue which stores at most k elements, and the queue used for BFS which can hold up to W nodes in the widest level.  In the worst case (a complete binary tree), W can be proportional to N, but in many cases, W will be much smaller than N.  If k is significantly smaller than N, the space complexity is approximately O(k).


In summary, this solution efficiently finds the kth largest level sum using BFS and a min-heap, optimizing both time and space complexity compared to naive approaches.  The use of a min-heap is crucial for the efficient management of the k largest sums without needing to store and sort all level sums.
