### Problem Understanding

The problem asks us to process an array of integers, `nums`. For each number in `nums`, we need to determine how many *other* numbers in the *same* array are strictly smaller than it. The final output should be an array where each element at index `i` represents the count of numbers smaller than `nums[i]`.

For example, if `nums = [8, 1, 2, 2, 3]`:
*   For `nums[0] = 8`: There are four numbers smaller than 8 (1, 2, 2, 3).
*   For `nums[1] = 1`: There are zero numbers smaller than 1.
*   For `nums[2] = 2`: There is one number smaller than 2 (1).
*   For `nums[3] = 2`: There is one number smaller than 2 (1).
*   For `nums[4] = 3`: There are three numbers smaller than 3 (1, 2, 2).

The expected output would be `[4, 0, 1, 1, 3]`.

### Approach / Intuition

The provided solution employs a straightforward, brute-force approach. The core idea is to take each number from the input array one by one and, for that specific number, iterate through the *entire* array again to count how many elements satisfy the "strictly smaller" condition.

The intuition is simple: if you want to know how many items in a list are smaller than a particular item, you have to look at every other item in that list and compare it. This approach directly translates that thought process into code using nested loops.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] nums`: The input array of integers.
    *   `int[] ans`: The output array of integers, storing the counts.
*   **Algorithms:**
    *   **Nested Loops (Brute-Force Iteration):** The solution uses two nested `for` loops. The outer loop picks a number, and the inner loop compares it against all other numbers in the array.

### Code Walkthrough

Let's break down the code step by step:

```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // Get the length of the input array. This will be used for loop bounds and array initialization.
        int n = nums.length;
        
        // Initialize an array 'ans' of the same size as 'nums'.
        // This array will store our results, where ans[i] will hold the count
        // of numbers smaller than nums[i].
        int[] ans = new int[n];
        
        // Outer loop: Iterates through each number in the 'nums' array.
        // For each 'nums[i]', we want to find how many numbers are smaller than it.
        for(int i = 0; i<n; i++) {
            // Initialize a counter 'cnt' to 0 for the current number nums[i].
            // This counter will accumulate the count of smaller numbers.
            int cnt = 0;
            
            // Inner loop: Iterates through the entire 'nums' array again.
            // This loop compares every number 'nums[j]' with the current 'nums[i]'.
            for(int j = 0; j<n; j++) {
                // Check if the number at index 'j' is strictly less than the number at index 'i'.
                // Note: It's important that 'j' can be equal to 'i'. If nums[j] is nums[i] itself,
                // the condition nums[j] < nums[i] will be false, which is correct because a number
                // is not smaller than itself.
                if(nums[j] < nums[i]) {
                    // If nums[j] is smaller, increment the counter.
                    cnt++;
                }
            }
            // After the inner loop completes, 'cnt' holds the total count of numbers
            // smaller than nums[i]. Store this count in the 'ans' array at the corresponding index 'i'.
            ans[i] = cnt;
        }

        // Return the 'ans' array, which now contains the desired counts for each number.
        return ans;
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(n^2)**
    *   The code has two nested `for` loops.
    *   The outer loop runs `n` times (where `n` is the length of the `nums` array).
    *   For each iteration of the outer loop, the inner loop also runs `n` times.
    *   Inside the inner loop, operations like comparison (`<`) and increment (`++`) take constant time, O(1).
    *   Therefore, the total number of operations is proportional to `n * n`, resulting in a time complexity of O(n^2).

*   **Space Complexity: O(n)**
    *   An additional array `ans` of size `n` is created to store the results. This contributes O(n) to the space complexity.
    *   A few integer variables (`n`, `i`, `j`, `cnt`) are used, which take up a constant amount of space, O(1).
    *   Combining these, the dominant factor is the `ans` array, making the overall space complexity O(n).