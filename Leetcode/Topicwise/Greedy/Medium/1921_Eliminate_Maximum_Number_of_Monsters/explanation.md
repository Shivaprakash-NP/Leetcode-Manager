## LeetCode Problem: Eliminate Maximum Number of Monsters - Explained

### 1. Problem Understanding:

The problem asks us to determine the maximum number of monsters that can be eliminated before they reach the city. We are given two arrays: `dist` representing the distance of each monster from the city, and `speed` representing the speed of each monster. We can eliminate one monster at the beginning of each minute. If a monster reaches the city at the beginning of a minute (i.e., its arrival time is less than or equal to the current minute), we can no longer eliminate it. The goal is to find the maximum number of monsters we can eliminate.

### 2. Approach / Intuition:

The core idea is to prioritize eliminating monsters that will reach the city earliest.  If we focus on eliminating the monsters that pose the most immediate threat, we maximize our chances of eliminating the most monsters overall.  This suggests a greedy approach:

1.  **Calculate Arrival Times:** Calculate the time each monster will take to reach the city.
2.  **Sort by Arrival Time:** Sort the monsters based on their arrival times in ascending order. This ensures that we consider the most urgent threats first.
3.  **Eliminate Greedy:** Iterate through the sorted monsters. For each monster, check if its arrival time is greater than the current minute (represented by the index `i`). If it is, we can eliminate it. If not, the monster has reached the city, and we can't eliminate it or any subsequent monsters.

Why this approach?  Sorting by arrival time guarantees that we always try to eliminate the monster closest to reaching the city first. If we were to eliminate a farther monster first, we might not have enough time to eliminate the closer monster before it arrives, resulting in eliminating fewer total monsters.

### 3. Data Structures and Algorithms:

*   **Arrays:** Used to store the input distances (`dist`) and speeds (`speed`).  Also, to store the calculated arrival times (`time`).
*   **Double[]:**  The `time` array stores the arrival times as floating-point numbers (doubles) to handle potential decimal values in the arrival times.
*   **Arrays.sort():**  The `Arrays.sort()` method is used to sort the `time` array in ascending order.  This is a key step in the greedy approach.

### 4. Code Walkthrough:

```java
class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        double[] time = new double[n];
        for (int i = 0; i < n; i++) 
            time[i] = (double) dist[i] / speed[i];
        Arrays.sort(time);
        for (int i = 0; i < n; i++) 
            if (time[i] <= i) return i;
        return n;
    }
}
```

1.  **`int n = dist.length;`**:  Gets the number of monsters from the length of the `dist` array.
2.  **`double[] time = new double[n];`**: Creates a new double array named `time` to store the arrival time of each monster.  Its size is equal to the number of monsters.
3.  **`for (int i = 0; i < n; i++) time[i] = (double) dist[i] / speed[i];`**: This loop calculates the arrival time for each monster by dividing its distance (`dist[i]`) by its speed (`speed[i]`). The `(double)` cast is crucial to ensure that the division results in a floating-point number, giving the precise arrival time. The arrival time is then stored in the `time` array.
4.  **`Arrays.sort(time);`**: Sorts the `time` array in ascending order. Now `time[0]` holds the smallest arrival time, `time[1]` the next smallest, and so on. This is the crucial step for the greedy approach.
5.  **`for (int i = 0; i < n; i++) if (time[i] <= i) return i;`**: This is the core elimination loop. `i` represents the current minute (0-indexed).
    *   `time[i] <= i`:  This condition checks if the monster at index `i` reaches the city at or before minute `i`. If the monster reaches the city at the same time or before the elimination time then, we can not eliminate it. So we return the current minute (`i`). We return `i` because we were able to eliminate `i` monsters.
    *   `return i;`: If the condition `time[i] <= i` is true, it means we cannot eliminate the current monster (`i`), therefore we return `i` (i.e. eliminated only `i` monsters).
6.  **`return n;`**: If the loop completes without returning, it means we were able to eliminate all `n` monsters.  Therefore, we return `n`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n log n) due to the `Arrays.sort()` operation. The other loops are O(n), which are dominated by the sorting time.
*   **Space Complexity:** O(n) because we create a new `time` array of size `n` to store the arrival times.  The `Arrays.sort()` in Java *may* have space complexity ranging from O(log n) to O(n) depending on the specific sorting algorithm used by the Java implementation, but given the dominant O(n) already, it is usually stated as O(n) overall.
