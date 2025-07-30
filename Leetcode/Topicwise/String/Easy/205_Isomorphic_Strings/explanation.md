## Isomorphic Strings Problem Explanation

Here's a detailed explanation of the LeetCode problem "Isomorphic Strings" and the provided Java solution:

### 1. Problem Understanding:

The problem asks us to determine if two given strings, `s` and `t`, are isomorphic. Two strings are isomorphic if the characters in `s` can be replaced to get `t`, following these rules:

*   Every occurrence of a character in `s` must be replaced with another character while preserving the order of characters.
*   No two characters in `s` can map to the same character in `t`, but a character can map to itself.
*   All occurrences of a character must be replaced with the same character.

In simpler terms, we need to check if there exists a one-to-one mapping between the characters of the two strings.

### 2. Approach / Intuition:

The core idea is to use two hash maps to track the mappings between the characters of the two strings.

*   `m1`: Maps characters from string `s` to characters in string `t`.  `m1` represents the mapping s -> t.
*   `m2`: Maps characters from string `t` to characters in string `s`.  `m2` represents the mapping t -> s.

We iterate through the strings character by character.  For each character pair `s[i]` and `t[i]`, we perform the following checks:

1.  **Check if `s[i]` already has a mapping in `m1`:**
    *   If it does, we verify that the mapping is consistent.  That is, `m1.get(s[i])` must be equal to `t[i]`. If not, the strings are not isomorphic, and we return `false`.
    *   If it doesn't, we create a new mapping from `s[i]` to `t[i]` in `m1`.

2.  **Check if `t[i]` already has a mapping in `m2`:**
    *   If it does, we verify that the mapping is consistent.  That is, `m2.get(t[i])` must be equal to `s[i]`. If not, the strings are not isomorphic, and we return `false`.
    *   If it doesn't, we create a new mapping from `t[i]` to `s[i]` in `m2`.

If we iterate through all the characters without finding any inconsistencies, it means the strings are isomorphic, and we return `true`.

The reason for using *two* hash maps is to ensure that the mapping is a true bijection (one-to-one and onto). For example, consider `s = "bad"` and `t = "bab"`. Without the second hash map, we could map 'b' to 'b', 'a' to 'a', and 'd' to 'b'. `s` would appear to be successfully mapped to `t`, but then 'b' would map to both 'b' and 'd', which is not allowed in the problem's condition.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `HashMap`. We use two HashMaps (`m1` and `m2`) to store the character mappings. HashMaps provide efficient (average O(1)) lookup, insertion, and deletion.
*   **Algorithm:** Iteration.  We iterate through the strings character by character to check the mapping.

### 4. Code Walkthrough:

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character , Character> m1 = new HashMap<>();
        HashMap<Character , Character> m2 = new HashMap<>();
        for(int i = 0 ; i <s.length() ; i++)
        {
            if(m1.containsKey(s.charAt(i)))
            {
                if(m1.get(s.charAt(i))!=t.charAt(i)) return false;
            }
            else m1.put(s.charAt(i) , t.charAt(i));

            if(m2.containsKey(t.charAt(i)))
            {
                if(m2.get(t.charAt(i))!=s.charAt(i)) return false;
            }
            else m2.put(t.charAt(i) , s.charAt(i));
        }
        return true;
    }
}
```

1.  **`HashMap<Character, Character> m1 = new HashMap<>();`**:  Creates a HashMap named `m1`.  It will store mappings from characters in string `s` to characters in string `t`.
2.  **`HashMap<Character, Character> m2 = new HashMap<>();`**: Creates a HashMap named `m2`.  It will store mappings from characters in string `t` to characters in string `s`.
3.  **`for(int i = 0 ; i < s.length() ; i++)`**:  Iterates through the strings `s` and `t` character by character using the index `i`.
4.  **`if(m1.containsKey(s.charAt(i)))`**:  Checks if the character at index `i` in string `s` is already present as a key in `m1`.  This means we have seen this character before.
5.  **`if(m1.get(s.charAt(i))!=t.charAt(i)) return false;`**:  If the character `s.charAt(i)` is already in `m1`, we need to verify that its mapped value in `m1` is equal to `t.charAt(i)`. If they are not equal, it means there's an inconsistency in the mapping, and we return `false` because the strings are not isomorphic.
6.  **`else m1.put(s.charAt(i) , t.charAt(i));`**:  If the character `s.charAt(i)` is not in `m1`, it means we are seeing this character for the first time. So, we add a new entry to `m1` mapping `s.charAt(i)` to `t.charAt(i)`.
7.  **`if(m2.containsKey(t.charAt(i)))`**: Similar to steps 4 and 5, but now we check the mapping in the other direction.  We check if the character at index `i` in string `t` is already present as a key in `m2`.
8.  **`if(m2.get(t.charAt(i))!=s.charAt(i)) return false;`**: If `t.charAt(i)` is in `m2`, we verify that its mapped value is equal to `s.charAt(i)`. If not, return `false`.
9.  **`else m2.put(t.charAt(i) , s.charAt(i));`**: If `t.charAt(i)` is not in `m2`, we add a new entry mapping `t.charAt(i)` to `s.charAt(i)`.
10. **`return true;`**: If the loop completes without finding any inconsistencies, it means the strings are isomorphic, so we return `true`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the strings `s` and `t`.  We iterate through the strings once.  HashMap operations (containsKey, get, put) take O(1) on average.
*   **Space Complexity:** O(1). In the worst case, all characters in the strings are unique. However, the number of possible unique characters is bounded by the size of the character set (e.g., 256 for ASCII, or potentially larger for Unicode), which we can consider as a constant. Therefore, the space complexity is considered O(1).  We use two HashMaps, but the size of the HashMaps is limited by the number of distinct characters in the input string. In the worst case, we might have two maps containing all unique characters in the respective strings, however, since the number of unique characters that can exist is limited, the space complexity can be considered constant space.

