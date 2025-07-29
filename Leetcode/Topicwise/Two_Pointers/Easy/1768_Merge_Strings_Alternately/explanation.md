## LeetCode: Merge Strings Alternately - Solution Explained

**1. Problem Understanding:**

The problem asks us to merge two strings, `word1` and `word2`, alternately.  This means taking characters from `word1` and `word2` one at a time, in an alternating fashion, to create a new string.  If one string is shorter than the other, the remaining characters of the longer string should be appended to the end of the merged string.

**2. Approach / Intuition:**

The solution uses a two-pointer approach along with a `StringBuilder` for efficient string manipulation.  We maintain two pointers, `l` and `r`, to iterate through `word1` and `word2` respectively.  The `while` loop iterates as long as both pointers are within the bounds of their respective strings.  Inside the loop, we append characters from `word1` and `word2` alternately to the `StringBuilder`.  After this loop, one string might have remaining characters;  the subsequent `while` loops handle appending those remaining characters. A `StringBuilder` is chosen over repeated string concatenation because string concatenation in Java creates new String objects repeatedly, leading to poor performance for large strings.  `StringBuilder` allows for efficient in-place modification.

**3. Data Structures and Algorithms:**

* **Data Structures:** `StringBuilder` is used to build the merged string efficiently.
* **Algorithms:** The core algorithm is a two-pointer approach combined with iterative string building.

**4. Code Walkthrough:**

* **`StringBuilder sb = new StringBuilder();`**:  A `StringBuilder` object is created to store the merged string. Using `StringBuilder` is crucial for efficiency, particularly with large input strings.

* **`int l = 0; int r = 0;`**: Two integer variables, `l` and `r`, are initialized to 0. These act as pointers to traverse `word1` and `word2`, respectively.

* **`while(l < word1.length() && r < word2.length()) { ... }`**: This `while` loop continues as long as both `l` and `r` are within the bounds of their corresponding strings.  Inside the loop:
    * **`sb.append(word1.charAt(l));`**: The character at index `l` of `word1` is appended to `sb`.
    * **`sb.append(word2.charAt(r));`**: The character at index `r` of `word2` is appended to `sb`.
    * **`l++; r++;`**: The pointers `l` and `r` are incremented to move to the next characters.

* **`while(l < word1.length()) sb.append(word1.charAt(l++));`**: This loop appends any remaining characters from `word1` if `word1` is longer than `word2`.  The post-increment (`l++`) cleverly increments `l` *after* using its current value.

* **`while(r < word2.length()) sb.append(word2.charAt(r++));`**: This loop appends any remaining characters from `word2` if `word2` is longer than `word1`.  Similar to the previous loop, post-increment is used for efficiency.

* **`return sb.toString();`**: Finally, the `StringBuilder` is converted to a String using `toString()` and returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(m + n), where m and n are the lengths of `word1` and `word2` respectively.  Each character is visited and appended to the `StringBuilder` once. The while loops iterate at most m + n times in total.

* **Space Complexity:** O(m + n).  The `StringBuilder` stores the merged string, which has a maximum length of m + n.  The space used by the pointers `l` and `r` is constant, O(1).  Therefore, the dominant factor is the space used by the `StringBuilder`.


This solution is efficient because it iterates through the input strings only once and utilizes `StringBuilder` for optimized string manipulation.  The time and space complexities are linear, which is optimal for this problem.
