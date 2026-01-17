### Problem Understanding

The problem "Best Sightseeing Pair" asks us to find the maximum possible "score" from selecting two distinct sightseeing spots from a given array `values`. Each element `values[k]` represents the attractiveness value of the spot at index `k`. We need to choose two indices `i` and `j` such that `i < j`. The score for such a pair `(i, j)` is defined as `values[i] + values[j] + i - j`. Our goal is to maximize this score.

For example, if `values = [8, 1, 5, 2, 6]`:
*   If we pick `i=0` and `j=2`: `values[0] + values[2] + 0 - 2 = 8 + 5 + 0 - 2 = 11`.
*   If we pick `i=0` and `j=4`: `values[0] + values[4] + 0 - 4 = 8 + 6 + 0 - 4 = 10`.
The task is to find the pair `(i, j)` that yields the highest score.

### Approach / Intuition

The key to solving this problem efficiently lies in transforming the score formula and using a single pass through the array.

The given score formula is `values[i] + values[j] + i - j`.
We can rearrange this formula as: `(values[i] + i) + (values[j] - j)`.

Let's consider iterating through the array from left to right, using the current index `j` as the right spot of our pair. For each `j`, we need to find an `i` such that `i < j` and `(values[i] + i)` is maximized. If we can maintain the maximum `(values[k] + k)` for all `k < j` as we iterate, we can easily calculate the best score ending at `j`.

Here's the intuition:
1.  We want to maximize `(values[i] + i) + (values[j] - j)`.
2.  As we move `j` (the right pointer) from left to right:
    *   For the current `j`, the term `(values[j] - j)` is fixed.
    *   To maximize the total score, we need to find the largest possible `(values[i] + i)` among all `i` that are strictly less than `j`.
3.  So, we can maintain a variable (let's call it `max_val_plus_idx`) that stores the maximum `(values[k] + k)` encountered so far for all `k` that could potentially be our `i`.
4.  When we are at index `j`:
    *   The current maximum score we can achieve with `j` as the right spot is `max_val_plus_idx + (values[j] - j)`. We update our overall maximum answer with this value.
    *   After considering `j` as the right spot, `(values[j] + j)` itself becomes a candidate for `max_val_plus_idx` for future `j`'s (i.e., for `j+1`, `j+2`, etc.). So, we update `max_val_plus_idx` to include `(values[j] + j)`.

This approach allows us to find the maximum score in a single pass, avoiding a nested loop which would be O(N^2).

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] values`: The input array.
    *   `int n`: Stores the length of the array.
    *   `int max`: Stores the maximum value of `(values[k] + k)` encountered so far.
    *   `int ans`: Stores the overall maximum score found.
*   **Algorithms:**
    *   **Single Pass Iteration:** The core of the solution is a single `for` loop that iterates through the array once. This is a common pattern for dynamic programming or greedy-like solutions where the optimal substructure can be built up efficiently.

### Code Walkthrough

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        // 'max' stores the maximum (values[k] + k) encountered for k < current_i (loop variable)
        // It's initialized with values[0] + 0, representing the first possible 'i' component.
        int max = values[0];    
        
        // 'ans' stores the overall maximum score found so far.
        // Initialized to 0. Since values[k] >= 1, and for i=0, j=1, score is values[0]+values[1]-1 >= 1+1-1=1,
        // the answer will always be at least 1, so 0 is a safe initial value.
        int