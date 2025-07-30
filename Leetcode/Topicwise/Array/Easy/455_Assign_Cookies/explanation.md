## LeetCode: Assign Cookies - Detailed Explanation

**1. Problem Understanding:**

The "Assign Cookies" problem asks us to determine the maximum number of children that can be satisfied with a given set of cookies.  Each child has a greed factor (`g`), representing the minimum size cookie they will accept, and we have a set of cookies (`s`) with varying sizes.  A cookie can only satisfy one child, and we want to find the maximum number of children we can satisfy by assigning cookies efficiently.


**2. Approach / Intuition:**

The most efficient approach is to use a greedy strategy.  We sort both the children's greed factors (`g`) and the cookie sizes (`s`) in ascending order.  Then, we iterate through the sorted arrays, attempting to assign the smallest available cookie to the child with the smallest greed factor.  If a cookie is large enough to satisfy a child (`s[j] >= g[i]`), we move on to the next child.  If not, we move to the next cookie.  This approach guarantees that we maximize the number of satisfied children because we prioritize satisfying the children with the smallest greed factors first using the smallest cookies possible.  We continue this process until we run out of children or cookies.


**3. Data Structures and Algorithms:**

* **Data Structures:** Arrays are used to store the children's greed factors and cookie sizes.
* **Algorithms:**  The core algorithm is a greedy approach combined with sorting.  `Arrays.sort()` is used to sort the input arrays, enabling the efficient greedy selection.


**4. Code Walkthrough:**

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // Sort children's greed factors in ascending order.
        Arrays.sort(s); // Sort cookie sizes in ascending order.

        int i = 0, j = 0; // Pointers for children and cookies.

        while (i < g.length && j < s.length) { // Iterate until we run out of children or cookies.
            if (g[i] <= s[j]) { // If the current cookie is large enough for the current child...
                i++; // ...satisfy the child and move to the next child.
            }
            j++; // Move to the next cookie regardless of whether it was assigned.
        }

        return i; // The number of satisfied children is equal to the index 'i'.
    }
}
```

* **`Arrays.sort(g);` and `Arrays.sort(s);`**: These lines sort the input arrays in ascending order, which is crucial for the greedy strategy to work correctly.

* **`int i = 0, j = 0;`**: These initialize pointers `i` and `j` to track the current child and cookie being considered.

* **`while (i < g.length && j < s.length)`**: This loop continues as long as there are children and cookies left to consider.

* **`if (g[i] <= s[j]) i++;`**: This condition checks if the current cookie (`s[j]`) is large enough to satisfy the current child (`g[i]`). If true, the child is considered satisfied, and the pointer `i` moves to the next child.

* **`j++;`**: This line always increments `j` to move to the next cookie.  Even if the current cookie wasn't large enough, we need to check the next one.

* **`return i;`**:  The final value of `i` represents the number of children that were successfully satisfied.



**5. Time and Space Complexity:**

* **Time Complexity:** O(m log m + n log n), where 'm' is the length of `g` (greed factors) and 'n' is the length of `s` (cookie sizes). This is dominated by the sorting step of both arrays. The while loop runs in O(min(m, n)) time in the worst case.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space regardless of the input size.  The sorting is done in-place (for most common implementations of `Arrays.sort()`).  We only use a constant number of variables (`i`, `j`).
