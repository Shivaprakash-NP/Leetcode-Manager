## LeetCode: Maximum Difference Between Even and Odd Frequency I - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum difference between the frequency of a character with even frequency and the frequency of a character with odd frequency in a given string.  If no such difference can be formed (e.g., all characters have odd frequencies or all have even frequencies), the result should be the difference between Integer.MAX_VALUE and Integer.MIN_VALUE (resulting in a very large positive number).

**2. Approach / Intuition:**

The solution uses a frequency counting approach. We first count the occurrences of each character in the string. Then, we iterate through the frequency counts. We maintain two variables: `a1` to track the maximum odd frequency and `a2` to track the minimum even frequency. Finally, we return the difference `a1 - a2`. This approach is efficient because it involves a single pass through the string and another pass through the frequency array (which has a fixed size of 26).  It directly addresses the problem statement by efficiently identifying the relevant frequencies.

**3. Data Structures and Algorithms:**

* **Data Structure:** An integer array `map` of size 26 is used as a frequency counter.  Each index represents a character (a-z), and the value at that index stores its frequency.
* **Algorithm:** The algorithm employs a simple frequency counting technique followed by a linear scan to find the minimum even and maximum odd frequencies.

**4. Code Walkthrough:**

```java
class Solution {
    public int maxDifference(String s) {
        int[] map = new int[26]; // Initialize a frequency array for lowercase letters (a-z)
        int a1 = Integer.MIN_VALUE; // Initialize a1 to the smallest possible integer (stores max odd frequency)
        int a2 = Integer.MAX_VALUE; // Initialize a2 to the largest possible integer (stores min even frequency)

        for(char c : s.toCharArray()) map[c-'a']++; // Count the frequency of each character
        
        for(int v : map) { // Iterate through the frequency array
            if(v!=0) { //Check if the character exists in the string
                if(v%2 == 0) a2 = Math.min(a2 , v); // Update a2 with the minimum even frequency
                else a1 = Math.max(a1 , v); // Update a1 with the maximum odd frequency
            }
        }
        return a1-a2; // Return the difference between the maximum odd and minimum even frequency
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N + K), where N is the length of the input string `s` and K is the size of the alphabet (26 in this case). The first loop iterates through the string once (O(N)), and the second loop iterates through the frequency array once (O(K)).  Since K is a constant, the overall time complexity is effectively O(N).

* **Space Complexity:** O(K), where K is the size of the alphabet (26). This is because we use a frequency array of size 26 to store the character frequencies. The space used is constant and does not depend on the input string length.  Therefore, the space complexity is O(1) in terms of the input size.


In summary, this solution efficiently solves the problem using a frequency counting approach with a time complexity linear to the input string length and a constant space complexity. The use of `Integer.MIN_VALUE` and `Integer.MAX_VALUE` handles edge cases gracefully, ensuring correct results even when no even or odd frequencies exist.
