### Problem Understanding

The problem "Count Residue Prefixes" asks us to iterate through all possible prefixes of a given input string `s` and count how many of them satisfy a specific condition. For each prefix `s[0...i]` (meaning the substring from index 0 up to and including index `i`), we need to check if the number of unique characters within that prefix is equal to `(length of the prefix) % 3`. The length of the prefix `s[0...i]` is `i+1`.

So, the condition for a prefix `s[0...i]` to be a "residue prefix" is:
`Number of unique characters in s[0...i] == (i + 1) % 3`

### Approach / Intuition

The core idea is to process the string character by character, building up prefixes incrementally. As we extend the prefix, we need an efficient way to keep track of the unique characters encountered so far.

1.  **Iterate through prefixes:** We can use a simple `for` loop to iterate from the first character to the last character of the string. Each iteration `i` corresponds to the prefix `s[0...i]`.
2.  **Track unique characters:** For each prefix, we need to know the count of unique characters. A `HashSet<Character>` is an ideal data structure for this. As we move from `s[0...i-1]` to `s[0...i]`, we simply add the new character `s.charAt(i)` to our set. The `HashSet` automatically handles duplicates, so its `size()` method will always give us the correct count of unique characters in the current prefix.
3.  **Check the condition:** In each iteration, after updating the set with the current character, we compare `set.size()` with `(i + 1) % 3`. If they are equal, we increment a counter.
4.  **Return the count:** After iterating through all characters (and thus all prefixes), the final count is returned.

This approach is chosen because it's straightforward, efficient, and directly addresses the problem requirements by incrementally building state (unique characters) and checking the condition at each step.

### Data Structures and Algorithms

*   **Data Structure:**
    *   `HashSet<Character>`: Used to efficiently store and retrieve the count of unique characters encountered in the current prefix. Its key advantage is O(1) average time complexity for `add` and `size` operations.
*   **Algorithm:**
    *   **Iteration:** A simple `for` loop is used to iterate through the input string, processing each character sequentially.

### Code Walkthrough

```java
class Solution {
    public int residuePrefixes(String s) {
        // 1. Initialize a HashSet to store unique characters.
        // This set will be updated for each prefix s[0...i].
        Set<Character> set = new HashSet<>();

        // 2. Initialize a counter for the number of "residue prefixes".
        int cnt = 0;

        // 3. Loop through the input string 's' character by character.
        // 'i' represents the current index, and also the end of the current prefix s[0...i].
        for(int i = 0; i<s.length(); i++) {
            // 4. Get the current character at index 'i'.
            char c = s.charAt(i);

            // 5. Add the current character to the set.
            // If the character is already present, the set remains unchanged.
            // This ensures 'set' always contains only the unique characters
            // from the prefix s[0...i].
            set.add(c);

            // 6. Check the core condition:
            //    set.size() gives the number of unique characters in s[0...i].
            //    (i+1) is the length of the current prefix s[0...i].
            //    (i+1)%3 calculates the remainder when the prefix length is divided by 3.
            //    If these two values are equal, increment the counter.
            if(set.size() == (i+1)%3) {
                cnt++;
            }
        }

        // 7. After checking all prefixes, return the total count.
        return cnt;
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(N)**
    *   Let `N` be the length of the input string `s`.
    *   The code contains a single `for` loop that iterates `N` times (from `i = 0` to `N-1`).
    *   Inside the loop:
        *   `s.charAt(i)` takes O(1) time.
        *   `set.add(c)` takes O(1) on average for a `HashSet`. In the worst case, due to hash collisions, it could be O(K) where K is the number of elements in the set, but for character sets (which are typically small and bounded by the alphabet size), it's practically O(1).
        *   `set.size()` takes O(1) time.
        *   The modulo operation `(i+1)%3` takes O(1) time.
    *   Since all operations inside the loop are constant time on average, the total time complexity is dominated by the loop, resulting in O(N).

*   **Space Complexity: O(1)**
    *   The `HashSet<Character>` stores unique characters encountered in the string.
    *   The maximum number of unique characters possible is bounded by the size of the character set (e.g., 26 for lowercase English letters, 256 for extended ASCII, or a larger but fixed constant for Unicode characters).
    *   Since the alphabet size is considered a constant, the space occupied by the `HashSet` does not grow with the length of the input string `N`. It's bounded by a constant value.
    *   The other variables (`cnt`, `i`, `c`) use a constant amount of space.
    *   Therefore, the total space complexity is O(1).