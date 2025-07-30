## LeetCode: Number of Substrings Containing All Three Characters - Solution Explained

**1. Problem Understanding:**

The problem asks us to count the number of substrings within a given string `s` that contain all three characters 'a', 'b', and 'c'.  For example, if `s = "abcabc"`, the substrings containing all three characters are "abc", "abca", "abcab", "abcabc", "bcabc", "cab", "cabc", "abc" and "abcabc". This means there are 9 such substrings in total.

**2. Approach / Intuition:**

The solution employs a clever sliding window technique combined with a frequency array. Instead of explicitly generating all substrings and checking if they contain 'a', 'b', and 'c', it iterates through the string only once.  The core idea is to maintain the last seen index of each character ('a', 'b', 'c').  At each position, the minimum of these last seen indices determines the length of the valid substring ending at that position.  This is because any substring starting before the minimum index would *not* contain all three characters.  The algorithm then adds the length of this valid substring to the total count.  This approach avoids redundant checks and significantly improves efficiency.

**3. Data Structures and Algorithms:**

* **Data Structures:** An integer array `map` of size 3 is used to store the last seen index of each character ('a', 'b', 'c').  The index in `map` corresponds to the character's position in the alphabet (a=0, b=1, c=2).
* **Algorithms:** The algorithm uses a single-pass linear scan of the string, employing the sliding window concept implicitly.  The core logic relies on maintaining and updating the last seen indices and calculating the count based on the minimum of these indices.

**4. Code Walkthrough:**

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] map = new int[3]; // Initialize array to store last seen indices of 'a', 'b', 'c' (-1 indicates not seen yet).
        Arrays.fill(map , -1); 
        int ans = 0; // Initialize the count of valid substrings.
        for(int i = 0 ; i<s.length() ; i++) {
            map[s.charAt(i)-'a'] = i; // Update the last seen index of the current character.
            ans+=(Math.min(Math.min(map[0] , map[1]) , map[2]) + 1); // Add the length of valid substring ending at i.
        }
        return ans;
    }
}
```

* **`int[] map = new int[3]; Arrays.fill(map , -1);`**:  Initializes an array `map` to track the last seen indices of 'a', 'b', and 'c'.  `-1` indicates that the character hasn't been seen yet.

* **`int ans = 0;`**: Initializes a variable `ans` to store the total count of substrings containing all three characters.

* **`for(int i = 0 ; i<s.length() ; i++) { ... }`**: This loop iterates through the string `s`.

* **`map[s.charAt(i)-'a'] = i;`**: This line updates the last seen index of the current character.  `s.charAt(i) - 'a'` calculates the index in `map` (0 for 'a', 1 for 'b', 2 for 'c').

* **`ans+=(Math.min(Math.min(map[0] , map[1]) , map[2]) + 1);`**: This is the core logic.  `Math.min(Math.min(map[0], map[1]), map[2])` finds the minimum of the last seen indices of 'a', 'b', and 'c'.  This minimum index represents the starting point of the longest valid substring ending at index `i`. Adding 1 gives the length of this substring, which is added to `ans`.

* **`return ans;`**: Returns the total count of valid substrings.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the string. The code iterates through the string only once.  All operations within the loop are constant time.

* **Space Complexity:** O(1). The `map` array has a constant size of 3, regardless of the input string length.  Therefore, the space used is constant.

This solution is highly efficient due to its linear time complexity and constant space usage, making it optimal for solving the "Number of Substrings Containing All Three Characters" problem.
