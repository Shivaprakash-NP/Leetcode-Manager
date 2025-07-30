## LeetCode Problem: Maximum Difference by Remapping a Digit - Explained

**1. Problem Understanding:**

The problem asks us to find the maximum possible difference between two numbers derived from a given integer `num`.  We achieve this by replacing a single digit in `num` with '9' to create the maximum number (`s1`), and replacing a single digit (different from the first replacement) with '0' to create the minimum number (`s2`). The final answer is the difference between these two resulting numbers (`s1 - s2`).

**2. Approach / Intuition:**

The solution uses a greedy approach. To maximize the difference, we need to make the larger number as large as possible and the smaller number as small as possible.  Therefore, we find the first digit that's not a '9' and replace it with '9' to obtain the maximum number. Similarly, we find the first digit that's not a '0' and replace it with '0' to obtain the minimum number. This approach guarantees the maximum possible difference is achieved by only modifying one digit each time. We avoid unnecessary iterations by stopping the search for `c1` and `c2` as soon as both are found.

**3. Data Structures and Algorithms:**

* **Data Structures:** Strings (`String n`, `s1`, `s2`) are used to efficiently manipulate the digits of the input number.
* **Algorithms:** The core algorithm is a simple linear scan (`for` loop) through the digits of the number. String manipulation functions like `replace()` are used. No sophisticated algorithms are required.


**4. Code Walkthrough:**

* **Lines 4-5:**  The input integer `num` is converted to a string `n`. `c1` and `c2` are initialized to null characters (`\0`) to indicate that the digits to be replaced haven't been found yet.
* **Lines 7-12:** This `for` loop iterates through each digit of the string `n`.
    * `if (c1 == '\0' && c != '9') c1 = c;`: If `c1` is still not found (meaning no non-'9' digit has been found yet) and the current digit `c` is not '9', then `c` is assigned to `c1`.
    * `if (c2 == '\0' && c != '0') c2 = c;`: Similarly, this checks for the first non-'0' digit.
    * `if (c1 != '\0' && c2 != '\0') break;`: The loop terminates early once both `c1` and `c2` are found. This improves efficiency by avoiding unnecessary iterations.
* **Lines 14-15:**  `String.replace()` is used to create the maximum number `s1` by replacing all occurrences of `c1` with '9', and the minimum number `s2` by replacing all occurrences of `c2` with '0'.
* **Line 17:** The difference between `s1` (parsed as an integer) and `s2` (parsed as an integer) is calculated and returned.

**5. Time and Space Complexity:**

* **Time Complexity:** O(m), where m is the number of digits in the input integer `num`. This is due to the single loop that iterates through the digits of the number. The `replace()` operation in strings takes linear time in the worst case.
* **Space Complexity:** O(m), where m is the number of digits in the input integer. This is because we create new strings `s1` and `s2` that have the same length as the input string `n`.  The space used by `c1` and `c2` is negligible.


**Improvements:**

While the given code is functional, a slight optimization could be to check for the edge case where `num` contains only 9s or only 0s before entering the loop. This would make the algorithm slightly faster for those edge cases.  However, this optimization would not change the overall time and space complexity.
