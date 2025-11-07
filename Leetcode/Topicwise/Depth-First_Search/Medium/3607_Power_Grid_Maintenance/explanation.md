### Problem Understanding

The problem "Power Grid Maintenance" asks us to manage a power grid consisting of `c` cities, numbered from 1 to `c`. We are given an initial set of `connections` representing power lines between cities. These connections establish connected components, meaning power can flow between any two cities within the same component.

We then need to process a series of `queries`. Each query is of one of two types:

1.  **Type 2 (Offline):** A specific city `u` goes offline, meaning it can no longer supply power or receive it.
2.  **Type 1 (Find Supplier):** For a given city `u`, we need to find a city that can supply power to it.
    *   If city `u` itself is online, it can supply power to itself.
    *   If city `u` is offline, we must find the smallest-indexed city `v` that is currently online and belongs to the same connected component as `u`.
    *   If no such online city `v` exists in `u`'s component, we should return -1.

We need to collect the results of all Type 1 queries and return them as an array.

### Approach / Intuition

The core of this problem revolves around two main concepts: connected components and efficiently finding the smallest online element within a component.

1.  **Connected Components:** The initial `connections` define groups of cities that are interconnected. The Disjoint Set Union (DSU) data structure is perfectly suited for managing these connected components. It allows us to efficiently `union` two cities (connecting their components) and `find` the representative (root) of a city's component. The DSU structure itself remains static after processing initial connections, as no new connections are added or existing ones removed.

2.  **Tracking Online Cities and Finding Smallest:**
    *   We need a way to know which cities are currently online. A simple boolean array `online[c+1]` can track this status.
    *   When a Type 1 query asks for a supplier for an offline city `u`, we need the smallest-indexed *online* city in `u`'s component. A `TreeSet` is an excellent choice for this. It stores elements in sorted order and provides `first()` to get the smallest element and `remove()` to efficiently take elements out.
    *   Since we need to do this for *each* connected component, we can use a `HashMap` to map the root of each component (an integer) to its corresponding `TreeSet` of online cities.

**High-Level Strategy:**

1.  **Initialize DSU:** Set up `parent` and `size` arrays for `c` cities, where each city is initially its own component.
2.  **Process Initial Connections:** Use the DSU's `union` operation to merge components based on the given `connections`.
3.  **Populate Component-to-OnlineCities Map:** After all initial connections are processed, iterate through all cities. For each city, find its component root using `find()`. Then, add the city to the `TreeSet` associated with its root in our `HashMap`. Initially, all cities are considered online.
4.  **Process Queries:**
    *   For a Type 2 query (`opt=2`, city `u` goes offline):
        *   Remove `u` from the `TreeSet` corresponding to its component root.
        *   Update `online[u]` to `false`.
    *   For a Type 1 query (`opt=1`, find supplier for `u`):
        *   Check `online[u]`. If `u` is online, it's the supplier.
        *   If `u` is offline, retrieve the `TreeSet` for `u`'s component.
        *   If the `TreeSet` is empty, no online cities exist in the component, so return -1.
        *   Otherwise, return the `first()` element of the `TreeSet` (which is the smallest-indexed online city).

### Data Structures and Algorithms

1.  **Disjoint Set Union (DSU) / Union-Find:**
    *   `int[] parent`: An array where `parent[i]` stores the parent of element `i`. If `parent[i] == i`, then `i` is the representative (root) of its set.
    *   `int[] size`: An array where `size[i]` stores the size of the set if `i` is the root of that set. Used for the "union by size" optimization.
    *   `find(int a)`: Implemented with **path compression** for efficient root finding.
    *   `union(int a, int b)`: Implemented with **union by size** for efficient merging of sets, keeping the trees flat.
2.  **HashMap (`Map<Integer, TreeSet<Integer>> map`):**
    *   Key: An integer representing the root of a connected component.
    *   Value: A `TreeSet<Integer>` containing all cities currently online within that component, stored in ascending order.
3.  **TreeSet (`TreeSet<Integer>`):**
    *   Used within the `HashMap` values. Provides `O(log K)` time complexity for `add`, `remove`, and `first()` operations, where `K` is the number of elements in the set.
4.  **Boolean Array (`boolean[] online`):**
    *   `online[i]` is `true` if city `i` is online, `false` otherwise. Provides `O(1)` lookup and update.
5.  **ArrayList (`List<Integer> ans`):**
    *   Used to store the results of Type 1 queries before converting to an `int[]`.

### Code Walkthrough

```java
class Solution {
    int[] parent; // DSU parent array
    int[] size;   // DSU size array (for union by size optimization)
    
    // Finds the representative (root) of the set containing element 'a'
    private int find(int a) {
        // Path compression: If 'a' is not its own parent,
        // set its parent directly to the root of its current parent.
        if(parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    // Unites the sets containing elements 'a' and 'b'
    private void union(int a, int b) {
