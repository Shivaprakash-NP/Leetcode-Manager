## Number of Ways to Split Array - Explained

Here's a detailed explanation of the provided Java code for the LeetCode problem "Number of Ways to Split Array":

### 1. Problem Understanding:

The problem asks us to find the number of ways to split an array `nums` into two non-empty parts such that the sum of the elements in the left part is greater than or equal to the sum of the elements in the right part.  A split occurs at each index `i` from `0` to `nums.length - 2`.  The left part is `nums[0...i]` and the right part is `nums[i+1...nums.length-1]`.

### 2. Approach / Intuition:

The core idea is to efficiently calculate the sum of the left and right parts for each possible split point. Instead of recalculating these sums for every split, we precompute the total sum of the array and then iterate through the array, maintaining a running sum of the left part.  The right part's sum can be derived by subtracting the left part's sum from the total sum. This optimization avoids nested loops and improves time complexity.

Specifically, we calculate the total sum `sum` first. Then, as we iterate through the array, we maintain a `summ` (left sum) which gets updated at each split. At each split point `i`, we check if `summ` is greater than or equal to `sum - summ`. If it is, then this is a valid split, and we increment the counter `c`.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  We primarily use integers and long to store sums and the count.  We use the given input array `nums`.
*   **Algorithms:** The algorithm uses a simple linear scan of the array along with basic arithmetic operations (addition, subtraction, and comparison).

### 4. Code Walkthrough:

```java
class Solution {
    public int waysToSplitArray(int[] nums) {
        long sum = 0;
        for(int v:nums)
            sum+=v;
        long summ = 0;
        int c = 0;
        for(int i = 0 ; i<nums.length-1 ; i++)
        {
            summ+=nums[i];
            if(summ>=(sum-summ))
                c++;
        }
        return c;
    }
}
```

1.  `long sum = 0;`: Initializes a long variable `sum` to 0. This variable will store the total sum of all elements in the `nums` array. We use `long` to prevent potential integer overflow if the sums are large.
2.  `for(int v:nums) sum+=v;`: This is a for-each loop that iterates through the `nums` array.  In each iteration, the current element `v` is added to the `sum`.
3.  `long summ = 0;`: Initializes a long variable `summ` to 0. This variable will store the sum of the left part of the array for each possible split. We use `long` to prevent potential integer overflow.
4.  `int c = 0;`: Initializes an integer variable `c` to 0. This variable will count the number of valid splits.
5.  `for(int i = 0 ; i<nums.length-1 ; i++)`: This loop iterates through the array from index 0 to `nums.length - 2`. The loop condition ensures that we only consider splits where both left and right parts are non-empty.
6.  `summ+=nums[i];`: In each iteration, the element at the current index `i` is added to `summ`. This updates the sum of the left part for the current split.
7.  `if(summ>=(sum-summ)) c++;`: This is the core logic.  It checks if the sum of the left part (`summ`) is greater than or equal to the sum of the right part (`sum - summ`). If this condition is true, it means that the current split is valid, and the counter `c` is incremented.
8.  `return c;`: Finally, the function returns the value of `c`, which represents the total number of valid splits found in the array.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the `nums` array. The code iterates through the array twice (once to calculate the total sum and once to check the split conditions), but both iterations are linear.
*   **Space Complexity:** O(1).  The code uses only a few extra variables (`sum`, `summ`, `c`) which take constant space, independent of the input array size. Therefore, the space complexity is constant.
