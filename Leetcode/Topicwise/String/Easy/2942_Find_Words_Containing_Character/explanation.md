## LeetCode Problem: Find Words Containing Character - Explained

Here's a detailed explanation of the provided Java code for the LeetCode problem "Find Words Containing Character".

### 1. Problem Understanding:

The problem asks us to find the indices of the words in a given array of strings (`words`) that contain a specific character (`x`).  We need to return a list of these indices.  The indexing starts at 0.

### 2. Approach / Intuition:

The most straightforward approach is to iterate through the `words` array. For each word, we check if the character `x` is present. If it is, we add the index of that word to our result list.

This approach is chosen because it's simple, easy to understand, and directly addresses the problem requirements.  There's no need for complex algorithms or data structures. We directly check each word for the presence of the target character.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `List<Integer>`:  Used to store the indices of the words that contain the target character.  Specifically, an `ArrayList` is used for dynamic resizing.
*   **Algorithms:**
    *   **Iteration:**  We use a `for` loop to iterate through the `words` array.
    *   **String Search:** The `String.indexOf(char)` method is used to efficiently check if a character exists within a string.

### 4. Code Walkthrough:

```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>(); // Initialize an empty list to store the indices.
        for(int i = 0 ; i < words.length ; i++) { // Iterate through the words array.
            String s = words[i]; // Get the current word at index i.
            if(s.indexOf(x) != -1) ans.add(i); // Check if the character x is present in the word s.
                                                  // If found (indexOf returns an index >= 0, so not -1), add the index i to the ans list.
        }
        return ans; // Return the list of indices.
    }
}
```

**Line-by-line explanation:**

1.  `class Solution {`: Defines the class `Solution` which is a standard requirement for LeetCode solutions.
2.  `public List<Integer> findWordsContaining(String[] words, char x) {`:  This is the method definition. It takes an array of strings `words` and a character `x` as input and returns a `List` of `Integer` representing the indices.
3.  `List<Integer> ans = new ArrayList<>();`: An `ArrayList` named `ans` is created to store the indices of the words containing the character `x`.
4.  `for(int i = 0 ; i < words.length ; i++) {`:  A `for` loop iterates through each word in the `words` array. `i` is the index of the current word.
5.  `String s = words[i];`: The current word at index `i` is assigned to the string variable `s`.
6.  `if(s.indexOf(x) != -1) ans.add(i);`: This is the core logic.
    *   `s.indexOf(x)`: This method searches for the first occurrence of the character `x` within the string `s`. If the character is found, it returns the index of the character. If the character is not found, it returns -1.
    *   `!= -1`: This checks if the character `x` was found in the string `s`. If `s.indexOf(x)` is not equal to -1, it means the character was found.
    *   `ans.add(i)`: If the character `x` is found in the string `s`, the index `i` of that string is added to the `ans` list.
7.  `return ans;`: After iterating through all the words, the `ans` list, containing the indices of the words that contain the character `x`, is returned.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N\*M), where N is the number of words in the `words` array and M is the average length of the words.  This is because we iterate through each word (O(N)) and use `indexOf()` which in the worst case might need to check every character in the string (O(M)).
*   **Space Complexity:** O(K), where K is the number of words that contain the character `x`. This is the space used to store the `ans` list. In the worst case, where every word contains the character `x`, the space complexity becomes O(N).
