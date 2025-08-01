## LeetCode: House Robber - Detailed Explanation

**1. Problem Understanding:**

The "House Robber" problem states that a robber is planning to rob houses along a street. Each house has a certain amount of money.  The robber cannot rob two adjacent houses because they are connected by an alarm system. The goal is to find the maximum amount of money the robber can steal without triggering the alarm.


**2. Approach / Intuition:**

This solution utilizes a recursive approach with memoization (dynamic programming).  The core idea is to explore two possibilities at each house:

* **Rob the current house:** If we rob the current house, we cannot rob the next house.  We recursively call the function for the house after the next one (`ind + 2`).
* **Don't rob the current house:**  We recursively call the function for the next house (`ind + 1`).

Memoization (`dp` array) is crucial for efficiency. It stores the results of subproblems to avoid redundant calculations.  The `dp` array is a 2D array where the first dimension represents the house index and the second dimension indicates whether the previous house was robbed (1) or not (0). This prevents revisiting already computed results.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is a 2D integer array (`dp`) for memoization. This array stores the maximum robbery amount for each subproblem.
* **Algorithms:** The algorithm is a recursive approach with memoization, which is a form of dynamic programming.  The recursion explores the decision tree of robbing or not robbing each house, and memoization optimizes it by storing and reusing the results of previously solved subproblems.


**4. Code Walkthrough:**

* **`rec(int[] arr, int ind, boolean isprepicked, int[][] dp)`:** This is a recursive helper function.
    * `arr`: The array of money amounts in each house.
    * `ind`: The index of the current house being considered.
    * `isprepicked`: A boolean flag indicating whether the previous house was robbed.
    * `dp`: The memoization 2D array.
    * **Base Case:** If `ind` is greater than or equal to the array length, it means we've reached the end, so we return 0 (no more money to steal).
    * **Memoization Check:** It checks if the result for the current subproblem (`dp[ind][flag]`) is already computed. If yes, it returns the stored value.
    * **Recursive Calls:**
        * `val1`: If the previous house was not robbed (`!isprepicked`), we rob the current house (`arr[ind]`) and recursively call `rec` for the house after the next one (`ind + 2`, `isprepicked = true`).
        * `val2`: If we don't rob the current house, we recursively call `rec` for the next house (`ind + 1`, `isprepicked = false`).
    * **Return Value:**  It returns the maximum of `val1` and `val2`, storing it in `dp[ind][flag]` for future use.

* **`rob(int[] nums)`:** This is the main function.
    * It initializes the `dp` array with -1 to indicate that no subproblems have been solved yet.
    * It calls the recursive helper function `rec` starting from the first house (`ind = 0`, `isprepicked = false`).
    * It returns the maximum amount of money that can be stolen.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of houses.  The memoization drastically improves the time complexity.  Each subproblem is solved only once.  The recursive calls with memoization effectively transform the exponential time complexity of a naive recursive approach into a linear time complexity.

* **Space Complexity:** O(N). The space complexity is dominated by the `dp` array, which has dimensions (N+1) x 2.  The recursive call stack might also consume some space, but in the worst case, it's proportional to N (although with tail call optimization, this could be constant space).  Therefore, the overall space complexity is linear.
