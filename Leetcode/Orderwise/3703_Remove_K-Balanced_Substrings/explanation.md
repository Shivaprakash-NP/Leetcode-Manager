## LeetCode Problem: Remove K-Balanced Substrings - Explanation

Here's a detailed explanation of the provided Java code solution.

### 1. Problem Understanding:

The problem, "Remove K-Balanced Substrings", requires us to remove all occurrences of a specific "k-balanced substring" from a given string `s`. A k-balanced substring is a string consisting of `k` opening parentheses "(" followed by `k` closing parentheses ")".  Our task is to repeatedly remove these k-balanced substrings from `s` until no more occurrences exist and then return the resulting string.

### 2. Approach / Intuition:

The solution employs a straightforward iterative approach that builds the result string incrementally using a `StringBuilder`.

The core idea is to maintain a `StringBuilder` (`sb`) representing the processed string so far. For each character in the input string `s`, we append it to `sb`. After appending, we check if the last `2*k` characters of `sb` form a k-balanced substring (k opening parentheses followed by k closing parentheses). If they do, we remove those `2*k` characters from `sb`, effectively removing the k-balanced substring.

This process repeats for all characters in `s`.  By iterating through the string and immediately removing k-balanced substrings as they are formed, we guarantee that all such substrings will be removed, resulting in the final string without any k-balanced substrings.

The approach is chosen for its simplicity and efficiency. It avoids complex pattern matching algorithms and relies on direct string manipulation.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `StringBuilder`: Used for efficiently building the resulting string.  StringBuilders allow for in-place modification, which is more efficient than repeatedly creating new `String` objects.
*   **Algorithms:**
    *   **String Manipulation:** The core algorithm involves appending characters to a StringBuilder and checking/removing substrings based on equality.

### 4. Code Walkthrough:

```java
class Solution {
    public String removeSubstring(String s, int k) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbb = new StringBuilder();

        for(int i = 0; i<k; i++) sbb.append("(");
        for(int i = 0; i<k; i++) sbb.append(")");

        String sub = sbb.toString();

        for(char c : s.toCharArray()) {
            sb.append(c);
            if(sb.length() >= 2*k && sb.substring(sb.length()-2*k).equals(sub)) {
                sb.setLength(sb.length()-2*k);
            }
        }

        return sb.toString();
    }
}
```

1.  **Initialization:**
    *   `StringBuilder sb = new StringBuilder();`: Creates a StringBuilder `sb` to store the processed string.
    *   `StringBuilder sbb = new StringBuilder();`: Creates a StringBuilder `sbb` to build the k-balanced substring.

2.  **Building the k-balanced substring:**
    *   `for(int i = 0; i<k; i++) sbb.append("(");`: Appends `k` opening parentheses to `sbb`.
    *   `for(int i = 0; i<k; i++) sbb.append(")");`: Appends `k` closing parentheses to `sbb`.
    *   `String sub = sbb.toString();`: Converts the StringBuilder `sbb` to a String `sub`. This string `sub` now contains the k-balanced substring "(...)(...)".

3.  **Iterating and removing k-balanced substrings:**
    *   `for(char c : s.toCharArray()) { ... }`: Iterates through each character `c` in the input string `s`.
    *   `sb.append(c);`: Appends the current character `c` to the `sb`.
    *   `if(sb.length() >= 2*k && sb.substring(sb.length()-2*k).equals(sub)) { ... }`: This is the crucial part. It checks:
        *   `sb.length() >= 2*k`: If the current length of `sb` is at least `2*k`. This check avoids out-of-bounds errors when taking the substring.
        *   `sb.substring(sb.length()-2*k).equals(sub)`:  Extracts a substring of length `2*k` from the end of `sb` and compares it to the k-balanced substring `sub`.  If they are equal, it means a k-balanced substring has just been formed.
        *   `sb.setLength(sb.length()-2*k);`: If a k-balanced substring is found, this line removes it from `sb` by setting the length of `sb` to its original length minus `2*k`.

4.  **Returning the result:**
    *   `return sb.toString();`: Converts the StringBuilder `sb` to a String and returns the final result.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n*k), where n is the length of the input string `s` and k is the integer value representing the "k-balanced substring".
    *   The outer loop iterates `n` times (once for each character in `s`).
    *   Inside the loop, `sb.substring(sb.length() - 2*k)` takes O(k) time.
    *   `equals()` operation on two strings of length 2k takes O(k) time.
    *   The operations within the loop take a maximum of O(k) time, resulting in an overall time complexity of O(n*k).

*   **Space Complexity:** O(n), where n is the length of the input string `s`.
    *   The `StringBuilder sb` can grow up to the size of the input string `s` in the worst case (when no k-balanced substrings are removed).
    *   `StringBuilder sbb` and `String sub` require O(k) space.  However, since k is a fixed input, and n can be much larger than k, we consider the overall space complexity as O(n).
