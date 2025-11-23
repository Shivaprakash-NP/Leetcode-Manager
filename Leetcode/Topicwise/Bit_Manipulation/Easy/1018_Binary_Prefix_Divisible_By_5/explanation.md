### Problem Understanding

The problem asks us to process an array of binary digits (`nums`), where each element is either 0 or 1. We must consider every possible prefix of this array, interpret that prefix as a binary number, and determine if that resulting binary number is divisible by 5. The final output must be a list of booleans, where the $i$-th element is `true` if the binary number formed by $nums[0]$ through $nums[i]$ is divisible by 5, and `false` otherwise.

For example, if `nums = [1, 0, 1]`:
1. Prefix [1]: Binary value is 1. (1 % 5 != 0) -> `false`
2. Prefix [1, 0]: Binary value is 2. (2 % 5 != 0) -> `false`
3. Prefix [1, 0, 1]: Binary value is 5. (5 % 5 == 0) -> `true`
Result: `[false, false, true]`

### Approach / Intuition

The critical challenge in this problem is the size of the numbers. Since the input array `nums` can have up to $3 \times 10^4$ elements, the resulting binary numbers can have $30,000$ bits. These numbers are far too large to store in standard integer types (like `int` or `long`) or even `BigInteger` efficiently for repeated calculations.

The key insight is that we only care about divisibility by 5, which means we only need to track the running remainder modulo 5. This technique is known as **Modular Arithmetic**.

#### Modular Arithmetic for Prefix Extension

When we append a new bit ($b$) to an existing binary number ($N$), the new number ($N'$) is calculated as:
$$N' = 2N + b$$

For example, if $N=5$ (binary `101`) and we append $b=0$, $N' = 10$ (binary `1010`). $2(5) + 0 = 10$.

To find the remainder of $N'$ when divided by 5, we can apply the modulus property:
$$(A + B) \pmod M = ((A \pmod M) + (B \pmod M)) \pmod M$$
$$(A \times B) \pmod M = ((A \pmod M) \times (B \pmod M)) \pmod M$$

If we let $mod_{prev}$ be the remainder of the previous prefix ($N$) modulo 5, then:
$$N' \pmod 5 = (2N + b) \pmod 5$$
$$N' \pmod 5 = ((2 \pmod 5) \times (N \pmod 5) + (b \pmod 5)) \pmod 5$$
$$N' \pmod 5 = (2 \times mod_{prev} + b) \pmod 5$$

By using this recursive relationship, we can calculate the remainder for the current prefix based only on the remainder of the previous prefix and the new bit, keeping all intermediate calculations small (at most $2(4) + 1 = 9$) and ensuring the running remainder (`mod`) always stays between 0 and 4.

### Data Structures and Algorithms

1.  **Data Structures:**
    *   `int[] nums`: Input array (standard array).
    *   `List<Boolean> res`: Output list (dynamic array/ArrayList).
    *   `int mod`: A simple integer variable used to store the running remainder modulo 5.

2.  **Algorithm:**
    *   **Algorithm:** Iterative application of Modular Arithmetic (specifically, prefix extension in base 2).
    *   **Core Logic:** Maintain a running remainder `mod`. In each step, update `mod` using the formula: `mod = (2 * mod + current_bit) % 5`.

### Code Walkthrough

```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> res = new ArrayList<>();

        int mod = 0; 
        // 'mod' stores the remainder of the binary number formed by the prefix 
        // processed so far, divided by 5. It starts at 0 (representing the value of the empty prefix).
        
        for(int bit : nums) {
            // 1. Calculate the new prefix value modulo 5
            // 2 * mod: Shifts the previous number left (multiplies by 2)
            // + bit: Adds the new least significant bit (0 or 1)
            // % 5: Ensures the result is the new remainder (0, 1, 2, 3, or 4)
            mod = (2*mod+bit)%5;
            
            // 2. Check for divisibility
            // If the remainder is 0, the number is divisible by 5.
            res.add(mod == 0);
        }
        
        return res;
    }
}
```

1.  **Initialization:**
    *   `List<Boolean> res = new ArrayList<>();`: Initializes the list that will store the results for each prefix.
    *   `int mod = 0;`: Initializes the running remainder. Since we start before processing any bits, the value of the number is 0, and $0 \pmod 5 = 0$.

2.  **Iteration:**
    *   `for(int bit : nums)`: The code iterates through every bit in the input array.

3.  **Core Calculation:**
    *   `mod = (2*mod+bit)%5;`: This is the modular arithmetic step. It efficiently calculates the remainder of the new, larger binary prefix without ever calculating the full value of that prefix.

4.  **Result Storage:**
    *   `res.add(mod == 0);`: After updating the running remainder, we check if it is zero. If it is, the current prefix is divisible by 5, and we add `true` to the result list; otherwise, we add `false`.

5.  **Return:**
    *   `return res;`: The final list of divisibility checks is returned.

### Time and Space Complexity

#### Time Complexity: $O(N)$
Where $N$ is the length of the input array `nums`.
The solution involves a single loop that iterates exactly $N$ times (once for each bit). Inside the loop, all operations (multiplication, addition, modulo, and list insertion) are constant time, $O(1)$. Therefore, the total time complexity is linear.

#### Space Complexity: $O(N)$
The space complexity is dominated by the storage required for the output list (`res`), which holds $N$ boolean values. We use only a few constant space variables (`mod`, `bit`, `n`). Thus, the auxiliary space complexity is $O(1)$, but the total space complexity including the required output structure is $O(N)$.