## LeetCode "Daily Temperatures" Problem Explanation

Here's a detailed explanation of the Java code solution for the LeetCode problem "Daily Temperatures".

### 1. Problem Understanding:

The problem asks us to find, for each day in a list of daily temperatures, how many days we need to wait until a warmer temperature. If there's no future day with a warmer temperature, we should record 0. For example, given `temperatures = [73, 74, 75, 71, 69, 72, 76, 73]`, the output should be `[1, 1, 4, 2, 1, 1, 0, 0]`.

### 2. Approach / Intuition:

The core idea is to use a stack to keep track of the indices of the days for which we haven't found a warmer day yet. We iterate through the temperatures *backwards* (from the last day to the first). For each day:

*   We compare its temperature to the temperatures of the days currently in the stack.
*   If the current day's temperature is warmer than the temperature of the top of the stack, it means we've found a warmer day for the day represented by the top of the stack.  We pop the top of the stack, because we have determined how many days to wait until the next warmer day.
*   We repeat this process of popping until the stack is empty or the top of the stack has a temperature warmer than the current day's temperature.
*   If the stack is empty after this process, it means there's no warmer day in the future, so we record 0 for the current day. Otherwise, the top of the stack represents the index of the next warmer day, so we calculate the difference between that index and the current day's index and record it.
*   Finally, we push the current day's index onto the stack.

Why iterate backwards? Iterating backwards allows us to keep track of potential "warmer days" in the future. By processing the days from the end, each time we encounter a warmer day, we can easily update the `ans` array for all previous days that were waiting for a warmer temperature. A forward iteration would make determining which days are waiting on that warmer temperature significantly more difficult.

### 3. Data Structures and Algorithms:

*   **Stack:** The `Stack` data structure is crucial for maintaining the indices of the days for which we haven't yet found a warmer day. Its LIFO (Last-In, First-Out) property is ideal because we're looking for the *next* warmer day, so we only need to consider days later in the sequence.
*   **Array:** The `temperatures` input is an array, and the `ans` array stores the results (the number of days to wait).
*   **Iteration (Backwards):** We iterate through the `temperatures` array backwards to efficiently find the next warmer days.

### 4. Code Walkthrough:

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n]; // Initialize the result array
        Stack<Integer> st = new Stack<>(); // Stack to store indices of days

        for(int i = n-1; i>=0; i--) { // Iterate backwards through the temperatures
            while(!st.isEmpty() && temperatures[st.peek()] <= temperatures[i]) st.pop();
            // While the stack is not empty and the temperature at the top of the stack
            // is less than or equal to the current temperature, pop the stack.
            // This is because the current day 'i' is warmer than the top of the stack.

            ans[i] = (st.isEmpty())?0:st.peek()-i;
            // If the stack is empty, there is no warmer day in the future, so store 0.
            // Otherwise, the top of the stack contains the index of the next warmer day,
            // so store the difference between the warmer day and the current day.

            st.push(i);
            // Push the current day's index onto the stack.
        }

        return ans;
    }
}
```

**Explanation:**

1.  **Initialization:**
    *   `n = temperatures.length;`: Get the length of the input `temperatures` array.
    *   `int[] ans = new int[n];`: Create an integer array `ans` of the same length to store the results. This array will store the number of days to wait for a warmer temperature for each day.
    *   `Stack<Integer> st = new Stack<>();`: Initialize an empty stack `st` to store the indices of the days. The stack will help us keep track of potential future warmer days.

2.  **Backwards Iteration:**
    *   `for(int i = n-1; i>=0; i--)`: This loop iterates through the `temperatures` array from the last element (index `n-1`) to the first element (index `0`).

3.  **Finding the Next Warmer Day:**
    *   `while(!st.isEmpty() && temperatures[st.peek()] <= temperatures[i]) st.pop();`: This `while` loop is the core of the algorithm. It does the following:
        *   `!st.isEmpty()`: Checks if the stack is not empty. We only enter the loop if there are potential warmer days to compare with.
        *   `temperatures[st.peek()] <= temperatures[i]`:  This compares the temperature of the day at the top of the stack (`st.peek()`) with the temperature of the current day (`i`). If the temperature at the top of the stack is less than or equal to the current temperature, it means we've found a warmer day for the day at the top of the stack (or at least a day that's not colder).
        *   `st.pop();`: If the condition is true, we pop the top of the stack. We no longer need to keep the index because we have found a warmer day.

4.  **Storing the Result:**
    *   `ans[i] = (st.isEmpty())?0:st.peek()-i;`:  This line determines how many days we need to wait for a warmer temperature.
        *   `st.isEmpty()`: If the stack is empty after the `while` loop, it means there's no warmer day in the future for the current day `i`. In this case, we store `0` in `ans[i]`.
        *   `st.peek()-i`: If the stack is not empty, `st.peek()` contains the index of the next warmer day. We calculate the difference between the warmer day's index and the current day's index (`st.peek() - i`) to get the number of days to wait, and store it in `ans[i]`.

5.  **Pushing the Current Day:**
    *   `st.push(i);`: After processing the current day `i`, we push its index onto the stack.  The stack is used for future comparisons as we traverse through the temperatures.

6.  **Returning the Result:**
    *   `return ans;`: Finally, the function returns the `ans` array, which contains the number of days to wait for a warmer temperature for each day.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the `temperatures` array. Although there's a nested `while` loop, each element in the `temperatures` array is pushed onto the stack and popped at most once. Therefore, the total number of operations performed by the `while` loop across all iterations of the `for` loop is bounded by O(n).

*   **Space Complexity:** O(n), where n is the length of the `temperatures` array. In the worst-case scenario (e.g., the temperatures are monotonically decreasing), all the indices of the days will be stored in the stack. Therefore, the space required by the stack can be proportional to the size of the input array. Additionally, the `ans` array also takes O(n) space.
