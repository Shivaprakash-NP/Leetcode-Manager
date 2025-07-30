## LeetCode: K-th Smallest in Lexicographical Order - Explained

**1. Problem Understanding:**

The problem asks us to find the k-th smallest number in lexicographical order within the range of 1 to n (inclusive).  Lexicographical order means the numbers are ordered as they would appear in a dictionary;  1, 10, 11, 12...  are smaller than 2.  The challenge is to efficiently find the k-th number without explicitly generating and sorting all numbers in the range.

**2. Approach / Intuition:**

This solution uses a clever approach based on prefix counting. Instead of generating all numbers, it efficiently counts how many numbers share a given prefix.  It iteratively explores prefixes, deciding whether to move to the next prefix or to extend the current prefix.

The `count()` function determines how many numbers start with a given prefix (`cur`) and are less than or equal to `n`.  It does this by calculating the count of numbers with that prefix in each order of magnitude until it exceeds `n`.

The main `findKthNumber()` function starts at 1 and iteratively decrements `k`.  For each prefix:

* It calls `count()` to determine how many numbers share that prefix.
* If `k` is greater than the count of numbers with that prefix, it means the k-th number must be further down the lexicographical order. So it moves to the next prefix (incrementing `c`).
* Otherwise, the k-th number lies within the numbers sharing the current prefix.  It then extends the current prefix by multiplying `c` by 10.

This process continues until `k` becomes 0, at which point `c` holds the k-th smallest number.

This approach is chosen because it avoids explicitly generating and sorting all numbers in the range [1, n], which would be highly inefficient for large values of n. The prefix counting strategy leads to a logarithmic time complexity.

**3. Data Structures and Algorithms:**

* **Data Structures:**  The solution primarily uses primitive data types (`long`, `int`). No complex data structures are needed.
* **Algorithms:**  The core algorithm is a prefix-based counting algorithm combined with a binary search-like iterative approach to find the k-th smallest number.

**4. Code Walkthrough:**

* **`count(long cur, long nxt, int n)`:** This function calculates the number of integers between `cur` (inclusive) and `nxt` (exclusive) that are less than or equal to `n`. It iteratively calculates this count for each order of magnitude (ones, tens, hundreds, etc.).  `step` accumulates the total count.  The `Math.min(n+1L, nxt)` handles cases where the upper bound (`nxt`) exceeds `n`.

* **`findKthNumber(int n, int k)`:**
    * `long c = 1;`:  Initialization: We start searching from the number 1.
    * `k--;`: Adjust k because it's 1-based indexed, but the algorithm uses 0-based indexing.
    * `while(k > 0)`: The main loop continues until the k-th number is found.
    * `long step = count(c, c + 1, n);`: Calculate the number of integers starting with the prefix `c`.
    * `if (step <= k)`: If the number of integers with prefix `c` is less than or equal to `k`, the k-th number is not in this prefix, so we move to the next prefix (`c++` and decrement `k`).
    * `else`: If the number of integers with prefix `c` is greater than `k`, then the k-th number is within the current prefix. We extend the prefix by multiplying `c` by 10, and we decrement `k` because we have consumed one prefix.
    * `return (int)c;`:  Once `k` reaches 0, `c` holds the k-th smallest number.


**5. Time and Space Complexity:**

* **Time Complexity:** O(log²n). The `count()` function iterates at most log₁₀(n) times (number of digits). The `findKthNumber()` function also iterates at most log₁₀(n) times to cover the prefixes of the numbers. The nested nature results in O(log²n) complexity.

* **Space Complexity:** O(1). The solution uses only constant extra space for variables.  No additional data structures are used that scale with the input size.


In summary, this solution elegantly leverages prefix counting to efficiently solve the problem without the need for generating and sorting all numbers. The logarithmic time complexity makes it highly efficient even for large values of `n`.
