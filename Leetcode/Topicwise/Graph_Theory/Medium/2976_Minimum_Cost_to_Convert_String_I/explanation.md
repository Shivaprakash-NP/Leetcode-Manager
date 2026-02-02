### Problem Understanding

The problem "Minimum Cost to Convert String I" asks us to find the minimum total cost to transform a `source` string into a `target` string of the same length. We are given a set of allowed direct character conversions: `original[i]` can be changed to `changed[i]` with a specific `cost[i]`. These direct conversions can be chained together (e.g., 'a' -> 'b' and 'b' -> 'c' implies 'a' -> 'c' with a combined cost). Our goal is to calculate the sum of minimum costs for converting each character `source.charAt(i)` to `target.charAt(i)`. If at any point a character cannot be converted to its target counterpart, we should return -1.

### Approach / Intuition

The core idea revolves around finding the minimum cost to convert *any* character 'A' to *any* other character 'B', considering both direct and chained conversions. Once we have these minimum costs for all possible character pairs, we can simply iterate through the `source` and `target` strings, sum up the individual conversion costs, and handle the impossible conversion case.

Here's the breakdown of the intuition:

1.  **Independent Character Conversions:** The problem implies that each character in the `source` string needs to be converted independently to its corresponding character in the `target` string. The total cost is the sum of these individual minimum conversion costs.

2.  **All-Pairs Shortest Path:** The subproblem of finding the minimum cost to convert character 'A' to character 'B' (considering intermediate conversions) is a classic "All-Pairs Shortest Path" problem. We can model the 26 lowercase English letters ('a' through 'z') as nodes in a directed graph. Each given conversion `original[i]` -> `changed[i]` with `cost[i]` represents a directed edge from `original[i]` to `changed[i]` with weight `cost[i]`. If multiple direct conversions exist between the same pair of characters, we should only consider the one with the minimum cost.

3.  **Floyd-Warshall Algorithm:** Since the number of nodes (characters) is very small and fixed (26), the Floyd-Warshall algorithm is an excellent choice for solving the all-pairs shortest path problem. It's relatively simple to implement and efficient enough for such a small graph.
    *   **Initialization:** We initialize a 2D matrix, `dist[u][v]`, to store the minimum cost to convert character `u` to character `v`.
        *   `dist[u][v]` is set to a very large value (infinity) if `u != v`, indicating no known path initially.
        *   `dist[u][u]` is set to 0, as the cost to convert a character to itself is zero.
        *   Then, we populate `dist[u][v]` with the given direct conversion costs, ensuring we always take the minimum cost if multiple direct paths exist.
    *   **Relaxation:** The Floyd-Warshall algorithm then iteratively updates `dist[i][j]` by considering every possible intermediate character `k`. For each pair `(i, j)`, it checks if going from `i` to `k` and then from `k` to `j` provides a shorter path than the currently known `dist[i][j]`. That is, `dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])`.

4.  **Final Summation:** After the Floyd-Warshall algorithm completes, the `dist` matrix will contain the minimum conversion cost for every possible pair of characters. We then iterate through the `source` and `target` strings from `i = 0` to `n-1`. For each position `i`:
    *   Retrieve the minimum cost to convert `source.charAt(i)` to `target.charAt(i)` from our precomputed `dist` matrix.
    *   If this cost is still "infinity" (the large value we used), it means the conversion is impossible, and we return -1.
    *   Otherwise, add this cost to a running total.
    *   Finally, return the accumulated total cost.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `long[][] dist`: A 2D array of size 26x26. Each index `[i][j]` stores the minimum cost to convert the character corresponding to `i` (0 for 'a', 1 for 'b', etc.) to the character corresponding to `j`. `long` is used for costs to prevent potential integer overflow, as total costs can be large.

*   **Algorithms:**
    *   **Floyd-Warshall Algorithm:** This algorithm is used to compute the all-pairs shortest paths between the 26 character nodes, effectively finding the minimum conversion cost between any two characters.

### Code Walkthrough

```java
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length(); // Get the length of the input strings (source and target are of equal length)

        // Initialize a 26x26 matrix to store minimum conversion costs between characters.
        // Each index (0-25) corresponds to 'a' through 'z'.
        long[][] dist = new long[26][26];
        
        // Step 1: Initialize distances
        // Fill the matrix with a very large value (1e8, representing infinity).
        // This signifies that initially, there's no known path or an extremely expensive path.
        // We use 1e8, which is 100,000,000. Maximum possible cost for a single conversion is 10^9,
        // so a sum of 25 such conversions could be 2.5 * 10^10. 1e8 is a safe "infinity" for intermediate sums.
        for(long[] row : dist) Arrays.fill(row, (long)1e8);
        
        // The cost to convert a character to itself is 0.
        for(int i = 0; i<26; i++) dist[i][i] = 0L;

        // Step 2: Populate direct conversion costs
        // Iterate through the given direct conversion rules.
        for(int i = 0; i<original.length; i++) {
            // Convert characters ('a' through 'z') to integer indices (0 through 25).
            int u = original[i]-'a'; 
            int v = changed[i]-'a';   
            
            // Update the direct conversion cost. If there are multiple ways to convert u to v,
            // we take the minimum cost.
            dist[u][v] = Math.min(dist[u][v], (long)cost[i]);
        }
        
        // Step 3: Apply Floyd-Warshall Algorithm
        // This triple nested loop computes the all-pairs shortest paths.
        // 'k' is the intermediate node (character) that we consider for potential shorter paths.
        for(int k = 0; k<26; k++) { // Intermediate character 'k'
            for(int i = 0; i<26; i++) { // Starting character 'i'
                for(int j = 0; j<26; j++) { // Ending character 'j'
                    // Check if paths from i to k AND k to j actually exist (are not infinity).
                    // This prevents adding infinity to infinity, which could lead to negative infinity or incorrect values.
                    if(dist[i][k] != (long)1e8 && dist[k][j] != (long)1e8) {
                        // If the path from 'i' to 'j' going through 'k' (dist[i][k] + dist[k][j])
                        // is shorter than the currently known minimum path dist[i][j], update dist[i][j].
                        dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                    }
                }
            }
        }

        // Step 4: Calculate total minimum cost for string conversion
        long totalCost = 0; // Accumulator for the total cost
        // Iterate through each character position in the source and target strings.
        for(int i = 0; i<n; i++) {
            // Get the integer