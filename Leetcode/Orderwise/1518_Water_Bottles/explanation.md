## Water Bottles - LeetCode Problem Explanation

### 1. Problem Understanding:

The problem asks: Given a certain number of initially full water bottles (`numBottles`) and an exchange rate (`numExchange`), where you can exchange `numExchange` empty bottles for one full bottle, determine the total number of water bottles you can drink. You drink all initial bottles, then exchange the empties, drink those, exchange again, and so on, until you no longer have enough empty bottles to exchange.

### 2. Approach / Intuition:

The intuitive approach is to simulate the drinking and exchanging process.  We start with the initial `numBottles` and repeatedly perform the following steps:

1.  Add the current `numBottles` to our total count of drunk bottles (`c`).
2.  Calculate how many new bottles we can get by exchanging.  This is the sum of new `numBottles` we get from exchanging and remaining `rem` empties from the previous exchange, divided by the `numExchange` rate.
3.  Calculate the remaining empty bottles after exchanging.  This is the modulo operation of `(numBottles + rem)` with `numExchange`.
4.  Update `numBottles` to the number of new bottles obtained in the exchange.
5.  Update `rem` to the number of remaining empty bottles from the current exchange.

We continue this process in a `while` loop until `numBottles` becomes 0, meaning we can't get any more full bottles. This iterative simulation directly reflects the problem statement's conditions.

### 3. Data Structures and Algorithms:

*   **Data Structures:** No complex data structures are used.  The solution relies primarily on integer variables to track the number of bottles, remains, and the total count.
*   **Algorithms:** This problem utilizes a simulation-based algorithm. The core idea is to iteratively simulate the process of drinking bottles, exchanging empty bottles, and updating the counts until a termination condition is met (no more bottles to exchange).  The arithmetic operations of division and modulo are crucial in the exchange calculation.

### 4. Code Walkthrough:

```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int c = 0; // Initialize the total number of bottles drunk to 0.
        int rem = 0; // Initialize the number of remaining empty bottles to 0.

        while(numBottles != 0) { // Loop until we have no more bottles to drink.
            c += numBottles; // Drink the current number of bottles and add to total count.

            int tem = (numBottles+rem)%numExchange; // Remaining bottles: bottles left from current exchange
            numBottles = (numBottles+rem)/numExchange; // Number of new bottles we get from exchange.
            rem = tem; //remaining from this cycle

        }
        return c; // Return the total number of bottles drunk.
    }
}
```

*   **`int c = 0;`**:  This initializes an integer variable `c` to 0. This variable will store the total number of water bottles drunk.
*   **`int rem = 0;`**: This initializes an integer variable `rem` to 0. This variable keeps track of any leftover empty bottles that couldn't be exchanged in the previous iteration.
*   **`while(numBottles != 0)`**: This `while` loop continues as long as `numBottles` is not 0. The loop simulates the process of drinking, exchanging, and getting new bottles. When `numBottles` becomes 0, it means we can't get any more full bottles.
*   **`c += numBottles;`**:  This line adds the current number of `numBottles` to the total count `c`.  This represents drinking all the current full bottles.
*   **`int tem = (numBottles+rem)%numExchange;`**: Calculate remaining bottles and store in temp
*   **`numBottles = (numBottles+rem)/numExchange;`**: This is the most important part. It calculates how many *new* full bottles we get by exchanging the empty bottles. `numBottles + rem` represents the total number of empty bottles we have (currently emptied bottles plus any remaining from previous exchanges).  Dividing this sum by `numExchange` gives us the number of full bottles we get back.
*   **`rem = tem;`**: This updates the remaining number of empty bottles by the temporary value calculated `tem`.
*   **`return c;`**:  Finally, the function returns the total number of water bottles drunk (`c`).

### 5. Time and Space Complexity:

*   **Time Complexity:** The time complexity is O(log<sub>n</sub>), where n is `numBottles` and the base of the logarithm is `numExchange`.  In each iteration of the `while` loop, `numBottles` is divided by `numExchange`.  Therefore, the loop will execute a logarithmic number of times relative to the initial value of `numBottles`.
*   **Space Complexity:** The space complexity is O(1) because we are only using a fixed number of integer variables, regardless of the input size. The space used does not scale with the input.
