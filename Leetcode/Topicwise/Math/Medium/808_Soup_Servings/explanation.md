## LeetCode Problem: Soup Servings Explained

**1. Problem Understanding:**

The "Soup Servings" problem asks:  Given two bowls of soup, A and B, containing `n` and `n` milliliters of soup respectively, what's the probability that bowl A will be emptied before bowl B, assuming you take 4, 3, 2, or 1 milliliters from a bowl each turn, with equal probability for each combination of taking from A and B?


**2. Approach / Intuition:**

This problem is best solved using dynamic programming with memoization.  The core idea is to recursively explore all possible soup-serving scenarios. We use a recursive helper function (`calc`) to calculate the probability of A being emptied first. Each recursive call represents a turn in the game. We explore four possible actions (taking soup from bowls A and B in different combinations), each with a probability of 0.25.  The base cases are when either bowl A or B is empty, or both are empty.  Memoization (using the `dp` array) drastically improves efficiency by storing the results of subproblems to avoid redundant calculations. The optimization for `n >= 4800` leverages the observation that for large n, the probability approaches 1.0.


**3. Data Structures and Algorithms:**

* **Data Structure:** A 2D array (`dp`) is used for memoization.  It stores the probability of bowl A being emptied first for different amounts of soup remaining in bowls A and B.
* **Algorithm:** Dynamic programming with memoization and recursion.


**4. Code Walkthrough:**

* **`soupServings(int n)`:**
    * Handles the edge case where `n` is very large (>= 4800), returning 1.0 as the probability approaches 1 in such cases.
    * Normalizes `n` by dividing by 25 and rounding up. This simplification is based on the fact that the probabilities are very close for amounts that are multiples of 25.
    * Initializes a 2D `dp` array of size `(n+1) x (n+1)` filled with -1.0 (indicating uncalculated probabilities).
    * Calls the recursive helper function `calc(n, n, dp)` to compute the probability.

* **`calc(int a, int b, double[][] dp)`:**
    * **Base Cases:**
        * If `a <= 0` and `b <= 0`, it means both bowls are empty, so the probability of A being emptied first is 0.5 (since we are considering the possibility of reaching this state).
        * If `a <= 0`, bowl A is empty, so the probability of A being emptied first is 0.
        * If `b <= 0`, bowl B is empty, so the probability of A being emptied first is 1.
    * **Memoization:** Checks if the result for `dp[a][b]` has already been computed. If so, it returns the stored value.
    * **Recursive Step:** If the result isn't memoized, it calculates the probability as the weighted average of the probabilities of the four possible actions (taking 4, 3, 2, or 1 milliliters in different combinations).  Each action has a probability of 0.25.
    * **Result:** Stores the calculated probability in `dp[a][b]` and returns it.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n^2).  The `calc` function explores at most `n x n` states in the worst case due to the memoization.  The number of states is proportional to the square of the normalized n.
* **Space Complexity:** O(n^2). This is due to the `dp` array which stores the results of subproblems.  The space used is proportional to the square of the normalized n.  The recursive call stack depth is at most `n` in the worst case, but this is negligible compared to the `dp` array's space.


The memoization in this dynamic programming solution significantly reduces the time complexity from an exponential time complexity (without memoization) to a polynomial time complexity of O(n^2).  This makes the solution efficient for reasonably sized inputs.
