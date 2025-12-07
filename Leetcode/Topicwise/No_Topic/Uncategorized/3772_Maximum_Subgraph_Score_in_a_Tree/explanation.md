### Problem Understanding

The problem asks us to find, for *every* node in a given tree, the maximum possible "score" of a connected subgraph that includes that specific node. The score of a subgraph is calculated by summing `+1` for each "good" node and `-1` for each "bad" node within that subgraph. We are given `n` nodes, a list of `edges` defining the tree structure, and an array `good` where `good[i] == 1` if node `i` is good, and `good[i] == 0` if it's bad. The output should be an array `ans` where `ans[i]` is the maximum subgraph score for node `i`.

### Approach / Intuition

This problem is a classic application of **Tree Dynamic Programming (Tree DP)**, specifically using the **rerooting technique** (also known as "two-DFS" or "reparenting DP").

The core idea for finding the maximum score for a connected subgraph containing a node `u` is similar to Kadane's algorithm for maximum subarray sum. If we consider `u` as the root of some subtree, we want to include `u` itself, and then for each child `v` of `u`, we should only extend the subgraph into `v`'s subtree if the maximum score obtainable from `v`'s subtree (including `v`) is positive. If it's zero or negative, we are better off not including that part of `v`'s subtree.

However, a connected subgraph containing `u` is not restricted to `u`'s "downward" subtree. It can also extend "upwards" towards `u`'s parent and then branch out into other parts of the tree. To account for this for *all* nodes, the rerooting technique is used, which involves two Depth-First Search (DFS) passes:

1.  **First DFS (Downward Pass - `dfs` function):**
    *   This pass arbitrarily roots the tree (e.g., at node 0) and computes `dp[u]` for each node `u`.
    *   `dp[u]` stores the maximum score of a connected subgraph that *includes `u`* and is entirely contained within the subtree rooted at `u` (when considering the tree rooted at node 0).
    *   The calculation for `dp[u]` is: `(score of u) + sum(max(0, dp[v]))` for all children `v` of `u`. This ensures we only add positive contributions from children's subtrees.

2.  **Second DFS (Upward/Rerooting Pass - `dfs1` function):**
    *   The `dp[u]` values from the first DFS are only fully correct if `u` is the root of the entire tree, or if we are only interested in subgraphs extending downwards.
    *   This second DFS pass propagates information about the "upwards" path to each node. When moving from a parent `u` to its child `v`:
        *   We temporarily modify `dp[u]` to represent the maximum score of a connected subgraph including `u` but *excluding* the path into `v`'s subtree. This effectively considers `u` as the root of the tree formed by removing `v`'s subtree.
        *   Then, we use this modified `dp[u]` to update `dp[v]`. If this "upwards" path from `v` through `u` is beneficial (i.e., its score is positive), we add it to `dp[v]`.
        *   After processing `v` and its subtree, we *restore* the `dp[u]` and `dp[v]` values to their original states. This restoration is crucial because `u` might have other children, and its original `dp` value is needed for their calculations.
    *   During this pass, `ans[u]` is set to `dp[u]` after `dp[u]` has been updated to reflect contributions from both its original subtree and the path extending upwards towards its parent.

By combining these two passes, `dp[u]` (and thus `ans[u]`) will eventually hold the maximum score for a connected subgraph containing `u`, regardless of whether that subgraph extends upwards or downwards.

### Data Structures and Algorithms

1.  **`ArrayList<Integer>[] adj`**: An array of `ArrayList`s representing the adjacency list of the tree. This is used to store the graph structure efficiently for traversal.
2.  **`int[] dp`**: A dynamic programming array where `dp[i]` stores the maximum subgraph score for node `i`. Its meaning changes between the two DFS passes as described above.
3.  **`int[] good`**: An input array indicating whether each node is good (1) or bad (0).
4.  **`int[] ans`**: An array to store the final answer for each node. `ans[i]` will be the maximum subgraph score for node `i`.
5.  **Depth-First Search (DFS)**: The primary algorithm used for traversing the tree in both passes.
6.  **Tree Dynamic Programming (Tree DP)**: The overarching technique for solving problems on trees by combining results from subtrees.
7.  **Rerooting DP**: A specific tree DP technique involving two DFS passes to calculate a value for each node considering the entire tree.

### Code Walkthrough

#### `maxSubgraphScore(int n, int[][] edges, int[] good)`

This is the main public method that orchestrates the solution.

1.  **Adjacency List Initialization:**
    ```java
    ArrayList<Integer>[] adj = new ArrayList[n];
    for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
    ```
    An adjacency list `adj` is created to represent the tree. `adj[i]` will store a list of neighbors of node `i`.

2.  **Building Adjacency List:**
    ```java
    for(int[] e : edges) {
        int u = e[0];
        int v = e[1];
        adj[u].add(v);
        adj[v].add(u);
    }
    ```
    The `edges` array is iterated to populate the adjacency list. Since it's an undirected tree, each edge `(u, v)` adds `v` to `u`'s list and `u` to `v`'s list.

3.  **DP and Answer Array Initialization:**
    ```java
    int[] dp = new int[n];
    int[] ans = new int[n];