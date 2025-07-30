```markdown
## Next Permutation - Detailed Explanation

### 1. Problem Understanding:

The "Next Permutation" problem asks us to find the lexicographically next greater permutation of a given array of integers. In simpler terms, we need to rearrange the numbers in the array to produce the next arrangement in increasing order.  If such an arrangement is not possible (i.e., the array is already in descending order), we should rearrange the array to its smallest possible order (ascending order). The modification must be done *in-place*.

### 2. Approach / Intuition:

The key idea is to find the first decreasing element from the right (let's call its index `i1`), then find the smallest element to the right of `i1` that is greater than `nums[i1]` (let's call its index `i2`). We swap `nums[i1]` and `nums[i2]`, and finally, reverse the subarray to the right of `i1` to minimize the remaining part of the permutation.

**Why this approach?**

*   **Finding the decreasing element from the right (`i1`)**: This indicates the point where we can find a larger permutation. Everything to the right of `i1` is in descending order, so we need to change something to the left of `i1` to get a larger permutation.

*   **Finding the smallest greater element to the right of `i1` (`i2`)**: By swapping `nums[i1]` with the smallest greater element `nums[i2]` on its right, we ensure that we're making the smallest possible increase to the left of `i1`.

*   **Reversing the subarray to the right of `i1`**: After the swap, the subarray to the right of `i1` is still in descending order. Reversing it puts it in ascending order, making it the smallest possible permutation for that subarray, thereby ensuring that we find the *next* greater permutation.

If no such `i1` is found, it means the entire array is in descending order, so we simply reverse the entire array to get the smallest possible permutation (ascending order).

### 3. Data Structures and Algorithms:

*   **Data Structures:** The solution primarily uses the input `int[] nums` array in-place.
*   **Algorithms:**
    *   **Linear Search:** Used to find the indices `i1` and `i2`.
    *   **Reversal:** Used to reverse the subarray to the right of `i1` and the entire array (in case the input is descending).
    *   **Swapping:**  Used to swap elements at indices `i1` and `i2`.

### 4. Code Walkthrough:

```java
class Solution {
    void swap(int[] nums , int i , int j)
    {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }
    void reverse(int[] nums , int s)
    {
        int i = s;
        int j = nums.length-1;
        while(i<j)
        {
            swap(nums , i , j);
            i++;
            j--;
        }
    }
    public void nextPermutation(int[] nums) {
        int i1 = -1;
        int i2 = -1;
        for(int i = nums.length-2 ; i>=0 ; i--)
        {
            if(nums[i]<nums[i+1])
            {
                i1 = i;
                break;
            }
        }
        if(i1==-1)
            reverse(nums , 0);
        else
        {
            for(int i = nums.length-1 ; i>=0 ; i--)
            {
                if(nums[i]>nums[i1])
                {
                    i2 = i;
                    break;
                }
            }
            swap(nums , i1 , i2);
            reverse(nums , i1+1);
        }
    }
}
```

*   **`swap(int[] nums, int i, int j)`:** This helper function swaps the elements at indices `i` and `j` in the `nums` array. It's a standard swap function using a temporary variable.

*   **`reverse(int[] nums, int s)`:** This helper function reverses the subarray of `nums` starting from index `s` to the end of the array.  It uses a two-pointer approach ( `i` starting from `s` and `j` starting from the end) and swaps elements until `i` and `j` cross each other.

*   **`nextPermutation(int[] nums)`:** This is the main function that implements the next permutation algorithm.

    *   `int i1 = -1; int i2 = -1;`: Initializes `i1` and `i2` to -1.  These variables will store the indices mentioned in the approach explanation.  Initializing to -1 is important to identify the case where `i1` and `i2` are not found.

    *   `for(int i = nums.length-2 ; i>=0 ; i--)`: This loop iterates from the second-to-last element to the first element of the array to find `i1`.

        *   `if(nums[i]<nums[i+1])`: This condition checks if `nums[i]` is smaller than the element to its right. If it is, it means we have found the first decreasing element from the right, so we assign `i` to `i1` and break out of the loop.

    *   `if(i1==-1)`: This condition checks if `i1` is still -1. If it is, it means the entire array is in descending order. In this case, we call `reverse(nums, 0)` to reverse the entire array, putting it in ascending order (the smallest permutation).

    *   `else`: If `i1` is not -1, it means we have found a decreasing element.

        *   `for(int i = nums.length-1 ; i>=0 ; i--)`: This loop iterates from the last element to the first element to find `i2`.

            *   `if(nums[i]>nums[i1])`: This condition checks if `nums[i]` is greater than `nums[i1]`. If it is, it means we have found the smallest element to the right of `i1` that is greater than `nums[i1]`, so we assign `i` to `i2` and break out of the loop.

        *   `swap(nums , i1 , i2)`: Swaps the elements at indices `i1` and `i2`.

        *   `reverse(nums , i1+1)`: Reverses the subarray starting from index `i1+1` to the end of the array.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the array `nums`. The code iterates through the array at most three times (once to find `i1`, once to find `i2`, and once to reverse a subarray).

*   **Space Complexity:** O(1), as the algorithm operates in-place and uses a constant amount of extra space (for variables like `i1`, `i2`, and temporary variables in `swap` and `reverse`).
