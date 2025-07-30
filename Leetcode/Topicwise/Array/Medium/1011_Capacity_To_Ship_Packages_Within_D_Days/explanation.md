## Capacity To Ship Packages Within D Days - Solution Explanation

Here's a detailed explanation of the provided Java code for the "Capacity To Ship Packages Within D Days" LeetCode problem.

### 1. Problem Understanding

The problem states that we are given an array `weights` representing the weights of packages and an integer `days` representing the number of days we have to ship all packages. We need to find the *minimum* weight capacity of the ship such that we can ship all packages within the given `days`.  We cannot split packages, and we must ship packages in the order they appear in the `weights` array.

In essence, we need to find the smallest possible capacity of a ship that allows us to deliver all packages within the specified number of days.

### 2. Approach / Intuition

The core idea behind the solution is to use **binary search**. Here's why this approach is suitable:

*   **Monotonicity:** The problem exhibits a monotonic property.  If a ship with capacity `x` can ship all packages within `days`, then a ship with capacity `x+1` can also do the same. This is because a larger capacity only makes it easier to stay within the day limit.  Similarly, if a ship with capacity `x` *cannot* ship all packages within `days`, then a ship with capacity `x-1` will also fail.

*   **Search Space:**  We can define a valid search space for the ship's capacity.
    *   The lower bound is the maximum weight of any single package (because we can't split packages).
    *   The upper bound is the sum of all package weights (the case where we ship all packages in one day).

With a monotonic function and a defined search space, binary search is an efficient way to find the *minimum* capacity that satisfies the condition (shipping within `days`).

The `possible()` function checks if it's possible to ship all the packages within the specified `days` given a ship capacity `m`.

### 3. Data Structures and Algorithms

*   **Data Structures:** The primary data structure is the `weights` array (integer array).
*   **Algorithms:**
    *   **Binary Search:**  The `shipWithinDays` function implements binary search to find the optimal ship capacity.
    *   **Greedy Algorithm (within `possible()` function):** The `possible` function greedily simulates shipping the packages. It keeps adding packages to the current day until the capacity is exceeded, then moves to the next day.

### 4. Code Walkthrough

Let's break down the code section by section:

**`possible(int[] val, int m, int days)` function:**

```java
private boolean possible(int[] val, int m, int days) {
    int a = 1; // Number of days used so far (initialized to 1)
    int c = 0; // Current capacity used for the current day (initialized to 0)
    for (int v : val) // Iterate through each weight in the 'weights' array
    {
        c += v; // Add the current package weight to the current day's capacity
        if (c > m) // If adding the weight exceeds the maximum capacity 'm'
        {
            a++; // Increment the number of days used
            c = v; // Start a new day with the current package weight
        }
    }
    return (a <= days); // Return true if the total number of days used is less than or equal to the allowed 'days', false otherwise
}
```

*   **Initialization:** `a` (number of days used) is initialized to 1, and `c` (current capacity used for the current day) is initialized to 0.
*   **Iteration:** The code iterates through the `weights` array (`val`).
*   **Capacity Check:** Inside the loop, the current package's weight (`v`) is added to the current day's used capacity (`c`).
*   **Day Increment:** If adding the weight exceeds the maximum capacity `m`, a new day is started by incrementing `a` and resetting `c` to the current package's weight.
*   **Return Value:** The function returns `true` if the total number of days used (`a`) is less than or equal to the allowed number of days (`days`); otherwise, it returns `false`. This indicates whether it's possible to ship all packages within the allowed days given the specified ship capacity `m`.

**`shipWithinDays(int[] weights, int days)` function:**

```java
public int shipWithinDays(int[] weights, int days) {
    int l = Arrays.stream(weights).max().getAsInt(); // Lower bound: the maximum weight of any single package
    int r = Arrays.stream(weights).sum(); // Upper bound: the sum of all package weights
    while (l <= r) // Binary search loop
    {
        int m = l + (r - l) / 2; // Calculate the middle value (potential ship capacity)
        if (possible(weights, m, days)) r = m - 1; // If possible to ship within 'days' with capacity 'm', try a lower capacity
        else l = m + 1; // If not possible, increase the capacity
    }
    return l; // Return the minimum capacity that works
}
```

*   **Initialization:**
    *   `l` (left pointer) is initialized to the maximum weight in the `weights` array. This is the minimum possible capacity.
    *   `r` (right pointer) is initialized to the sum of all weights in the `weights` array.  This is the maximum possible capacity.
*   **Binary Search Loop:** The `while (l <= r)` loop performs binary search.
*   **Midpoint Calculation:** `m` (middle value) is calculated as `l + (r - l) / 2`. This is done to prevent potential integer overflow.
*   **Capacity Check:** The `possible()` function is called to check if it's possible to ship all packages within `days` using a ship with capacity `m`.
*   **Pointer Adjustment:**
    *   If `possible(weights, m, days)` is `true`, it means we can ship all packages within `days` with capacity `m`.  We try to find an even smaller capacity by setting `r = m - 1`.
    *   If `possible(weights, m, days)` is `false`, it means we cannot ship all packages within `days` with capacity `m`. We need to increase the capacity by setting `l = m + 1`.
*   **Return Value:** After the binary search loop finishes, `l` will contain the minimum possible capacity that allows us to ship all packages within `days`.  The function returns `l`.

### 5. Time and Space Complexity

*   **Time Complexity:**
    *   `Arrays.stream(weights).max()` and `Arrays.stream(weights).sum()` take O(N) time, where N is the number of packages.
    *   The `while` loop in `shipWithinDays` performs binary search, which takes O(log(Sum - Max)) time, where Sum is the sum of all weights and Max is the maximum weight.
    *   Inside the `while` loop, the `possible()` function takes O(N) time in the worst case (where it iterates through all the weights).
    *   Therefore, the overall time complexity is O(N + N * log(Sum - Max)), which simplifies to O(N * log(Sum - Max)).

*   **Space Complexity:**
    *   O(1) - The algorithm uses only a few constant extra variables.
