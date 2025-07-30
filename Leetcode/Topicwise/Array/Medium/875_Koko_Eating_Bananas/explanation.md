## Koko Eating Bananas: Detailed Explanation

### 1. Problem Understanding:

Koko loves to eat bananas. There are `n` piles of bananas, the `i`-th pile has `piles[i]` bananas. The guards are coming back in `h` hours. Koko can decide her bananas-per-hour eating speed of `k`. Each hour, she chooses a pile of bananas and eats `k` bananas from that pile. If the pile has less than `k` bananas, she eats all of them instead and will not eat any more bananas during this hour. Koko likes to eat slowly, but still wants to finish all the bananas before the guards come back. Find the minimum integer `k` such that she can eat all the bananas within `h` hours.

### 2. Approach / Intuition:

The core idea is to use binary search to find the minimum eating speed `k`.

*   **Why Binary Search?** The problem asks for the *minimum* value that satisfies a condition. This immediately suggests binary search.  The possible values of `k` form a range.  A smaller `k` means it takes longer to eat the bananas, and a larger `k` means it takes less time. The function "time to eat all bananas" is monotonically decreasing with increasing `k`.  This monotonicity allows us to efficiently search for the optimal `k` using binary search.

*   **Search Space:** The search space for `k` is from 1 (the minimum possible speed) to the maximum number of bananas in any single pile (the maximum possible speed needed to empty the largest pile in one hour).

*   **`time(piles, k)` Function:**  This function calculates the total time it takes for Koko to eat all the bananas in the given piles at a speed of `k`. For each pile, we calculate how many hours it takes to finish that pile (using `(v + k - 1L) / k`), and sum it up.

*   **Binary Search Logic:**

    1.  We initialize `l` to 1 and `r` to the maximum number of bananas in any pile.
    2.  In the `while` loop (`l <= r`), we calculate the middle value `m` (potential speed).
    3.  We use the `time(piles, m)` function to compute how long it takes Koko to eat all bananas with speed `m`.
    4.  If the eating time is less than or equal to `h` (the allowed time), we know that `m` *might* be a possible solution. Since we are looking for the *minimum* `k`, we try to reduce the speed even further by searching the lower half of the range ( `r = m - 1`).
    5.  If the eating time is greater than `h`, we need a higher speed, so we search the upper half of the range (`l = m + 1`).
    6.  The loop continues until `l > r`. At this point, `l` is the smallest possible speed that allows Koko to finish the bananas in time.

### 3. Data Structures and Algorithms:

*   **Data Structures:** `int[] piles` (input array), primitive variables (int, long)
*   **Algorithms:** Binary Search

### 4. Code Walkthrough:

```java
import java.util.Arrays;

class Solution {
    private long time(int[] val, int m) {
        long t = 0; 
        for (int v : val) t += (v + m - 1L) / m; 
        return t;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt(); 
        while (l <= r) 
        {
            int m = l + (r - l) / 2; 
            if (time(piles, m) <= h) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}
```

*   **`time(int[] val, int m)` function:**

    *   `long t = 0;`: Initializes a variable `t` to store the total time required to eat all bananas.  `long` is used to prevent potential integer overflow.
    *   `for (int v : val) t += (v + m - 1L) / m;`: This loop iterates through each pile of bananas (`v`). For each pile, it calculates the time required to eat all the bananas at speed `m`. The formula `(v + m - 1L) / m` is equivalent to `Math.ceil((double) v / m)`, which calculates the number of hours needed to eat `v` bananas at speed `m`. The `1L` addition ensures that integer division rounds up correctly, simulating ceiling division, which is crucial because Koko must complete each hour.
    *   `return t;`: Returns the total time `t`.

*   **`minEatingSpeed(int[] piles, int h)` function:**

    *   `int l = 1;`: Initializes the left boundary `l` of the search space to 1 (minimum possible eating speed).
    *   `int r = Arrays.stream(piles).max().getAsInt();`: Initializes the right boundary `r` of the search space to the maximum number of bananas in any pile. We use `Arrays.stream(piles).max().getAsInt()` to efficiently find the maximum value in the `piles` array.
    *   `while (l <= r)`: This loop implements the binary search algorithm. It continues as long as the left boundary is less than or equal to the right boundary.
    *   `int m = l + (r - l) / 2;`: Calculates the middle value `m` of the current search space.  Using `l + (r - l) / 2` instead of `(l + r) / 2` prevents potential integer overflow when `l` and `r` are large. `m` represents the potential eating speed.
    *   `if (time(piles, m) <= h) r = m - 1;`: If the time required to eat all bananas at speed `m` is less than or equal to `h`, it means that `m` is a possible solution. However, we want to find the *minimum* possible speed, so we try to reduce the speed further by searching the lower half of the range (`r = m - 1`).
    *   `else l = m + 1;`: If the time required to eat all bananas at speed `m` is greater than `h`, it means we need a higher speed. We update the left boundary to `m + 1`, effectively searching the upper half of the range.
    *   `return l;`: After the loop terminates (when `l > r`), `l` represents the minimum eating speed that allows Koko to eat all the bananas within `h` hours. We return this value.

### 5. Time and Space Complexity:

*   **Time Complexity:**  O(N * log(M)), where N is the number of piles and M is the maximum number of bananas in any pile.  The binary search takes O(log(M)) iterations. In each iteration, the `time()` function takes O(N) time to calculate the total eating time for all the piles.  Therefore, the overall time complexity is O(N * log(M)).

*   **Space Complexity:** O(1). The solution uses a constant amount of extra space for variables, regardless of the input size.
