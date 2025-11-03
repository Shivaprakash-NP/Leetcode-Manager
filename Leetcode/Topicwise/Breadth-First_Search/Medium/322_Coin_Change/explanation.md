### Problem Understanding

The "Coin Change" problem asks us to find the minimum number of coins required to make a specific `amount` using a given set of `coins` denominations. We can use each coin as many times as needed. If the `amount` cannot be formed using any combination of the given coins, we should return -1.

For example, if `coins = [1, 2, 5]` and `amount = 11`, the minimum number of coins is 3 (one 5-cent coin, one 5-cent coin, and one 1-cent coin, or one 5-cent coin and three 2-cent coins, or one 5-cent coin, one 2-cent coin, and two 2-cent coins, etc. The optimal is 5+5+1 = 11 using 3 coins, or 5+2+2+2 = 11 using 4 coins. The code will find 5+5+1).

### Approach / Intuition

The problem asks for the *minimum* number of items (coins) to reach a target state (the `amount`). This is a classic indicator for either dynamic programming or a shortest path algorithm on an unweighted graph.

We can model this problem as finding the shortest path in a graph:
*   **Nodes:** Each possible `amount` from `0` up to the target `amount`.
*   **Edges:** An edge exists from `current_amount` to `current_amount + coin_denomination` for every `coin_denomination` in the input `coins` array. Each edge has a weight of 1 (representing one coin used).

A Breadth-First Search (BFS) is the perfect algorithm for finding the shortest path in an *unweighted* graph. BFS explores the graph layer by layer: it first visits all nodes reachable in 1 step, then all nodes reachable in 2 steps, and so on. The first time BFS reaches our target `amount`, it guarantees that it has found the path with the minimum number of steps (i.e., the minimum number of coins).

The intuition is to start at `amount = 0` (which requires 0 coins). From `0`, we can reach `0 + coin1`, `0 + coin2`, etc., in 1 coin. From these new amounts, we can reach further amounts in 2 coins, and so on. We keep track of the minimum coins needed to reach each intermediate amount.

### Data Structures and Algorithms

1.  **Data Structures:**
    *   `int[] vis`: An array of size `amount + 1`. `vis[i]` stores the minimum number of coins required to form the sum `i`. It acts as a distance array in BFS, where `vis[i]` is the shortest distance (number of coins) from `0` to `i`. It also implicitly serves as a "visited" array, as any value other than `Integer.MAX_VALUE` indicates that the amount has been reached and its minimum coin count has been determined.
    *   `Queue<Integer> q`: A standard queue (implemented using `LinkedList` in Java) used for the BFS traversal. It holds the `amount` values that are currently at the "frontier" of our search, waiting to be processed.

2.  **Algorithms:**
    *   **Breadth-First Search (BFS):** The core algorithm used to explore the state space (possible amounts) layer by layer, ensuring that the first time we reach the target `amount`, it's with the minimum number of coins.

### Code Walkthrough

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 1. Initialize 'vis' array
        // vis[i] will store the minimum number of coins to make amount 'i'.
        // Initialize with Integer.MAX_VALUE to signify unreachability initially.
        int[] vis = new int[amount+1];
        Arrays.fill(vis, Integer.MAX_VALUE);

        // 2. Base case for BFS
        // 0 coins are needed to make an amount of 0. This is our starting point.
        vis[0] = 0;

        // 3. Initialize BFS queue
        Queue<Integer> q = new LinkedList<>();
        q.offer(0); // Start BFS from amount 0

        // 4. Main BFS loop
        while(!q.isEmpty()) {
            int c = q.poll(); // Dequeue the current amount to process

            // 5. Explore next possible amounts by adding each coin
            for(int v : coins) {
                // Check if adding coin 'v' is valid and potentially leads to a shorter path
                // Condition 1: c + v must not exceed the target 'amount'.
                //    v <= amount - c  is equivalent to  c + v <= amount
                // Condition 2: We found a shorter path to c + v.
                //    vis[c+v] > vis[c]+1 means the current path (vis[c]+1) is better
                //    than the previously recorded path (vis[c+v]).
                if(v <= amount-c && vis[c+v] > vis[c]+1) {
                    // If a shorter path is found:
                    q.offer(c+v); // Add the new amount to the queue for further exploration
                    vis[c+v] = vis[c]+1; // Update the minimum coins needed for this amount
                }
            }
        }

        // 6. Return result
        // If vis[amount] is still Integer.MAX_VALUE, it means the amount is unreachable.
        // Otherwise, vis[amount] holds the minimum number of coins.
        return vis[amount] == Integer.MAX_VALUE ? -1 : vis[amount];
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(amount * N)**
    *   Where `amount` is the target amount and `N` is the number of coin denominations (`coins.length`).
    *   The BFS algorithm visits each possible `amount` (from `0` to `amount`) at most once.
    *   For each `amount` `c` dequeued from the queue, we iterate through all `N` coin denominations.
    *   In the worst case, each state (amount) is processed, and for each state, we perform `N` operations.
    *   Therefore, the total time complexity is proportional to `amount * N`.

*   **Space Complexity: O(amount)**
    *   The `vis` array requires `O(amount)` space to store the minimum coin counts for each amount from `0` to `amount`.
    *   The `q` (queue) can, in the worst case, store up to `O(amount)` elements. For example, if all intermediate amounts at a certain "level" of the BFS are distinct and added to the queue before being processed.
    *   Thus, the total space complexity is dominated by the `vis` array and the queue, resulting in `O(amount)`.