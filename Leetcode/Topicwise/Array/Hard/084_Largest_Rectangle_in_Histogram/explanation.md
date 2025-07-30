```markdown
## Largest Rectangle in Histogram Problem Explanation

### 1. Problem Understanding:

The problem asks us to find the largest rectangular area that can be formed within a histogram, where the width of each bar is 1.  Given an array of non-negative integers representing the heights of the histogram's bars, we need to find the largest rectangle that can be fit within those bars. For example, given `heights = [2,1,5,6,2,3]`, the largest rectangle has an area of 10 (height 5, width 2).

### 2. Approach / Intuition:

The key idea is to, for each bar in the histogram, consider it as the *height* of a potential rectangle.  The *width* of that rectangle will be determined by the leftmost and rightmost boundaries where the height is greater than or equal to the current bar's height.  In other words, we need to find the nearest smaller elements to the left and right of each bar.

For a given bar `i`, if `pre_small[i]` is the index of the nearest smaller element to the left, and `nxt_small[i]` is the index of the nearest smaller element to the right, the width of the rectangle with height `heights[i]` will be `nxt_small[i] - pre_small[i] - 1`.  The area would then be `heights[i] * (nxt_small[i] - pre_small[i] - 1)`.

To find the nearest smaller elements efficiently, we use a stack.  The stack maintains a monotonically increasing sequence of bar indices. When we encounter a bar that's smaller than the top of the stack, we pop elements from the stack until we find an element smaller than the current bar or the stack is empty.  This allows us to easily determine the nearest smaller elements.

Why is this approach chosen?  Because it provides an efficient way to determine the possible boundaries for a rectangle of a certain height, avoiding quadratic time complexity which would result from checking every possible start and end position for a given height.  The stack efficiently helps us identify the boundaries where the current height can be maintained to make up a rectangle.

### 3. Data Structures and Algorithms:

*   **Stack:** Used to maintain a monotonically increasing stack of bar indices, enabling efficient finding of nearest smaller elements to the left and right.
*   **Arrays:** `pre_small` and `nxt_small` store the indices of the nearest smaller elements to the left and right of each bar, respectively.
*   **Monotonic Stack:** The core algorithm involves maintaining a monotonic (increasing in this case) stack.

### 4. Code Walkthrough:

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pre_small = new int[n];
        int[] nxt_small = new int[n];
        int max = 0;

        Stack<Integer> st = new Stack<>();

        // Find the nearest smaller element to the left for each bar
        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            pre_small[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        // Clear the stack for finding the nearest smaller elements to the right
        st.clear();

        // Find the nearest smaller element to the right for each bar
        for(int i = n-1 ; i>=0 ; i--) {
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            nxt_small[i] = (st.isEmpty())?n:st.peek();
            st.push(i);
        }

        // Calculate the area for each bar and update the maximum area
        for(int i = 0 ; i<n ; i++) {
            int h = heights[i];
            int w = nxt_small[i]-pre_small[i]-1;
            max = Math.max(max , h*w);
        }
        return max;
    }
}
```

*   **`largestRectangleArea(int[] heights)`:**  The main function that takes the `heights` array as input.

*   **`int n = heights.length;`:** Gets the length of the input array.

*   **`int[] pre_small = new int[n];`:** Creates an array to store the index of the nearest smaller element to the *left* of each bar.

*   **`int[] nxt_small = new int[n];`:** Creates an array to store the index of the nearest smaller element to the *right* of each bar.

*   **`int max = 0;`:** Initializes the variable `max` to store the maximum rectangular area found so far.

*   **`Stack<Integer> st = new Stack<>();`:** Creates a stack to store the indices of the bars.

*   **First Loop (`for(int i = 0 ; i < n ; i++)`):**
    *   This loop iterates through the `heights` array from left to right to find the nearest smaller element to the *left* of each bar.
    *   **`while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();`:**  While the stack is not empty and the height of the bar at the top of the stack is greater than or equal to the current bar's height, pop elements from the stack. This ensures the stack remains monotonically increasing (from bottom to top).
    *   **`pre_small[i] = (st.isEmpty())?-1:st.peek();`:**  If the stack is empty after popping, it means there is no smaller element to the left of the current bar, so `pre_small[i]` is set to -1. Otherwise, `pre_small[i]` is set to the index of the element at the top of the stack (which is the nearest smaller element to the left).
    *   **`st.push(i);`:** Pushes the index of the current bar onto the stack.

*   **`st.clear();`:** Clears the stack after finding the nearest smaller elements to the left, ready for the next loop.

*   **Second Loop (`for(int i = n-1 ; i>=0 ; i--)`):**
    *   This loop iterates through the `heights` array from right to left to find the nearest smaller element to the *right* of each bar.
    *   **`while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();`:**  Similar to the first loop, pop elements from the stack if they are greater than or equal to the current bar's height.
    *   **`nxt_small[i] = (st.isEmpty())?n:st.peek();`:** If the stack is empty after popping, it means there is no smaller element to the right of the current bar, so `nxt_small[i]` is set to `n`. Otherwise, `nxt_small[i]` is set to the index of the element at the top of the stack.
    *   **`st.push(i);`:** Pushes the index of the current bar onto the stack.

*   **Third Loop (`for(int i = 0 ; i<n ; i++)`):**
    *   This loop iterates through the `heights` array and calculates the area of the rectangle for each bar.
    *   **`int h = heights[i];`:** Gets the height of the current bar.
    *   **`int w = nxt_small[i]-pre_small[i]-1;`:** Calculates the width of the rectangle, using the nearest smaller elements to the left and right.
    *   **`max = Math.max(max , h*w);`:** Updates `max` with the maximum area found so far.

*   **`return max;`:** Returns the maximum rectangular area.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the number of bars in the histogram.  Each bar is pushed onto the stack and popped at most once in each of the first two loops. The third loop iterates through the array once. Therefore, the overall time complexity is linear.
*   **Space Complexity:** O(N), due to the `pre_small`, `nxt_small` arrays, and the stack which can potentially store all indices in the worst-case scenario (e.g., a monotonically increasing histogram).
