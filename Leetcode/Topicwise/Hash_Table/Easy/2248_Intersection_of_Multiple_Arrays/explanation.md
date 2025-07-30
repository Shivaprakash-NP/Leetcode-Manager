## Intersection of Multiple Arrays - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to find the intersection of multiple integer arrays. Given a 2D integer array `nums`, we need to return a sorted list containing only the integers that are present in *all* the arrays within `nums`.

### 2. Approach / Intuition:

The core idea is to use a frequency counting approach.  We iterate through all the arrays in `nums` and count the occurrences of each number using a hash map (HashMap in Java).  The keys of the hash map will be the numbers themselves, and the values will represent how many arrays contain that number.  After counting, we iterate through the keys of the hash map and check if a number appears in *all* the arrays.  If it does (its count equals the number of arrays), we add it to our result list. Finally, we sort the result list and return it.

This approach is chosen because:

*   **Efficiency:**  Using a hash map allows us to count the occurrences of each number efficiently (O(1) on average for `put` and `get`).
*   **Simplicity:** The logic is relatively straightforward to implement.
*   **Appropriate for Frequency Counting:** Hash maps are the ideal data structure for counting the frequency of elements.

### 3. Data Structures and Algorithms:

*   **HashMap:** Used to store the frequency of each number. The key is the number itself, and the value is the count of how many arrays contain that number.
*   **ArrayList:** Used to store the final result (the intersection).
*   **Iteration (for loops):** Used to traverse the input arrays and the hash map.
*   **`getOrDefault()` method of HashMap:** This simplifies the frequency counting logic by providing a default value (0) if a key is not yet present in the map.
*   **`Collections.sort()`:**  Used to sort the resulting list in ascending order, as required by the problem statement.

### 4. Code Walkthrough:

```java
class Solution {
    public List<Integer> intersection(int[][] nums) {
        HashMap<Integer , Integer> val = new HashMap<>();
        int n = nums.length;
        for(int[] a : nums) 
            for(int v : a) 
                val.put(v , val.getOrDefault(v , 0)+1);

        ArrayList<Integer> ans = new ArrayList<>();
        for(int v : val.keySet()) 
            if(val.get(v) == n) ans.add(v);
        Collections.sort(ans);
        return ans;
    }
}
```

1.  **`HashMap<Integer, Integer> val = new HashMap<>();`**:
    *   This line creates a new HashMap called `val`.
    *   The keys of the HashMap will be integers (the numbers from the input arrays).
    *   The values will also be integers, representing the count of how many arrays contain each number.

2.  **`int n = nums.length;`**:
    *   This line stores the number of arrays in the `nums` 2D array into the variable `n`. This is needed later to check if a number is present in *all* arrays.

3.  **`for(int[] a : nums) { ... }`**:
    *   This is the outer loop. It iterates through each array `a` within the `nums` 2D array.  This is an enhanced for loop which simplifies iterating through arrays.

4.  **`for(int v : a) { ... }`**:
    *   This is the inner loop.  It iterates through each number `v` within the current array `a`.

5.  **`val.put(v , val.getOrDefault(v , 0)+1);`**:
    *   This is the core logic for counting the frequency of each number.
    *   `val.getOrDefault(v, 0)`: This tries to retrieve the current count of the number `v` from the `val` HashMap.
        *   If `v` is already in the HashMap, it returns its current count.
        *   If `v` is not in the HashMap, it returns a default value of `0`.
    *   `+ 1`: This increments the count (either the existing count or the default `0`).
    *   `val.put(v, ...)`: This puts the number `v` and its updated count back into the `val` HashMap.

6.  **`ArrayList<Integer> ans = new ArrayList<>();`**:
    *   This creates a new ArrayList called `ans` to store the intersection numbers.

7.  **`for(int v : val.keySet()) { ... }`**:
    *   This loop iterates through all the keys (the numbers) in the `val` HashMap.

8.  **`if(val.get(v) == n) { ... }`**:
    *   This checks if the count of the number `v` (obtained using `val.get(v)`) is equal to the total number of arrays `n`.
    *   If the count is equal to `n`, it means that the number `v` is present in all the arrays.

9.  **`ans.add(v);`**:
    *   If the condition in the `if` statement is true, this line adds the number `v` to the `ans` ArrayList.

10. **`Collections.sort(ans);`**:
    *   This sorts the `ans` ArrayList in ascending order.

11. **`return ans;`**:
    *   This returns the `ans` ArrayList, which now contains the sorted list of numbers that are present in all the arrays.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N * M + K * log(K)), where N is the number of arrays in `nums`, M is the average length of each array, and K is the number of elements in the intersection.
    *   The outer loop iterates through N arrays. The inner loop iterates through the elements of each array. In the worst case, we visit all N * M elements.
    *   The loop iterating through the HashMap's keys takes O(U) time, where U is the number of unique elements across all input arrays. Since U could be at most N * M, it simplifies to O(N*M).
    *   `Collections.sort(ans)` takes O(K * log(K)) time, where K is the number of elements in the `ans` array (the intersection).
    *   Therefore, the overall time complexity is dominated by O(N * M + K * log(K)).
    *    If K is relatively small compared to N*M, we can simplify this to O(N*M).

*   **Space Complexity:** O(U), where U is the number of unique elements across all input arrays.
    *   The `val` HashMap stores the frequency of each number. In the worst case, all numbers in all arrays are unique, resulting in a space complexity of O(U) = O(N*M).
    *   The `ans` ArrayList stores the intersection, which can contain at most `U` elements, but will generally contain significantly fewer elements if the intersection is small.
