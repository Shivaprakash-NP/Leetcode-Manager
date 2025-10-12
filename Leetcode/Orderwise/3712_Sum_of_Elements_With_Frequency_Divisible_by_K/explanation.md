## LeetCode Problem: Sum of Elements With Frequency Divisible by K

### 1. Problem Understanding:

The problem asks us to calculate the sum of elements in an array `nums` whose frequency (number of occurrences) is divisible by a given integer `k`. For example, if `nums = [1, 2, 2, 3, 3, 3]` and `k = 2`, the element `2` appears twice (divisible by 2), and the element `3` appears three times (not divisible by 2). Therefore, we would include `2 * 2 = 4` in the final sum. The element 1 appears once, also not divisible by 2, so it's not included. The final answer would be 4.

### 2. Approach / Intuition:

The most straightforward approach is to count the frequency of each element in the array. We can then iterate through the unique elements and check if their frequency is divisible by `k`. If it is, we add the product of the element and its frequency to the overall sum.

This approach is chosen for its simplicity and efficiency. It directly addresses the problem statement without requiring complex algorithms or data structures.  It avoids unnecessary computations by only considering elements whose frequencies are divisible by `k`.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `HashMap`: Used to store the frequency of each element in the array. The key is the element, and the value is the frequency.
*   **Algorithm:**  Frequency Counting, Iteration.

### 4. Code Walkthrough:

```java
class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int v : nums) map.put(v, map.getOrDefault(v, 0)+1);
        int sum = 0;
        for(int v : map.keySet()) if(map.get(v) % k == 0) sum += v*map.get(v);

        return sum;
    }
}
```

1.  **`Map<Integer, Integer> map = new HashMap<>();`**:
    *   This line creates a new `HashMap` called `map`. This map will store the frequency of each element in the input array `nums`. The keys will be the elements from `nums`, and the values will be their corresponding counts.

2.  **`for(int v : nums) map.put(v, map.getOrDefault(v, 0)+1);`**:
    *   This is a `for-each` loop that iterates through each element `v` in the input array `nums`.
    *   `map.put(v, map.getOrDefault(v, 0)+1);` This line updates the frequency count for the current element `v` in the `map`.
        *   `map.getOrDefault(v, 0)`: This retrieves the current frequency count for the element `v` from the `map`. If `v` is not already a key in the map (i.e., it's the first time we've encountered this element), it returns a default value of 0.
        *   `+ 1`:  This increments the frequency count by 1.
        *   `map.put(v, ...)`:  This puts the updated frequency count back into the `map`, associated with the element `v`.

3.  **`int sum = 0;`**:
    *   This line initializes an integer variable `sum` to 0. This variable will store the sum of the elements whose frequencies are divisible by `k`.

4.  **`for(int v : map.keySet()) if(map.get(v) % k == 0) sum += v*map.get(v);`**:
    *   This is a `for-each` loop that iterates through the keys (unique elements) in the `map`.
    *   `map.keySet()`:  This returns a `Set` of all the keys in the `map` (i.e., all the unique elements from `nums`).
    *   `if(map.get(v) % k == 0)`: This condition checks if the frequency of the current element `v` (obtained using `map.get(v)`) is divisible by `k`. The `%` operator calculates the remainder of the division. If the remainder is 0, it means the frequency is divisible by `k`.
    *   `sum += v*map.get(v);`: If the frequency of `v` is divisible by `k`, this line adds the product of the element `v` and its frequency `map.get(v)` to the `sum`.

5.  **`return sum;`**:
    *   This line returns the final calculated `sum`, which represents the sum of elements whose frequencies are divisible by `k`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array `nums`.
    *   The first loop iterates through the array `nums` once (O(n)).
    *   The second loop iterates through the unique elements in the `map`. In the worst case, all elements are unique, so this loop could also iterate up to n times. However, since the second loop iterates at most `n` times over *unique* elements and performs constant time operations inside the loop, it contributes O(n) to the overall time complexity.
*   **Space Complexity:** O(n), where n is the length of the input array `nums`.
    *   In the worst case, if all elements in `nums` are unique, the `map` will store n key-value pairs. Therefore, the space required for the `map` can be up to O(n).
