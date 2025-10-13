## LeetCode Problem: Find Resultant Array After Removing Anagrams - Explained

Here's a detailed explanation of the provided Java code for the "Find Resultant Array After Removing Anagrams" LeetCode problem:

### 1. Problem Understanding:

The problem asks us to take an array of strings `words` as input. We need to iterate through the array and remove any string that is an anagram of the *previous* string in the resulting array. The final result should be a list containing only the strings that are *not* anagrams of their immediate predecessor.

### 2. Approach / Intuition:

The core idea is to iterate through the `words` array, comparing each word with the last word added to the `ans` list. If the current word is an anagram of the last word in the `ans` list, we skip it. Otherwise, we add the current word to the `ans` list.

The chosen approach leverages the fact that we only need to compare a word against its immediate predecessor in the output list. This simplifies the problem considerably, making the algorithm straightforward and efficient.

### 3. Data Structures and Algorithms:

*   **`List<String>`:**  Used to store the resulting array of strings after removing anagrams. `ArrayList` is chosen for its efficiency in adding elements to the end.
*   **`int[]` (Frequency Array):** Used within the `is()` function to count the frequency of each character in a string.
*   **Anagram Check (Algorithm):** The `is()` function implements the anagram check algorithm. It compares the frequency of characters in two strings. If the character frequencies are identical, the strings are anagrams.

### 4. Code Walkthrough:

**`Solution` Class:**

```java
class Solution {
    // ... (is() method and removeAnagrams() method)
}
```

This class contains the main logic for solving the problem.

**`is(String s, String t)` Method:**

```java
    private boolean is(String s, String t) {
        int[] m1 = new int[26];
        int[] m2 = new int[26];

        for(char c : s.toCharArray()) m1[c-'a']++;
        for(char c : t.toCharArray()) m2[c-'a']++;

        for(int i = 0; i<26; i++) if(m1[i] != m2[i]) return false;

        return true;
    }
```

*   This method checks if two strings `s` and `t` are anagrams of each other.
*   `int[] m1 = new int[26];` and `int[] m2 = new int[26];`:  Two integer arrays of size 26 are created. These will store the frequency of each lowercase English alphabet character (a-z) in the input strings `s` and `t`, respectively.
*   `for(char c : s.toCharArray()) m1[c-'a']++;`: This loop iterates through the characters of the string `s`. For each character `c`, its ASCII value is subtracted by the ASCII value of 'a'. The result is the index corresponding to that character (e.g., 'a' - 'a' = 0, 'b' - 'a' = 1, etc.).  The count at that index in `m1` is incremented, effectively counting the frequency of each character in `s`.
*   `for(char c : t.toCharArray()) m2[c-'a']++;`: This loop does the same as the previous one, but for the string `t` and storing the frequencies in the `m2` array.
*   `for(int i = 0; i<26; i++) if(m1[i] != m2[i]) return false;`: This loop iterates through the frequency arrays `m1` and `m2`. If, at any index `i`, the frequencies `m1[i]` and `m2[i]` are different, it means the strings have different character frequencies and are therefore not anagrams.  The method immediately returns `false` in this case.
*   `return true;`: If the loop completes without finding any mismatched frequencies, it means the strings have the same character frequencies and are anagrams. The method returns `true`.

**`removeAnagrams(String[] words)` Method:**

```java
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);

        for(int i = 0; i<n; i++) if(!is(words[i], ans.get(ans.size()-1))) ans.add(words[i]);

        return ans;
    }
```

*   `int n = words.length;`:  Gets the length of the input `words` array.
*   `List<String> ans = new ArrayList<>();`: Creates a new `ArrayList` called `ans` to store the resultant array of strings.
*   `ans.add(words[0]);`: The first word in the input array is always added to the `ans` list because it has no preceding word to be compared with.
*   `for(int i = 0; i<n; i++) if(!is(words[i], ans.get(ans.size()-1))) ans.add(words[i]);`: This loop iterates through the `words` array starting from index 0.  For each word `words[i]`, it calls the `is()` method to check if it's an anagram of the last word currently present in the `ans` list (`ans.get(ans.size()-1)`).
    *   If `!is(words[i], ans.get(ans.size()-1))` evaluates to true (meaning `words[i]` is *not* an anagram of the last word in `ans`), then `words[i]` is added to the `ans` list. Note that the loop is comparing `words[i]` from the original input `words` array with the *last* element added to the `ans` array.
*   `return ans;`: Finally, the method returns the `ans` list, which now contains the strings from the input array after removing the anagrams.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   The `is()` method has a time complexity of O(s + t), where s and t are the lengths of the input strings.  This is because it iterates through each string once to calculate character frequencies and then iterates through the 26 possible characters. In the worst case where the string lengths are the same, we can approximate the complexity to O(L), where L is the length of the strings being compared.
    *   The `removeAnagrams()` method iterates through the `words` array of length `n`. In each iteration, it calls the `is()` method.  Therefore, the overall time complexity is O(n * L), where n is the number of words and L is the average length of a word.

*   **Space Complexity:**
    *   The `is()` method uses two integer arrays of size 26. Therefore, its space complexity is O(1) because the space used is constant regardless of the input string lengths.
    *   The `removeAnagrams()` method uses an `ArrayList` `ans` to store the result. In the worst case, the `ans` list might contain all the words in the input array if none of them are anagrams of each other. Therefore, the space complexity in the worst case is O(n), where n is the number of words.  The overall space complexity is O(n).
