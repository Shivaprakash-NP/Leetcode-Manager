## Minimum Adjacent Swaps to Alternate Parity: A Detailed Explanation

**1. Problem Understanding:**

The problem asks for the minimum number of adjacent swaps required to make the parity (even or odd) of elements in an array alternate.  For example, if we have `[1, 2, 3, 4]`, an alternating parity arrangement could be `[1, 2, 3, 4]` (odd, even, odd, even) or `[2, 1, 4, 3]` (even, odd, even, odd).  The solution needs to find the minimum number of swaps to achieve one of these arrangements. If it's impossible to achieve an alternating parity (e.g., unequal number of odd and even numbers in an array with an even length), the function should return -1.

**2. Approach / Intuition:**

The solution uses a clever approach based on two key ideas:

* **Target Arrays:**  It first creates two "target" arrays representing the desired alternating parity arrangements: one starting with an even number, the other starting with an odd number.  These target arrays represent the ideal ordering.

* **Inversion Counting with Merge Sort:** The core logic then involves finding the minimum number of inversions (pairs of elements out of order) between the original array and each target array.  The number of inversions directly corresponds to the minimum number of adjacent swaps needed to sort the array.  Merge sort is used efficiently to count inversions.

This approach is efficient because it avoids brute-forcing all possible swap combinations. Instead, it leverages the properties of inversions to quickly determine the minimum swaps required.


**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is an integer array (`int[]`).  A temporary array is also used within the merge sort function.
* **Algorithms:** The core algorithm is a variation of Merge Sort, modified to count inversions during the merge process.  This is an efficient approach with O(n log n) time complexity.


**4. Code Walkthrough:**

* **`minSwaps(int[] nums)`:** This is the main function.
    * It first counts the number of odd and even numbers in the input array.
    * It checks if an alternating parity arrangement is even possible (if the lengths are incompatible, returns -1).
    * It generates two target arrays (`getTargetArray`) – one starting with an even number and one starting with an odd number.
    * It calculates the inversions for both target arrays using `countInversions`, which is based on merge sort.
    * It returns the minimum number of inversions found.

* **`getTargetArray(int[] nums, boolean evenStart)`:** This function creates a target array.
    * It takes the input array and a boolean indicating whether to start with an even or odd number.
    * It assigns indices to elements based on their parity to create the alternating pattern.

* **`countInversions(int[] arr)`:** This function acts as a wrapper to call the recursive merge sort function.

* **`mergeSortAndCount(int[] arr, int left, int right)`:** This function recursively performs merge sort and counts inversions.
    * The base case is when the subarray has only one element (or is empty).
    * It recursively sorts the left and right halves.
    * It merges the sorted halves and counts inversions during the merge process (`merge`).

* **`merge(int[] arr, int left, int mid, int right)`:** This function merges two sorted subarrays and counts inversions.
    * It compares elements from the left and right subarrays.
    * If an element from the right subarray is smaller than an element from the left subarray, it means an inversion exists. The number of inversions is calculated efficiently.
    * The merged array is copied back into the original array.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n log n). This is dominated by the merge sort algorithm used for inversion counting. The other operations (counting odd/even numbers, creating target arrays) are O(n).

* **Space Complexity:** O(n).  The space complexity is primarily due to the temporary array used in the merge sort function.  The space used for other variables is O(1).


In summary, the solution cleverly uses the concept of inversions and an efficient merge sort-based algorithm to solve the "Minimum Adjacent Swaps to Alternate Parity" problem in an optimized manner.  The code is well-structured and easy to understand once the underlying logic is grasped.
