## Find Triangular Sum of an Array - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to compute the "triangular sum" of an array of non-negative integers.  We perform the following operation repeatedly:  Replace each element of the array (except the last) with the sum of the current element and the next element, modulo 10. We then shorten the array by removing the last element.  We continue this process until only one element remains in the array. That remaining element is the triangular sum.

For example, given `nums = [1,2,3,4,5]`:

1. `nums = [(1+2)%10, (2+3)%10, (3+4)%10, (4+5)%10] = [3, 5, 7, 9]`
2. `nums = [(3+5)%10, (5+7)%10, (7+9)%10] = [8, 2, 6]`
3. `nums = [(8+2)%10, (2+6)%10] = [0, 8]`
4. `nums = [(0+8)%10] = [8]`

The triangular sum is 8.

### 2. Approach / Intuition:

The most straightforward approach is to directly simulate the process described in the problem statement. We repeatedly iterate through the array, updating each element with the sum of itself and the next element, modulo 10.  After each iteration, we decrement the effective size of the array, effectively removing the last element. We continue this until only one element remains.

The intuition behind this approach is that the problem itself is well-defined and amenable to direct simulation. There aren't any obvious optimizations that dramatically improve the solution, given the constraints of the problem. Direct simulation is relatively easy to implement and understand.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure used is an integer array (`int[] nums`).
*   **Algorithms:** The algorithm is iterative and involves a simple modulo arithmetic operation.  It's essentially a repeated application of a summation and modulo operation within a loop.

### 4. Code Walkthrough:

```java
class Solution {
    public int triangularSum(int[] nums) {
        int n = nums.length;

        while(n > 0) {
            for(int i = 0; i<n-1; i++) nums[i] = (nums[i]+nums[i+1])%10;
            n--;
        }
        
        return nums[0];
    }
}
```

1.  **`class Solution { ... }`**:  This defines the `Solution` class, which is standard practice for LeetCode problems.

2.  **`public int triangularSum(int[] nums) { ... }`**: This is the main function that takes an integer array `nums` as input and returns the triangular sum as an integer.

3.  **`int n = nums.length;`**:  This line initializes `n` to the length of the input array. `n` represents the effective size of the array that we are currently processing.

4.  **`while (n > 0) { ... }`**: This `while` loop continues as long as the effective size of the array `n` is greater than 0.  This loop executes the reduction process until only one element remains.

5.  **`for (int i = 0; i < n - 1; i++) nums[i] = (nums[i] + nums[i + 1]) % 10;`**:  This inner `for` loop iterates through the array from index 0 up to `n - 2` (inclusive).  Inside the loop:
    *   `nums[i] = (nums[i] + nums[i + 1]) % 10;`:  This is the core operation. It updates the element at index `i` with the sum of the current element and the next element, modulo 10. The modulo operation ensures the result stays within the range [0, 9].

6.  **`n--;`**: After the `for` loop completes, this line decrements `n`. This effectively reduces the size of the array by one, simulating the removal of the last element in the array for the next iteration.

7.  **`return nums[0];`**:  Once the `while` loop terminates (when `n` becomes 1 or 0), this line returns the element at index 0 of the array, which is the triangular sum.

### 5. Time and Space Complexity:

*   **Time Complexity:** The outer `while` loop runs `n` times in the worst case (where `n` is the original length of the array). The inner `for` loop runs `n-1`, `n-2`, ..., 1 times. The total number of iterations of the inner loop is approximately `n * (n - 1) / 2`, which is O(n^2). Therefore, the time complexity is O(n^2).

*   **Space Complexity:** The algorithm modifies the input array in-place.  It only uses a few extra variables (`n`, `i`).  Therefore, the space complexity is O(1) (constant space).
