## LeetCode: Remove Duplicates from Sorted Array - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to remove duplicate elements from a *sorted* array in-place, meaning we modify the original array directly without creating a new one.  The function should return the length of the array after removing duplicates.  The order of the remaining unique elements should be preserved.


**2. Approach / Intuition:**

The solution uses a two-pointer approach.  This is efficient because we avoid creating a new array, operating directly on the input.  Since the array is sorted, duplicate elements will be adjacent to each other. We use one pointer (`i`) to track the index of the last unique element encountered and another pointer (`j`) to iterate through the array. If `nums[j]` is different from `nums[i]`, it means we've encountered a new unique element, so we increment `i` and copy `nums[j]` to `nums[i]`.  This effectively overwrites the duplicate elements.

This approach is chosen because it's highly efficient for sorted arrays.  Other approaches like using sets would require extra space, and brute-force comparisons would be less efficient.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is an array (`int[] nums`).
* **Algorithm:** The core algorithm is a two-pointer iteration. It leverages the sorted nature of the input to efficiently identify and remove duplicates in-place.


**4. Code Walkthrough:**

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0; // Pointer to the last unique element
        for(int j=1;j<nums.length;j++) // Iterate through the array starting from the second element
        {
            if(nums[i]!=nums[j]) // Check if the current element is different from the last unique element
            {
                i++; // Move the unique element pointer to the next position
                nums[i]=nums[j]; // Copy the new unique element to the next position
            }
        }
        return i+1; // Return the length of the array containing unique elements (i+1 because i is 0-indexed)
    }
}
```

* **`int i = 0;`**: Initializes a pointer `i` to 0, representing the index of the last unique element found so far.

* **`for(int j=1;j<nums.length;j++)`**: This loop iterates through the array, starting from the second element (index 1). The first element is implicitly considered unique.

* **`if(nums[i]!=nums[j])`**: This condition checks if the current element `nums[j]` is different from the last unique element `nums[i]`.

* **`i++; nums[i]=nums[j];`**: If the condition is true (a new unique element is found), the `i` pointer is incremented, and the current element `nums[j]` is copied to the position pointed to by `i`, effectively placing the unique element in its correct position.

* **`return i+1;`**: Finally, `i+1` is returned, which represents the number of unique elements in the array (the effective length of the modified array).  We add 1 because `i` is zero-indexed.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input array.  The loop iterates through the array once.  All operations inside the loop are constant time.

* **Space Complexity:** O(1). The algorithm operates in-place; it does not use any extra space that scales with the input size.  It only uses a constant number of variables (`i` and `j`).  This makes it a very space-efficient solution.
