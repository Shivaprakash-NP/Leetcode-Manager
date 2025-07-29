## LeetCode: Count Hills and Valleys in an Array - Solution Explanation

**1. Problem Understanding:**

The problem asks us to count the number of "hills" and "valleys" in an array of integers. A "hill" is defined as a peak where the element is strictly greater than its immediate neighbors, while a "valley" is a trough where the element is strictly smaller than its immediate neighbors.  Crucially, consecutive identical numbers are treated as a single point.


**2. Approach / Intuition:**

The solution employs a two-pointer approach to efficiently identify hills and valleys. Instead of directly comparing each element with its neighbors, it first handles consecutive identical numbers by skipping them.  This prevents counting false positives.  Then, it compares the current element with its left and right neighbors (after skipping identical numbers) to determine if it forms a hill or a valley. This approach is efficient because it iterates through the array only once (mostly) while intelligently handling duplicate elements.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is an array (`nums`) to store the input integers. No additional significant data structures are employed.
* **Algorithms:** The core algorithm is a linear scan of the array with a two-pointer technique to efficiently find hills and valleys.  The logic involves conditional statements to check the inequality between elements.


**4. Code Walkthrough:**

```java
class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int ans = 0; // Initialize the count of hills and valleys
        int l = 1; // Left pointer (initialized to 1 because we start comparing from the second element)
        int r = 1; // Right pointer (initialized to 1)
        for(int i = 1; i<n; ) { // Iterate through the array, starting from the second element
            l = i-1; // Set the left pointer to the previous element
            r = i+1; // Set the right pointer to the next element

            while(l>=0 && nums[i] == nums[l]) l--; // Skip consecutive identical numbers on the left
            while(r<n && nums[i] == nums[r]) r++; // Skip consecutive identical numbers on the right

            if(l>=0 && r<n) { // Check if valid left and right neighbors exist after skipping identical elements
                int v1 = nums[l]; // Value of the left neighbor
                int v2 = nums[i]; // Value of the current element
                int v3 = nums[r]; // Value of the right neighbor
                if((v1<v2 && v3<v2) || (v1>v2 && v3>v2)) ans++; // Check for hill or valley condition and increment count
            }

            i = r; // Move the main pointer to the next non-identical element
        }
        return ans; // Return the total count of hills and valleys
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the input array.  While there are nested `while` loops, in the worst case (no consecutive identical numbers), these loops execute a total of O(N) times across the entire `for` loop.  The `for` loop itself iterates at most N times.

* **Space Complexity:** O(1). The algorithm uses only a constant amount of extra space to store variables like `ans`, `l`, `r`, `v1`, `v2`, and `v3`.  The space used does not depend on the size of the input array.


In summary, this solution efficiently solves the "Count Hills and Valleys in an Array" problem using a linear-time, constant-space approach by cleverly handling consecutive identical elements and using a two-pointer strategy to minimize comparisons.
