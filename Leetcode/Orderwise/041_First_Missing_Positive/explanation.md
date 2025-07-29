## LeetCode: First Missing Positive - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find the smallest positive integer (greater than 0) that is missing from an unsorted array of integers.  For example, if the input array is `[1, 2, 0]`, the missing positive integer is `3`. If the input is `[3, 4, -1, 1]`, the missing positive integer is `2`.


**2. Approach / Intuition:**

The solution employs an in-place cyclic sort algorithm. The core idea is to utilize the array's indices to represent the positive integers.  We can achieve this because we are only interested in positive integers up to the length of the array.

The algorithm works as follows:

1. **Cyclic Sort:**  Iterate through the array.  For each element, if it's within the range [1, n] (where n is the length of the array) and is not in its correct position (meaning `nums[i] != i + 1`), we swap it with the element at the index `nums[i] - 1`. This process continues until all elements within the range [1, n] are in their correct positions.  Essentially, we're trying to place each number `x` at the index `x-1`.

2. **Find the Missing:** After the cyclic sort, we iterate through the array again. The first index `i` where `nums[i] != i + 1` indicates that the integer `i + 1` is missing. If all elements are in their correct positions, then the missing positive integer is `n + 1`.

This approach is chosen because it's efficient and uses constant extra space (in-place sorting).  Other approaches, like using a HashSet to store seen numbers, would require extra space proportional to the input size.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is the input array itself.  No additional data structures are needed.
* **Algorithm:** The core algorithm is a variation of cyclic sort.  The second part uses a simple linear scan.


**4. Code Walkthrough:**

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // Cyclic Sort
        for(int i = 0 ; i < nums.length ; i++) {
            while(nums[i]>=1 && nums[i]<=n && nums[i] != nums[nums[i]-1]) {
                int crtind = nums[i]-1; // Calculate the correct index for nums[i]
                int tem = nums[crtind]; // Store the element at the correct index temporarily
                nums[crtind] = nums[i]; // Place nums[i] at its correct index
                nums[i] = tem; // Place the temporarily stored element at the current index
            }
        }

        // Find the missing positive integer
        for(int i = 0 ; i<n ; i++) {
            if(nums[i] != i+1) return i+1; // If an element is not at its correct index, its value+1 is missing
        }
        
        return n+1; // If all elements are in their correct position, n+1 is missing
    }
}
```

* **`int n = nums.length;`**: Stores the length of the input array for convenience.
* **`for(int i = 0 ; i < nums.length ; i++)`**: The outer loop iterates through each element of the array.
* **`while(nums[i]>=1 && nums[i]<=n && nums[i] != nums[nums[i]-1])`**: This inner loop performs the cyclic sort. It continues as long as the current element is a positive integer within the valid range and not in its correct position.
* **`int crtind = nums[i]-1;`**: Calculates the correct index for `nums[i]`.
* **`int tem = nums[crtind]; ... nums[crtind] = nums[i]; ... nums[i] = tem;`**: These lines perform the swap.
* **`for(int i = 0 ; i<n ; i++) { if(nums[i] != i+1) return i+1; }`**: This loop searches for the first index where the element is not in its correct position.
* **`return n+1;`**: If all elements are in their correct positions, it means the smallest missing positive integer is `n + 1`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n).  The cyclic sort might perform multiple swaps in the worst case, but the total number of swaps is still proportional to n. The final loop to find the missing integer also takes O(n) time.

* **Space Complexity:** O(1). The algorithm operates in-place; it doesn't use any extra space proportional to the input size.  Only a few constant extra variables are used.
