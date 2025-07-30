```markdown
## Range Sum Query - Immutable: Solution Explanation

### 1. Problem Understanding:

The "Range Sum Query - Immutable" problem asks us to implement a class `NumArray` that can efficiently compute the sum of elements within a given range (inclusive) of an array.  The key constraint is that the original array `nums` is immutable (cannot be changed).  We need to optimize the `sumRange(left, right)` query so that it can be answered quickly, as it will be called multiple times.

### 2. Approach / Intuition:

The most straightforward approach (iterating through the range for each query) would result in a time complexity of O(n) for each `sumRange` call, where n is the size of the range.  This is inefficient if we have many queries.

The chosen approach uses the concept of *prefix sums*. A prefix sum array stores the cumulative sum of elements up to each index.  By precomputing the prefix sums in the constructor, we can calculate the sum of any range in O(1) time.

The intuition behind this is:
- `prefixSum[i]` stores the sum of `nums[0]` to `nums[i]`.
- To find the sum of `nums[left]` to `nums[right]`, we can compute `prefixSum[right] - prefixSum[left-1]`. This is because `prefixSum[right]` contains the sum from `nums[0]` to `nums[right]`, and `prefixSum[left-1]` contains the sum from `nums[0]` to `nums[left-1]`. Subtracting them gives us the desired range sum.
- A special case exists when `left` is 0, in which case we simply return `prefixSum[right]` since there's nothing to subtract.

This approach prioritizes faster query times at the cost of additional preprocessing during object construction. Because the array is immutable, this trade-off is usually advantageous.

### 3. Data Structures and Algorithms:

- **Data Structure:** The solution primarily uses an integer array (`int[] num`) to store the prefix sums.
- **Algorithm:** The algorithm used is prefix sum computation and retrieval.

### 4. Code Walkthrough:

```java
class NumArray {
    private int[] num; // Stores the prefix sums

    public NumArray(int[] nums) {
        // In-place prefix sum calculation
        for(int i = 1 ; i < nums.length ; i++)
        {
            nums[i] = nums[i-1]+nums[i]; // Update nums[i] with the sum of nums[0...i]
        }
        this.num = nums; // Assign the modified nums (containing prefix sums) to the member variable 'num'
    }
    
    public int sumRange(int left, int right) {
        if(left == 0) return num[right]; // If left is 0, the sum is simply the prefix sum up to right
        return num[right]-num[left-1]; // Otherwise, subtract the prefix sum up to left-1 from the prefix sum up to right
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
```

- **`NumArray(int[] nums)` (Constructor):**
    - It iterates through the input `nums` array starting from the second element (index 1).
    - In each iteration, it updates `nums[i]` to store the sum of `nums[0]` to `nums[i]`. This effectively converts the original array into a prefix sum array *in-place*.
    - Finally, it assigns the modified `nums` array to the `num` instance variable.
- **`sumRange(int left, int right)`:**
    - It checks if `left` is 0.  If it is, the sum of the range `[0, right]` is simply the prefix sum stored at `num[right]`.
    - If `left` is not 0, it calculates the sum of the range `[left, right]` as `num[right] - num[left-1]`. This subtracts the prefix sum up to `left-1` from the prefix sum up to `right`, leaving the sum of the desired range.

### 5. Time and Space Complexity:

- **Time Complexity:**
    - **Constructor `NumArray(int[] nums)`:** O(n), where n is the length of the input array `nums`.  This is because we iterate through the array once to compute the prefix sums.
    - **`sumRange(int left, int right)`:** O(1).  We perform a single subtraction (or direct access) to get the range sum.
- **Space Complexity:**
    - O(1) - We are modifying the input array `nums` in-place. We store it as part of the object, but that's storing the input, not using *additional* space proportional to the input size. If modifying the input array is not allowed, a copy must be created, which would incur O(n) space.
