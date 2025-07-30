## Container With Most Water - Problem Explanation and Solution

### 1. Problem Understanding:

The "Container With Most Water" problem asks us to find the largest area that can be formed by a container created from two vertical lines and the x-axis. We are given an array `height` where each element `height[i]` represents the height of the i-th vertical line. The container's width is the distance between the lines, and its height is the height of the shorter line. We need to find the two lines that maximize the area of the container.

### 2. Approach / Intuition:

The core idea behind the solution is to use a two-pointer approach.  We start with two pointers, `l` and `r`, pointing to the leftmost and rightmost lines, respectively.  The area formed by these two lines is calculated, and then we move the pointer that points to the shorter line inward.

Why does this work?

*   **Maximizing Width:** Initially, we consider the widest possible container.
*   **Optimizing Height:** If we move the taller line inward, the width decreases, and the height of the container is limited by the shorter line.  Therefore, moving the taller line is unlikely to yield a larger area. However, if we move the shorter line, there's a chance that we encounter a taller line, which would potentially increase the height and thus increase the area, despite the decrease in width.
*   **Greedy Approach:** By repeatedly moving the shorter line, we are effectively exploring potential containers that are wider or taller, ensuring we eventually find the optimal container with the maximum area.

In essence, we are systematically reducing the width while searching for a potentially larger height that could compensate for the reduced width and result in a larger overall area.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure used is an array (`height`) representing the heights of the vertical lines.
*   **Algorithms:** The algorithm used is the two-pointer technique, specifically the "shrinking window" pattern.

### 4. Code Walkthrough:

```java
class Solution {
    public int maxArea(int[] height) {
        int l = 0; // Initialize the left pointer to the beginning of the array
        int r = height.length - 1; // Initialize the right pointer to the end of the array
        int ans = 0; // Initialize the variable to store the maximum area found so far
        while (l < r) { // Continue as long as the left pointer is to the left of the right pointer
            ans = Math.max(ans, (r - l) * (Math.min(height[l], height[r]))); // Calculate the current area and update the maximum area if necessary
            if (height[l] < height[r]) { // If the left line is shorter than the right line
                l++; // Move the left pointer one step to the right
            } else { // Otherwise (if the right line is shorter or equal to the left line)
                r--; // Move the right pointer one step to the left
            }
        }
        return ans; // Return the maximum area found
    }
}
```

*   **Initialization:**
    *   `l = 0`:  The left pointer is initialized to the first element of the `height` array.
    *   `r = height.length - 1`: The right pointer is initialized to the last element of the `height` array.
    *   `ans = 0`: The variable `ans` stores the maximum area found so far, initialized to 0.

*   **`while (l < r)` loop:**
    *   This loop continues as long as the left pointer is to the left of the right pointer.  This ensures that we are considering all possible pairs of lines.
    *   `ans = Math.max(ans, (r - l) * (Math.min(height[l], height[r])))`:  This line calculates the area of the container formed by the lines at indices `l` and `r`. The width of the container is `r - l`, and the height is `Math.min(height[l], height[r])` (the height of the shorter line). The `Math.max` function updates `ans` with the larger value between the current `ans` and the calculated area.
    *   `if (height[l] < height[r]) l++`:  If the line at index `l` is shorter than the line at index `r`, we increment `l`. This is because moving the shorter line might lead to a larger height, which could potentially increase the area.
    *   `else r--`:  Otherwise (if the line at index `r` is shorter or equal to the line at index `l`), we decrement `r`.  The same reasoning applies here: moving the shorter line (in this case, the right line) might lead to a larger height.

*   **`return ans`:** After the loop finishes, the function returns the maximum area found, stored in the `ans` variable.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the `height` array. The two pointers `l` and `r` move towards each other, and in each iteration, we perform constant-time operations. Each pointer traverses the array at most once, so the time complexity is linear.

*   **Space Complexity:** O(1). The algorithm uses a constant amount of extra space, regardless of the input size. We only use a few integer variables (`l`, `r`, `ans`) to store the pointers and the maximum area. Therefore, the space complexity is constant.
