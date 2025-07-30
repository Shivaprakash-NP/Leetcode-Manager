```markdown
## Kth Missing Positive Number - Explained

### 1. Problem Understanding:

The problem asks us to find the *k*th missing positive integer in a given sorted array `arr` of positive integers.  For example, if `arr = [2, 3, 4, 7, 11]` and `k = 5`, the missing positive integers are `[1, 5, 6, 8, 9, ...]`.  We need to find the 5th missing positive integer, which is `9`.

### 2. Approach / Intuition:

The array is sorted, which is a big clue that we can use binary search to optimize our search. The core idea is to use binary search to find the index `l` such that all numbers *before* `arr[l]` have fewer than `k` missing positive numbers, and all numbers *after* `arr[l]` have at least `k` missing positive numbers.

For any number `arr[m]` at index `m`, the number of missing positive integers *before* `arr[m]` (including the numbers that would normally occupy index positions) can be calculated as `arr[m] - (m + 1)`. This is because the first `m+1` positive integers *should* be in the first `m+1` positions in the array if there were no missing numbers. Subtracting `m+1` from `arr[m]` gives the count of missing positives.

Based on this count of missing numbers `mis`, we adjust our binary search range:

- If `mis < k`, it means that `arr[m]` and all the numbers before it don't account for all *k* missing numbers. So, we need to search in the right half of the array (`l = m + 1`).
- If `mis >= k`, it means that `arr[m]` or some number before it already accounts for at least *k* missing numbers. So, we need to search in the left half of the array (`r = m - 1`).

After the binary search, `l` will be the index where the kth missing number occurs *after*.  The final answer is `l + k`, where `l` represents how many numbers we've seen in the array *that don't account for the k missing numbers yet*, and `k` represents the remaining missing numbers after scanning up to the index `l`. This works since l represents the number of elements we've "skipped" due to them not having enough missing numbers before them, and *k* is the number of missing numbers *after* this skipped amount.

### 3. Data Structures and Algorithms:

- **Data Structures:** Integer array (`arr`).
- **Algorithms:** Binary Search.

### 4. Code Walkthrough:

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int l = 0;
        int r = arr.length-1;
        while(l<=r)
        {
            int m = (l+r)/2;
            int mis = arr[m] - m - 1;
            if(mis < k) l = m+1;
            else r = m-1;
        }
        return l+k; 
    }
}
```

1.  **Initialization:**
    *   `l = 0`:  Initializes the left pointer of the binary search to the beginning of the array.
    *   `r = arr.length - 1`: Initializes the right pointer to the end of the array.

2.  **Binary Search Loop:**
    *   `while (l <= r)`: Continues the binary search as long as the left pointer is less than or equal to the right pointer.
    *   `int m = (l + r) / 2`: Calculates the middle index.
    *   `int mis = arr[m] - m - 1`:  Calculates the number of missing positive integers up to `arr[m]`. As explained before.
    *   `if (mis < k) l = m + 1`: If the number of missing integers `mis` is less than *k*, it means we need to search in the right half of the array because the *k*th missing number is greater than `arr[m]`.
    *   `else r = m - 1`: Otherwise, we need to search in the left half because the *k*th missing number is less than or equal to `arr[m]`.

3.  **Return Value:**
    *   `return l + k`: After the binary search, `l` will point to the index where the *k*th missing number would have been inserted to keep the array sorted. Therefore, `l + k` will be the *k*th missing positive number.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(log n), where n is the length of the array `arr`. This is because we are performing binary search on the array.
*   **Space Complexity:** O(1), as we are only using a constant amount of extra space for variables like `l`, `r`, `m`, and `mis`.
