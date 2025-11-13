### Problem Understanding

The problem asks for the maximum number of operations required to move all '1's to the end of a binary string $S$. An operation consists of selecting a '1' immediately followed by a '0' ("10") and swapping them to "01".

The core insight is that the total number of operations is independent of the order in which the swaps are performed. Every operation represents a single '1' moving past a single '0'. Therefore, the maximum number of operations is simply the total number of pairs $(i, j)$ such that $S[i] = '1'$, $S[j] = '0'$, and $i < j$.

We can simplify this calculation: for every block of consecutive zeros in the string, we need to count how many '1's preceded that block. The sum of these counts gives the total maximum operations.

### Approach / Intuition

The solution employs a single-pass, greedy counting approach. We iterate through the string from left to right, maintaining a running count of the '1's encountered so far.

**Core Logic:**

1.  **Track Preceding Ones:** We use a variable, `one`, to keep track of the number of '1's encountered since the beginning of the string.
2.  **Identify Zero Blocks:** When we encounter a '0', it signifies a point where the preceding '1's must cross.
3.  **Calculate Operations:** If we find a '0', and there are $K$ ones preceding it (i.e., `one = K`), then each of those $K$ ones must eventually move past this '0'. This contributes $K$ operations to the total count.
4.  **Handle Consecutive Zeros:** If the '0' is part of a block (e.g., "11000"), we must ensure we only count the contribution of the preceding '1's once for the entire block. If we are at the start of the "000" block, the two preceding '1's contribute 2 operations to cross the entire block. We use an inner loop to skip over the subsequent consecutive zeros after counting the operations for the start of the block.

This approach works because the movement of a '1' past a '0' is independent of the movement of other '1's or other '0's. By counting the number of '1's preceding each distinct block of '0's, we correctly sum up all possible required swaps.

### Data Structures and Algorithms

1.  **Data Structures:**
    *   `String s`: The input string.
    *   `int one`: Simple integer counter for preceding ones.
    *   `int cnt`: Simple integer counter for total operations.

2.  **Algorithms:**
    *   **Single-Pass Iteration:** The solution uses a single `for` loop to traverse the string.
    *   **Greedy Counting:** The decision to add `one` to `cnt` is made locally whenever a '0' is encountered, ensuring we maximize the count based on the state observed so far.

### Code Walkthrough

```java
class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int one = 0; // Count of '1's encountered so far
        int cnt = 0; // Total operations count

        for(int i = 0; i < n; i++) {
            
            // Case 1: We found a '0'
            if(s.charAt(i) == '0') {
                
                // If 'one' > 0, the 'one's preceding this '0' must cross it.
                // We calculate the operations contribution here.
                
                // Sub-step: Skip consecutive '0's. 
                // We only want to count the operations once per block of zeros.
                while(i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                    i++;
                }
                
                // Add the contribution of the current block of zeros.
                // If 'one' is 3, this block contributes 3 operations.
                cnt += one;
                
            } else {
                // Case 2: We found a '1'. Increment the running count.
                one++;
            }
        }

        return cnt;
    }
}
```

1.  **Initialization:**
    *   `n`: Stores the length of the string.
    *   `one = 0`: Initializes the counter for '1's seen before the current index.
    *   `cnt = 0`: Initializes the total operations counter.

2.  **Main Loop (`for(int i = 0; i < n; i++)`):** Iterates through the string.

3.  **Handling '0' (`if(s.charAt(i) == '0')`):**
    *   When a '0' is found, we know that all currently counted '1's (`one`) must cross this position.
    *   **Inner `while` loop:** This loop is critical for handling blocks of zeros (e.g., "1000").
        *   It advances the index `i` past all subsequent consecutive '0's. This ensures that when we calculate `cnt += one`, we are counting the contribution of the preceding '1's against the *entire* zero block, not just the first zero in the block.
    *   **Operation Count (`cnt += one`):** Adds the current count of preceding '1's to the total operations.

4.  **Handling '1' (`else one++`):**
    *   If the current character is a '1', we simply increment the `one` counter, preparing for the next zero block calculation.

5.  **Return:** The final accumulated count `cnt` is returned.

### Time and Space Complexity

#### Time Complexity: $O(N)$

The solution involves a single pass over the string of length $N$. Although there is a nested `while` loop inside the `for` loop, the inner `while` loop explicitly increments the main loop counter `i`. This means that every character in the string is visited and processed a constant number of times (at most once by the `for` loop and at most once by the `while` loop). Therefore, the overall time complexity is linear with respect to the length of the input string.

#### Space Complexity: $O(1)$

The algorithm uses only a few fixed-size integer variables (`n`, `one`, `cnt`) regardless of the size of the input string $S$. No auxiliary data structures are used.