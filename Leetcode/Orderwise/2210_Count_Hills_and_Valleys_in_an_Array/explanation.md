## LeetCode: Count Hills and Valleys in an Array

**1. Problem Understanding:**

The problem asks us to count the number of "hills" and "valleys" in an array of integers. A hill is defined as a peak where the element is greater than its immediate neighbors, and a valley is defined as a trough where the element is smaller than its immediate neighbors.  Crucially, consecutive identical numbers are treated as a single point, effectively eliminating plateaus.


**2. Approach / Intuition:**

The solution uses a two-pointer approach to efficiently identify hills and valleys.  Instead of comparing each element to its neighbors directly, it first handles consecutive duplicate elements. It then identifies the indices `l` (left) and `r` (right) of the immediate non-identical neighbors around the current element. This allows it to efficiently assess if the current element represents a hill or a valley. This approach is chosen for its efficiency in reducing redundant comparisons, particularly when dealing with arrays containing many consecutive identical numbers.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is the input array `nums` itself. No additional significant data structures are needed.
* **Algorithm:** The algorithm is primarily based on a linear scan with a two-pointer technique to handle consecutive duplicates and check for hill/valley conditions efficiently.


**4. Code Walkthrough:**

* `int n = nums.length; int ans = 0;`: Initializes the array length and the counter for hills and valleys.
* `int l = 1; int r = 1;`: Initializes left and right pointers. These are used to find the nearest non-equal neighbors.
* `for(int i = 1; i<n; )`: Iterates through the array.  The loop condition `i < n` is important and directly relates to how `i` is updated. Note that the loop increment is not `i++` as it’s updated within the loop.
* `l = i-1; r = i+1;`: Sets initial pointers for left and right neighbours.
* `while(l>=0 && nums[i] == nums[l]) l--;`:  Moves the left pointer `l` to the left until it finds an element different from `nums[i]` or reaches the beginning of the array.  This efficiently handles consecutive duplicates.
* `while(r<n && nums[i] == nums[r]) r++;`:  Similarly, moves the right pointer `r` to the right until it finds an element different from `nums[i]` or reaches the end of the array.
* `if(l>=0 && r<n)`: Checks if valid left and right neighbors were found (to avoid index out of bounds).  This condition ensures both `l` and `r` are within the array's bounds after skipping the duplicates.
* `int v1 = nums[l]; int v2 = nums[i]; int v3 = nums[r];`: Assigns values for efficient comparison.
* `if((v1<v2 && v3<v2) || (v1>v2 && v3>v2)) ans++;`: Checks the hill/valley condition and increments the counter if the condition is met.
* `i = r;`: Updates the main loop index to `r`, effectively skipping over the consecutive identical numbers already processed.

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input array. Although there are nested `while` loops, each element in the array is visited at most a constant number of times. The `while` loops primarily serve to skip over consecutive duplicates, which doesn't add to the overall complexity beyond O(n).

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space regardless of the input array size.  It only uses a few integer variables to store indices and values.


This solution efficiently solves the "Count Hills and Valleys in an Array" problem by cleverly handling consecutive duplicates using a two-pointer approach.  The time complexity of O(n) makes it suitable for large input arrays, and the constant space complexity further enhances its efficiency.
