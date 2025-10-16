## LeetCode Problem: Smallest Missing Non-negative Integer After Operations

### 1. Problem Understanding:

The problem asks us to find the smallest non-negative integer that is *not* present in the array `nums` after we apply the following operation any number of times to each element:  `nums[i] = nums[i] + k * value`, where `k` can be any integer (positive, negative, or zero). In essence, we can add multiples of `value` to any element. The goal is to find the smallest missing non-negative integer we can obtain in `nums` after these operations.

### 2. Approach / Intuition:

The key insight is that the operation `nums[i] = nums[i] + k * value` doesn't change the remainder of `nums[i]` when divided by `value`. In other words, `nums[i] % value` remains constant throughout all valid operations.

Therefore, the potential values that `nums[i]` can take after applying the operation are all integers congruent to `nums[i]` modulo `value`. We can group the numbers in the input array `nums` based on their remainders modulo `value`.

The solution leverages this by maintaining a count of how many numbers have a particular remainder modulo `value`. The array `map` stores these counts. We iterate from `0` upwards and check if any remainder has a count of `0`. If `map[i % value] == 0`, it means the number `i` can *never* be obtained through the specified operations from the original `nums` array. Thus, `i` is the smallest missing non-negative integer. We decrement the counter as we potentially 'use' each value.

For example, if value is 3:

nums = [1, 2, 4, 7]

remainders:
1 % 3 = 1
2 % 3 = 2
4 % 3 = 1
7 % 3 = 1

map = [0, 3, 1] (0: count of remainder 0, 1: count of remainder 1, 2: count of remainder 2)

We then start checking from 0.

- i = 0, map[0 % 3] = map[0] = 0.  So, 0 is the answer.

If `nums = [1, 2, 4, 7, 0, 3]`

remainders:
1 % 3 = 1
2 % 3 = 2
4 % 3 = 1
7 % 3 = 1
0 % 3 = 0
3 % 3 = 0

map = [2, 3, 1]

- i = 0, map[0 % 3] = map[0] = 2.  Decrement map[0] to 1. i++
- i = 1, map[1 % 3] = map[1] = 3. Decrement map[1] to 2. i++
- i = 2, map[2 % 3] = map[2] = 1. Decrement map[2] to 0. i++
- i = 3, map[3 % 3] = map[0] = 1. Decrement map[0] to 0. i++
- i = 4, map[4 % 3] = map[1] = 2. Decrement map[1] to 1. i++
- i = 5, map[5 % 3] = map[2] = 0. Return 5.

### 3. Data Structures and Algorithms:

*   **Array (map):** Used to store the frequency of each remainder modulo `value`.
*   **Modulo Operator (%):** Core to determining which group each number belongs to.
*   **Iteration:** Used to find the smallest missing non-negative integer.

### 4. Code Walkthrough:

```java
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] map = new int[value]; // Initialize an array of size 'value' to store the counts of remainders

        for(int v : nums) {
            if(v<0) map[(value-(Math.abs(v)%value))%value]++; // Handle negative numbers
            else map[v%value]++; // Increment the count for the remainder when 'v' is divided by 'value'
        }

        int st = 0; // Start searching for the smallest missing integer from 0
        while(true) {
            if(map[st%value] == 0) return st; // If the count for the remainder 'st % value' is 0, we found the missing integer
            map[st%value]--; // Otherwise, decrement the count for that remainder (meaning we can use it)
            st++; // Increment 'st' to check the next integer
        }

        // return -1; // Unreachable code as the while loop is infinite until a return statement is hit
    }
}
```

*   **`int[] map = new int[value];`**:  An integer array `map` of size `value` is created. `map[i]` will store the number of elements in `nums` that have a remainder of `i` when divided by `value`.

*   **`for(int v : nums) { ... }`**: This loop iterates through each element `v` in the `nums` array.

    *   **`if(v<0) map[(value-(Math.abs(v)%value))%value]++;`**: If v is negative, calculates the correct positive remainder `(value - (Math.abs(v) % value)) % value` and increments the corresponding counter in `map`. This ensures that remainders are always positive.
    *   **`else map[v%value]++;`**: If `v` is non-negative, it calculates the remainder `v % value` and increments the corresponding counter in the `map` array.

*   **`int st = 0;`**:  Initializes a variable `st` to 0. This variable represents the potential smallest missing non-negative integer.

*   **`while(true) { ... }`**: This loop continues indefinitely until a missing integer is found.

    *   **`if(map[st%value] == 0) return st;`**:  Checks if the count of elements having the remainder `st % value` is 0. If it is, it means the number `st` cannot be obtained from the elements in `nums` by adding multiples of `value`. Hence, `st` is the smallest missing non-negative integer, and the function returns `st`.
    *   **`map[st%value]--;`**: If `map[st%value]` is not 0, it means we can reach 'st' with the provided operations. So we decrement the count in the map.
    *   **`st++;`**:  Increments `st` to check the next integer.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N + value), where N is the length of the input array `nums`. The first loop iterates through the `nums` array once, taking O(N) time. The `while` loop, in the worst case, might iterate until `st` becomes approximately `value`, but because we are reducing the counter in each iteration, the `while` loop executes at most O(value) times. Thus, the overall time complexity is O(N + value).

*   **Space Complexity:** O(value).  The `map` array has a fixed size equal to `value`, regardless of the input array's size.
