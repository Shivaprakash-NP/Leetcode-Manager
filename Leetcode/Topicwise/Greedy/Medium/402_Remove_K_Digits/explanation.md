```markdown
## Remove K Digits - Problem Explanation and Solution

### 1. Problem Understanding:

The problem asks us to remove *k* digits from a given string *num* (representing a non-negative integer) such that the resulting number is the smallest possible.  The input string *num* consists of digits from '0' to '9'. The goal is to return the string representation of this smallest number. If removing all digits results in an empty string, we should return "0".

### 2. Approach / Intuition:

The core idea is to build the smallest number digit by digit using a stack. We iterate through the input string *num*.  At each digit, we compare it with the digit at the top of the stack.  If the current digit is smaller than the top of the stack, it means we can potentially make the number smaller by removing the top of the stack. We pop digits from the stack as long as:

1.  The stack is not empty.
2.  We still have removals left (`k > 0`).
3.  The digit at the top of the stack is greater than the current digit.

After processing all digits, we might still have some removals left (`k > 0`). This happens if the number is in increasing order (e.g., "12345").  In this case, we remove the last `k` digits from the stack, because they are the largest ones.

Finally, we need to handle leading zeros. We iterate through the resulting string (built from the stack) and remove any leading zeros. If the final string is empty after removing leading zeros, we return "0".

The key intuition behind this approach is that a smaller digit in a more significant place will always result in a smaller overall number.

### 3. Data Structures and Algorithms:

*   **Stack:** A stack is used to maintain the digits that are potentially part of the smallest number.  The stack allows us to easily compare the current digit with the last added digit and remove digits from the "end" (top) if necessary.
*   **StringBuilder:** A StringBuilder is used to efficiently construct the final result string.
*   **Greedy Algorithm:** This solution uses a greedy approach, making the locally optimal choice (removing a larger digit if a smaller one is available) at each step in the hope of finding the global optimum (the smallest possible number).

### 4. Code Walkthrough:

```java
class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() <= k) return "0"; // If we can remove all digits, return "0"

        int n = num.length();
        Stack<Character> st = new Stack<>(); // Initialize a stack to store the digits

        for(char c : num.toCharArray()) { // Iterate through the digits of the input string
            while(!st.isEmpty() && k>0 && st.peek() > c) { // While stack is not empty, we have removals and top is larger
                st.pop(); // Remove the top element from the stack
                k--; // Decrement the number of removals left
            }
            st.push(c); // Push the current digit onto the stack
        }

        while(k-->0 && !st.isEmpty()) st.pop(); // Remove remaining K digits from the end

        StringBuilder sb = new StringBuilder();
        for(char v : st) { // Build the string from the stack
            if(sb.length() == 0 && v == '0') { // Skip leading zeros
                continue;
            } 
            sb.append(v); // Append the digit to the string builder
        }
        return (sb.length()==0)?"0":sb.toString(); // Return the result, or "0" if empty
    }
}
```

*   **`if(num.length() <= k) return "0";`**: This is a base case. If we can remove all digits (k is greater than or equal to the length of the number), the smallest possible number is "0".
*   **`Stack<Character> st = new Stack<>();`**: Initializes a stack to store the digits that will form the smallest number.
*   **`for(char c : num.toCharArray()) { ... }`**: Iterates through each character (digit) in the input string.
*   **`while(!st.isEmpty() && k>0 && st.peek() > c) { ... }`**: This is the core logic.  It checks:
    *   `!st.isEmpty()`:  Is the stack empty? We can't pop from an empty stack.
    *   `k > 0`:  Do we still have removals allowed?
    *   `st.peek() > c`: Is the digit at the top of the stack greater than the current digit?
    If all these conditions are true, it means we can improve the number by removing the larger digit at the top of the stack and replacing it with the smaller current digit.
*   **`st.pop(); k--;`**: Removes the larger digit from the stack and decrements the remaining removals.
*   **`st.push(c);`**: Pushes the current digit onto the stack.
*   **`while(k-->0 && !st.isEmpty()) st.pop();`**:  After processing all digits, if we still have removals (`k > 0`), it means the remaining digits in the stack are in increasing order (or non-decreasing), so we remove the last `k` digits from the stack.
*   **`StringBuilder sb = new StringBuilder();`**:  Initializes a StringBuilder to build the result string.
*   **`for(char v : st) { ... }`**: Iterates through the digits in the stack.
*   **`if(sb.length() == 0 && v == '0') { continue; }`**: Skips leading zeros. We only skip zeros if the StringBuilder is empty (i.e., we haven't added any non-zero digits yet).
*   **`sb.append(v);`**: Appends the digit to the StringBuilder.
*   **`return (sb.length()==0)?"0":sb.toString();`**: Returns the resulting string. If the StringBuilder is empty after removing leading zeros, it means all digits were removed or were zeros, so we return "0".

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the length of the input string `num`. We iterate through the string once to push elements onto the stack, and at most, pop each element once. The remaining operations (building the string) are also O(N).
*   **Space Complexity:** O(N), where N is the length of the input string `num`.  In the worst case, the stack can store all the digits of the input string (e.g., when the number is in increasing order). The StringBuilder can also grow to a size of N in the worst case.
