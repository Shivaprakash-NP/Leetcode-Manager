# Amount of Time for Binary Tree to Be Infected - LeetCode Solution Explained

## 1. Problem Understanding

The problem asks us to determine the number of minutes it takes for an infection to spread throughout a binary tree.  The infection starts at a specified node (`start`) and spreads to its neighbors (children and parent) in each minute.  A node, once infected, cannot be re-infected.

## 2. Approach / Intuition

The solution uses a Breadth-First Search (BFS) algorithm.  It first performs a BFS traversal to find the starting node and build a parent map. This map is crucial because it allows us to efficiently access the parent of any node during the infection spread simulation.

The second BFS simulates the infection spread. We start at the `startNode`, mark it as infected (by setting its value to -1), and add its neighbors (children and parent from the `parentMap`) to the queue. In each iteration of the outer `while` loop (representing a minute), we process all nodes currently in the queue, marking them as infected and adding their uninfected neighbors to the queue. The number of minutes it takes to infect all nodes is then returned.

This approach is chosen because BFS guarantees that we infect nodes in the order of their shortest distance from the starting node, accurately reflecting the minute-by-minute spread.


## 3. Data Structures and Algorithms

* **Data Structures:**
    * `Queue<TreeNode> q`: A queue is used to implement the BFS traversal.  `LinkedList` is used for efficient enqueue and dequeue operations.
    * `Map<TreeNode, TreeNode> parentMap`: A hash map stores the parent-child relationships in the tree.  This allows for efficient lookup of a node's parent during the infection spread simulation.
* **Algorithms:**
    * **Breadth-First Search (BFS):**  Used twice: once to find the starting node and create the parent map, and again to simulate the infection spread.


## 4. Code Walkthrough

**Part 1: Finding Start Node and Building Parent Map**

```java
Queue<TreeNode> q = new LinkedList<>();
Map<TreeNode, TreeNode> parentMap = new HashMap<>();
TreeNode startNode = null;

q.offer(root);
while (!q.isEmpty()) {
    TreeNode node = q.poll();
    if (node.val == start) startNode = node;
    if (node.left != null) {
        parentMap.put(node.left, node);
        q.offer(node.left);
    }
    if (node.right != null) {
        parentMap.put(node.right, node);
        q.offer(node.right);
    }
}
```

This part performs a standard BFS traversal of the tree.  It adds each node to the queue, finds the `startNode`, and populates the `parentMap` with parent-child relationships.

**Part 2: Simulating Infection Spread**

```java
q.offer(startNode);
int minutes = -1; 

while (!q.isEmpty()) {
    int size = q.size();
    for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        node.val = -1; 

        if (node.left != null && node.left.val != -1)
            q.offer(node.left);

        if (node.right != null && node.right.val != -1)
            q.offer(node.right);

        TreeNode parent = parentMap.get(node);
        if (parent != null && parent.val != -1)
            q.offer(parent);
    }
    minutes++;
}

return minutes;
```

This part simulates the infection spread using BFS. The outer `while` loop iterates through each minute. The inner `for` loop processes all nodes ready to be infected in that minute.  `node.val = -1;` marks a node as infected.  The code then adds uninfected neighbors (children and parent) to the queue.  `minutes` keeps track of the elapsed time.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Both BFS traversals visit each node at most once.
* **Space Complexity:** O(N) in the worst case, due to the queue and the `parentMap`. In a skewed tree, the queue could hold almost all nodes at once.  The `parentMap` also stores N-1 entries (excluding the root).

The space complexity could be improved slightly by not explicitly storing the parent map; however, this would require more complex logic during the infection spread simulation, potentially negating any time complexity gain.  The current solution provides a clear and efficient balance between readability and performance.
