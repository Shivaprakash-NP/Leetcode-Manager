## LeetCode: Find Subsequence of Length K With the Largest Sum

**1. Problem Understanding:**

The problem asks us to find a subsequence of length `k` from a given integer array `nums` such that the sum of the elements in this subsequence is maximized.  Crucially, we need to maintain the original order of the elements within the selected subsequence.  Simply selecting the `k` largest elements isn't sufficient if it disrupts the original order.


**2. Approach / Intuition:**

The solution uses a two-step approach:

1. **Identify the k largest elements:** We first sort the input array based on the values of the elements in descending order.  This allows us to easily identify the `k` largest elements.  We use a `Pair` class to store both the value and its original index in the array to maintain the original order information.

2. **Maintain original order:** After selecting the `k` largest elements, we sort them again, this time based on their original indices. This ensures that the final subsequence respects the order present in the original `nums` array.

This approach is chosen because it efficiently combines the need to select the largest elements while preserving the original order.  A purely greedy approach (iterating and selecting the largest available element maintaining order) would be less efficient and potentially miss an optimal solution.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `ArrayList<Pair>`:  Used to store the elements along with their original indices. The `Pair` class is a custom class created for this purpose.
    * `int[]`: Used to store the final result.

* **Algorithms:**
    * **Sorting:**  `Collections.sort()` (or `list.sort()`, which is a more modern approach) is used twice: once to sort by value in descending order and again to sort by original index in ascending order. This is a comparison-based sorting algorithm (likely merge sort or Timsort, depending on the Java implementation), with a time complexity of O(N log N) where N is the length of the array.


**4. Code Walkthrough:**

* **Lines 1-6:** Defines a `Pair` class to store the value and original index of each element.

* **Lines 9-13:** Creates an `ArrayList` of `Pair` objects.  It iterates through the input array `nums` and adds each element along with its index to the list.

* **Line 15:** Sorts the list of `Pair` objects in descending order based on their values (`b.val - a.val`).

* **Line 16:** Extracts the top `k` elements from the sorted list using `subList()` and creates a new list `topK`.

* **Line 18:** Sorts the `topK` list in ascending order based on their original indices (`a.ind - b.ind`).

* **Lines 21-24:** Creates an integer array `ans` of size `k` and populates it with the values of the top `k` elements, maintaining their original order as established in the previous sorting step.

* **Line 25:** Returns the `ans` array.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the length of the input array `nums`. This is dominated by the two sorting operations. The other operations (creation of lists, iteration) have linear time complexity, O(N).

* **Space Complexity:** O(N). This is due to the creation of the `ArrayList` which stores all elements of the input array, plus the `topK` list which stores k elements. The output array `ans` also takes O(k) space, where k <= N. Therefore the overall space complexity is O(N).

In summary, the solution efficiently solves the problem by leveraging sorting to find the k largest elements while carefully preserving the original order through the use of the index information.  The time complexity is optimal for comparison-based sorting, and the space complexity is linear with respect to the input size.
