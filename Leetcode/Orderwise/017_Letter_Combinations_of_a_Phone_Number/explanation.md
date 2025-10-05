```markdown
## Letter Combinations of a Phone Number - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to generate all possible letter combinations that can be formed from a given string of digits, where each digit corresponds to a set of letters on a phone keypad (e.g., 2 maps to "abc", 3 maps to "def", etc.). The input is a string of digits, and the output should be a list of all possible letter combinations.

### 2. Approach / Intuition:

The problem lends itself nicely to a recursive solution. The core idea is to explore each possible letter for each digit in the input string.  We can visualize this as a tree, where each level represents a digit and each branch represents a letter associated with that digit.

The key is to:

*   **Base Case:** When we've processed all the digits in the input string, we have a complete combination, which we add to our result list.
*   **Recursive Step:** For each digit, we iterate through its corresponding letters. For each letter, we append it to our current combination (using a `StringBuilder`), make a recursive call to process the next digit, and then remove the last appended letter (backtracking) to explore other possibilities.

This approach is chosen because it systematically explores all possible combinations, ensuring that no valid combination is missed. Backtracking is essential to undo the choices made in previous recursive calls, allowing us to explore other combinations efficiently.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `List<String>`:  To store the resulting combinations.
    *   `Map<Integer, String>`:  To map digits to their corresponding letters.
    *   `StringBuilder`: To efficiently build the combinations during the recursive calls.

*   **Algorithms:**
    *   **Recursion:**  To explore the combination space.
    *   **Backtracking:**  To efficiently explore different combinations by undoing choices made in previous recursive calls.

### 4. Code Walkthrough:

```java
class Solution {
    List<String> ans = new ArrayList<>();

    private void rec(String s, int i, Map<Integer, String> map, StringBuilder sb) {
        if(i == s.length()) {
            if(sb.length() != 0) ans.add(sb.toString());
            return;
        }

        for(char c : map.get(s.charAt(i)-'0').toCharArray()) {
            sb.append(c);
            rec(s, i+1, map, sb);
            sb.setLength(sb.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        rec(digits, 0, map, new StringBuilder());
        return ans;

    }
}
```

*   **`Solution` class:**  Contains the `letterCombinations` method and the helper recursive function `rec`.
*   **`ans` (List<String>):** A class-level `ArrayList` to store the resulting letter combinations.

*   **`rec(String s, int i, Map<Integer, String> map, StringBuilder sb)` (Recursive Helper Function):**
    *   `s`: The input string of digits.
    *   `i`: The current index in the input string `s`.
    *   `map`: The mapping of digits to letters.
    *   `sb`: A `StringBuilder` to build the current letter combination.

    1.  **Base Case:** `if(i == s.length()) { ... }`
        *   If `i` is equal to the length of the input string `s`, it means we have processed all the digits.
        *   `if(sb.length() != 0) ans.add(sb.toString());`: Before adding, we ensure the combination isn't empty (handles the case of an empty input string). Converts the current combination in `sb` to a string and adds it to the `ans` list.
        *   `return;`: Ends the recursive call.

    2.  **Recursive Step:** `for(char c : map.get(s.charAt(i)-'0').toCharArray()) { ... }`
        *   `map.get(s.charAt(i)-'0').toCharArray()`: Retrieves the string of letters corresponding to the digit at index `i` in the input string `s`.  `s.charAt(i)-'0'` converts the character digit to its integer value. Then, the string is converted to a char array so we can iterate over the letters.
        *   `for(char c : ...)`: Iterates through each character `c` in the string of letters.
        *   `sb.append(c)`: Appends the current character `c` to the `StringBuilder sb`. This adds `c` to the current letter combination.
        *   `rec(s, i+1, map, sb)`: Makes a recursive call to process the next digit (at index `i+1`).
        *   `sb.setLength(sb.length() - 1)`:  **Backtracking Step:** After the recursive call returns, this line removes the last appended character `c` from the `StringBuilder sb`. This is crucial for exploring other possible letter combinations.

*   **`letterCombinations(String digits)` (Main Function):**
    1.  `Map<Integer, String> map = new HashMap<>();`: Creates a `HashMap` to store the digit-to-letter mappings.
    2.  `map.put(2, "abc"); ... map.put(9, "wxyz");`: Populates the `map` with the standard phone keypad mappings.
    3.  `rec(digits, 0, map, new StringBuilder());`: Calls the recursive helper function `rec` to start the combination generation.  The initial index is 0, and a new empty `StringBuilder` is passed in.
    4.  `return ans;`: Returns the `ans` list containing all generated letter combinations.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(4<sup>N</sup>), where N is the length of the input `digits` string. In the worst-case scenario (digits like 7 or 9), each digit can map to 4 letters.  The recursion tree has a branching factor of up to 4, and the depth of the tree is N.  Therefore, we have approximately 4 * 4 * 4 ... (N times), which is 4<sup>N</sup>. The time taken to create a new string from `StringBuilder` is O(N).

*   **Space Complexity:** O(N), primarily due to the depth of the recursion stack. In the worst case, the recursion depth can be equal to the length of the input string `digits`. Also, the `StringBuilder` has a maximum size of N.  The space occupied by the `ans` list depends on the number of combinations, which can be up to 4<sup>N</sup> in the worst case.  Therefore, the space complexity contributed by storing the result can also be considered O(4<sup>N</sup>). However, the space used by the recursion stack is the dominant factor when considering the "auxiliary" space complexity used by the algorithm itself.
