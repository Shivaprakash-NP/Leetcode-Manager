## Min Stack Problem Explanation and Solution

Here's a detailed explanation of the provided Java code for the LeetCode "Min Stack" problem.

**1. Problem Understanding:**

The "Min Stack" problem asks us to implement a stack data structure that supports the following operations:

*   `push(val)`: Pushes the integer `val` onto the stack.
*   `pop()`: Removes the element on the top of the stack.
*   `top()`: Gets the top element of the stack.
*   `getMin()`: Retrieves the minimum element in the stack in *constant time*.

The crucial requirement is to efficiently (O(1) time) retrieve the minimum element at any point in the stack's history.

**2. Approach / Intuition:**

The core idea behind this solution is to cleverly store the minimum element along with the stack elements, without using an auxiliary stack.  Here's how it works:

*   **Encoding the Minimum:** Instead of storing the actual values when a new minimum is encountered, we store a modified value in the stack. This modified value encodes both the new value to be pushed and the prior minimum value. This allows us to reconstruct the prior minimum value upon a `pop` operation. Specifically, when a new value `val` is pushed that's smaller than the current minimum `min`, instead of directly pushing `val`, we push `2 * val - min`.  Since val < min, 2*val - min < val < min. So this pushed value will be smaller than the min value which is currently in the stack.
*   **Updating the Minimum:** When we push a new minimum, we also update the `min` variable with the new minimum value.
*   **Decoding on Pop:** When popping an element, if the popped value is *less than* the current minimum, it means that the actual value we're popping is encoded, and it represents a previous minimum. We then update the `min` to be the previous minimum using the formula `min = 2 * min - poppedValue`.
*   **Long Data Type:**  The data type `Long` is used to prevent potential integer overflow issues when performing the multiplication operations.  If we use `int`, `2*val - min` or `2*min - po` can exceed the maximum or minimum value of `int` and lead to incorrect values.

**Why this approach?**

This approach uses only a single stack and a single variable, providing an efficient solution in terms of space complexity. It avoids the need for an auxiliary stack to store the minimums. The O(1) time complexity for `getMin()` is achieved by tracking the minimum in a separate variable.

**3. Data Structures and Algorithms:**

*   **Stack:** The core data structure is a `java.util.Stack` to store the elements.  Stacks follow the LIFO (Last-In, First-Out) principle.
*   **Variable to store min:** A `long` variable, `min`, stores the current minimum value in the stack.
*   **Algorithms:** No specific algorithms are used besides the fundamental stack operations (push, pop, peek).

**4. Code Walkthrough:**

```java
import java.util.Stack;

class MinStack {

    Stack<Long> s = new Stack<>();
    long min = Long.MAX_VALUE;

    public MinStack() {

    }

    public void push(int val) {
        if(s.isEmpty()) {
            s.push((long)val);
            min = val;
        } else {
            if(val < min) {
                s.push(2*(long)val - min);
                min = val;
            }
            else s.push((long)val);
        }
    }

    public void pop() {
        long po = s.pop();
        if(po < min) min = 2*min - po;
        // if(s.isEmpty()) return;
        // int x = s.peek();
        // if(x < min) return 2*min - x;
        // else return x;
    }

    public int top() {
        long x = s.peek();
        if(x < min) return (int)min;
        else return (int)x;
    }


    public int getMin() {
        return (int)min;
    }
}
```

*   **`MinStack()` Constructor:**
    *   Initializes an empty stack `s` of `Long` type.
    *   Initializes the `min` variable to `Long.MAX_VALUE`. This ensures that the first element pushed will always be considered the minimum initially.

*   **`push(int val)` Method:**
    *   Handles the insertion of a new element `val` into the stack.
    *   `if(s.isEmpty())`: If the stack is empty:
        *   Pushes `val` onto the stack (casted to `long`).
        *   Sets `min` to `val`.
    *   `else`: If the stack is not empty:
        *   `if(val < min)`: If the new value is less than the current minimum:
            *   Pushes `2 * val - min` onto the stack (casted to `long`). This encodes the new minimum and the previous minimum.
            *   Updates `min` to `val`.
        *   `else`: If the new value is not less than the current minimum:
            *   Pushes `val` onto the stack (casted to `long`).

*   **`pop()` Method:**
    *   Removes the top element from the stack.
    *   `long po = s.pop();`: Pops the top element from the stack and stores it in `po`.
    *   `if(po < min)`: If the popped value is less than the current minimum:
        *   This means that the popped value is not the actual value, but rather the encoded value `2 * val - min`.
        *   We need to update the minimum.  The previous minimum can be found by `min = 2 * min - po;`.
    * `if(po < min)` the `min` value updates to be the previous `min`

*   **`top()` Method:**
    *   Returns the top element of the stack without removing it.
    *   `long x = s.peek();`: Peeks at the top element of the stack and stores it in `x`.
    *   `if(x < min)`: If the top element is less than the current minimum:
        *   This indicates that the top element is an encoded minimum value.  We want to return the *actual* minimum value in this case which is `min`.
        *   Returns the current `min` value (casted to `int`).
    *   `else`: Otherwise:
        *   The top element is a regular value, so we return it (casted to `int`).

*   **`getMin()` Method:**
    *   Returns the current minimum value stored in the `min` variable (casted to `int`). This is the core part that ensures O(1) time complexity for retrieving the minimum.

**5. Time and Space Complexity:**

*   **Time Complexity:**
    *   `push()`: O(1) - Constant time.
    *   `pop()`: O(1) - Constant time.
    *   `top()`: O(1) - Constant time.
    *   `getMin()`: O(1) - Constant time.

*   **Space Complexity:**
    *   O(N), where N is the number of elements in the stack.  In the worst-case scenario, we might push N elements onto the stack. The auxiliary space used is just one variable for `min`. Therefore, the space complexity is determined by the size of the stack.
