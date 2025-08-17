## LeetCode: New 21 Game - Solution Explanation

**1. Problem Understanding:**

The "New 21 Game" problem simulates a simplified card game.  You have a score, initially 0.  You repeatedly draw a card with a value between 1 and `maxPts` (inclusive).  Your score increases by the card's value.  The goal is to reach a score of `k` without exceeding `n`.  The problem asks for the probability of winning the game (reaching exactly `k`).

**2. Approach / Intuition:**

This solution uses dynamic programming (DP) to efficiently calculate the probability of winning.  Instead of brute-forcing all possible game sequences (which is computationally expensive), we build a DP array where `dp[i]` represents the probability of reaching a score of `i`.

The core idea is that the probability of reaching score `i` is the sum of probabilities of reaching scores `i-1`, `i-2`, ..., `i-maxPts`, each weighted by the probability of drawing the corresponding card (which is 1/`maxPts`).  We iteratively compute `dp[i]` using this relationship.  The final answer is the sum of probabilities of reaching scores `k` through `n`.


This approach is chosen because it avoids redundant calculations.  Once we've calculated the probability of reaching a particular score, we can reuse that value when calculating probabilities for higher scores.  Brute force would recalculate the same probabilities many times.

**3. Data Structures and Algorithms:**

* **Data Structure:** A `double` array (`dp`) is used to store the probabilities of reaching each possible score.
* **Algorithm:** Dynamic Programming is the core algorithm employed.


**4. Code Walkthrough:**

```java
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        //Base Cases:
        if(k == 0) return 1.0; //Already won
        if(n >= k-1+maxPts) return 1.0; //Guaranteed to win (can always reach k)

        double[] dp = new double[n+1]; // DP array to store probabilities
        dp[0] = 1.0; // Probability of reaching score 0 is 1 (game start)
        double win = 1.0; // Running sum of probabilities of scores that can lead to a win.
        double ans = 0; // Accumulates probabilities of winning scores

        for(int i = 1; i<=n; i++) {
            dp[i] = win/maxPts; // Probability of reaching score i is the average of previous maxPts scores

            if(i<k) win+=dp[i]; // If score i is less than k, it contributes to the possibility of reaching k later.
            else ans+=dp[i]; // If i >= k, it is a winning score.

            if(i-maxPts >= 0) win-=dp[i-maxPts];  //Subtract the probability of scores that are too far to contribute to future scores.
        }

        return ans;
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the maximum score. The code iterates through the `dp` array once.
* **Space Complexity:** O(n), due to the `dp` array.

The space complexity could be slightly improved to O(maxPts) by using a sliding window approach to update the `win` variable, however, this optimization doesn't significantly change the overall complexity.  The linear time complexity makes this solution very efficient for reasonably sized inputs.
