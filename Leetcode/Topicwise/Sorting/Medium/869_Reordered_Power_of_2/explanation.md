## LeetCode: Reordered Power of 2 - Solution Explanation

**1. Problem Understanding:**

The problem asks us to determine if a given integer `n` can be reordered to form a power of 2.  In other words, we need to check if we can rearrange the digits of `n` to create a number that is equal to 2 raised to some integer power.


**2. Approach / Intuition:**

The solution uses a brute-force approach combined with efficient string manipulation.  The core idea is to:

1. **Normalize the input:** Convert the input integer `n` into a string, sort its digits, and convert it back to a string. This creates a "canonical representation" of the input, regardless of digit order.
2. **Iterate through powers of 2:** Generate powers of 2 (up to 2<sup>30</sup>, which is sufficient to cover numbers with at most 10 digits) and perform the same normalization for each power of 2.
3. **Compare canonical representations:** For each power of 2, compare its normalized string representation to the normalized representation of the input. If they match, it means the digits of the input can be rearranged to form a power of 2, and we return `true`.
4. **Return false if no match is found:** If the loop completes without finding a match, it means the input cannot be reordered to form a power of 2, so we return `false`.

This approach is chosen because it efficiently handles the digit rearrangement aspect of the problem.  Sorting the digits allows for easy comparison of numbers with different digit orders.  While brute force, iterating through powers of 2 is manageable due to their limited range within the context of the problem.


**3. Data Structures and Algorithms:**

* **Data Structures:** Strings (`String`) and character arrays (`char[]`) are used to represent and manipulate the digits of numbers.
* **Algorithms:** The core algorithm is a brute-force search combined with string sorting (`Arrays.sort()`). The sorting algorithm used within `Arrays.sort()` is typically a highly optimized comparison-based sorting algorithm like mergesort or quicksort (implementation-dependent).


**4. Code Walkthrough:**

```java
class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = String.valueOf(n);  // Convert input integer to string
        char[] arr = s.toCharArray(); // Convert string to char array
        Arrays.sort(arr);            // Sort the char array (in-place)
        s = new String(arr);         // Convert sorted char array back to string (canonical form)

        for(int i = 0; i<=30; i++) { // Iterate through powers of 2 (up to 2^30)
            int nn = (int) Math.pow(2, i);  // Calculate power of 2
            String ss = String.valueOf(nn);  // Convert to string
            char[] arrr = ss.toCharArray();  // Convert to char array
            Arrays.sort(arrr);               // Sort the char array
            ss = new String(arrr);            // Convert sorted char array back to string (canonical form)
            if(s.equals(ss)) return true;    // Compare canonical forms; return true if they match
        }

        return false; // Return false if no match is found
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(M log M + K log K), where M is the number of digits in the input number `n` and K is the maximum number of digits in a power of 2 (which is a constant, approximately log<sub>10</sub>(2<sup>30</sup>) ≈ 9). The dominant operations are the sorting of the input digits (O(M log M)) and the sorting of the digits of each power of 2 (O(K log K) repeated for a constant number of times, O(K log K)). The string conversions and comparisons take linear time, which is less significant compared to sorting.

* **Space Complexity:** O(M + K), where M and K are defined as above.  The space used is dominated by the character arrays created for the input number and for each power of 2.  This space is linear in the number of digits.  The space used by the strings is proportional to M and K, which are considered to be relatively small and relatively constant.

In essence, the algorithm has a near-linear time complexity because the number of digits involved is relatively small in the context of the problem (maximum 10 digits).  The space complexity is also linear in the number of digits.  The use of strings for manipulation might increase the constant factor of space complexity, but this is not significantly impacting asymptotic analysis.
