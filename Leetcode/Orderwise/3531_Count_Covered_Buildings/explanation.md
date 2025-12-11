### Problem Understanding

The problem "Count Covered Buildings" asks us to identify and count specific buildings based on their spatial relationship with other buildings. According to the provided code's logic, a building located at `(x, y)` is considered "covered" if it satisfies two conditions simultaneously:

1.  **Horizontal Coverage (`b1`):** There exists at least one other building `(x_left, y)` such that `x_left < x` (to its left on the same horizontal line) AND at least one other building `(x_right, y)` such that `x_right > x` (to its right on the same horizontal line).
2.  **Vertical Coverage (`b2`):** There exists at least one other building `(x, y_down)` such that `y_down < y` (below it on the same vertical line) AND at least one other building `(x, y_up)` such that `y_up > y` (above it on the same vertical line).

In essence, a building `(x, y)` is covered if it is strictly "sandwiched" by other buildings along its own x-axis and along its own y-axis. It must have neighbors to its left, right, top, and bottom, all lying on the same respective horizontal or vertical lines as itself.

### Approach / Intuition

The core idea is to efficiently determine, for any given `x` or `y` coordinate, what are the minimum and maximum `x` values (for a given `y`) and minimum and maximum `y` values (for a given `x`) present among all buildings. This pre-computation allows for a quick O(1) check for each building to see if it meets the "covered" criteria.

Here's the breakdown of the strategy:

1.  **Pre-computation Phase:**
    *   We create four auxiliary arrays: `xmin`, `xmax`, `ymin`, `ymax`.
    *   `xmin[y]` will store the smallest x-coordinate found among all buildings that have the y-coordinate `y`.
    *   `xmax[y]` will store the largest x-coordinate found among all buildings that have the y-coordinate `y`.
    *   `ymin[x]` will store the smallest y-coordinate found among all buildings that have the x-coordinate `x`.
    *   `ymax[x]` will store the largest y-coordinate found among all buildings that have the x-coordinate `x`.
    *   These arrays are initialized with extreme values (`Integer.MAX_VALUE` for `min` arrays and `Integer.MIN_VALUE` for `max` arrays) so that any actual building coordinate will correctly update them.
    *   We iterate through all given `buildings` once. For each building `(x, y)`, we update `xmin[y]`, `xmax[y]`, `ymin[x]`, and `ymax[x]` using `Math.min()` and `Math.max()`.

2.  **Counting Phase:**
    *   We initialize a counter `cnt` to 0.
    *   We iterate through all `buildings` a second time. For each building `(x, y)`:
        *   We check the horizontal coverage condition: `x < xmax[y]` (is there a building to the right on the same y-line?) AND `x > xmin[y]` (is there a building to the left on the same y-line?). Let's call this `b1`.
        *   We check the vertical coverage condition: `y < ymax[x]` (is there a building above on the same x-line?) AND `y > ymin[x]` (is there a building below on the same x-line?). Let's call this `b2`.
        *   If both `b1` and `b2` are true, it means the current building `(x, y)` is covered according to the problem's definition, and we increment `cnt`.

This approach is chosen because it transforms what could be a complex O(M^2) check (where M is the number of buildings) into a much faster O(M) check after an O(N+M) pre-computation (where N is the maximum coordinate value).

### Data Structures and Algorithms

*   **Data Structures:**
    *   **Arrays:** Four integer arrays (`xmin`, `xmax`, `ymin`, `ymax`) are used as lookup tables. Their size `n+1` (where `n` is the maximum coordinate value) allows direct indexing by coordinate values.
*   **Algorithms:**
    *   **Iteration/Looping:** Two main loops iterate through the `buildings` array.
    *   **Min/Max Operations:** `Math.min()` and `Math.max()` are used extensively during the pre-computation phase to find the extreme coordinates.
    *   **Array Initialization:** `Arrays.fill()` is used to quickly set initial values for the auxiliary arrays.

### Code Walkthrough

```java
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // 'n' represents the maximum possible coordinate value (inclusive).
        // Arrays are sized n+1 to handle coordinates from 0 up to n.

        // 1. Initialize arrays for pre-computation:
        // xmin[y]: stores the minimum x-coordinate for a given y.
        // xmax[y]: stores the maximum x-coordinate for a given y.
        // ymin[x]: stores the minimum y-coordinate for a given x.
        // ymax[x]: stores the maximum y-coordinate for a given x.
        int[] xmin = new int[n+1];
        int[] xmax = new int[n+1];
        int[] ymin = new int[n+1];
        int[] ymax = new int[n+1];

        // Initialize xmin and ymin with a very large value.
        // Any actual x or y coordinate will be smaller, correctly updating these.
        Arrays.fill(xmin, Integer.MAX_VALUE);
        Arrays.fill(ymin, Integer.MAX_VALUE);

        // Initialize xmax and ymax with a very small value.
        // Any actual x or y coordinate will be larger, correctly updating these.
        Arrays.fill(xmax, Integer.MIN_VALUE);
        Arrays.fill(ymax, Integer.MIN_VALUE);

        // 2. First Pass: Populate the min/max arrays by iterating through all buildings.
        for(int[] b : buildings) {
            int x = b[0]; // Current building's x-coordinate
            int y = b[1]; // Current building's y-coordinate

            // Update min/max x-coordinates for the current y-level.
            xmin[y] = Math.min(xmin[y], x);
            xmax[y] = Math.max(xmax[y], x);

            // Update min/max y-