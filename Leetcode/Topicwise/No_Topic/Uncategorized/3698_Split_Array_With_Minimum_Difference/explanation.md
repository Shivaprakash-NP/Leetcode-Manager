## LeetCode Problem: Split Array With Minimum Difference - Explanation

### 1. Problem Understanding:

The problem asks us to split an array `nums` into two non-empty subarrays at some index `i` (0 <= i < n-1). The goal is to find the split that minimizes the absolute difference between the sums of the two subarrays. However, there's a constraint: the first subarray (from index 0 to i) must be strictly increasing, and the second subarray (from index i+1 to n-1) must be strictly decreasing. If no such split exists satisfying these conditions, the function should return -1.

### 2. Approach / Intuition:

The core idea is to iterate through all possible split points of the array and check if the increasing/decreasing conditions are met for the corresponding subarrays. If a split satisfies the conditions, we calculate the absolute difference of the subarray sums and keep track of the minimum difference encountered so far.

The approach leverages dynamic programming principles to efficiently check if the increasing/decreasing conditions hold for each subarray. Instead of recomputing these conditions for every split, we precompute and store them in `inc` and `dec` arrays.

Specifically, the `inc[i]` boolean indicates whether the subarray from 0 to `i` is strictly increasing, and `dec[i]` boolean indicates whether the subarray from `i` to n-1 is strictly decreasing.

By precomputing these arrays, we can check if a given split at index `i` is valid by simply checking `inc[i]` and `dec[i+1]` in O(1) time.

Why this approach?  It avoids redundant calculations and makes checking the increasing/decreasing condition for each possible split very efficient.

### 3. Data Structures and Algorithms:

*   **Arrays:** `nums` (input array), `inc` (boolean array storing increasing subarray information), `dec` (boolean array storing decreasing subarray information).
*   **Dynamic Programming (Prefix Computation):** Used to precompute and store the increasing/decreasing subarray information efficiently.

### 4. Code Walkthrough:

```java
class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;
        boolean[] inc = new boolean[n];
        boolean[] dec = new boolean[n];
        long tot = nums[0];
        inc[0] = true;
        dec[n-1] = true;
        for(int i = 1; i<n; i++) {
            inc[i] = (inc[i-1]&&nums[i-1]<nums[i]);
            tot+=nums[i];
        }

        for(int i = n-2; i>=0; i--) dec[i] = (dec[i+1]&&nums[i+1]<nums[i]);

        long sum = 0;
        long ans = Long.MAX_VALUE;
        for(int i = 0; i<n-1; i++) {
            sum+=nums[i];
            if(inc[i]&&dec[i+1]) ans = Math.min(ans, Math.abs(tot-2*sum));
        }

        return (ans == Long.MAX_VALUE)?-1:ans;
    }
}
```

1.  **Initialization:**
    *   `n = nums.length;`: Stores the length of the input array.
    *   `inc = new boolean[n];`: Creates a boolean array `inc` of size `n`.  `inc[i]` will be true if the subarray `nums[0...i]` is strictly increasing.
    *   `dec = new boolean[n];`: Creates a boolean array `dec` of size `n`. `dec[i]` will be true if the subarray `nums[i...n-1]` is strictly decreasing.
    *   `tot = nums[0];`: Initializes `tot` with the first element of `nums`.  `tot` will store the total sum of all elements in `nums`.
    *   `inc[0] = true;`: The first element is always considered increasing.
    *   `dec[n-1] = true;`: The last element is always considered decreasing.

2.  **Compute `inc` and `tot`:**
    ```java
    for(int i = 1; i<n; i++) {
        inc[i] = (inc[i-1]&&nums[i-1]<nums[i]);
        tot+=nums[i];
    }
    ```
    *   This loop iterates from `i = 1` to `n-1` to populate the `inc` array and compute the total sum.
    *   `inc[i] = (inc[i-1]&&nums[i-1]<nums[i]);`:  The current subarray `nums[0...i]` is increasing only if the previous subarray `nums[0...i-1]` was increasing (`inc[i-1]`) and the current element `nums[i]` is greater than the previous element `nums[i-1]`.
    *   `tot+=nums[i];`: Adds the current element to the total sum.

3.  **Compute `dec`:**
    ```java
    for(int i = n-2; i>=0; i--) dec[i] = (dec[i+1]&&nums[i+1]<nums[i]);
    ```
    *   This loop iterates from `i = n-2` down to `0` to populate the `dec` array.
    *   `dec[i] = (dec[i+1]&&nums[i+1]<nums[i]);`: The current subarray `nums[i...n-1]` is decreasing only if the next subarray `nums[i+1...n-1]` was decreasing (`dec[i+1]`) and the next element `nums[i+1]` is smaller than the current element `nums[i]`.

4.  **Find Minimum Difference:**
    ```java
    long sum = 0;
    long ans = Long.MAX_VALUE;
    for(int i = 0; i<n-1; i++) {
        sum+=nums[i];
        if(inc[i]&&dec[i+1]) ans = Math.min(ans, Math.abs(tot-2*sum));
    }
    ```
    *   `sum = 0;`: Initializes `sum` to 0. This will store the sum of the first subarray `nums[0...i]`.
    *   `ans = Long.MAX_VALUE;`: Initializes `ans` to `Long.MAX_VALUE`. This will store the minimum absolute difference found so far.
    *   The loop iterates from `i = 0` to `n-2`, representing each possible split point.
    *   `sum+=nums[i];`: Updates `sum` by adding the current element `nums[i]`.
    *   `if(inc[i]&&dec[i+1]) ans = Math.min(ans, Math.abs(tot-2*sum));`: This is the crucial part.
        *   It checks if the split at index `i` is valid by examining the `inc` and `dec` arrays. If `inc[i]` is true (the first subarray is increasing) and `dec[i+1]` is true (the second subarray is decreasing), then the split is valid.
        *   If the split is valid, it calculates the absolute difference between the sums of the two subarrays: `abs(tot - 2*sum)`.
            *   `tot` is the total sum of the entire array.
            *   `sum` is the sum of the first subarray.
            *   `tot - sum` is the sum of the second subarray.
            *   Therefore, `abs(sum - (tot - sum)) = abs(tot - 2*sum)`.
        *   It updates `ans` with the minimum difference encountered so far.

5.  **Return Result:**
    ```java
    return (ans == Long.MAX_VALUE)?-1:ans;
    ```
    *   If `ans` is still `Long.MAX_VALUE` after the loop, it means no valid split was found, so return -1.
    *   Otherwise, return the minimum absolute difference `ans`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array `nums`.
    *   The code iterates through the array three times: once to compute `inc` and `tot`, once to compute `dec`, and once to find the minimum difference. Each iteration takes O(n) time.
    *   The operations inside the loops are constant time O(1).

*   **Space Complexity:** O(n), where n is the length of the input array `nums`.
    *   The code uses two boolean arrays, `inc` and `dec`, both of size `n`.
    *   The variables `n`, `tot`, `sum`, and `ans` take up constant space O(1).
