## Combination Sum III Explained

Here's a detailed explanation of the provided Java code for the LeetCode problem "Combination Sum III":

### 1. Problem Understanding

The "Combination Sum III" problem asks us to find all possible combinations of `k` distinct numbers from the range `1` to `9` (inclusive) such that the sum of these numbers equals `n`. Each combination should be a list of numbers.  The same number may not be used multiple times within a single combination.

For example:

*   `k = 3`, `n = 7`  -> `[[1, 2, 4]]`
*   `k = 3`, `n = 9`  -> `[[1, 2, 6], [1, 3, 5], [2, 3, 4]]`

### 2. Approach / Intuition

The solution employs a backtracking approach. Here's the intuition:

*   **Build combinations recursively:**  The code explores all possible combinations by recursively trying each number from 1 to 9.  At each step, we have a choice: either include the current number in our combination, or exclude it.
*   **Maintain state:** We maintain the current combination in a `list`, the current sum in `sum`, and the number of elements included so far in `cnt`.
*   **Base Cases:**
    *   If the `sum` exceeds `n` or if we have included more than `k` numbers, we backtrack (return). This prevents us from exploring branches that will never lead to a valid solution.
    *   If we've reached the end of our number range ( `i > 9` ), we check if the `sum` is equal to `n` and the `cnt` is equal to `k`. If both conditions are met, we add the current combination (`list`) to our answer `ans`.
*   **Inclusion and Exclusion:** For each number `i`, we recursively call the function twice:
    *   **Include `i`:** We add `i` to the `list`, increment the `cnt`, update the `sum`, and recursively call the function for the next number (`i+1`).
    *   **Exclude `i`:** We remove `i` from the `list` (backtracking step) and recursively call the function for the next number (`i+1`).  This effectively skips the current number.

Why backtracking?  Backtracking is a natural fit for problems involving combinations or permutations because it allows us to systematically explore all possible solutions by building them incrementally and abandoning paths that don't lead to valid results.  It efficiently prunes the search space.

### 3. Data Structures and Algorithms

*   **Data Structures:**
    *   `List<List<Integer>> ans`: Stores the final list of valid combinations.
    *   `List<Integer> list`:  Stores the current combination being built during the recursive exploration. This acts as a stack in the recursion.
*   **Algorithms:**
    *   **Backtracking:** The core algorithm used to explore the search space.
    *   **Recursion:**  Used to implement the backtracking algorithm.

### 4. Code Walkthrough

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    private void rec(int i, int cnt, int k, List<Integer> list, int sum, int n) {
        // Base Cases:

        // 1. Sum exceeds n or we've included more than k numbers: backtrack
        if(sum > n || cnt > k) return;

        // 2. Reached the end of the number range (1-9)
        if(i > 9) {
            // Check if we've found a valid combination
            if(sum == n && cnt == k)
                ans.add(new ArrayList<>(list)); // Add a *copy* of the list!
            return;
        }

        // Recursive Steps:

        // 1. Include the current number 'i'
        list.add(i); // Add 'i' to the current combination
        rec(i+1, cnt+1, k, list, sum+i, n); // Recursive call with 'i' included
        list.remove(list.size()-1); // Backtrack: remove 'i' to explore other possibilities

        // 2. Exclude the current number 'i'
        rec(i+1, cnt, k, list, sum, n); // Recursive call with 'i' excluded
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> set = new ArrayList<>(); // Initialize an empty combination list
        rec(1, 0, k, set, 0, n); // Start the recursive process from 1, with count=0, sum=0
        return ans; // Return the list of valid combinations
    }
}
```

**Explanation:**

*   **`ans`:** This `List` stores the final result – a list of lists, where each inner list is a valid combination.
*   **`rec(int i, int cnt, int k, List<Integer> list, int sum, int n)`:**  This is the recursive function:
    *   `i`:  The current number being considered (from 1 to 9).
    *   `cnt`: The number of elements currently in the combination.
    *   `k`: The desired number of elements in the combination.
    *   `list`: The current combination being built.
    *   `sum`: The current sum of the elements in the combination.
    *   `n`: The target sum.

    *   **Base Cases:** The `if` statements check for the base cases. If the sum exceeds `n` or the number of elements exceeds `k`, the function returns. If `i` exceeds 9 (we've considered all numbers), we check if `sum` equals `n` and `cnt` equals `k`. If both are true, we add a *copy* of the current `list` to `ans` using `new ArrayList<>(list)`. This is crucial; otherwise, we'd be adding references to the same list, and it would be modified during backtracking, leading to incorrect results.
    *   **Recursive Steps:**
        *   `list.add(i)`:  Includes the current number `i` in the combination.
        *   `rec(i+1, cnt+1, k, list, sum+i, n)`: Makes a recursive call to explore combinations starting from the next number (`i+1`), incrementing the count `cnt` and updating the sum `sum`.
        *   `list.remove(list.size()-1)`: Backtracks. After exploring combinations with `i` included, we remove `i` to explore combinations *without* `i`.
        *   `rec(i+1, cnt, k, list, sum, n)`: Makes a recursive call to explore combinations starting from the next number (`i+1`), *without* including `i`.

*   **`combinationSum3(int k, int n)`:**
    *   This is the main function that initializes the `ans` list and the `set` (which is actually a list called `list` in the `rec` function).
    *   It calls the recursive function `rec` to start the backtracking process.
    *   It returns the `ans` list, which contains all the valid combinations.

### 5. Time and Space Complexity

*   **Time Complexity:** The time complexity is difficult to express precisely due to the nature of backtracking.  In the worst-case scenario, we might explore a significant portion of the search space. A loose upper bound could be considered  O(C(9, k)), where C(9, k) is the binomial coefficient "9 choose k", representing the number of ways to choose k distinct numbers from the range 1 to 9. For example, if k=3, then C(9,3) = 84.  However, this does not account for the pruning that happens within the recursion when the sum exceeds 'n'.  Therefore, a more accurate assessment would be that the time complexity is *O(k * C(9, k))*, where the multiplication by `k` comes from the time it takes to copy the `list` when a valid combination is found ( `ans.add(new ArrayList<>(list));` ). Because `k` is capped at 9 and `C(9,k)` at 84, the time complexity can be considered as low.

*   **Space Complexity:**
    *   O(k): The maximum depth of the recursion is `k`. This is because we can only add at most `k` elements to the `list` (the current combination). Therefore, the space used by the call stack is O(k).
    *   O(Number of valid combinations * k):  The `ans` list stores the valid combinations. In the worst case, we might find many such combinations, each of size `k`.  So, the space used by `ans` can grow to O(number of valid combinations * k).
    * The `list` has a space complexity of O(k) as well.

In summary, the dominant factor in space complexity is typically the storage of the `ans` list, making the overall space complexity *O(k * Number of valid combinations)* in practice.
