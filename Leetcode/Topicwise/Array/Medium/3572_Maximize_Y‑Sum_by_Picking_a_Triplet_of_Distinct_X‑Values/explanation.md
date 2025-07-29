## LeetCode: Maximize Y-Sum by Picking a Triplet of Distinct X-Values

**1. Problem Understanding:**

The problem asks us to find the maximum sum of three distinct `y` values, where each `y` value corresponds to a unique `x` value from the input arrays `x` and `y`.  In essence, we need to select three distinct `x` values, and for each selected `x`, we choose the maximum corresponding `y` value.  The objective is to maximize the sum of these three selected `y` values. If fewer than three distinct `x` values exist, we return -1.


**2. Approach / Intuition:**

The solution uses a greedy approach.  Since we aim to maximize the sum of three `y` values corresponding to three distinct `x` values, we first consolidate the data to find the maximum `y` value for each unique `x`.  This is done using a HashMap. Then, we iterate through the maximum `y` values and select the top three largest values to compute the maximum sum. This is efficient because it avoids exploring all possible combinations of three `x` values, which would have a significantly higher time complexity.


**3. Data Structures and Algorithms:**

* **HashMap (Map in Java):** Used to store unique `x` values as keys and their corresponding maximum `y` values as values. This allows efficient lookup of maximum `y` for each unique `x`.
* **Sorting (implicitly):**  While not explicitly using a sorting algorithm, the code implicitly sorts the maximum `y` values by iterating and tracking the three largest values ( `f`, `m`, `l` representing first, middle, and last).


**4. Code Walkthrough:**

* **Lines 3-8:** A HashMap `map` is created to store unique `x` values and their maximum corresponding `y` values. The code iterates through the input arrays `x` and `y`. If an `x` value is already in the map, its associated `y` value is updated to the maximum of the current `y` value and the existing `y` value in the map. Otherwise, the `x` and `y` pair is added to the map.

* **Line 9:** Checks if there are at least three unique `x` values. If not, it returns -1 as specified in the problem statement.

* **Lines 11-17:**  This section finds the three largest `y` values ( `f`, `m`, `l`). It iterates through the values of the `map` (which are the maximum `y` values). `f`, `m`, and `l` keep track of the largest, second largest, and third largest values seen so far. The code efficiently updates these variables as it iterates.

* **Line 18:** The sum of the three largest `y` values (`f`, `m`, `l`) is returned.

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input arrays.  The HashMap creation takes O(n) time in the worst case (all x values are unique). Iterating through the HashMap's values also takes O(n) time in the worst case (all x values are unique). The selection of top three y values is O(n). Therefore, the overall time complexity is dominated by these linear operations.

* **Space Complexity:** O(n) in the worst case. This is because the HashMap could potentially store all unique `x` values if all `x` values are distinct.  The variables `f`, `m`, and `l` use constant space. Therefore, the space complexity is linear with respect to the input size.


In summary, this solution efficiently solves the problem using a HashMap for data consolidation and a greedy approach for selecting the top three `y` values, resulting in an optimal time and space complexity of O(n).  The code is well-structured and easy to understand, making it a good example of efficient problem-solving.
