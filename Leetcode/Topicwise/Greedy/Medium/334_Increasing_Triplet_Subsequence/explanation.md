## LeetCode: Increasing Triplet Subsequence - Solution Explanation

**1. Problem Understanding:**

The problem asks whether a given integer array `nums` contains an increasing subsequence of length 3.  This means, are there three numbers `nums[i]`, `nums[j]`, and `nums[k]` (where `i < j < k`) such that `nums[i] < nums[j] < nums[k]`?


**2. Approach / Intuition:**

The provided solution uses a greedy approach with two variables, `s1` and `s2`.  Instead of explicitly searching for all possible triplets, it efficiently tracks the smallest and second smallest numbers encountered so far.

- `s1`: Stores the smallest number encountered.
- `s2`: Stores the second smallest number encountered.

The algorithm iterates through the array. If a number is smaller than or equal to `s1`, it updates `s1`. If a number is greater than `s1` but smaller than or equal to `s2`, it updates `s2`.  If a number is greater than both `s1` and `s2`, it means we've found a triplet (implicitly, a previous number less than `s1`, `s1` itself, and this number).  Therefore, we can immediately return `true`.  If the loop completes without finding such a triplet, it means no increasing triplet subsequence exists, so we return `false`.

This approach is efficient because it avoids the need for nested loops which would lead to O(n^3) time complexity. It intelligently maintains only the necessary information to detect an increasing triplet.


**3. Data Structures and Algorithms:**

- **Data Structure:** The input is an integer array (`nums`).  The solution uses only two integer variables (`s1` and `s2`) to store the smallest and second smallest numbers encountered so far.
- **Algorithm:** The core algorithm is a greedy approach using a single pass through the input array.


**4. Code Walkthrough:**

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int s1 = Integer.MAX_VALUE; // Initialize s1 to the maximum possible integer value
        int s2 = Integer.MAX_VALUE; // Initialize s2 to the maximum possible integer value

        for(int i = 0 ; i<n ; i++) {
            if(nums[i] <= s1) s1 = nums[i]; // If current number is smaller than or equal to s1, update s1
            else if(nums[i] <= s2) s2 = nums[i]; // If current number is greater than s1 but smaller than or equal to s2, update s2
            else return true; // If current number is greater than both s1 and s2, we found an increasing triplet
        }
        return false; // No increasing triplet found
    }
}
```

- **Initialization:** `s1` and `s2` are initialized to `Integer.MAX_VALUE`. This ensures that the first two numbers encountered will always be smaller and thus update `s1` and `s2` accordingly.

- **Iteration:** The `for` loop iterates through each element in the `nums` array.

- **Conditional Logic:** The `if-else if-else` structure efficiently checks the relationship between the current number and `s1` and `s2`, updating them appropriately or immediately returning `true` if an increasing triplet is found.

- **Return Value:** The function returns `true` if an increasing triplet is found and `false` otherwise.


**5. Time and Space Complexity:**

- **Time Complexity:** O(n), where n is the length of the input array. The algorithm iterates through the array only once.

- **Space Complexity:** O(1). The algorithm uses only a constant amount of extra space to store the variables `s1` and `s2`, regardless of the input size.  The space used is constant even for large inputs.  This makes it a very efficient solution for this problem.
