## LeetCode Problem: Solving Questions With Brainpower - Explained

### 1. Problem Understanding:

The problem presents a series of questions, where each question has associated points and brainpower cost. We're allowed to choose whether to solve a question or skip it. If we solve a question, we gain its points, but we cannot solve the next `brainpower` number of questions. The goal is to maximize the total points we can get by strategically solving or skipping questions.

### 2. Approach / Intuition:

The problem can be effectively solved using dynamic programming.  The core idea is to work backwards from the last question to the first. For each question, we have two choices:

*   **Solve the question:** If we solve the question at index `i`, we gain `questions[i][0]` points, but we have to skip the next `questions[i][1]` questions. So, we can attempt the question after these `questions[i][1]` skipped questions, which is at index `i + 1 + questions[i][1]`.
*   **Skip the question:** If we skip the question at index `i`, we move on to the next question, which is at index `i + 1`.

We want to choose the option that yields the maximum points. Therefore, we use a `dp` array where `dp[i]` stores the maximum points we can obtain starting from question `i`. Working backwards allows us to build up the solution by considering the optimal solutions for the subproblems (questions later in the sequence). We use `Math.max` to decide between solving and skipping for each question `i`.

Why this approach? Dynamic programming is suitable here because the problem exhibits overlapping subproblems (the optimal solution from question `i` depends on the optimal solutions from subsequent questions) and optimal substructure (the optimal solution to the overall problem can be constructed from the optimal solutions to subproblems).

### 3. Data Structures and Algorithms:

*   **Data Structure:** `long[] dp`: A one-dimensional array of type `long` is used to store the maximum points achievable starting from each question.  Using `long` prevents potential integer overflow issues when calculating accumulated points.
*   **Algorithm:** Dynamic Programming (specifically, a bottom-up approach). We fill the `dp` array from the end to the beginning.

### 4. Code Walkthrough:

```java
class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 0);    
        for(int i = n-1 ; i>=0 ; i--)
        {
            long sk = (i+1<n)?dp[i+1]:0;

            int nxind = i+1+questions[i][1];
            long sol = questions[i][0] + ((nxind<n)?dp[nxind]:0);

            dp[i] = Math.max(sk , sol);
        }
        return dp[0];
    }
}
```

1.  **`public long mostPoints(int[][] questions)`:** This is the main function that takes the `questions` array as input and returns the maximum points achievable.

2.  **`int n = questions.length;`:** Gets the number of questions.

3.  **`long[] dp = new long[n];`:** Creates a `dp` array of size `n` to store the maximum points achievable starting from each question.

4.  **`Arrays.fill(dp, 0);`:** Initializes all elements of the `dp` array to 0.

5.  **`for(int i = n-1 ; i>=0 ; i--)`:** This loop iterates backwards from the last question (`n-1`) to the first question (`0`).

6.  **`long sk = (i+1<n)?dp[i+1]:0;`:**  Calculates the maximum points if we *skip* the current question. `sk` represents the points we'd have if we skipped question `i` and continued from question `i+1`. The ternary operator `(i+1<n)?dp[i+1]:0` handles the case where `i` is the last question.  In that case, there's no question to skip to, so `sk` is 0.

7.  **`int nxind = i+1+questions[i][1];`:** Calculates the index of the next question we can solve *if* we solve the current question `i`. We add 1 (to move past the current question) and `questions[i][1]` (the brainpower cost of solving the current question) to `i`.

8.  **`long sol = questions[i][0] + ((nxind<n)?dp[nxind]:0);`:** Calculates the maximum points if we *solve* the current question.  `sol` represents the points from the current question (`questions[i][0]`) plus the maximum points we can obtain from the question at index `nxind` (if `nxind` is within the bounds of the `questions` array). Again, the ternary operator handles boundary conditions.  If `nxind` is out of bounds, we add 0, effectively indicating that we cannot solve any further questions.

9.  **`dp[i] = Math.max(sk , sol);`:** Updates `dp[i]` to store the maximum of either skipping the current question or solving the current question.  This is the core dynamic programming step, choosing the better option between skipping and solving.

10. **`return dp[0];`:** After the loop finishes, `dp[0]` contains the maximum points achievable starting from the first question (index 0), which is the final result.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the number of questions.  The code iterates through the `questions` array once. All other operations inside the loop are constant time.
*   **Space Complexity:** O(n), where n is the number of questions.  This is due to the `dp` array of size `n`. We could potentially reduce this to O(1) by storing only the values of the previous two `dp` calculations, but it would come at the expense of readability.
