## LeetCode: Longest Repeating Character Replacement Explained

**1. Problem Understanding:**

The problem "Longest Repeating Character Replacement" asks us to find the length of the longest substring containing the same character after performing at most `k` character replacements.  In other words, we can change at most `k` characters in a substring to make all characters the same.  We need to find the longest such substring.


**2. Approach / Intuition:**

The solution employs a **sliding window** approach.  This approach is efficient because it avoids redundant calculations by only focusing on a portion of the string at a time. The core idea is:

* We maintain a sliding window within the string `s`.
* `map` array tracks the frequency of each character within the window.
* `maxf` keeps track of the maximum frequency of any character in the current window.
* `r-l+1` represents the current window size.
* `r-l+1 - maxf` represents the number of characters that need to be replaced to make all characters in the window the same.  This is essentially the number of characters *different* from the most frequent character.

If `r-l+1 - maxf` (characters needing replacement) exceeds `k` (allowed replacements), it means the window is invalid (too many replacements needed). We then shrink the window from the left (`l++`) by reducing the count of the character leaving the window.  This process continues until a valid window is found or the entire string is processed.

This approach is chosen because it efficiently explores all possible substrings without brute-force checking every possible substring, which would be much less efficient (O(n^2) or worse).


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `map`: An integer array of size 26 to store the frequency of each uppercase character (A-Z). This acts as a frequency counter.
    * `s`: The input string.

* **Algorithm:**
    * Sliding Window technique.


**4. Code Walkthrough:**

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int len = 0; // Stores the length of the longest substring found so far.
        int l = 0 , r = 0; // Pointers for the sliding window (l: left, r: right).
        int c = 0; //Not used
        int[] map = new int[26]; //Frequency array for characters (A-Z).
        int maxf = 0; // Maximum frequency of a character in the current window.

        while(r < s.length()) {
            map[s.charAt(r)-'A']++; // Increment frequency of character at index r.
            maxf = Math.max(maxf , map[s.charAt(r)-'A']); // Update maxf.
            //If characters that needs to be replaced is greater than k then shrink window
            if(r-l+1-maxf > k) { 
                map[s.charAt(l++)-'A']--; // Decrement frequency of character leaving the window.
            }
            len = Math.max(len , r-l+1); // Update the length of the longest substring.
            r++; // Move the right pointer.
        }
        return len; // Return the length of the longest substring.
    }
}
```


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the string. The `while` loop iterates through the string at most twice (once for each pointer).  All operations inside the loop are O(1).

* **Space Complexity:** O(1). The `map` array has a constant size of 26, regardless of the input string length.  The other variables use constant extra space.


In summary, this solution efficiently solves the "Longest Repeating Character Replacement" problem using a sliding window technique. The use of a frequency array allows for constant-time character frequency updates, leading to an optimal linear time complexity. The space complexity is also constant, making the solution highly efficient for large input strings.
