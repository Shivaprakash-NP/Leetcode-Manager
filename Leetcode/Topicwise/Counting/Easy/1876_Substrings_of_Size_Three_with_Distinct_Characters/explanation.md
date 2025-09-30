## LeetCode Problem: Substrings of Size Three with Distinct Characters - Explanation

### 1. Problem Understanding:

The problem asks us to count the number of "good" substrings of length 3 within a given string `s`. A "good" substring is defined as a substring that contains only distinct characters (i.e., no character appears more than once).

### 2. Approach / Intuition:

The core idea is to use a sliding window approach. A sliding window of size 3 is maintained, and the following logic is applied:

*   **Expanding the Window:** The right boundary of the window (`r`) moves through the string. For each character encountered, its frequency is tracked using a HashMap.

*   **Shrinking the Window:** If the window size exceeds 3, the left boundary (`l`) is moved to the right until the window size is exactly 3. When the left boundary shifts, the character at the previous left boundary position is removed from the window's frequency count. If the count drops to zero, we remove the character from the map entirely.

*   **Checking for "Good" Substring:** Once the window size is 3, it's checked if all characters in the window are distinct. This is done by checking if the size of the HashMap (containing unique characters) is equal to 3. If it is, then increment the `ans` counter.

Why this approach?

The sliding window technique is efficient for problems involving contiguous subarrays or substrings. It avoids redundant computations by maintaining a window that slides through the data, updating the necessary information instead of recalculating it for each possible substring. Using a HashMap allows efficient tracking of character frequencies within the window, which is crucial for determining if the substring contains only distinct characters.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `HashMap` (to store character frequencies within the sliding window).
*   **Algorithm:** Sliding Window

### 4. Code Walkthrough:

```java
class Solution {
    public int countGoodSubstrings(String s) {
        int n = s.length(); // Store the length of the string.
        int l = 0; // Left boundary of the sliding window.
        int ans = 0; // Counter for the number of "good" substrings.
        Map<Character, Integer> map = new HashMap<>(); // HashMap to store character frequencies within the window.

        for(int r = 0; r<n; r++) { // Iterate through the string using the right boundary 'r'.
            char c = s.charAt(r); // Get the character at the current right boundary.
            map.put(c, map.getOrDefault(c, 0)+1); // Update the frequency of the character in the HashMap.

            while(r-l+1 > 3) { // Shrink the window if its size exceeds 3.
                char lc = s.charAt(l++); // Get the character at the left boundary and increment 'l'.
                map.put(lc, map.get(lc)-1); // Decrement the frequency of the character in the HashMap.
                if(map.get(lc) == 0) map.remove(lc); // If the frequency becomes 0, remove the character from the map.
            }

            if(r-l+1 == 3)  //If window size is 3
                if(map.size() == 3) ans++; // Check if all characters in the window are distinct (map.size() == 3). If they are, increment the answer.
        }

        return ans; // Return the total number of "good" substrings.
    }
}
```

**Detailed Breakdown:**

1.  **Initialization:**
    *   `n = s.length();`:  Stores the length of the input string.
    *   `l = 0;`: Initializes the left pointer of the sliding window to 0 (start of the string).
    *   `ans = 0;`: Initializes a counter to store the number of "good" substrings.
    *   `map = new HashMap<>();`: Creates a HashMap to store the frequency of each character in the current window.

2.  **Outer Loop (Sliding Window):**
    *   `for(int r = 0; r < n; r++)`:  The `r` pointer iterates through the string, effectively expanding the sliding window.
    *   `char c = s.charAt(r);`: Gets the character at the current right pointer position.
    *   `map.put(c, map.getOrDefault(c, 0) + 1);`: Updates the frequency count of character `c` in the `map`.  `getOrDefault(c, 0)` returns the current frequency of `c` (or 0 if `c` is not yet in the map), and then 1 is added.

3.  **Inner Loop (Shrinking the Window):**
    *   `while (r - l + 1 > 3)`: This `while` loop ensures that the window size never exceeds 3. It shrinks the window from the left when necessary.
    *   `char lc = s.charAt(l++);`: Gets the character at the left pointer position (`l`) and then increments `l` to move the left boundary to the right.
    *   `map.put(lc, map.get(lc) - 1);`: Decrements the frequency of the character `lc` in the `map`.
    *   `if (map.get(lc) == 0) map.remove(lc);`: If the frequency of `lc` becomes 0 after decrementing, it means `lc` is no longer present in the window, so it's removed from the map.

4.  **Checking for "Good" Substring:**
    *   `if (r - l + 1 == 3)`: Checks if the window size is exactly 3.
    *   `if (map.size() == 3) ans++;`: If the window size is 3, check if the size of the `map` is also 3.  If `map.size()` is 3, it means all 3 characters in the window are distinct (because the map only contains unique characters). If this condition is true, then increment the `ans` counter.

5.  **Return Result:**
    *   `return ans;`: After iterating through the entire string, the function returns the final count of "good" substrings.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the string. The outer loop iterates through the string once. The inner `while` loop, in the worst case, can also iterate n times in total, as `l` can move at most `n` times. Since the HashMap operations (`put`, `get`, `remove`) take constant time on average, the overall time complexity remains O(n).

*   **Space Complexity:** O(1). The `HashMap` can, in the worst case, store up to 3 distinct characters (since the window size is at most 3). Therefore, the space used by the `map` is constant and independent of the input string size.
