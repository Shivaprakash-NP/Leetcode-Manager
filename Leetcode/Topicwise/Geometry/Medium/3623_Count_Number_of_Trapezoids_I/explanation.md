## LeetCode: Count Number of Trapezoids I - Solution Explanation

**1. Problem Understanding:**

The problem asks us to count the number of trapezoids that can be formed from a given set of points in a 2D plane.  Crucially, the trapezoids must have parallel bases which are horizontal lines (i.e., points with the same y-coordinate).  The code provided efficiently solves this problem by leveraging the structure of the input.


**2. Approach / Intuition:**

The solution uses a clever approach based on the observation that a trapezoid with horizontal parallel bases is defined by choosing two points with the same y-coordinate for one base and two points with the same y-coordinate for the other base (the bases can be on the same horizontal line, forming a rectangle or square as a special case).

The algorithm first groups points by their y-coordinate.  For each group (horizontal line), it calculates the number of ways to choose two points from that line to form a base.  This is done using combinations (n choose 2).  Then, it iteratively combines the number of ways to form bases from different horizontal lines to count the total number of trapezoids.  A crucial optimization involves using modular arithmetic (`% MOD`) to prevent integer overflow.

This approach is efficient because it avoids brute-force enumeration of all possible quadruplets of points.  It leverages the inherent structure of the problem to reduce the computational complexity significantly.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeMap<Integer, List<Integer>> map`: A `TreeMap` stores points grouped by their y-coordinate. The key is the y-coordinate, and the value is a `List` of x-coordinates for points sharing that y-coordinate.  `TreeMap` is chosen for efficient retrieval of points based on y-coordinate.
    * `ArrayList<Long> com`: An `ArrayList` to store the number of ways to choose two points from each horizontal line.
* **Algorithms:**
    * **Combinations:** The algorithm calculates combinations (n choose 2) to determine the number of ways to select two points from a given set of points.
    * **Modular Arithmetic:**  Modular arithmetic (`% MOD`) is used to prevent integer overflow during the calculations.


**4. Code Walkthrough:**

* **Lines 4-8:**  The code iterates through the input `points` array and populates the `map`.  `computeIfAbsent` ensures that if a y-coordinate is not present as a key, a new `ArrayList` is created for it. Then, the corresponding x-coordinate is added to that list.

* **Lines 10-17:** This section calculates the number of ways to choose two points from each horizontal line (`dum`). The formula `(long)n*(n-1)/2` efficiently calculates "n choose 2" (number of combinations). These values are stored in `com` and summed up in `ts`.

* **Lines 19-24:** This is the core logic for counting trapezoids. The code iterates through the `com` list.  In each iteration, it subtracts the current value (`val`) from the running total (`ts`), calculates the product of the updated `ts` and `val` (modulo `MOD`), and adds this product to the final answer (`ans`). This calculation cleverly counts the pairs of bases that form trapezoids.

* **Line 26:** The function returns the final count of trapezoids as an integer.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the number of points. The `TreeMap` operations (insertion and retrieval) take O(log N) time on average. The rest of the computations are linear in the number of points.

* **Space Complexity:** O(N) in the worst case, as the `map` could store all N points if they have distinct y-coordinates. The `com` list's size is bounded by the number of unique y-coordinates, also at most N.

The algorithm's efficiency is significantly better than the naive O(N^4) approach of checking all possible quadruplets of points.  The use of a `TreeMap` and a concise combination calculation contributes to this efficiency.
