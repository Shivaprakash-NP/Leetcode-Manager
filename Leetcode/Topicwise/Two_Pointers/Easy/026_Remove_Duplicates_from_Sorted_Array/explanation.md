## LeetCode: Remove Duplicates from Sorted Array - Solution Explained

**1. Problem Understanding:**

The problem asks us to remove duplicate elements from a *sorted* array of integers in-place.  This means we should modify the original array directly, without creating a new one. The function should return the length of the array after removing duplicates.  The order of the remaining unique elements must be preserved.


**2. Approach / Intuition:**

The solution employs a two-pointer approach.  We use `i` as a slow-moving pointer that tracks the index of the last unique element encountered so far, and `j` as a fast-moving pointer that iterates through the entire array.

The core logic is simple: if `nums[i]` is different from `nums[j]`, it means we've found a new unique element.  We increment `i` to point to the next available position and copy the value of `nums[j]` (the new unique element) to `nums[i]`.  This effectively overwrites the duplicate elements with unique ones.

This approach is chosen because it's highly efficient for in-place manipulation of a sorted array.  It avoids the overhead of creating a new array and performs the operation in a single pass.  Other approaches like using sets would require extra space.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is an integer array (`int[] nums`).
* **Algorithm:** The algorithm used is a two-pointer approach, a variation of the "in-place" algorithm family.


**4. Code Walkthrough:**

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0; // Slow pointer, points to the last unique element
        for(int j=1;j<nums.length;j++) // Fast pointer, iterates through the array
        {
            if(nums[i]!=nums[j]) // Check for uniqueness
            {
                i++; // Move slow pointer to the next position
                nums[i]=nums[j]; // Copy the unique element
            }
        }
        return i+1; // Return the length of the array after removing duplicates (i+1 because i starts at 0)
    }
}
```

- **`int i = 0;`**: Initializes the slow pointer `i` to 0. This points to the beginning of the array, where the first unique element will be placed.

- **`for(int j=1;j<nums.length;j++)`**: The fast pointer `j` iterates through the array, starting from the second element (`j=1`).

- **`if(nums[i]!=nums[j])`**: This condition checks if the element at `j` is different from the element at `i`.  If they are different, it signifies a new unique element.

- **`i++;`**:  If a new unique element is found, the slow pointer `i` is incremented to point to the next available slot.

- **`nums[i]=nums[j];`**: The value of the unique element at `j` is copied to the location pointed to by `i`, effectively overwriting any duplicate.

- **`return i+1;`**: Finally, the function returns `i+1`.  Since `i` points to the last unique element, `i+1` represents the total number of unique elements in the modified array.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input array. The `for` loop iterates through the array once. All operations inside the loop are constant time.

* **Space Complexity:** O(1). The algorithm operates in-place; it uses only a constant amount of extra space for the variables `i` and `j`.  No additional data structures are used that scale with the input size.
