## LeetCode: Greatest Common Divisor of Strings - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the greatest common divisor (GCD) of two strings.  This means finding the longest string that divides both input strings without leaving a remainder.  If such a string doesn't exist (i.e., they don't share a common divisor), we return an empty string.  Crucially, the "division" here is string concatenation; the GCD string must be repeatedly concatenated to form both input strings.


**2. Approach / Intuition:**

The solution leverages a crucial observation: if two strings, `str1` and `str2`, have a common divisor string, then concatenating them in either order (`str1+str2` or `str2+str1`) will result in the same string.  This is because the common divisor string will simply repeat. If this condition isn't met, there's no common divisor string.

If the concatenation condition holds, we compute the greatest common divisor (GCD) of the lengths of the two strings using Euclid's algorithm. This GCD represents the length of the common divisor string. We then extract a substring of this length from `str1` (or `str2`; it's the same) and return it.

This approach is efficient because finding the GCD of two integers is a very fast operation (logarithmic time complexity).  It avoids brute-force string comparison methods which would be significantly slower for large strings.


**3. Data Structures and Algorithms:**

* **Data Structures:** Strings are the primary data structure used.
* **Algorithms:**
    * **Euclid's algorithm:** Used to find the GCD of the lengths of the two input strings.  This is a recursive implementation.
    * **String concatenation and substring extraction:** Basic string operations used to check the concatenation condition and extract the result.


**4. Code Walkthrough:**

```java
class Solution {
    private int gcd(int a, int b) { //Euclid's algorithm for GCD
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public String gcdOfStrings(String str1, String str2) {
        if(str1.equals(str2)) return str1; //Base case: If strings are equal, the GCD is the string itself.
        if(!(str1+str2).equals(str2+str1)) return ""; //If concatenations are different, no common divisor exists.
        int n = str1.length();
        int m = str2.length();
        int gcd = gcd(n,m); //Find GCD of string lengths.
        return str1.substring(0,gcd); //Extract and return the GCD string.
    }
}
```

* **`gcd(int a, int b)`:** This is a standard recursive implementation of Euclid's algorithm to find the greatest common divisor of two integers.

* **`gcdOfStrings(String str1, String str2)`:**
    * It first checks if `str1` and `str2` are equal. If they are, the GCD is simply `str1`.
    * It then checks the crucial concatenation condition: `(str1+str2).equals(str2+str1)`.  If this is false, there's no common divisor, so it returns an empty string.
    * If the concatenation condition holds, it gets the lengths of the strings and uses the `gcd` function to calculate the GCD of these lengths.
    * Finally, it extracts a substring of length `gcd` from `str1` (or `str2`, it's equivalent) and returns it.


**5. Time and Space Complexity:**

* **Time Complexity:** O(log(min(m, n))), where m and n are the lengths of str1 and str2 respectively. This is dominated by the time taken by Euclid's algorithm to compute the GCD, which has a logarithmic time complexity. String concatenation and substring extraction take linear time in the worst case, but this is dwarfed by the logarithmic time of the GCD calculation.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space irrespective of the input string lengths.  The recursive calls in Euclid's algorithm use space on the call stack, but this is also logarithmic in the input size.  We are not creating any new data structures that scale with the input size.

Therefore, this solution is highly efficient for finding the GCD of strings, significantly better than brute-force approaches.
