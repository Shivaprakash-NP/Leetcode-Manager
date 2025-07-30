## LeetCode: Max Difference You Can Get From Changing an Integer - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the maximum difference achievable by replacing one or more digits in a given integer `num` with other digits (0-9).  We need to find the smallest and largest possible integers that can be created by such replacements, and then return the difference between them.  Crucially, the resulting numbers cannot start with a '0', and replacing a digit with itself is disallowed.


**2. Approach / Intuition:**

The solution employs a brute-force approach. It iterates through all possible digit replacements. For each digit in the original number, it tries replacing it with every other digit (0-9). This generates a large set of candidate numbers.  While brute-force might seem inefficient for very large numbers, the constraint that we're only changing digits within a given number keeps the solution tractable.  A more optimized approach might involve clever digit manipulation and analyzing digit positions, but this brute-force solution prioritizes simplicity and clarity.

**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is a `String` to represent the integer.  Strings allow for easy character-by-character manipulation, which is essential for replacing digits.  Integers (`int`) are also used to store the minimum and maximum values.
* **Algorithms:** The core algorithm is a nested loop for brute-force exploration of all possible digit replacements. `Math.min()` and `Math.max()` are used to efficiently track the minimum and maximum values encountered. `Integer.parseInt()` converts strings back to integers.

**4. Code Walkthrough:**

* **Initialization:** `min` is initialized to `Integer.MAX_VALUE` and `max` to `Integer.MIN_VALUE`. This ensures that the first valid number encountered will update `min` and `max` correctly. The input number is converted to a string `original` for easier manipulation.

* **Nested Loops:** The outer loop iterates through each digit ('0' to '9') as a candidate for replacement (`i`). The inner loop iterates through each digit ('0' to '9') as the replacement digit (`j`).

* **Replacement and Validation:** `original.replace(i, j)` replaces all occurrences of digit `i` with digit `j` in the `original` string.  The `if (replaced.charAt(0) == '0' || i == j) continue;` statement handles two important constraints:
    * It skips the replacement if the resulting number starts with '0' (invalid).
    * It skips the replacement if the replacement digit is the same as the original digit (invalid).

* **Update Minimum and Maximum:** If a valid replacement is made, `Integer.parseInt(replaced)` converts the modified string back into an integer. Then, `min` and `max` are updated using `Math.min()` and `Math.max()` to track the smallest and largest values encountered.

* **Return Value:** Finally, the function returns `max - min`, which represents the maximum difference achievable.

**5. Time and Space Complexity:**

* **Time Complexity:** The nested loops iterate through 10 * 10 = 100 possible replacements for each digit in the input number. Let's assume the input number has 'n' digits.  The time complexity is thus O(n * 100), which simplifies to O(n). The String manipulation operations within the loop (replace, parseInt) have constant time complexity relative to the input number size.

* **Space Complexity:** The space complexity is O(1).  The algorithm uses a constant amount of extra space to store variables like `min`, `max`, and `original`. The space used by the intermediate `replaced` string is also constant relative to the input size.  The space does not grow significantly with the size of the input number.

In summary, this solution, despite using a brute-force approach, has a linear time complexity and constant space complexity making it efficient enough for most practical inputs on LeetCode.  More advanced algorithms could potentially offer better performance for extremely large input numbers, but this solution balances simplicity and efficiency effectively.
