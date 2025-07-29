## LeetCode: Reverse Vowels of a String - Solution Explained

**1. Problem Understanding:**

The problem asks us to take a string as input and reverse only the vowels (a, e, i, o, u, and their uppercase counterparts) within that string, leaving the consonants in their original positions.  For example, "hello" would become "holle".

**2. Approach / Intuition:**

The solution uses a two-pointer approach with a `StringBuilder` to efficiently reverse the vowels.  A `HashSet` is used to quickly check if a character is a vowel.

We choose this approach because:

* **Two-Pointers:**  This allows us to efficiently traverse the string from both ends simultaneously, swapping vowels as we find them. This is significantly faster than iterating multiple times.
* **StringBuilder:**  Strings in Java are immutable. Using a `StringBuilder` allows us to modify the string in-place, avoiding the overhead of creating many new strings during the swapping process. This improves performance, especially for longer input strings.
* **HashSet for Vowel Check:**  Checking if a character is a vowel repeatedly using a `HashSet` provides O(1) lookup time, which is much faster than linear search.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `StringBuilder`: Used for efficient string manipulation.
    * `HashSet<Character>`: Stores vowels for O(1) lookup.
* **Algorithms:**
    * Two-pointer technique:  Pointers move from both ends of the string towards the middle.
    * In-place swapping: Vowels are swapped in place using the `StringBuilder`'s `setCharAt` method.


**4. Code Walkthrough:**

```java
class Solution {
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder(s); // Create a StringBuilder from the input string.
        Set<Character> vowels = new HashSet<>(Arrays.asList( // Create a HashSet of vowels.
    'a', 'e', 'i', 'o', 'u',
    'A', 'E', 'I', 'O', 'U'
));
        int l = 0; // Left pointer.
        int r = s.length()-1; // Right pointer.
        while(l<r) { // Iterate until the pointers cross.
            while(l<r && !vowels.contains(s.charAt(l))) l++; // Move left pointer until a vowel is found.
            while(l<r && !vowels.contains(s.charAt(r))) r--; // Move right pointer until a vowel is found.
            if(l>=r) break; // If pointers cross, we're done.
            char tem = s.charAt(l); // Swap vowels using temp variable
            sb.setCharAt(l, s.charAt(r));
            sb.setCharAt(r, tem);
            l++; // Move pointers towards the middle.
            r--;
        }
        return sb.toString(); // Return the modified string.
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the string.  We iterate through the string at most once with the two pointers. The vowel check in the `HashSet` takes O(1) time.

* **Space Complexity:** O(1). The `HashSet` of vowels uses constant space (regardless of input string size). The `StringBuilder` uses space proportional to the input string, but this is considered part of the input space and not additional space used by the algorithm.  Therefore, we can consider the space complexity to be O(1) if the output space is ignored.  If the output space is counted it becomes O(n).

In summary, this solution efficiently reverses the vowels in a string using a two-pointer approach and a `HashSet` for optimized vowel checking.  The time and space complexity are both optimal for this problem.
