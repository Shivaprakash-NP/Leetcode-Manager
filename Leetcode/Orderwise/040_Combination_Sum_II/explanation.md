```markdown
## Combination Sum II - Problem Explanation and Solution

### 1. Problem Understanding:

The "Combination Sum II" problem asks us to find all unique combinations of numbers from a given array `candidates` that sum up to a specific `target` value.  Each number in `candidates` can only be used once in each combination.  Crucially, the output should not contain duplicate combinations.

### 2. Approach / Intuition:

The core idea is to use a backtracking algorithm to explore all possible combinations.  Here's the breakdown of the chosen approach:

*   **Backtracking:** We recursively explore the potential combinations.  At each step, we either include the current number or exclude it.
*   **Sorting:** Sorting the `candidates` array is crucial for efficiently handling duplicate numbers and avoiding duplicate combinations in the result.
*   **Skipping Duplicates:** After sorting, we can skip duplicate numbers at each level of the recursion. This ensures that we only consider unique combinations.  The `if (i > ind && arr[i] == arr[i - 1]) continue;` condition is key to achieving this.  `i > ind` ensures we are not at the starting index of the current recursion level, and `arr[i] == arr[i-1]` checks if the current element is a duplicate of the previous one *at the same level of recursion*.
*   **HashSet to Remove Duplicates:** The problem requires the removal of any duplicate combinations. To achieve this, a `HashSet` called `ans` is used to store the combinations. The `HashSet` automatically handles the uniqueness.

Why this approach? Backtracking allows us to systematically explore all possible combinations. Sorting enables us to handle duplicates effectively and efficiently. The `HashSet` guarantees that we only store unique combinations.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `List<Integer>`: Used to store the current combination being explored (represented by `ds`).
    *   `List<List<Integer>>`: Represents the final result, a list of all valid combinations.
    *   `HashSet<List<Integer>>`: Used to store the unique combinations encountered during backtracking. This is crucial for avoiding duplicate combinations in the final result.
*   **Algorithms:**
    *   **Backtracking:** The core algorithm to explore all possible combinations recursively.
    *   **Sorting:** Used to handle duplicate numbers and ensure that combinations are generated in a consistent order.

### 4. Code Walkthrough:

```java
class Solution {
    private Set<List<Integer>> ans; // Stores the unique combinations. Using HashSet to avoid duplicates.

    private void find(int ind , int t , int[] arr , List<Integer> ds)
    {
        if(t == 0)
        {
            ans.add(new ArrayList<>(ds)); // If the target is reached, add a copy of the current combination to the result.
            return;
        }

        for(int i = ind ; i<arr.length ; i++)
        {
            if(i>ind && arr[i]==arr[i-1]) continue; // Skip duplicate numbers at the same level of recursion.
            else
            {
                if(arr[i]<=t)
                {
                    ds.add(arr[i]); // Include the current number in the combination.
                    find(i+1 , t-arr[i] , arr , ds); // Recursive call to explore further combinations with the current number included.
                    ds.remove(ds.size() - 1); // Backtrack: remove the current number from the combination to explore other possibilities.
                }
                else break; // If the current number is greater than the remaining target, no need to explore further in this branch (since the array is sorted).
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new HashSet<>(); // Initialize the set to store combinations.
        Arrays.sort(candidates); // Sort the input array to handle duplicates efficiently.
        find(0 , target , candidates , new ArrayList<>()); // Start the backtracking process.
        return new ArrayList<>(ans); // Convert the set of combinations to a list and return.
    }
}
```

*   **`ans` (HashSet):**  A `HashSet` to store the results and automatically remove duplicate combinations.
*   **`find(int ind, int t, int[] arr, List<Integer> ds)` (Recursive function):**
    *   `ind`: The starting index in the `candidates` array for the current level of recursion.
    *   `t`: The remaining target value.
    *   `arr`: The `candidates` array.
    *   `ds`: The current combination (a `List<Integer>`).
    *   **Base Case:** `if (t == 0)`: If the target `t` becomes 0, it means we've found a valid combination.  We add a *copy* of the current combination `ds` to the `ans` set (using `new ArrayList<>(ds)` to avoid modifying the original `ds`).
    *   **Loop:** The `for` loop iterates through the `candidates` array from the current index `ind`.
        *   **Duplicate Check:** `if (i > ind && arr[i] == arr[i - 1]) continue;`: This is the crucial part for handling duplicates. It checks if the current number is the same as the previous number *at the same recursion level*. If it is, we skip it to avoid generating duplicate combinations. `i > ind` is important to ensure that we don't skip the first element in the sorted list.
        *   **Target Check:** `if (arr[i] <= t)`: If the current number is less than or equal to the remaining target, we include it in the combination.
        *   **Include:** `ds.add(arr[i]);`: Add the current number to the combination `ds`.
        *   **Recurse:** `find(i + 1, t - arr[i], arr, ds);`: Make a recursive call to explore combinations with the current number included.  Notice `i+1` is used here, indicating that each number can only be used *once*.
        *   **Backtrack:** `ds.remove(ds.size() - 1);`: Backtrack by removing the last added number to explore other possibilities.  This is essential for exploring different combinations.
        *   **Optimization:** `else break;`: If the current number is greater than the remaining target, we can break the loop because the array is sorted, and all subsequent numbers will also be greater than the target.
*   **`combinationSum2(int[] candidates, int target)` (Main function):**
    *   Initializes the `ans` set.
    *   Sorts the `candidates` array.
    *   Calls the `find` function to start the backtracking process.
    *   Returns the `ans` as `List<List<Integer>>`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(2<sup>n</sup>) in the worst case, where n is the number of candidates. This is because, in the worst case, we might explore all possible subsets of the candidates array. However, due to the duplicate handling and sorting, the actual runtime can be significantly lower. The sorting takes O(n log n) time. Adding elements to the `HashSet` takes O(K) where K is the average length of the list being added.
*   **Space Complexity:** O(K * M) + O(N), where:
    *   K is the average length of a combination.
    *   M is the number of unique combinations.
    *   N is the depth of the recursion tree.

    The space complexity is primarily dominated by the space used to store the `ans` (the list of combinations), which is O(K * M).  The recursion stack can grow up to O(N) where N is the number of elements in `candidates` (in the worst-case scenario).
```