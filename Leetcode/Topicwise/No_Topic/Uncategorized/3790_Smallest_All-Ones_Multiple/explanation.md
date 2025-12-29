### Problem Understanding

The problem asks us to find the smallest positive integer `cnt` such that a number consisting of `cnt` ones (e.g., 1, 11, 111, 1111, ...) is a multiple of a given integer `k`. If no such number exists, we should return -1.

For example:
*   If `k = 3`, the number `111` (three ones) is a multiple of 3, so the answer is `3`.
*   If `k = 7`, the number `111111` (six ones) is a multiple of 7, so the answer is `6`.

### Approach / Intuition

1.  **Generating All-Ones Numbers:**
    Let's denote the all-ones number with `i` digits as `R_i`.
    *   `R_1 = 1`
    *   `R_2 = 11 = R_1 * 10 + 1`
    *   `R_3 = 111 = R_2 * 10 + 1`
    *   In general, `R_i = R_{i-1} * 10 + 1`.

2.  **The Challenge of Large Numbers:**
    The all-ones numbers can grow extremely large very quickly. For `k = 10^5`, `cnt` could be up to `k`, meaning a number with `10^5` digits. Standard integer types (`int`, `long`) cannot store such large numbers.

3.  **Modular Arithmetic to the Rescue:**
    We are only interested in whether `R_i` is a multiple of `k`, which means checking if `R_i % k == 0`. We can apply the modulo operation at each step of generating `R_i`:
    `R_i % k = ( (R_{i-1} * 10) + 1 ) % k`
    Using the properties of modular arithmetic:
    `R_i % k = ( (R_{i-1} % k * 10 % k) % k + (1 % k) ) % k`
    This simplifies to `R_i % k = ( (R_{i-1} % k) * 10 + 1 ) % k`.

    Let `n` be `R_i % k`.
    *   Initially, for `cnt = 1`, `n = 1 % k = 1`.
    *   For subsequent steps, `n = (n * 10 + 1) % k`.
    We continue this process, incrementing `cnt` with each step, until `n` becomes 0.

4.  **Edge Cases: When no solution exists (returning -1):**
    An all-ones number always ends in the digit `1`.
    *   If `k` is an even number (e.g., 2, 4, 6, ...), any multiple of `k` must also be an even number, meaning it must end in an even digit (0, 2, 4, 6, 8). Since an all-ones number ends in 1, it can never be a multiple of an even `k`.
    *   If `k` is a multiple of 5 (e.g., 5, 10, 15, ...), any multiple of `k` must end in `0` or `5`. Since an all-ones number ends in 1, it can never be a multiple of a `k` that is a multiple of 5.
    Therefore, if `k % 2 == 0` or `k % 5 == 0`, we can immediately return -1.

5.  **Guaranteed Termination (if not -1):**
    If `k` is not divisible by 2 or 5, it's a known property from number theory (related to the concept of "repunit numbers" and modular arithmetic cycles) that such an all-ones multiple *always* exists.
    The sequence of remainders `n = (n * 10 + 1) % k` can only take `k` possible values (0 to `k-1`). If we haven't found `n=0` within `k` steps, we must have encountered a repeated remainder (by the Pigeonhole Principle). Since `k` is coprime to 10 (not divisible by 2 or 5), the sequence will eventually hit 0. Thus, the loop is guaranteed to terminate within at most `k` iterations.

### Data Structures and Algorithms

*   **Data Structures:**
    *   Primitive types (`int`, `long`) are used to store `k`, `cnt`, and the current remainder `n`. No complex data structures like arrays, lists, or hash maps are explicitly used.
*   **Algorithms:**
    *   **Modular Arithmetic:** The core technique used to handle potentially very large numbers by performing all calculations within the modulo `k` space.
    *   **Iteration:** A `while` loop iteratively generates the next all-ones number's remainder and increments the count.
    *   **Edge Case Handling:** An initial `if` condition efficiently handles cases where no solution is possible.

### Code Walkthrough

```java
class Solution {
    public int minAllOneMultiple(int k) {
        // 1. Edge Case Handling
        // If k is even or a multiple of 5, no all-ones number (which ends in 1)
        // can be a multiple of k.
        if(k%2 == 0 || k%5 == 0) return -1;

        // 2. Initialization
        int cnt = 1;      // Tracks the number of '1's in the current all-ones number.
                          // Starts at 1 for the number '1'.
        long n = 1L;      // Stores the current all-ones number modulo k.
                          // Starts at 1 (representing 1 % k). Using long to prevent
                          // potential intermediate overflow before the modulo operation,
                          // though for k up to 10^5, (k-1)*10+1 would fit in int.
                          // It's good practice for modular arithmetic.

        // 3. Iterative Calculation using Modular Arithmetic
        while(true) {
            // Check if the current all-ones number (modulo k) is 0.
            // If it is, we found our multiple.
            if(n%k == 0) return cnt;

            // Generate the next all-ones number (modulo k):
            // Conceptually, if 'n' was R_i % k, we want to calculate R_{i+1} % k.
            // R_{i+1} = R_i * 10 + 1
            n *= 10L;   // Multiply by 