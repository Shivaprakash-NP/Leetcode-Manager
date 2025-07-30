Okay, I'm ready to provide a detailed explanation of the Java code for the LeetCode problem "Minimum Cost of Buying Candies With Discount."

## Minimum Cost of Buying Candies With Discount - Solution Explanation

### 1. Problem Understanding:

The problem states that you are given an array `cost` where `cost[i]` is the cost of the i-th candy. You can buy candies in groups of three.  For every group of three candies you buy, you get the cheapest one for free. The goal is to find the minimum total cost to buy all the candies.

### 2. Approach / Intuition:

The core idea behind the solution is to maximize the discount you get. Since you get the *cheapest* candy free for every group of three, it's optimal to group the most expensive candies together. This allows you to get the cheapest (least expensive) candy in those groups for free, thereby minimizing the total cost.

Here's the breakdown:

1.  **Sort the candies:** Sort the `cost` array in ascending order.  This puts the cheapest candies at the beginning of the array and the most expensive at the end.
2.  **Group and Discount:** Starting from the *end* of the sorted array (the most expensive candies), consider groups of three.  For each group of three, the *first* candy in the group (which is the smallest of the three due to sorting) gets a discount of its entire cost.
3.  **Calculate the Total Cost:** The algorithm iterates through the sorted array, effectively skipping every third element (starting from the beginning) as those elements represent the 'free' candies in the groups of three. The sum of the remaining elements represents the minimum cost.

**Why this approach?**

Sorting the array and strategically removing the cost of the cheapest candies within groups of three guarantees that you are taking maximum advantage of the "buy two get one free" deal. Any other grouping will inevitably lead to a higher total cost.

### 3. Data Structures and Algorithms:

*   **Data Structure:**  The code uses an integer array `cost` as its primary data structure to store the candy prices.
*   **Algorithm:**  The core algorithm is sorting (using `Arrays.sort()`) followed by a simple loop to calculate the minimum cost after applying the discounts.  `Arrays.sort()` uses a dual-pivot quicksort algorithm, which provides O(n log n) average time complexity.

### 4. Code Walkthrough:

```java
class Solution {
    public int minimumCost(int[] cost) {
        int sum = 0; // Initialize the total cost to 0

        // Calculate the sum of all costs initially
        for(int v : cost) sum+=v;

        // Sort the cost array in ascending order
        Arrays.sort(cost);

        int n = cost.length;  // Get the length of the cost array

        // Iterate backwards through the array, skipping every third element from the end.
        // These skipped elements are the cheapest candies in each group of three,
        // which are "free".
        for(int i = n-3 ; i>=0 ; i-=3)
            sum-=cost[i]; // Subtract the cost of the "free" candy from the total sum

        return sum;  // Return the minimum cost
    }
}
```

**Line-by-line explanation:**

1.  **`int sum = 0;`**: Initializes an integer variable `sum` to 0. This variable will store the total cost of candies *before* applying discounts.
2.  **`for(int v : cost) sum+=v;`**: This is an enhanced for loop that iterates through each element `v` in the `cost` array and adds it to the `sum`.  So, `sum` becomes the sum of all candy prices.
3.  **`Arrays.sort(cost);`**: This line uses the `Arrays.sort()` method to sort the `cost` array in ascending order.  After this line, `cost[0]` will be the cheapest candy, and `cost[cost.length - 1]` will be the most expensive.
4.  **`int n = cost.length;`**: Gets the length of the `cost` array and stores it in the variable `n`.
5.  **`for(int i = n-3 ; i>=0 ; i-=3)`**: This loop is the heart of the discount logic:
    *   `int i = n - 3`:  Starts at the index that is three positions before the end of the array. This index represents the cheapest candy of the last possible group of three.
    *   `i >= 0`:  Continues the loop as long as `i` is a valid index in the array.
    *   `i -= 3`:  Decrements `i` by 3 in each iteration, moving to the next group of three candies towards the beginning of the array.
6.  **`sum-=cost[i];`**: This line subtracts the value of `cost[i]` from the `sum`. Since `cost` is sorted, `cost[i]` represents the *cheapest* candy in a group of three. By subtracting it from the sum, we are effectively applying the discount.
7.  **`return sum;`**:  Finally, the function returns the `sum`, which now represents the minimum cost to buy all the candies after applying the discounts.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   `Arrays.sort(cost)`:  O(n log n) on average, due to the dual-pivot quicksort algorithm.
    *   The first `for` loop to calculate the sum: O(n).
    *   The second `for` loop (discount calculation): O(n/3) which simplifies to O(n).
    *   The dominant term is O(n log n) because of sorting.  Therefore, the overall **time complexity is O(n log n)**.

*   **Space Complexity:**
    *   The algorithm uses a few integer variables (`sum`, `n`, `i`). These take constant space.
    *   `Arrays.sort()` might use some extra space depending on the implementation, but in place sorting algorithms exist. Therefore, the overall **space complexity is O(1)** (constant), assuming that the sorting algorithm used is an in-place sorting algorithm or utilizes only a constant amount of extra space.
