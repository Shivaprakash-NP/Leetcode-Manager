## LeetCode: Using a Robot to Print the Lexicographically Smallest String

**1. Problem Understanding:**

The problem asks us to simulate a robot that prints characters from an input string `s`.  The robot can only print characters in the order they appear in `s`, but it can temporarily store characters in a stack. The goal is to find the lexicographically smallest string the robot can print by strategically using the stack.


**2. Approach / Intuition:**

The solution uses a greedy approach. It pre-processes the input string to find the minimum character for each suffix.  This allows the algorithm to make informed decisions about whether to push a character onto the stack or print it immediately.  The core idea is to only push a character onto the stack if it's guaranteed that a smaller character will appear later (according to the pre-calculated minimums).  Otherwise, it's better to print it immediately to get a lexicographically smaller result.

We choose this approach because it efficiently determines the optimal strategy for using the stack without exhaustively exploring all possible combinations of stack usage, which would be computationally expensive.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `StringBuilder`: Used to efficiently build the final lexicographically smallest string.
    * `Stack<Character>`: Used to store characters temporarily.  The stack follows LIFO (Last-In, First-Out) principle.
    * `char[] min`: An array to store the minimum character for each suffix of the input string.

* **Algorithms:**
    * **Greedy Algorithm:** The main algorithm is greedy because it makes the locally optimal choice at each step (printing a character immediately if it's smaller than future characters, otherwise pushing it).
    * **Suffix Minimum Calculation:**  We use a simple iteration to find the minimum character in each suffix efficiently.


**4. Code Walkthrough:**

```java
class Solution {
    public String robotWithString(String s) {
        StringBuilder sb = new StringBuilder(); // Result string
        Stack<Character> st = new Stack<>(); // Temporary storage
        int n = s.length();
        char[] min = new char[n]; // Stores minimum character for each suffix
        min[n-1] = s.charAt(n-1); // Initialize the last element

        // Calculate minimum characters for each suffix
        for(int i = n-2 ; i>=0 ; i--) {
            min[i] = (char) Math.min(s.charAt(i) , min[i+1]);
        }

        // Main loop: process each character
        for(int i = 0; i < n ; i++) {
            st.push(s.charAt(i)); // Push the current character
            // While the stack is not empty and either we're at the last character or the top of the stack is less than or equal to the minimum of the remaining suffix
            while(!st.isEmpty() && (i == n-1 || st.peek() <= min[i+1])) {
                sb.append(st.pop()); // Pop and append to the result
            }
        }

        // Append any remaining characters from the stack
        while(!st.isEmpty()) sb.append(st.pop());

        return sb.toString();
    }
}
```


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the input string `s`.  Each character is processed at most twice: once for calculating the minimums and once in the main loop.  The stack operations (push and pop) take amortized O(1) time.

* **Space Complexity:** O(N). This is primarily due to the `min` array and the `Stack<Character>`. In the worst case (e.g., string of all same character), the stack can hold all N characters.  The `StringBuilder` also uses O(N) space in the worst case.

In summary, the solution efficiently solves the problem using a greedy strategy and auxiliary data structures to achieve a linear time and space complexity.  The use of the `min` array is key to making informed decisions at each step without needing to explore all possibilities.
