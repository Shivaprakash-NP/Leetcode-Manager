### Problem Understanding

The goal of this problem is to simulate a process of repeated multiplication based on the presence of values within a given array. We are provided with an array of integers, `nums`, and a starting integer, `original`.

The process is as follows:
1. Check if the current value of `original` exists in the `nums` array.
2. If it exists, double the value of `original` (i.e., `original = original * 2`).
3. Repeat step 1 with the new, doubled value.
4. Stop the process when the current value of `original` is *not* found in the `nums` array.
The final value of `original` after the process terminates is the result we must return.

### Approach / Intuition

The core operation required by the problem is **repeated lookups** (checking if a number exists in the input collection).

If we used the input array `nums` directly, each lookup would require iterating through the entire array, resulting in $O(N)$ time complexity per check. Since we might perform several checks (the number of checks is logarithmic with respect to the maximum integer size), the total time complexity could become inefficient.

**Intuition for Optimization:** We need a data structure that allows for near-instantaneous (average $O(1)$) lookups.

**Strategy:**
1.  **Preprocessing:** Convert the input array `nums` into a `HashSet`. This takes $O(N)$ time but ensures subsequent lookups are highly efficient.
2.  **Iterative Doubling:** Use a `while` loop. The loop condition is simply checking if the current value of `original` is present in the newly created `HashSet`.
3.  **Multiplication:** Inside the loop, if the value is found, we update `original` by multiplying it by two.
4.  The loop terminates automatically when the doubled value is no longer present in the set.

This approach minimizes the time spent on the critical lookup phase, making the overall solution very efficient.

### Data Structures and Algorithms

1.  **Data Structure:**
    *   `HashSet<Integer>`: Used to store the elements of `nums`. This is the key optimization, allowing for $O(1)$ average time complexity for the `contains()` operation.

2.  **Algorithm:**
    *   **Preprocessing/Initialization:** A single iteration over `nums` to populate the `HashSet`.
    *   **Iterative Search and Multiplication:** A simple `while` loop structure that continues executing a constant-time check (`set.contains()`) and a constant-time operation (`original *= 2`) until the condition is false.

### Code Walkthrough

```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        // 1. Initialize the HashSet
        Set<Integer> set = new HashSet<>();

        // 2. Populate the set (Preprocessing step)
        // This converts the O(N) lookup array into an O(1) lookup set.
        for(int v : nums) set.add(v);

        // 3. Iterative Doubling and Checking
        // The loop continues as long as the current 'original' value is found in the set.
        while(set.contains(original)) {
            // If found, double the value.
            original *= 2;
        }

        // 4. Return the final value that was not found in the set.
        return original;
    }
}
```

1.  **`Set<Integer> set = new HashSet<>();`**: A hash set is initialized to store the unique values from the input array `nums`.
2.  **`for(int v : nums) set.add(v);`**: This loop iterates through every element `v` in the input array `nums` and adds it to the `set`. This step ensures that all subsequent lookups are fast.
3.  **`while(set.contains(original))`**: This is the core logic loop. It repeatedly checks if the current value of `original` is present in the `set`.
4.  **`original *= 2;`**: If the `set.contains(original)` check returns true, the value of `original` is doubled. The loop then repeats, checking the new, larger value.
5.  **`return original;`**: Once the loop terminates (meaning the current value of `original` is not present in the set), the final value is returned.

### Time and Space Complexity

#### Time Complexity: $O(N + \log M)$

Where $N$ is the number of elements in `nums`, and $M$ is the maximum possible integer value (or the largest possible value `original` can reach before exceeding integer limits).

1.  **Set Population:** Iterating through `nums` and inserting $N$ elements into the `HashSet` takes $O(N)$ time.
2.  **While Loop:** The value of `original` doubles in every iteration. Since it grows exponentially, the number of iterations required to reach the maximum integer limit $M$ is very small, specifically $O(\log_2 M)$.
3.  **Total Time:** The total time is dominated by the initial setup and the loop: $O(N) + O(\log M)$. Since $N$ can be up to $1000$ and $\log_2(2^{31})$ is only about 31, the $O(N)$ term usually dominates, but the precise complexity is $O(N + \log M)$.

#### Space Complexity: $O(N)$

1.  We use a `HashSet` to store all elements of the input array `nums`. In the worst case, all $N$ elements are unique, requiring space proportional to the input size.
2.  Therefore, the space complexity is $O(N)$.