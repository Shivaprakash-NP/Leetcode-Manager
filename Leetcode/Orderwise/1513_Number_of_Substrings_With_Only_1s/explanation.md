### Problem Understanding

The problem asks us to count the total number of substrings within a given binary string $S$ that consist entirely of the character '1'. Since the total count can be very large, the final result must be returned modulo $10^9 + 7$.

For example, if the input string is "11101", the substrings of '1's are:
1. From the first block ("111"): "1", "1", "1", "11", "11", "111" (6 substrings).
2. From the second block ("1"): "1" (1 substring).
Total: 7.

The core difficulty lies in efficiently calculating the contribution of large, contiguous blocks of '1's and managing potential integer overflow using modular arithmetic.

### Approach / Intuition

The crucial observation is that substrings composed only of '1's can only be formed within contiguous sequences of '1's. The character '0' acts as a delimiter, separating independent blocks of '1's.

**Core Logic: Arithmetic Series Summation**

Consider a contiguous block of $N$ ones (e.g., $N=4$, "1111").
The number of substrings we can form is:
*   Length 1: $N$ substrings ("1")
*   Length 2: $N-1$ substrings ("11")
*   Length 3: $N-2$ substrings ("111")
*   ...
*   Length $N$: 1 substring ("11...1")

The total number of substrings for a block of length $N$ is the sum of integers from 1 to $N$:
$$S_N = 1 + 2 + 3 + \dots + N$$

We use Gauss's formula for the sum of an arithmetic series:
$$S_N = \frac{N(N+1)}{2}$$

**Strategy**

The chosen approach is a **single-pass linear scan**:
1.  Iterate through the string, maintaining a counter (`cnt`) for the current length of consecutive '1's.
2.  When a '0' is encountered (or the string ends), the current block of '1's is terminated.
3.  Calculate the contribution of this block using the formula $S_{cnt} = \frac{cnt(cnt+1)}{2}$.
4.  Add this contribution to the total answer (`ans`), ensuring all calculations respect the modulo $10^9 + 7$.
5.  Reset the counter (`cnt`) to 0 and continue scanning.

This approach is highly efficient because it processes each character exactly once.

### Data Structures and Algorithms

1.  **Data Structures:**
    *   **String:** The input data structure.
    *   **Primitive Types (`int`, `long`):** Used for counting consecutive '1's (`cnt`), storing the running total (`ans`), and the modulus (`MOD`). `long` is used for `ans` and intermediate calculations to prevent overflow before the modulo operation.

2.  **Algorithms:**
    *   **Linear Scan (Iterative Traversal):** The primary algorithm used to process the string character by character.
    *   **Arithmetic Series Summation:** Using the closed-form formula $N(N+1)/2$ to quickly calculate the contribution of each block.
    *   **Modular Arithmetic:** Applied extensively to keep the running total within the bounds required by the problem (modulo $10^9 + 7$).

### Code Walkthrough

```java
class Solution {
    public int numSub(String s) {
        int cnt = 0; // 1. Tracks the length of the current consecutive block of '1's.
        long ans = 0; // 2. Stores the running total number of substrings. Must be long for safety.
        long MOD = 1000000007; // 3. The required modulus.

        for(char c : s.toCharArray()) {
            if(c == '1') cnt++; // 4. If '1', increment the current count.
            else {
                // 5. Block termination (c == '0'). Calculate contribution of the previous block.
                
                // The intent is to calculate (cnt * (cnt + 1) / 2) % MOD.
                // Note: Since N(N+1) is always even, standard integer division by 2 is safe here.
                // The complex modular arithmetic shown handles the intermediate products:
                long contribution = ((long)cnt * (cnt + 1)) / 2;
                ans = (ans + contribution) % MOD;
                
                // The original code's complex modular division implementation:
                // ans = (ans + ((((cnt%MOD)*(cnt+1))%MOD) / 2))%MOD;
                
                cnt = 0; // 6. Reset the counter for the next block.
            }
        }

        // 7. Handle the final block of '1's if the string ends with '1's.
        // This calculation is necessary because the loop terminates before the 'else' block is hit.
        long finalContribution = ((long)cnt * (cnt + 1)) / 2;
        ans = (ans % MOD + finalContribution) % MOD;
        
        return (int)ans; // 8. Cast the final result back to int.
    }
}
```

**Explanation of Key Sections:**

1.  **Initialization:** `cnt` (current block length) is 0. `ans` (total count) is 0. `MOD` is defined.
2.  **Iteration:** The code iterates through the string character by character.
3.  **Counting:** If `c == '1'`, `cnt` increases.
4.  **Block Calculation (The `else` block):** When `c == '0'`, the current block of `cnt` ones is finished. The calculation `((long)cnt * (cnt + 1)) / 2` determines the total substrings from this block. This result is added to `ans`, and the modulo operation `% MOD` is applied immediately to prevent overflow and keep the running total correct. `cnt` is then reset to 0.
5.  **Post-Loop Calculation:** If the string ends with one or more '1's (e.g., "11011"), the final block calculation is missed by the loop's `else` condition. Line 7 explicitly calculates the contribution of the remaining `cnt` and adds it to the total `ans` modulo $MOD$.
6.  **Return:** The final `long` answer is cast to `int` before returning, as required by the method signature.

### Time and Space Complexity

#### Time Complexity: $O(N)$
Where $N$ is the length of the input string $S$.
The solution involves a single loop that iterates over every character of the string exactly once. All operations performed inside the loop (incrementing, multiplication, division, addition, and modulo) are $O(1)$. Therefore, the total time complexity is linear with respect to the input size.

#### Space Complexity: $O(1)$
The solution uses a fixed number of auxiliary variables (`cnt`, `ans`, `MOD`, and the loop variable `c`). The space required does not scale with the size of the input string $N$. Thus, the auxiliary space complexity is constant.