### Problem Understanding

The problem "Create Components With Same Value" asks us to take a given tree (represented by `nums` for node values and `edges` for connections) and partition it into several connected components by cutting some edges. The crucial constraint is that *all* resulting components must have the *exact same sum* of node values. Our goal is to find the maximum number of edges we can cut while satisfying this condition. This is equivalent to maximizing the number of resulting components.

For example, if a tree has nodes with values `[6, 2, 2, 2, 2]` and we cut it into two components, each must sum to `(6+2+2+2+2)/2 = 7`. If we cut it into three components, each must sum to `(6+2+2+2+2)/3 = 5`.

### Approach / Intuition

The core idea behind solving this problem revolves around the properties of sums and divisibility.

1.  **Total Sum and Divisors:** Let `S` be the total sum of all node values in the original tree. If we partition the tree into `C` components, and each component has a sum `K`, then it must be true that `C * K = S`. This immediately implies that `K` must be a divisor of `S`.
    This insight allows us to significantly reduce the search space. Instead of trying arbitrary component sums, we only need to consider `K` values that are divisors of the total sum `S`.

2.  **Constraint on `K`:** For any candidate component sum `K`, it must be at least as large as the maximum individual node value (`max_val`) in the tree. If `K` were smaller than `max_val`, then any node with value `max_val` could not be part of a component summing to `K` (since all node values are positive).

3.  **Feasibility Check with DFS:** For each valid candidate `K` (i.e., `K` is a divisor of `S` and `K >= max_val`), we need to check if it's actually possible to partition the tree into components, each summing to `K`. We can do this using a Depth-First Search (DFS):
    *   Start a DFS from an arbitrary root (e.g., node 0).
    *   For each node `u`, the DFS recursively calculates the sum of values in the subtree rooted at `u` (including `u` itself), *after* any possible cuts have been made within that subtree.
    *   If the accumulated sum for a subtree reaches `K`, it means we can form a complete component with sum `K` using that subtree. We "cut" this component off from its parent, increment a global counter for components, and return `0` to the parent. Returning `0` signifies that this subtree has been successfully isolated as a component and contributes nothing further to its parent's sum.
    *   If the accumulated sum is less than `K`, it means this subtree alone doesn't form a component of sum `K`, so its sum must be passed up to its parent to continue accumulating.
    *   If the accumulated sum ever exceeds `K` (without having formed a component of `K` first), then `K` is not a feasible component sum for this configuration. However, the current DFS logic implicitly handles this: if the final `dfs(0, ...)` call returns a non-zero value, it means the entire tree couldn't be perfectly partitioned into components of sum `K`, thus `K` is not feasible.

4.  **Maximizing Cuts:** For each feasible `K`, the DFS will count the total number of components (`cnt`). If there are `C` components, we need `C-1` cuts to achieve this partitioning from a single initial tree. We want to maximize this `C-1` value, which means maximizing `C`.

By iterating through all valid divisors `K` of the total sum `S`, performing the DFS check, and keeping track of the maximum `cnt-1` found, we can determine the maximum number of cuts.

### Data Structures and Algorithms

1.  **Adjacency List (`ArrayList<Integer>[] adj`):** Used to represent the tree structure. Each index `i` in the array corresponds to node `i`, and the `ArrayList` at that index stores its neighbors. This is a standard way to represent graphs/trees.
2.  **Depth-First Search (DFS):** The core algorithm for traversing the tree to check the feasibility of a target component sum `K`. It explores as far as possible along each branch before backtracking.
3.  **Boolean Visited Array (`boolean[] vis`):** Used within the DFS to keep track of visited nodes. In a tree, this primarily prevents revisiting the parent node immediately, ensuring a proper traversal down the tree.
4.  **Divisor Iteration:** The outer loop iterates through numbers up to `sqrt(sum)` to efficiently find all divisors of `sum`. For each `i` that divides `sum`, both `i` and `sum/i` are divisors.

### Code Walkthrough

Let's break down the code section by section:

#### `cnt` field

```java
int cnt;
```
This global variable `cnt` is used to count the number of components successfully formed for a given target sum `k` during a DFS traversal. It's reset for each new `k` being tested.

#### `dfs` function

```java
private long dfs(int u, ArrayList<Integer>[] adj, int[] values, boolean[] vis, int k) {
    long sum = values[u]; // Initialize current subtree sum with the value of the current node 'u'

    // Iterate through all neighbors of 'u'
    for(int v : adj[u]) {
        if(!vis[v]) { // If neighbor 'v' has not been visited (i.e., it's a child in the DFS tree)
            vis[v] = true; // Mark 'v' as visited
            sum += dfs(v, adj, values, vis, k); // Recursively call DFS on 'v' and add its returned sum
        }
    }

    // After visiting all children and accumulating their sums:
    if(sum == k) { // If the current subtree's accumulated sum exactly matches the target 'k'
        cnt++; // Increment the global component counter
        return 0; // This subtree forms a complete component; its contribution to its parent is 0
    }

    return sum; // If sum < k, return the accumulated sum to the parent to continue building
}
```
This is the heart of the feasibility check.
*   It takes the current node `u`, the adjacency list, node values, visited array, and the target component sum `k`.
*   It starts by adding `values[u]` to its `sum`.
*   It then iterates through `u`'s neighbors. For each unvisited neighbor `v` (which means `v` is a child in the DFS traversal path), it recursively calls `dfs(v, ...)`. The returned value from `dfs(v, ...)` is added to `u`'s `sum`. If `dfs(v, ...)` returns `0`, it means `v`'s subtree formed a component of sum `k` and was "cut off".
*   After