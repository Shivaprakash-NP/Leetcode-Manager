## LeetCode: Pascal's Triangle II - Detailed Solution Explanation

**1. Problem Understanding:**

The problem "Pascal's Triangle II" asks us to return the `rowIndex`-th row of Pascal's Triangle. Pascal's Triangle is a triangular array where each number is the sum of the two numbers directly above it. The first row is `[1]`, the second row is `[1, 1]`, the third row is `[1, 2, 1]`, and so on.  The goal is to efficiently generate only the specified row without calculating the preceding rows.


**2. Approach / Intuition:**

This solution uses a formulaic approach to directly calculate the elements of the specified row.  Instead of building the entire Pascal's Triangle iteratively, it leverages the mathematical property that each element in a row can be calculated using the previous element and its index.  Specifically, the `i`-th element in row `n` (where indexing starts from 0) is given by the formula:  `n! / (i! * (n-i)!)`. However, to avoid potential integer overflow, this code uses a more optimized calculation:  `previous_element * (n - i + 1) / i`

This approach is chosen for its efficiency.  Building the entire triangle would be unnecessarily time-consuming if only one row is needed.  The direct calculation avoids redundant computations.


**3. Data Structures and Algorithms:**

* **Data Structures:** `ArrayList<Integer>` is used to store and return the elements of the `rowIndex`-th row.
* **Algorithms:** The core algorithm is a direct calculation using a loop and the formula mentioned above.  It's essentially a mathematical calculation rather than a complex graph traversal or dynamic programming algorithm.


**4. Code Walkthrough:**

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> val = new ArrayList<>();
        val.add(1); // Add the first element (always 1)
        long r = rowIndex + 1; //Using long to avoid integer overflow. Represents the total number of elements in the row.

        for(long i = 1 ; i<r ; i++) {
            val.add((int)(val.get((int)i-1)*(r-i)/i)); //Calculates and adds the next element using the formula
        }
        return val;
    }
}
```

* **Line 3:** An `ArrayList` named `val` is created to store the integers of the row.  The first element, 1, is added.
* **Line 4:** `r` is calculated as `rowIndex + 1`. This represents the total number of elements in the target row.  `long` is used to prevent potential integer overflow during calculations.
* **Line 5-6:** A `for` loop iterates from 1 up to `r`.  Inside the loop:
    * `val.get((int)i-1)` gets the previous element in the `val` list.
    * `(r-i)/i` calculates a part of the formula which represents the ratio that needs to be multiplied to the previous element to obtain the current element.
    * The result is cast to `int` before adding to the list.
* **Line 7:** The `ArrayList` containing the calculated row is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is `rowIndex`. The loop iterates `rowIndex` times, performing a constant number of operations in each iteration.

* **Space Complexity:** O(n), where n is `rowIndex`. The `ArrayList` stores `rowIndex + 1` elements.


**Potential Improvements:**

While this code works correctly, using `long` to avoid overflow is a good practice.  A more robust solution might include explicit overflow checks or use of `BigInteger` for extremely large input values of `rowIndex`.  Also, the formula itself could be slightly improved for clarity, though its efficiency is already good.  For example, explicitly calculating the binomial coefficient would be less concise but would make the intention clearer.
