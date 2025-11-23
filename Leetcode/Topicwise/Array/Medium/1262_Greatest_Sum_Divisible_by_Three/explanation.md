### Problem Understanding

The problem asks us to find the maximum possible sum of elements from a given array of integers (`nums`) such that this sum is perfectly divisible by three. We can choose to include any subset of the numbers from the input array. If no such sum can be formed (e.g., the array is empty or contains only numbers that, when summed, can never be divisible by three), the answer should be 0.

### Approach / Intuition

The core idea behind this solution is to first calculate the total sum of all numbers in the array. Let's call this `totalSum`.

1.  **Check `totalSum`'s divisibility by 3:**
    *   If `totalSum` is already divisible by 3 (`totalSum % 3 == 0`), then this is the maximum possible sum, and we can return `totalSum`.

2.  **Adjusting `totalSum` if not divisible by 3:**
    *   If `totalSum % 3` is not 0, we need to remove some numbers from the array to make the remaining sum divisible by 3.
    *   To maximize the *remaining* sum, we must minimize the sum of the numbers we remove.
    *   We need to consider the remainder of `totalSum` when divided by 3:
        *   **Case 1: `totalSum % 3 == 1`**
            To make the sum divisible by 3, we need to reduce its remainder by 1. This can be achieved in two ways by removing numbers:
            *   Remove one number `x` such that `x % 3 == 1`. To minimize the removed sum, we should pick the *smallest* such `x`.
            *   Remove two numbers `y` and `z` such that `y % 3 == 2` and `z % 3 == 2`. Their sum `(y + z) % 3 == (2 + 2) % 3 == 4 % 3 == 1`. To minimize the removed sum, we should pick the *two smallest* such `y` and `z`.
            We then choose the minimum of these two options (removing one number with remainder 1, or removing two numbers with remainder 2).

        *   **Case 2: `totalSum % 3 == 2`**
            To make the sum divisible by 3, we need to reduce its remainder by 2. This can be achieved in two ways by removing numbers:
            *   Remove one number `x` such that `x % 3 == 2`. To minimize the removed sum, we should pick the *smallest* such `x`.
            *   Remove two numbers `y` and `z` such that `y % 3 == 1` and `z % 3 == 1`. Their sum `(y + z) % 3 == (1 + 1) % 3 == 2 % 3 == 2`. To minimize the removed sum, we should pick the *two smallest* such `y` and `z`.
            We then choose the minimum of these two options (removing one number with remainder 2, or removing two numbers with remainder 1).

3.  **Tracking minimums:**
    To efficiently find the smallest numbers with remainders 1 and 2, the solution iterates through the input array once. During this pass, it keeps track of:
    *   The two smallest numbers that have a remainder of 1 when divided by 3 (`r1a`, `r1b`).
    *   The two smallest numbers that have a remainder of 2 when divided by 3 (`r2a`, `r2b`).
    We use `Integer.MAX_VALUE` (represented as `INF`) as a sentinel value to indicate that no such number has been found yet.

By following this strategy, we ensure that if an adjustment is needed, we always remove the smallest possible sum of elements, thereby maximizing the final sum that is divisible by 3.

### Data Structures and Algorithms

*   **Data Structures:**
    *   Primitive integers (`int`): Used to store the total sum, individual numbers, remainders, and the smallest/second smallest numbers for each remainder category.
*   **Algorithms:**
    *   **Single Pass Iteration:** The solution uses a single loop to iterate through the input array `nums`. This loop calculates the total sum and simultaneously identifies the smallest two numbers for each remainder (1 and 2).
    *   **Conditional Logic / Comparison:** Extensive use of `if-else if` statements and comparisons (`<`) to update the smallest and second smallest numbers efficiently.
    *   **`Math.min()`:** Used to select the absolute minimum sum to remove from the available options.

### Code Walkthrough

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0; // Stores the total sum of all numbers in nums
        int INF = Integer.MAX_VALUE; // Sentinel value for "infinity" or "not found"

        // r1a, r1b will store the two smallest numbers with remainder 1 when divided by 3.
        // r1a is the smallest, r1b is the second smallest.
        int r1a = INF, r1b = INF; 
        // r2a, r2b will store the two smallest numbers with remainder 2 when divided by 3.
        // r2a is the smallest, r2b is the second smallest.
        int r2a = INF, r2b = INF;

        // Iterate through each number in the input array
        for(int x : nums) {
            sum += x; // Add current number to the total sum
            int r = x % 3; // Get the remainder of the current number when divided by 3

            if(r == 1) { // If the remainder is 1
                if(x < r1a) { // If x is smaller than the current smallest (r1a)
                    r1b = r1a; // Move current r1a to r1b (becomes second smallest)
                    r1a = x;   // x becomes the new smallest (r1a)
                } else if(x < r1b) { // If x is not smaller than r1a, but smaller than r1b
                    r1b = x; // x becomes the new second smallest (r1b)
                }
            } else if(r == 2) { // If the remainder is 2 (similar logic as above for remainder 1)
                if(x < r2a) {
                    r2b = r2a;
                    r2a = x;
                } else if(x < r2b) {
                    r2b = x;
                }
            }
        }

        int rem = sum % 3; // Calculate the remainder of the total sum

        if(rem == 0) return sum; // If total sum is already divisible by 3, it's the answer

        int ans = 0; // This variable will store the minimum sum to remove

        if(rem == 1) { // If total sum % 3 == 1, we need to remove a sum that is 1 (mod 3)
            int remove1 = r1a; // Option 1: Remove the smallest number with remainder 1
            // Option 2: Remove two smallest numbers with remainder 2.
            // Check if both r2a and r2b exist (are not INF) before summing.
            int remove2 = (r2a < INF && r2b < INF) ? r2a + r2b : INF;
            ans = Math.min(remove1, remove2); // Choose the minimum of these two options
        } else { // If total sum % 3 == 2, we need to remove a sum that is 2 (mod 3)
            int remove1 = r2a; // Option 1: Remove the smallest number with remainder 2
            // Option 2: Remove two smallest numbers with remainder 1.
            // Check if both r1a and r1b exist (are not INF) before summing.
            int remove2 = (r1a < INF && r1b < INF) ? r1a + r1b : INF;
            ans = Math.