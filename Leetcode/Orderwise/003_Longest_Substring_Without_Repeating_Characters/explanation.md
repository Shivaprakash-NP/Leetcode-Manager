## LeetCode: Longest Substring Without Repeating Characters - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the length of the longest substring within a given string that contains no repeating characters.  For example, in the string "abcabcbb", the longest substring without repeating characters is "abc", which has a length of 3.

**2. Approach / Intuition:**

This solution uses a sliding window approach with a `HashMap` to efficiently track the most recent index of each character encountered.  The sliding window expands from the left (`l`) and right (`r`) pointers.

The core logic is as follows:

* We maintain a `HashMap` (`map`) to store each character encountered and its most recent index.
* The right pointer (`r`) iterates through the string.
* If a character is already in the `map` and its index is within the current window (`l <= map.get(s.charAt(r))`), it means we've encountered a repeating character.  We then move the left pointer (`l`) to the right of the previous occurrence of that character to shrink the window and eliminate the repetition.
* We update `len` (the length of the longest substring found so far) to the maximum of `len` and the current window size (`r - l + 1`).
* We update the character's index in the `map` with the current `r` index.
* This process continues until the right pointer reaches the end of the string.

This approach is efficient because it avoids unnecessary checks. Instead of repeatedly checking substrings, we dynamically adjust the window based on the presence of repeating characters.


**3. Data Structures and Algorithms:**

* **Data Structures:** `HashMap` (or `Hashtable`) is used to store characters and their indices. This provides O(1) average time complexity for insertion, lookup, and deletion.
* **Algorithms:** Sliding Window technique is employed to efficiently traverse the string and find the longest substring.


**4. Code Walkthrough:**

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character , Integer> map = new HashMap<>(); // Hashmap to store character and its index
        int len = 0; // Length of the longest substring found so far
        int l = 0, r = 0; // Left and right pointers of the sliding window

        while(r<s.length()) { // Iterate through the string using the right pointer
            if(map.containsKey(s.charAt(r)) && l<=map.get(s.charAt(r))) { // Check for repetition within the window
                l = map.get(s.charAt(r)) + 1; // Move left pointer to eliminate repetition
            }
            len = Math.max(len , r-l+1); // Update the maximum length
            map.put(s.charAt(r) , r++); // Update the character's index and increment the right pointer
        }
        return len; // Return the length of the longest substring
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the string. In the worst case, the right pointer iterates through the entire string once.  While the left pointer might move multiple times, the total number of movements of both pointers is at most 2n which simplifies to O(n).

* **Space Complexity:** O(min(m, n)), where n is the length of the string and m is the size of the character set (e.g., 256 for ASCII). The space used by the `HashMap` is proportional to the number of unique characters in the substring, which is at most the minimum of the length of the string and the size of the character set.  In the worst case (all characters unique), the space used would be O(n).  If the character set is small (e.g., only lowercase English letters), it would be O(m).


**In summary,** this solution provides an efficient and concise way to solve the "Longest Substring Without Repeating Characters" problem using a sliding window approach and a `HashMap` for character tracking. The time and space complexities are optimized for good performance.
