## LeetCode: Count Hills and Valleys in an Array

**1. Problem Understanding:**

The problem asks us to count the number of "hills" and "valleys" in an array of integers. A hill is defined as a peak where the element is strictly greater than its immediate neighbors, and a valley is defined as a trough where the element is strictly smaller than its immediate neighbors.  We need to handle cases where consecutive elements might have the same value.

**2. Approach / Intuition:**

The solution employs a two-pointer approach to efficiently iterate through the array and identify hills and valleys.  Instead of simply comparing an element with its immediate neighbors, it first handles plateaus (sequences of identical numbers).  The `while` loops effectively skip over these plateaus, finding the true left and right neighbors for comparison. This prevents spurious counts of hills and valleys where they don't actually exist due to flat sections in the array.  This approach is efficient because it avoids redundant comparisons and directly targets the significant changes in the array's values.

**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is an array (`nums`) to store the input integers.
* **Algorithm:** The core algorithm is a linear scan of the array using two pointers (`l` and `r`) to identify the true neighbors for comparison, combined with a conditional check to determine if a point is a hill or valley.

**4. Code Walkthrough:**

```java
class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int ans = 0; // Initialize the count of hills and valleys
        int l = 1; //Left pointer initialized to 1 (we start comparing from index 1)
        int r = 1; //Right pointer initialized to 1
        for(int i = 1; i<n; ) { // Iterate through the array, starting from the second element
            l = i-1; //Set left pointer
            r = i+1; //Set right pointer

            while(l>=0 && nums[i] == nums[l]) l--; //Move left pointer until a different value is found, handling plateaus
            while(r<n && nums[i] == nums[r]) r++; //Move right pointer until a different value is found, handling plateaus

            if(l>=0 && r<n) { //Check if valid left and right neighbors exist after plateau handling
                int v1 = nums[l]; //Value of left neighbor
                int v2 = nums[i]; //Value of current element
                int v3 = nums[r]; //Value of right neighbor
                if((v1<v2 && v3<v2) || (v1>v2 && v3>v2)) ans++; //Check for hill or valley condition
            }

            i = r; //Update the main iterator to the new right pointer position
        }
        return ans; //Return the total count
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input array.  Although we have nested `while` loops,  in the worst case (no plateaus), each element is visited and compared at most a constant number of times. The outer loop iterates through the array, and the inner `while` loops only adjust the pointers to skip over identical elements, not adding significantly to the overall time complexity.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space to store variables (`n`, `ans`, `l`, `r`, `v1`, `v2`, `v3`).  The space used does not depend on the input array's size.
