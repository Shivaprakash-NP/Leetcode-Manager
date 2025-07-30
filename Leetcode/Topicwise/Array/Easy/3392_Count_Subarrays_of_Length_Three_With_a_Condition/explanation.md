## LeetCode Problem: Count Subarrays of Length Three With a Condition

### 1. Problem Understanding:

The problem asks us to find the number of subarrays of length three in a given array `nums` that satisfy a specific condition. The condition is that twice the sum of the first and last elements of the subarray must equal the middle element.  More formally, for a subarray `[nums[i], nums[i+1], nums[i+2]]`, we need to check if `2 * (nums[i] + nums[i+2]) == nums[i+1]`. We need to count how many such subarrays exist in the input array.

### 2. Approach / Intuition:

The most straightforward approach is to iterate through the array, considering each possible subarray of length three, and check if it satisfies the given condition. The problem constraints aren't specified, but for the core logic, a simple iterative approach is most fitting due to its simplicity and direct implementation of the problem's requirements. There is no need for complex algorithms or data structures. We slide a window of size 3 across the array, checking the condition at each window position.  This ensures that every possible subarray of length 3 is considered.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure is the input array `nums` itself. No other auxiliary data structures are used.
*   **Algorithms:** The solution utilizes a simple iterative approach with a sliding window. It doesn't employ any complex algorithms like sorting or searching.

### 4. Code Walkthrough:

```java
class Solution {
    public int countSubarrays(int[] nums) {
        int i = 0;
        int j = 1;
        int k = 2;
        int c = 0;
        while(k<nums.length)
        {
            if(2*(nums[i]+nums[k]) == nums[j]) c++;
            i++;
            j++;
            k++;
        }
        return c;
    }
}
```

*   **`class Solution { ... }`**: Defines the class `Solution` which is standard for LeetCode problems.
*   **`public int countSubarrays(int[] nums)`**: This is the main method that takes the input array `nums` as an argument and returns an integer representing the count of subarrays that satisfy the condition.
*   **`int i = 0; int j = 1; int k = 2;`**: These lines initialize three index variables: `i`, `j`, and `k`. They represent the indices of the first, second, and third elements of the subarray, respectively.  They effectively form the sliding window.
*   **`int c = 0;`**: Initializes a counter variable `c` to 0. This variable will store the count of subarrays that satisfy the given condition.
*   **`while(k<nums.length)`**: This `while` loop iterates through the array as long as the index `k` is less than the length of the array. This condition ensures that we don't go out of bounds when accessing `nums[k]`.  It's crucial for controlling the sliding window.
*   **`if(2*(nums[i]+nums[k]) == nums[j]) c++;`**: This is the core logic of the solution. It checks if the condition `2 * (nums[i] + nums[k]) == nums[j]` is true for the current subarray. If it is, the counter `c` is incremented.  This directly implements the problem's constraint.
*   **`i++; j++; k++;`**: These lines increment the indices `i`, `j`, and `k` to move the sliding window to the next subarray.
*   **`return c;`**: Finally, the method returns the value of the counter `c`, which represents the total number of subarrays that satisfy the condition.

### 5. Time and Space Complexity:

*   **Time Complexity:** The `while` loop iterates at most `n - 2` times, where `n` is the length of the input array. Inside the loop, the operations are constant time (arithmetic operations and comparisons). Therefore, the time complexity is **O(n)**.
*   **Space Complexity:** The solution uses a fixed number of variables (i, j, k, c), regardless of the size of the input array. Therefore, the space complexity is **O(1)**, which is constant space.
