## Maximum Product Subarray - Explained

Here's a detailed explanation of the provided Java code for the LeetCode problem "Maximum Product Subarray":

### 1. Problem Understanding

The problem asks us to find the contiguous subarray within a given integer array `nums` that has the largest product. We need to return this maximum product. The key challenge is that the array can contain negative numbers, which can flip the sign of the product and potentially lead to a larger product.

### 2. Approach / Intuition

The main idea behind the solution is to keep track of the maximum product seen so far while traversing the array from both ends. Since negative numbers can flip the sign, a small negative number can become a large positive number when multiplied by another negative number.  Therefore, instead of just tracking one maximum product, we track forward product and suffix product simultaneously:

1.  **Forward Product (`pp`):** Calculate the product of elements from the beginning of the array to the current index `i`.

2.  **Suffix Product (`sp`):** Calculate the product of elements from the end of the array to the current index `i`.

3.  **Maximum Product:** Update the overall maximum product (`max`) with the maximum of the current `max`, `pp`, and `sp`.

4.  **Resetting to 1:** If `pp` or `sp` becomes 0, reset it to 1. This is because a 0 will nullify the subsequent products, and we want to start fresh from the next element. This is essential to handle cases where 0s are present in the array.

Why this approach?  By tracking both prefix and suffix products, we effectively cover all possible subarrays. A contiguous subarray's product can be obtained by either starting from the beginning, the end, or somewhere in the middle. The algorithm efficiently captures these possibilities without explicitly iterating through all possible subarrays.

### 3. Data Structures and Algorithms

*   **Data Structure:** The code uses a simple `int` array (`nums`) as input. No other complex data structures are employed.
*   **Algorithm:** The algorithm uses a single loop to traverse the array while maintaining and updating the running products. It utilizes a dynamic programming approach to avoid redundant calculations.  Specifically, it leverages a sliding window technique to track both forward and backward products.

### 4. Code Walkthrough

```java
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE; // Initialize max to the smallest possible integer value.

        int pp = 1; // Forward product
        int sp = 1; // Suffix product

        for(int i = 0 ; i<n ; i++)
        {
            pp*=nums[i]; // Update the forward product by multiplying with the current element.
            sp*=nums[n-1-i]; // Update the suffix product by multiplying with the element at the opposite end.

            max = Math.max(max , Math.max(pp , sp)); // Update the overall maximum product.

            if(pp==0) pp=1; // If forward product becomes 0, reset it to 1.
            if(sp==0) sp=1; // If suffix product becomes 0, reset it to 1.
        }

        return max; // Return the overall maximum product found.
    }
}
```

**Line-by-line explanation:**

1.  `class Solution {`: Defines the class containing the solution.
2.  `public int maxProduct(int[] nums) {`: Defines the `maxProduct` function, which takes an integer array `nums` as input and returns an integer representing the maximum product of a subarray.
3.  `int n = nums.length;`: Stores the length of the input array in the variable `n`.
4.  `int max = Integer.MIN_VALUE;`: Initializes the `max` variable to the smallest possible integer value. This ensures that any product found will be greater than the initial value.
5.  `int pp = 1;`: Initializes the `pp` (forward product) variable to 1.
6.  `int sp = 1;`: Initializes the `sp` (suffix product) variable to 1.
7.  `for(int i = 0 ; i<n ; i++) {`: Starts a loop that iterates through the array from the beginning to the end.
8.  `pp*=nums[i];`: Multiplies the current forward product `pp` by the element at index `i` of the array.
9.  `sp*=nums[n-1-i];`: Multiplies the current suffix product `sp` by the element at index `n-1-i` of the array (working backward from the end).
10. `max = Math.max(max , Math.max(pp , sp));`: Updates the `max` variable to the maximum of its current value, the forward product `pp`, and the suffix product `sp`.
11. `if(pp==0) pp=1;`: Checks if the forward product `pp` is 0. If it is, it resets `pp` to 1. This is crucial for handling cases where the array contains 0s. If the current product is zero, then this zero will get multiplied in the further iterations and make the overall product as zero, which might not be the desired output.
12. `if(sp==0) sp=1;`: Checks if the suffix product `sp` is 0. If it is, it resets `sp` to 1.  This handles 0s in the reverse traversal.
13. `}`: Closes the `for` loop.
14. `return max;`: Returns the final maximum product found.

### 5. Time and Space Complexity

*   **Time Complexity:** O(n). The code iterates through the array once. The operations inside the loop (multiplication, comparison, and assignment) take constant time.
*   **Space Complexity:** O(1). The code uses a fixed number of variables (`n`, `max`, `pp`, `sp`) regardless of the input array size. Therefore, the space complexity is constant.
