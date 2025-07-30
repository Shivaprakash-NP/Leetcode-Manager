## Explanation of the "Sort an Array" LeetCode Solution

This explanation covers the provided Java code, which implements a solution to sort an array using the Merge Sort algorithm.

### 1. Problem Understanding:

The "Sort an Array" problem requires you to take an integer array as input and return a new array containing the same integers, but arranged in non-decreasing order (ascending order).  The goal is to implement an efficient sorting algorithm.

### 2. Approach / Intuition:

The solution employs the Merge Sort algorithm. Merge Sort is a divide-and-conquer algorithm that works as follows:

1.  **Divide:**  The input array is divided into two halves.
2.  **Conquer:** Each half is recursively sorted using Merge Sort.
3.  **Combine:** The two sorted halves are merged into a single sorted array.

The key idea behind Merge Sort is that merging two sorted arrays is a relatively efficient operation.  By recursively dividing the array and then merging the sorted sub-arrays, we can achieve a guaranteed `O(n log n)` time complexity, which is better than some simpler sorting algorithms like Bubble Sort or Insertion Sort, which have `O(n^2)` complexity in the worst case.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  The primary data structure used is the input array `nums`.  A temporary array `temp` is used during the merge process.
*   **Algorithms:**
    *   **Merge Sort:**  The core sorting algorithm.
    *   **Divide and Conquer:**  The fundamental paradigm behind Merge Sort.
    *   **Merging:** The process of combining two sorted subarrays into a single sorted array.

### 4. Code Walkthrough:

The provided code consists of three methods: `merge`, `mergesort`, and `sortArray`.

**`sortArray(int[] nums)`:**

```java
public int[] sortArray(int[] nums) {
    mergesort(nums , 0 , nums.length-1);
    return nums;
}
```

*   This is the main entry point of the solution.
*   It takes the input array `nums` as input.
*   It calls the `mergesort` function to sort the array in place.
*   It returns the sorted `nums` array.  Since `mergesort` modifies the array directly, no new array needs to be created.

**`mergesort(int[] arr, int l, int r)`:**

```java
public void mergesort(int[] arr , int l , int r) {
    if(l>=r) return;
    int m = (l+r)/2;
    mergesort(arr , l , m);
    mergesort(arr , m+1 , r);
    merge(arr , l , m , r);
}
```

*   This is the recursive Merge Sort function.
*   It takes the array `arr`, the left index `l`, and the right index `r` as input, representing the portion of the array to be sorted.
*   **Base Case:** `if(l>=r) return;` This checks if the sub-array has only one element or is empty. If so, it's already sorted, and the function returns.
*   **Divide:** `int m = (l+r)/2;` Calculates the middle index `m` of the sub-array.
*   **Conquer:** `mergesort(arr , l , m);` Recursively sorts the left half of the sub-array (from `l` to `m`).  `mergesort(arr , m+1 , r);` Recursively sorts the right half of the sub-array (from `m+1` to `r`).
*   **Combine:** `merge(arr , l , m , r);`  Calls the `merge` function to merge the two sorted halves into a single sorted sub-array.

**`merge(int[] arr, int l, int m, int r)`:**

```java
public void merge(int[] arr, int l, int m, int r) {
    int[] temp = new int[r - l + 1];
    int i = l, j = m + 1, k = 0;

    while (i <= m && j <= r) {
        if (arr[i] < arr[j]) temp[k++] = arr[i++];
        else temp[k++] = arr[j++];
    }
    while (i <= m) temp[k++] = arr[i++];
    while (j <= r) temp[k++] = arr[j++];

    for (int x = 0; x < temp.length; x++) {
        arr[l + x] = temp[x];
    }
}
```

*   This function merges two sorted sub-arrays of `arr` into a single sorted sub-array.
*   It takes the array `arr`, the left index `l`, the middle index `m`, and the right index `r` as input. The sub-arrays to be merged are `arr[l...m]` and `arr[m+1...r]`.
*   `int[] temp = new int[r - l + 1];` Creates a temporary array `temp` to store the merged sorted elements. The size of the `temp` array is the same as the size of the sub-array being merged.
*   `int i = l, j = m + 1, k = 0;` Initializes three index variables: `i` points to the beginning of the left sub-array, `j` points to the beginning of the right sub-array, and `k` points to the beginning of the `temp` array.
*   `while (i <= m && j <= r)`: This loop iterates as long as there are elements in both sub-arrays.  Inside the loop:
    *   `if (arr[i] < arr[j]) temp[k++] = arr[i++];` If the element at index `i` in the left sub-array is smaller than the element at index `j` in the right sub-array, the element at `arr[i]` is copied to `temp[k]`, and both `i` and `k` are incremented.
    *   `else temp[k++] = arr[j++];` Otherwise, the element at `arr[j]` is copied to `temp[k]`, and both `j` and `k` are incremented.
*   `while (i <= m) temp[k++] = arr[i++];` This loop handles the case where there are remaining elements in the left sub-array after the previous loop has finished.  It copies the remaining elements from the left sub-array to `temp`.
*   `while (j <= r) temp[k++] = arr[j++];` This loop handles the case where there are remaining elements in the right sub-array after the first loop has finished. It copies the remaining elements from the right sub-array to `temp`.
*   `for (int x = 0; x < temp.length; x++) { arr[l + x] = temp[x]; }` This loop copies the elements from the `temp` array back to the original `arr` array, overwriting the unsorted sub-array with the sorted elements. The index `l+x` is used to place the merged elements back into the correct position in the original array.

### 5. Time and Space Complexity:

*   **Time Complexity:** `O(n log n)`
    *   The `mergesort` function divides the array into halves recursively, which takes `O(log n)` steps.
    *   The `merge` function takes `O(n)` time to merge the two sorted halves.
    *   Therefore, the overall time complexity is `O(n log n)`.
*   **Space Complexity:** `O(n)`
    *   The `merge` function uses a temporary array `temp` of size `n` to store the merged elements.  Therefore, the space complexity is `O(n)`. Although the recursion itself uses stack space, for a well-balanced merge sort, the depth of the recursion is `log n` and the space required for function calls is `O(log n)`. This is smaller than `O(n)` for large `n` so we can consider it `O(n)`.
