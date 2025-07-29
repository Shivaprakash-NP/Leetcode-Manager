## LeetCode: Partition Array Such That Maximum Difference Is K - Explained

**1. Problem Understanding:**

The problem asks us to find the minimum number of partitions needed to divide an array of integers such that the maximum difference between any two elements within the same partition is at most `k`.  In simpler terms, we want to group the numbers so that the largest number minus the smallest number in each group is never greater than `k`.

**2. Approach / Intuition:**

The solution employs a greedy approach.  The core idea is to sort the array first. After sorting, we can iteratively build partitions. We start with the first element as the beginning of a partition. We then extend this partition as far right as possible, adding elements as long as the difference between the current element and the first element of the partition remains less than or equal to `k`.  Once this condition is violated, we've found the limit of our current partition, and we increment the partition count and start a new partition from the element that violated the condition. This approach works because sorting guarantees that we explore the smallest possible differences first, leading to the minimum number of partitions.

**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is an array (`nums`).
* **Algorithms:** The algorithm uses sorting (`Arrays.sort()`), and a greedy iterative approach to partition the array.

**4. Code Walkthrough:**

```java
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array in ascending order. This is crucial for the greedy approach.
        int l = 0; // Pointer to the leftmost element of the current partition.
        int r = 1; // Pointer to the rightmost element of the current partition.
        int c = 1; // Counter for the number of partitions. Initialized to 1 because we start with at least one partition.

        while(r < nums.length) { // Iterate through the array.
            if(nums[r]-nums[l] > k) { // Check if the difference between the rightmost and leftmost elements exceeds k.
                c++; // Increment the partition count if the difference exceeds k.
                l = r; // Move the left pointer to the current right pointer to start a new partition.
                r = l+1; // Move the right pointer one step forward.
            } else r++; // Otherwise, extend the current partition by moving the right pointer.
        }
        return c; // Return the total number of partitions.
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), dominated by the sorting step (`Arrays.sort()`). The while loop iterates through the array once, which is O(N), but the sorting is more computationally expensive.
* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space to store the pointers `l`, `r`, and `c`.  The in-place sorting used by `Arrays.sort()` in Java does not significantly increase the space complexity in most cases (although some implementations may use extra space in the worst case).


**Example:**

Let's say `nums = [3, 6, 1, 2, 8, 5]` and `k = 3`.

1. `Arrays.sort(nums)` results in `nums = [1, 2, 3, 5, 6, 8]`.
2. The loop starts:
   - `l = 0`, `r = 1`. `nums[r] - nums[l] = 1 <= k`. `r` increments to 2.
   - `nums[r] - nums[l] = 2 <= k`. `r` increments to 3.
   - `nums[r] - nums[l] = 4 > k`. `c` increments to 2. `l` becomes 3, `r` becomes 4.
   - `nums[r] - nums[l] = 1 <= k`. `r` increments to 5.
   - `nums[r] - nums[l] = 3 <= k`. `r` increments to 6.
   - The loop ends. `c = 2` is returned.

Therefore, the minimum number of partitions is 2.  The partitions would be `[1, 2, 3, 5]` and `[6, 8]`.  The maximum difference within each partition is less than or equal to `k = 3`.
