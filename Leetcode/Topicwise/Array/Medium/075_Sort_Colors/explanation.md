```markdown
## Sort Colors Problem Explanation

### 1. Problem Understanding:

The "Sort Colors" problem asks us to sort an array of integers representing colors (0, 1, and 2) in-place.  The goal is to arrange the array such that all 0s come first, followed by all 1s, and finally all 2s.  We must do this *without* using any built-in sorting functions (like `Arrays.sort()` in Java).

### 2. Approach / Intuition:

The solution uses the "Dutch National Flag" algorithm, also known as the three-way partitioning algorithm.  The intuition is to maintain three pointers: `l` (low), `m` (mid), and `h` (high).

*   `l` points to the end of the region containing only 0s. Elements to the left of `l` are all 0s.
*   `m` points to the current element being considered. Elements between `l` and `m` are all 1s.
*   `h` points to the beginning of the region containing only 2s. Elements to the right of `h` are all 2s.

We iterate through the array using the `m` pointer.  Based on the value of `nums[m]`, we do the following:

*   **If `nums[m] == 0`:** We swap `nums[m]` with `nums[l]`, because we want to move the 0 to its correct position at the beginning. Then, we increment both `l` and `m` because we now know `nums[l]` is a 0 and the element previously at `nums[l]` (now at `nums[m]`) has to be checked as well.
*   **If `nums[m] == 2`:** We swap `nums[m]` with `nums[h]`, because we want to move the 2 to its correct position at the end.  We decrement `h` because we now know `nums[h]` is a 2. *However*, we do *not* increment `m` because the element we just swapped into `nums[m]` could be any of 0, 1 or 2, and needs to be checked in the next iteration.
*   **If `nums[m] == 1`:** We simply increment `m` because the element is already in its correct position between 0s and 2s.

The algorithm terminates when `m` crosses `h`.  At this point, all elements are correctly sorted.

This approach is chosen because it's an efficient in-place sorting algorithm tailored for a small, fixed number of distinct values (in this case, 0, 1, and 2).  It avoids the general-purpose comparison-based sorting algorithms, which would be less efficient.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure is the input array `nums` itself.  No additional data structures are used.
*   **Algorithms:** The core algorithm is the "Dutch National Flag" algorithm or three-way partitioning.  It relies on in-place swaps to rearrange elements based on their values.

### 4. Code Walkthrough:

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int l = 0;
        int m = 0;
        int h = n-1;
        while(m<=h) {
            if(nums[m] == 0) {
                int tem = nums[l];
                nums[l] = nums[m];
                nums[m] = tem;
                l++;
                m++;
            } else if(nums[m] == 2) {
                int tem = nums[h];
                nums[h] = nums[m];
                nums[m] = tem;
                h--;
            } else m++;
        }
    }
}
```

*   **`class Solution { ... }`:**  Defines the class `Solution` which contains the `sortColors` method.
*   **`public void sortColors(int[] nums) { ... }`:** This is the main method that takes the integer array `nums` as input and sorts it in-place.
*   **`int n = nums.length;`:** Gets the length of the input array and stores it in `n`.
*   **`int l = 0;`:** Initializes the `l` pointer to 0.  `l` marks the boundary between the 0s section and the 1s/unsorted section.
*   **`int m = 0;`:** Initializes the `m` pointer to 0. `m` is the current element being processed.
*   **`int h = n - 1;`:** Initializes the `h` pointer to the last index of the array. `h` marks the boundary between the 2s section and the 1s/unsorted section.
*   **`while (m <= h) { ... }`:** The main loop that continues as long as the `m` pointer is less than or equal to the `h` pointer. This loop iterates through the unsorted portion of the array.
*   **`if (nums[m] == 0) { ... }`:** If the current element `nums[m]` is 0:
    *   **`int tem = nums[l];`**  Stores the value at `nums[l]` in a temporary variable `tem`.
    *   **`nums[l] = nums[m];`**  Moves the 0 from `nums[m]` to `nums[l]`.
    *   **`nums[m] = tem;`** Moves the value previously at `nums[l]` to `nums[m]`.
    *   **`l++;`**  Increments `l` to point to the next position after the 0s section.
    *   **`m++;`**  Increments `m` to move to the next element.
*   **`else if (nums[m] == 2) { ... }`:** If the current element `nums[m]` is 2:
    *   **`int tem = nums[h];`**  Stores the value at `nums[h]` in a temporary variable `tem`.
    *   **`nums[h] = nums[m];`**  Moves the 2 from `nums[m]` to `nums[h]`.
    *   **`nums[m] = tem;`**  Moves the value previously at `nums[h]` to `nums[m]`.
    *   **`h--;`**  Decrements `h` to point to the next position before the 2s section.  **Note:** `m` is *not* incremented here because the value swapped from `nums[h]` to `nums[m]` needs to be processed in the next iteration.
*   **`else m++;`:** If the current element `nums[m]` is 1, it's already in the correct position, so simply increment `m` to move to the next element.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array. The `while` loop iterates through the array at most once. Each element is visited and processed a constant number of times.
*   **Space Complexity:** O(1). The algorithm sorts the array in-place and uses only a constant amount of extra space for the `l`, `m`, `h`, and `tem` variables, regardless of the input size.
```