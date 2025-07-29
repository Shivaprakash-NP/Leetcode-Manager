## LeetCode: Remove Duplicates from Sorted Array - Solution Explained

**1. Problem Understanding:**

The problem asks us to remove duplicate elements from a *sorted* array of integers in-place (meaning without creating a new array).  The function should return the length of the array after removing duplicates.  The order of the remaining unique elements must be preserved.

**2. Approach / Intuition:**

This solution utilizes a two-pointer approach.  We use `i` as a pointer to track the index of the last unique element encountered so far, and `j` as a pointer to iterate through the array.  Because the array is sorted, duplicate elements will be adjacent to each other.

The core logic is simple: if `nums[i]` is different from `nums[j]`, it means we've encountered a new unique element.  We increment `i`, and copy the value of `nums[j]` to `nums[i]`, effectively placing the unique element at the correct position in the modified array.  This way, we maintain a compacted array of unique elements up to index `i`.

This approach is chosen because it's efficient for sorted arrays.  It avoids the overhead of creating a new array or using more complex data structures.  The in-place modification directly alters the input array, saving space.

**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure is an array (`int[] nums`).
* **Algorithm:** The algorithm used is a two-pointer technique.  It's a variation of the in-place sorting algorithms concept.

**4. Code Walkthrough:**

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0; // Pointer to the last unique element
        for(int j=1;j<nums.length;j++) // Iterate from the second element
        {
            if(nums[i]!=nums[j]) // Check if the current element is different from the last unique element
            {
                i++; // Move the unique element pointer
                nums[i]=nums[j]; // Copy the new unique element
            }
        }
        return i+1; // Return the length of the array with unique elements (i+1 because i is 0-indexed)
    }
}
```

* **`int i = 0;`**: Initializes a pointer `i` to 0, representing the index of the last unique element. Initially, we assume the first element is unique.
* **`for(int j=1;j<nums.length;j++)`**: This loop iterates through the array, starting from the second element (`j=1`).
* **`if(nums[i]!=nums[j])`**: This condition checks if the current element (`nums[j]`) is different from the last unique element (`nums[i]`).
* **`i++;`**: If the elements are different, it means we've found a new unique element. We increment `i` to point to the next available position for a unique element.
* **`nums[i]=nums[j];`**:  We copy the value of `nums[j]` (the new unique element) to `nums[i]`, effectively placing it in the compacted array.
* **`return i+1;`**: Finally, the function returns `i+1`, which is the number of unique elements in the array.  We add 1 because `i` is a 0-based index.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input array. The outer loop iterates through the array once.  The operations inside the loop are constant time.

* **Space Complexity:** O(1). The algorithm modifies the input array in-place, using only a constant amount of extra space for the pointers `i` and `j`.  There is no use of additional data structures that scale with the input size.


This solution efficiently removes duplicates from a sorted array while satisfying the in-place requirement and maintaining optimal time and space complexity.
