## LeetCode Problem: Valid Word - Solution Explanation

**1. Problem Understanding:**

The problem asks us to determine if a given word is "valid." A valid word, according to the problem's implicit definition, must meet two conditions: (1) it must have a length of at least 3 characters, and (2) it must contain at least one vowel and at least one consonant.  The input is a string (`word`), and the output is a boolean (`true` if the word is valid, `false` otherwise). Non-alphanumeric characters invalidate the word.

**2. Approach / Intuition:**

The solution employs a straightforward approach. It iterates through the characters of the input string, checking for the validity conditions.  We maintain two boolean flags: `hasVowel` and `hasConsonant`.  These flags are set to `true` when we encounter a vowel or a consonant, respectively. The algorithm efficiently checks both conditions simultaneously during a single pass through the word. This single-pass approach minimizes the time complexity.

This approach was chosen because it's both efficient and easy to understand.  More complex algorithms are unnecessary given the simplicity of the problem's requirements.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is a String to represent the input word.  A String is implicitly converted into an array of characters.  The `vowels` string is used as a lookup table.
* **Algorithms:** The core algorithm used is a linear scan/iteration. We iterate through each character of the input string.  The `indexOf` method is used for efficient vowel checking.


**4. Code Walkthrough:**

* **`public boolean isValid(String word)`:** This is the main function that takes the word as input and returns a boolean indicating validity.
* **`if (word.length() < 3) return false;`:** This line checks the first condition: the word must be at least 3 characters long. If not, it immediately returns `false`.
* **`String vowels = "aeiouAEIOU";`:** This line initializes a string containing all vowels (both uppercase and lowercase). This acts as a lookup table for efficient vowel detection.
* **`boolean hasVowel = false; boolean hasConsonant = false;`:** These lines initialize boolean flags to track the presence of vowels and consonants.
* **`for (char c : word.toCharArray()) { ... }`:** This loop iterates through each character (`c`) of the input word.
* **`if (!Character.isLetterOrDigit(c)) return false;`:** This crucial check ensures that only alphanumeric characters are considered.  Any other character immediately invalidates the word.
* **`if (Character.isLetter(c)) { ... }`:** This condition ensures that we only process letters.
* **`if (vowels.indexOf(c) != -1) hasVowel = true; else hasConsonant = true;`:** This line checks if the character is a vowel using the `indexOf` method. If `indexOf` returns a value other than -1, it's a vowel, and `hasVowel` is set to `true`. Otherwise, it's a consonant, and `hasConsonant` is set to `true`.
* **`return hasVowel && hasConsonant;`:** Finally, the function returns `true` only if both `hasVowel` and `hasConsonant` are `true`, meaning the word contains at least one vowel and at least one consonant.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input string. The code iterates through the string once.  The `indexOf` operation on a string of fixed size (vowels) takes constant time O(1) in the average and best cases.  Worst case is O(m) where m is the length of vowels, but m is constant, not dependent on n.
* **Space Complexity:** O(1). The space used is constant regardless of the input string's length.  The `vowels` string and the boolean variables occupy a fixed amount of memory.  While `toCharArray()` creates a temporary array, its space is proportional to the word's length (O(n)). However, because this temporary array is independent of the size of the input and is used only during one iteration, it doesn't significantly impact space complexity in this context.  The overall space consumed remains constant in relation to the size of input.
