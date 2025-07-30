## Escape The Ghosts - Detailed Explanation

### 1. Problem Understanding:

The problem "Escape The Ghosts" asks whether you can reach a target location on a 2D grid before any of the ghosts can. You and the ghosts move simultaneously, and each can only move one unit horizontally or vertically in each step. The question boils down to: can you reach the target before *any* of the ghosts do?  We are given the coordinates of the ghosts' starting positions and the target location.

### 2. Approach / Intuition:

The core idea is based on the Manhattan distance. The Manhattan distance between two points (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|. This represents the minimum number of moves required to reach one point from the other, moving only horizontally and vertically.

The approach focuses on determining if *any* of the ghosts can reach the target faster than you. You can escape the ghosts only if all the ghosts take strictly longer to reach the target than you.  So we need to calculate the Manhattan distance from your starting point (0,0) to the target, and then the Manhattan distance from each ghost's starting point to the target.

If *any* ghost has a Manhattan distance to the target that is less than or equal to your Manhattan distance to the target, that ghost can reach the target at the same time or before you do, meaning you cannot escape.  Therefore, you're doomed and the function should return `false`.  Otherwise, if all ghosts have larger Manhattan distances to the target than you do, you can escape, so the function should return `true`.

This approach is efficient because it directly compares the minimum number of moves required for you and each ghost to reach the target without simulating the actual movements.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  We use a simple 2D integer array `int[][] ghosts` to store the coordinates of the ghosts, and a 1D integer array `int[] target` to store the target coordinates.

*   **Algorithms:** The core algorithm involves calculating the Manhattan distance. The Manhattan distance is calculated using absolute differences and addition, which are fundamental arithmetic operations.  We also use a simple loop to iterate through the ghosts array.

### 4. Code Walkthrough:

```java
class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int d_h = Math.abs(target[0]) + Math.abs(target[1]);
        for(int[] v : ghosts)
        {
            if(Math.abs(target[0] - v[0]) + Math.abs(target[1] - v[1]) <= d_h) return false;
        }
        return true;
    }
}
```

1.  **`class Solution { ... }`:** This defines the class `Solution` which contains the `escapeGhosts` method.

2.  **`public boolean escapeGhosts(int[][] ghosts, int[] target) { ... }`:** This is the main function that takes the ghosts' coordinates as a 2D integer array `ghosts` and the target coordinates as an integer array `target` as input. It returns a boolean value indicating whether it is possible to escape the ghosts.

3.  **`int d_h = Math.abs(target[0]) + Math.abs(target[1]);`:** This line calculates your Manhattan distance (`d_h`) from the origin (0, 0) to the target location.  `target[0]` represents the x-coordinate of the target and `target[1]` represents the y-coordinate of the target.

4.  **`for(int[] v : ghosts) { ... }`:** This loop iterates through each ghost's coordinates in the `ghosts` array.  In each iteration, `v` represents the coordinates of a single ghost.

5.  **`if(Math.abs(target[0] - v[0]) + Math.abs(target[1] - v[1]) <= d_h) return false;`:**  This is the crucial comparison.  Inside the loop, for each ghost, we calculate its Manhattan distance to the target location. If the ghost's Manhattan distance to the target is less than or equal to your Manhattan distance to the target (`d_h`), it means the ghost can reach the target at the same time or before you do.  In this case, you cannot escape, so we return `false` immediately.

6.  **`return true;`:** If the loop completes without finding any ghost that can reach the target faster than you, it means you can escape. Therefore, we return `true`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the number of ghosts.  The code iterates through the `ghosts` array once. Inside the loop, the operations performed are constant time (calculating the Manhattan distance and comparison).

*   **Space Complexity:** O(1). The code uses only a few integer variables to store the Manhattan distances and coordinates. The space used does not depend on the input size.
