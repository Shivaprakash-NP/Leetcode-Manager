## Best Time to Buy and Sell Stock - Explanation

Here's a detailed explanation of the Java code provided for solving the "Best Time to Buy and Sell Stock" problem:

### 1. Problem Understanding:

The problem asks us to find the maximum profit we can make by buying and selling a stock, given an array of prices where `prices[i]` is the price of the stock on day `i`. We can only perform one transaction (buy and then sell).  We need to buy before we sell, and we want to maximize our profit. If no profit can be made, we should return 0.

### 2. Approach / Intuition:

The core idea is to iterate through the `prices` array and keep track of the minimum price seen so far (our potential buying price).  For each day, we check if selling the stock on that day would yield a greater profit than the maximum profit we've found so far. If it does, we update the maximum profit. We also update the minimum price seen so far if the current day's price is lower.

**Why this approach?**

This approach is efficient because it uses a single pass through the array.  It avoids unnecessary comparisons by only considering potential buying points that are lower than the current minimum. We don't need nested loops or complex data structures. It's a greedy approach, as at each step we are making the locally optimal decision by comparing the current price with the minimum price we've seen so far.

### 3. Data Structures and Algorithms:

*   **Data Structures:** We are primarily using an array (`prices`) as input, and scalar integer variables (`dp`, `max`) to store intermediate values and the final result.  No complex data structures are needed.
*   **Algorithms:** This solution implements a form of dynamic programming although it doesn't explicitly use a DP table. The `dp` variable stores the minimum price *seen so far*. We can also view it as a variant of a greedy algorithm.

### 4. Code Walkthrough:

```java
class Solution {
    public int maxProfit(int[] prices) {
        int dp = prices[0];
        int max = 0;
        for(int i = 1 ; i<prices.length ; i++)
        {
            if(prices[i]>=dp) max = Math.max(max , prices[i] - dp);
            dp = Math.min(dp , prices[i]);
        }
        return max;
    }
}
```

1.  **`class Solution { ... }`**:  This defines the class `Solution` which is standard for LeetCode submissions.

2.  **`public int maxProfit(int[] prices) { ... }`**: This defines the `maxProfit` method which takes an integer array `prices` as input and returns an integer representing the maximum profit.

3.  **`int dp = prices[0];`**:  This line initializes `dp` to the price of the stock on the first day (`prices[0]`). `dp` represents the minimum price we've seen so far. In essence, `dp` represents the best buying price encountered to date.  It's initialized to the first day's price because initially, the first day is the only potential buying day we've considered.

4.  **`int max = 0;`**: This line initializes `max` to 0. `max` stores the maximum profit we've found so far. We initialize it to 0 because if no profit can be made (prices always decrease), we should return 0.

5.  **`for(int i = 1 ; i<prices.length ; i++) { ... }`**: This loop iterates through the `prices` array from the *second* day (index 1) to the last day.

6.  **`if(prices[i]>=dp) max = Math.max(max , prices[i] - dp);`**: Inside the loop, this `if` statement checks if the price on the current day (`prices[i]`) is greater than or equal to the minimum price we've seen so far (`dp`). If it is, it means we can potentially make a profit by buying at `dp` and selling at `prices[i]`. We update `max` to be the larger of the current `max` and the profit `prices[i] - dp`.

7.  **`dp = Math.min(dp , prices[i]);`**: This line updates `dp` to be the smaller of the current `dp` and the price on the current day (`prices[i]`). This ensures that `dp` always holds the minimum price seen so far.  This is crucial for identifying the best potential buying price.

8.  **`return max;`**: After the loop finishes, the method returns the value of `max`, which represents the maximum profit that can be made.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n)**
    The code iterates through the `prices` array once in a single `for` loop. Therefore, the time complexity is linear with respect to the size of the input array.

*   **Space Complexity: O(1)**
    The code uses only a constant amount of extra space to store the `dp` and `max` variables, regardless of the size of the input array. Thus, the space complexity is constant.
