### Problem Understanding

The problem "Process Restricted Friend Requests" asks us to simulate a series of friend requests among `n` people, subject to certain restrictions. We are given:

1.  `n`: The total number of people, labeled from `0` to `n-1`.
2.  `restrictions`: A list of pairs `[u, v]`, indicating that person `u` and person `v` can *never* be friends. This restriction applies not only directly but also indirectly; if `u` and `v` end up in the same connected "friend group" through a chain of friendships, the restriction is violated.
3.  `requests`: A list of pairs `[a, b]`, representing friend requests. These requests must be processed sequentially.

For each request `[a, b]`, we need to determine if `a` and `b` can become friends. If granting the request would cause any restriction to be violated (i.e., if any restricted pair `[u, v]` would end up in the same friend group), then the request cannot be granted. Otherwise, the request is granted, and `a` and `b` become friends (merging their respective friend groups if they were separate). We need to return a boolean array where `ans[i]` is `true` if `requests[i]` was granted, and `false` otherwise.

### Approach / Intuition

The core of this problem revolves around managing dynamic sets of connected components (friend groups) and checking for connectivity. This immediately points towards using the **Disjoint Set Union (DSU)** data structure, also known as Union-Find.

Here's the intuition:

1.  **Representing Friend Groups:** Each person initially belongs to their own friend group. When two people become friends, their respective friend groups merge into a single larger group. DSU is perfectly suited for this:
    *   The `find` operation efficiently determines the "representative" (root) of a person's friend group.
    *   The `union` operation efficiently merges two friend groups.
2.  **Processing Requests Sequentially:** We iterate through the `requests` array. For each request `(a, b)`:
    *   **Check Existing Friendship:** First, we find the representatives of `a`'s group and `b`'s group. If they are already the same, it means `a` and `b` are already friends (directly or indirectly), so the request can be granted without any change or potential violation.
    *   **Hypothetical Merge and Restriction Check:** If `a` and `b` are not yet friends, granting their request would merge their current friend groups. Before actually performing this merge, we must check if this *hypothetical* merge would violate any restriction.
        *   For every restriction `[u, v]`, we find the current representatives of `u`'s group and `v`'s group.
        *   If `u` is currently in `a`'s group (i.e., `find(u)` is the same as `find(a)`) AND `v` is currently in `b`'s group (i.e., `find(v)` is the same as `find(b)`), then merging `a`'s and `b`'s groups would effectively connect `u` and `v`. This violates the restriction `[u, v]`, so the request `(a, b)` cannot be granted.
        *   The same logic applies if `u` is in `b`'s group and `v` is in `a`'s group.
    *   **Perform Actual Merge:** If the hypothetical merge passes all restriction checks, then the request `(a, b)` can be granted. We then perform the `union` operation to merge `a`'s and `b`'s friend groups.
3.  **Why DSU?** DSU provides amortized near-constant time complexity for `find` and `union` operations (when optimized with path compression and union by size/rank), making it highly efficient for problems involving dynamic connectivity. The ability to quickly determine if two elements are in the same set and to merge sets is exactly what's needed here.

### Data Structures and Algorithms

*   **Algorithm:** **Disjoint Set Union (DSU)**, also known as Union-Find.
    *   It uses **path compression** in the `find` operation to flatten the tree structure, making subsequent `find` calls faster.
    *   It uses **union by size** (or rank) in the `union` operation to attach the smaller tree under the root of the larger tree, keeping the trees relatively flat and minimizing their height.
*   **Data Structures:**
    *   `parent[]`: An integer array where `parent[i]` stores the parent of element `i`. If `parent[i] == i`, then `i` is the root (representative) of its set.
    *   `size[]`: An integer array where `size[i]` stores the number of elements in the set if `i` is the root of that set. Used for the union by size optimization.
    *   `boolean[] ans`: A boolean array to store the result for each friend request.

### Code Walkthrough

Let's go through the provided Java code section by section:

```java
class Solution {
    int[] parent; // Stores the parent of each element
    int[] size;   // Stores the size of the set rooted at the index (if it's a root)

    // Initializes the DSU structure for n people
    private void init(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i = 0; i<n; i++) {
            parent[i] = i; // Each person is initially their own parent (root of their own set)
            size[i] = 1;   // Each set initially has one element
        }
    }

    // Finds the representative (root) of the set containing element 'a'
    // Implements path compression
    private int find(int a) {
        if(parent[a] != a) { // If 'a' is not its own parent, it's not the root
            parent[a] = find(parent[a]); // Recursively find the root and compress the path
        }
        return parent[a]; // Return the root
    }

    // Attempts to union (merge) the sets containing 'a' and 'b'.
    // Checks for restrictions before performing the actual union.
    private boolean union(int a, int b, int[][] restrictions) {
        // Find the roots of the sets containing 'a' and 'b'
        a = find(a);
        b = find(b);

        // If 'a' and 'b' are already in the same set, no new connection is formed.
        // The request is valid, and nothing needs to be done.
        if(a == b) return true;

        // Before merging, check if this union would violate any restriction
        for(int[] r : restrictions) {
            int u = find(r[0]); // Root of the first person in the restriction
            int v = find(r[1]); // Root of the second person in the restriction

            // Check if merging 'a' and 'b' would connect 'u' and 'v'
            // This happens if 'u' is in 'a's group and 'v' is in 'b's group, OR
            // if 'u' is in 'b's group and 'v' is in 'a's group.
            if((a == u && b == v) || (a == v && b == u)) {
                return false; // Restriction violated, cannot grant request
            }
        }

        // If no restrictions are violated, perform the union (merge) operation
        // Uses union by size optimization: attach smaller tree to root of larger tree
        if(size[a] > size[b]) {
            parent[b] = a;    // 'a' becomes parent of 'b'
            size[a] += size[b]; // Update size of 'a's set
        } else {
            parent[a] = b;    // 'b' becomes parent of 'a'
            size[b] += size[a]; // Update size of 'b's set
        }

        return