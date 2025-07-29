## LeetCode: Longest Binary Subsequence Less Than or Equal to K

**1. Problem Understanding:**

The problem asks us to find the length of the longest subsequence of a binary string (containing only '0's and '1's) such that the decimal value represented by this subsequence is less than or equal to a given integer `k`.  The subsequence must be formed by selecting a contiguous set of characters from the original string, starting from the right.

**2. Approach / Intuition:**

The solution uses a greedy approach. It iterates through the binary string from right to left.  It keeps track of the running decimal value (`val`) represented by the subsequence and the number of '0's encountered (`z`).

The core idea is that '0's always contribute to a smaller decimal value and are always beneficial to keep as long as the total value remains less than or equal to `k`.  The algorithm prioritizes including '1's because they increase the decimal value more significantly. If including a '1' makes the total value exceed `k`, we know we cannot include any more characters after this point to stay below `k`, so the process terminates. The algorithm returns the length of the selected subsequence (the number of characters processed before exceeding `k`).

This approach is chosen because it's efficient.  It avoids exploring all possible subsequences, which would lead to exponential time complexity. The greedy approach ensures that the generated subsequence has maximum length while satisfying the constraint.


**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is a string (`s`) to store the input binary string.  Integers (`z`, `n`, `k`, `pow`) and a long (`val`) are used for numerical calculations. No other significant data structure is needed.
* **Algorithms:** The core algorithm is a greedy iteration from right to left.  No sophisticated algorithm (like dynamic programming or backtracking) is required. The algorithm utilizes bit manipulation (left shift `<<`) for efficiently calculating the decimal value of the binary subsequence.


**4. Code Walkthrough:**

* `int z = 0; int n = s.length();`: Initializes `z` (count of '0's) to 0 and `n` (length of the string) to the string's length.
* `for(char c : s.toCharArray()) if(c == '0') z++;`: This loop counts the total number of '0's in the string. This is a preprocessing step to quickly adjust the subsequence length when encountering a '1' that exceeds `k`.
* `int pow = 0; long val = 0;`: Initializes `pow` (power of 2 for each bit) and `val` (decimal value of the subsequence) to 0.
* `for(int i = n-1 ; i>=0 ; i--)`: This loop iterates through the string from right to left.
* `if(s.charAt(i) == '0') z--;`: If the character is '0', decrement `z`. This accounts for removing a '0' from the subsequence if the algorithm later excludes some initial part of the string.
* `else val += (1L << pow);`: If the character is '1', add 2<sup>pow</sup> to `val`. This correctly adds the value of this bit to the total.  `1L` ensures that the addition is done using `long` to avoid integer overflow.
* `pow++;`: Increment `pow` to reflect the next bit position.
* `if(val <= k) continue;`: If the current `val` is still less than or equal to `k`, continue the loop.
* `else return n-i+z-1;`: If `val` exceeds `k`, this line calculates and returns the length of the longest subsequence found so far.  `n - i` gives the length of the subsequence considered up to the current position; `z -1` is added because we remove the current `1` and  `z` keeps track of how many trailing zeros we have.
* `return n;`: If the loop completes without exceeding `k`, it means the entire string satisfies the condition, so the length of the string is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input string.  The code iterates through the string once. All other operations (counting zeros, bit manipulation) are done in constant time relative to the string's length.
* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space to store variables regardless of the input size.  It does not use any data structures that scale with the input size.

The solution is efficient and optimally solves the problem with linear time complexity, making it suitable even for large binary strings.
