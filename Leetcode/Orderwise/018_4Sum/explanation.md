```markdown
## 4Sum Problem Explanation

### 1. Problem Understanding:

The "4Sum" problem asks us to find all unique quadruplets (sets of four numbers) in a given array of integers that sum up to a specific target value. The solution should return a list of these unique quadruplets.  Duplicate quadruplets should not be included in the result.

### 2. Approach / Intuition:

The chosen approach is based on the following ideas:

1.  **Sorting:** Sort the input array. This allows us to use the two-pointer technique efficiently and easily skip duplicate values.

2.  **Nested Loops:**  The solution employs a series of nested loops. The outer two loops iterate through the array to fix the first two numbers of the quadruplet.

3.  **Two-Pointer Technique:** For each pair of the first two numbers, the remaining two numbers are found using the two-pointer technique. This involves having two pointers, one starting from the element after the second fixed number and the other starting from the end of the array. The pointers move inwards based on whether the current sum is less than, greater than, or equal to the target.

4.  **Skipping Duplicates:** To avoid duplicate quadruplets, the code includes checks to skip over duplicate values during the iteration of the loops and when moving the two pointers.

**Why this approach?**

*   Sorting allows for the efficient use of the two-pointer technique to search for the remaining two numbers to complete the quadruplet.
*   The two-pointer technique offers a linear time complexity O(n) within the nested loops, making the overall solution more efficient than brute-force approaches which could be O(n^4).
*   Skipping duplicates after sorting ensures that only unique quadruplets are included in the result, which is a crucial requirement of the problem.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `List<List<Integer>>`:  Used to store the result, which is a list of lists, where each inner list represents a quadruplet of integers that sums to the target.
    *   `int[]`: The input array of integers.
*   **Algorithms:**
    *   **Sorting:** `Arrays.sort(nums)` uses a sorting algorithm (likely a variation of quicksort or mergesort in Java's standard library).
    *   **Two-Pointer Technique:**  Used within the nested loops to efficiently find the remaining two numbers that sum to the target.

### 4. Code Walkthrough:

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // Sort the input array. Crucial for the two-pointer approach and skipping duplicates.
        int n = nums.length;
        List<List<Integer>> val = new ArrayList<>(); // Initialize the list to store the quadruplets.

        for (int i = 0; i < n - 3; i++) // First number of the quadruplet. Iterate up to n-3 because we need at least 4 numbers.
        { 
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicate values for the first number.
            for(int j = i+1 ; j<n-2 ; j++) // Second number of the quadruplet.  Starts from i+1 to avoid duplicates and redundancy.
            {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicate values for the second number.
                int k = j + 1, l = n - 1; // Initialize the two pointers: k starts after j, l starts at the end of the array.
                while (k < l) // Two-pointer loop.  Continues until the two pointers cross.
                {
                    long s = (long) nums[i] + nums[j] + nums[k] + nums[l]; // Calculate the sum of the four numbers. Use long to prevent potential integer overflow.
                    if (s < target) 
                        k++; // If the sum is too small, move the left pointer (k) to the right to increase the sum.
                    else if (s > target)
                        l--; // If the sum is too large, move the right pointer (l) to the left to decrease the sum.
                    else 
                    { 
                        val.add(Arrays.asList(nums[i], nums[j], nums[k] , nums[l])); // Found a quadruplet! Add it to the result list.
                        while (k < l && nums[k] == nums[k + 1]) k++; // Skip duplicate values for the third number.
                        while (k < l && nums[l] == nums[l - 1]) l--; // Skip duplicate values for the fourth number.
                        k++; // Move the left pointer to the next distinct element.
                        l--; // Move the right pointer to the next distinct element.
                    }
                }
            }
        }
        return val; // Return the list of unique quadruplets.
    }
}
```

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n^3)
    *   Sorting: O(n log n)
    *   Outer two loops: O(n^2)
    *   Inner two-pointer loop: O(n)
    *   The dominant factor is O(n^2 * n) = O(n^3). The sorting complexity is less significant.
*   **Space Complexity:** O(1) or O(n) (depending on the sorting algorithm's implementation)

    *   The space used by `val` is O(k), where k is the number of quadruplets found.  In the worst case, this could be O(n^3), but the problem asks for *unique* quadruplets. The space used by `val` depends on the number of the quadruplets, and is not related to input size in general. So `val` does not contribute to the overall space complexity analysis.
    *   The space used by the two pointers (k and l) and other variables is constant, O(1).
    *   The sorting algorithm may use O(1) (e.g., in-place quicksort) or O(n) auxiliary space (e.g., mergesort). Thus, depending on the sorting algorithm, the space complexity can be considered O(1) or O(n).
```