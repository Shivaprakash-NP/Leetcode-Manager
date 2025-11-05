### Problem Understanding

The problem asks us to calculate the "X-Sum" for each subarray of length `k` within a given array `nums`. The "X-Sum" of a subarray is calculated as follows: For each distinct number in the subarray, multiply the number by the number of times it appears in the subarray.  Then, select the `x` largest products (number * frequency) and sum them up.  The result is the X-Sum. The task is to return an array containing the X-Sum for each subarray of length `k`.

### Approach / Intuition

The core idea is to efficiently maintain the counts of each number within the sliding window of size `k` and to keep track of the `x` largest (frequency * number) products.  A naive approach would involve recalculating the frequency of each number and then sorting the products for each subarray, leading to a high time complexity.

The provided solution uses a sliding window approach combined with `TreeSet` and `HashMap` to optimize this process. The `TreeSet` is used to maintain the `x` largest (frequency * number) products and the remaining products. The `HashMap` is used to store the frequency of each number in the current window.

The `TreeSet` is split into two sets: `large` and `small`. The `large` set contains the `x` largest (frequency * number) products, and the `small` set contains the remaining products. This allows us to quickly access and update the `x` largest products as the window slides.

The key advantage of this approach is that it avoids sorting the products for each subarray. Instead, it maintains the sorted order of the products in the `TreeSet` and updates the sets as the window slides. This significantly reduces the time complexity of the solution.

### Data Structures and Algorithms

*   **`int[] nums`**: The input array of integers.
*   **`int k`**: The length of the subarrays.
*   **`int x`**: The number of largest frequency-multiplied-by-number products to sum.
*   **`long[] num`**: A copy of `nums` cast to `long` to avoid integer overflow during calculations.
*   **`List<Long> ans`**: A list to store the X-Sum of each subarray.
*   **`TreeSet<Pair> large`**: A sorted set (TreeSet) to store the `x` largest (frequency, number) pairs. The sorting is based on frequency first and then the number itself.
*   **`TreeSet<Pair> small`**: A sorted set (TreeSet) to store the remaining (frequency, number) pairs that are not among the `x` largest.
*   **`HashMap<Long, Integer> map`**: A hash map to store the frequency of each number in the current window. The key is the number, and the value is the frequency.
*   **Sliding Window**: The core algorithm used to iterate through the subarrays of length `k`.
*   **Comparable Interface**: The `Pair` class implements the `Comparable` interface to allow for custom sorting based on frequency and number.

### Code Walkthrough

1.  **`Pair` Class**:
    *   This class represents a pair of (frequency, number).
    *   It implements the `Comparable` interface to allow sorting based on frequency first and then number.
    *   The `compareTo` method defines the sorting order.
    *   `equals` and `hashCode` methods are overridden to ensure correct behavior when using `Pair` objects in hash-based collections like `HashMap` and `HashSet`.
    *   `toString` method is overridden for easy debugging.

2.  **`findXSum` Method**:
    *   **Initialization**:
        *   `n = nums.length`: Gets the length of the input array.
        *   `long[] num = new long[n]`: Creates a `long` array to store the input numbers. This is done to prevent potential integer overflow when calculating the X-Sum.
        *   `tot = 0L`: Initializes the variable to store the current X-Sum.
        *   `List<Long> ans = new ArrayList<>()`: Initializes the list to store the X-Sum of each subarray.
        *   `TreeSet<Pair> large = new TreeSet<>()`: Initializes the `TreeSet` to store the `x` largest (frequency, number) pairs.
        *   `TreeSet<Pair> small = new TreeSet<>()`: Initializes the `TreeSet` to store the remaining (frequency, number) pairs.
        *   `Map<Long, Integer> map = new HashMap<>()`: Initializes the hash map to store the frequency of each number in the current window.
        *   `l = 0`: Initializes the left pointer of the sliding window.

    *   **Sliding Window Loop**:
        *   The `for` loop iterates through the input array, representing the right pointer of the sliding window.
        *   **Update Frequency**:
            *   `occ = map.getOrDefault(num[i], 0)`: Gets the current frequency of the number `num[i]` from the `map`. If the number is not in the map, the default value is 0.
            *   The code then updates the `large` and `small` sets based on the new frequency of `num[i]`. The logic ensures that the `large` set always contains the `x` largest (frequency * number) products. It handles cases where the new frequency causes a shift between the `large` and `small` sets.  The `tot` variable is updated accordingly to reflect the changes in the X-Sum.

        *   **Update Sliding Window**:
            *   `map.put(num[i], occ + 1)`: Updates the frequency of the number `num[i]` in the `map`.
        *   **Remove Element from Left**:
            *   `if (i - l == k)`: Checks if the current window size is equal to `k`. If it is, it means the window has reached its maximum size, and the element at the left pointer needs to be removed.
            *   The code then removes the element at the left pointer from the `map` and updates the `large` and `small` sets accordingly. The logic is similar to the adding part, ensuring that the `large` set always contains the `x` largest (frequency * number) products. The `tot` variable is updated accordingly.
            *   `l++`: Moves the left pointer of the sliding window to the right.

        *   **Calculate X-Sum**:
            *   `if (i - l == k - 1)`: Checks if the current window size is equal to `k`. If it is, it means a valid subarray of length `k` is formed, and the X-Sum can be calculated.
            *   `ans.add(tot)`: Adds the current X-Sum to the `ans` list.

    *   **Return Result**:
        *   `return ans.stream().mapToLong(Long::longValue).toArray()`: Converts the `ans` list to a `long[]` array and returns it.

### Time and Space Complexity

*   **Time Complexity**: O(n log x), where n is the length of the input array and x is the given parameter. The dominant operations are the `add`, `remove`, and `contains` operations on the `TreeSet`, which take O(log x) time each. The outer loop iterates n times.
*   **Space Complexity**: O(n) in the worst case. The `HashMap` can store up to n distinct numbers, and the `large` and `small` `TreeSet`s can also store up to n elements in the worst case (when x is close to n). The `num` array also takes O(n) space.
