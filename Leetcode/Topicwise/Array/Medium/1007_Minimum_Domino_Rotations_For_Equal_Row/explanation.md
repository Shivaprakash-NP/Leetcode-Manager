## Minimum Domino Rotations For Equal Row

Here's a detailed explanation of the provided Java code for the LeetCode problem "Minimum Domino Rotations For Equal Row":

### 1. Problem Understanding:

We are given two arrays, `tops` and `bottoms`, representing the values on the top and bottom halves of a row of dominoes. Our goal is to find the minimum number of rotations needed to make all the values in either the `tops` row or the `bottoms` row equal. A rotation involves swapping the top and bottom values of a single domino. If it is not possible to make either row equal, we should return -1.

### 2. Approach / Intuition:

The core idea is based on the observation that if it's possible to make all values in a row equal to some number `x`, then `x` must appear in at least one of the top or bottom rows at every index. In other words, if `x` is not present in both `tops[i]` and `bottoms[i]` for any `i`, then it's impossible to make any row equal to `x`.

The approach involves the following steps:

1.  **Check Validity:** Iterate through possible values (1 to 6, as dominoes have numbers 1 to 6).
2.  **Check if Possible:** For each possible value `x`, check if it's possible to make either the top or bottom row equal to `x`. This is done by checking if at each index `i`, either `tops[i]` or `bottoms[i]` is equal to `x`. If not, then `x` is not a valid target.
3.  **Calculate Minimum Rotations:** If `x` is a valid target, calculate the minimum number of rotations needed to make the `tops` row equal to `x` and the minimum number of rotations needed to make the `bottoms` row equal to `x`. Return the smaller of these two values.

This approach is chosen because it's a direct and efficient way to check all possible values (1 to 6) and determine the minimum number of rotations needed to achieve the desired state.  It leverages the constraint that domino values are limited to the range of 1 to 6.

### 3. Data Structures and Algorithms:

*   **Arrays:** The `tops` and `bottoms` arrays store the domino values.
*   **Iteration:** The code uses nested loops to iterate through possible target values and the dominoes.
*   **Conditional Logic:** `if` statements are used extensively to check conditions and determine the validity of a target value and to count rotations.
*   **`Math.min()`:** This function is used to find the minimum number of rotations between making the top row equal or the bottom row equal to a target value.

### 4. Code Walkthrough:

```java
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        if(tops.length != bottoms.length) return -1; // Check if lengths are unequal - impossible to solve

        for(int i = 1 ; i<=6 ; i++) { // Iterate through possible values (1 to 6)
            boolean valid = true; // Assume the value is valid

            for(int j = 0 ; j < tops.length ; j++) { // Check each domino
                if(tops[j] != i && bottoms[j] != i) { // If neither top nor bottom is equal to 'i'
                    valid = false; // Invalid, 'i' cannot be the target
                    break; // No point continuing, go to the next 'i'
                }
            }

            if(valid) { // If 'i' is a valid target
                int m1 = 0; // Rotations to make 'tops' equal to 'i'
                int m2 = 0; // Rotations to make 'bottoms' equal to 'i'

                for(int k = 0 ; k<tops.length ; k++) { // Iterate again, now to count rotations
                    if(tops[k] != i) m1++; // Count rotations needed for 'tops'
                    if(bottoms[k] != i) m2++; // Count rotations needed for 'bottoms'
                }
                return Math.min(m1 , m2); // Return the minimum of the two rotation counts
            }
        }
        return -1; // No valid target found, return -1
    }
}
```

**Explanation:**

1.  **`if(tops.length != bottoms.length) return -1;`**:  This line checks if the lengths of the `tops` and `bottoms` arrays are equal. If they aren't, it's impossible to achieve a solution, so it immediately returns -1.

2.  **`for(int i = 1 ; i<=6 ; i++) { ... }`**:  This outer loop iterates through the possible values for the dominoes (1 to 6). `i` represents the target value we're trying to achieve in either the top or bottom row.

3.  **`boolean valid = true;`**: Inside the outer loop, this line initializes a boolean variable `valid` to `true`. We assume that the current target value `i` is valid until proven otherwise.

4.  **`for(int j = 0 ; j < tops.length ; j++) { ... }`**: This inner loop iterates through each domino (index `j`).

5.  **`if(tops[j] != i && bottoms[j] != i) { ... }`**:  This `if` statement checks if *neither* the top value (`tops[j]`) nor the bottom value (`bottoms[j]`) of the current domino is equal to the target value `i`. If this condition is true, it means we cannot make either the top or bottom row equal to `i` at this index.

6.  **`valid = false; break;`**: If the `if` condition in step 5 is true, we set `valid` to `false` because the target value `i` is not possible. The `break` statement exits the inner loop because there's no point in continuing the check if we already know `i` is not a valid target.

7.  **`if(valid) { ... }`**:  After the inner loop completes, this `if` statement checks if `valid` is still `true`. If it is, it means that for every domino, at least one of the top or bottom values was equal to the target value `i`.  Therefore, `i` is a potentially valid target.

8.  **`int m1 = 0; int m2 = 0;`**:  These lines initialize two integer variables:
    *   `m1`: Counts the number of rotations needed to make the `tops` row equal to `i`.
    *   `m2`: Counts the number of rotations needed to make the `bottoms` row equal to `i`.

9.  **`for(int k = 0 ; k<tops.length ; k++) { ... }`**: This loop iterates through the dominoes again to count the necessary rotations.

10. **`if(tops[k] != i) m1++; if(bottoms[k] != i) m2++;`**:
    *   If the top value `tops[k]` is not equal to `i`, we need to rotate the domino to bring `i` to the top. So, we increment `m1`.
    *   If the bottom value `bottoms[k]` is not equal to `i`, we need to rotate the domino to bring `i` to the bottom. So, we increment `m2`.

11. **`return Math.min(m1 , m2);`**: After the loop in step 9 completes, this line returns the minimum of `m1` and `m2`.  This is the minimum number of rotations needed to make either the top or bottom row equal to `i`.

12. **`return -1;`**: If the outer loop completes without finding a valid target value `i` (i.e., `valid` is never `true` for any `i` from 1 to 6), then this line returns -1, indicating that it's not possible to make either row equal to any value.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the length of the `tops` and `bottoms` arrays. The outer loop iterates a maximum of 6 times (constant), and the inner loops iterate up to N times.  Therefore, the overall time complexity is O(6 * N), which simplifies to O(N).

*   **Space Complexity:** O(1). The code uses a fixed number of variables, regardless of the size of the input arrays. Therefore, the space complexity is constant.
