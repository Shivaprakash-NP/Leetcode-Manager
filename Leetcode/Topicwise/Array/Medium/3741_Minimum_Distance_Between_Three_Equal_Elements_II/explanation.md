### Problem Understanding

The problem "Minimum Distance Between Three Equal Elements II" asks us to find the minimum "distance" among all possible triplets of equal elements in a given integer array `nums`.

Specifically, for any value `v` that appears at least three times in the array, we need to consider all possible sets of three *consecutive* occurrences of `v`. Let these three occurrences be at indices `a`, `b`, and `c` in the original array, such that `a < b < c`. The "distance" for such a triplet is defined as `2 * (c - a)`. Our goal is to find the smallest such distance across all unique values `v` and all valid consecutive triplets of their indices. If no value appears at least three times, we should return -1.

### Approach / Intuition

The core idea behind solving this problem efficiently is to first organize the input data in a way that allows us to easily access all occurrences of each unique number and then iterate through them to calculate the required distances.

1.  **Group Indices by Value:** Since we are interested in *equal* elements, the first step is to identify all unique numbers in the array and, for each unique number, store all the indices where it appears. A `HashMap` is a natural choice for this, mapping each number to a list of its indices. It's crucial that these lists of indices are sorted, which they will be if we populate them by iterating through the `nums` array from left to right.

2.  **Filter by Frequency:** The problem states we need "three equal elements". This means any number `v` must appear at least three times in the array. We can maintain a frequency count for each number while populating the index map, or simply check the size of the index list later. If a number appears fewer than three times, it cannot contribute to the solution, so we can ignore it.

3.  **Calculate Minimum Distance:**
    *   For each number `v` that appears at least three times, we retrieve its sorted list of indices.
    *   We then iterate through this list, considering triplets of *consecutive* indices. For example, if the indices are `[i_0, i_1, i_2, i_3, i_4, ...]`, we would consider triplets `(i_0, i_1, i_2)`, then `(i_1, i_2, i_3)`, then `(i_2, i_3, i_4)`, and so on.
    *   For each such triplet `(a, b, c)` where `a = ind.get(k)`, `b = ind.get(k+1)`, and `c = ind.get(k+2)`, we calculate the distance `2 * (c - a)`.
    *   We keep track of the minimum distance found so far across all such triplets and all unique values.

4.  **Handle No Triplet Case:** If after checking all numbers, no valid triplet was found (i.e., no number appeared three or more times), the minimum distance variable will retain its initial `Integer.MAX_VALUE`. In this case, we return -1 as per the problem requirements.

This approach ensures that we consider all valid triplets and correctly identify the minimum distance by systematically checking all possibilities that meet the criteria. The use of sorted indices automatically helps in minimizing `c - a` for any given set of three *consecutive* occurrences.

### Data Structures and Algorithms

1.  **`HashMap<Integer, ArrayList<Integer>> map`**: This `HashMap` is used to store the mapping from each unique integer value found in `nums` to an `ArrayList` containing all the indices where that value appears in `nums`. The `ArrayList` naturally stores indices in ascending order because we iterate through `nums` from left to right.
2.  **`HashMap<Integer, Integer> fre`**: This `HashMap` is used to store the frequency (count of occurrences) of each unique integer value in `nums`. This allows for a quick check to see if a value appears at least 3 times.
3.  **Linear Scan / Iteration**:
    *   The initial population of both `map` and `fre` involves a single linear scan through the input array `nums`.
    *   The subsequent calculation of the minimum distance involves iterating through the keys of `fre` (unique values) and then, for each relevant value, iterating through its `ArrayList` of indices.
4.  **`Math.min`**: Used repeatedly to update the `min` distance found so far.

### Code Walkthrough

```java
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length; // Get the total number of elements in the input array.

        // map: Stores each unique number as a key and a list of its indices as the value.
        // Example: If nums = [1, 2, 1, 3, 1], map would be {1: [0, 2, 4], 2: [1], 3: [3]}
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        // fre: Stores the frequency of each unique number.
        // Example: If nums = [1, 2, 1, 3, 1], fre would be {1: 3, 2: 1, 3: 1}
        Map<Integer, Integer> fre = new HashMap<>();

        // First pass: Populate the map with indices and the frequency map.
        for(int i = 0; i<n; i++) {
            int v = nums[i]; // Get the current value from the array.

            // Add the current index 'i' to the list associated with value 'v'.
            // computeIfAbsent ensures that an ArrayList is created if 'v' is not yet in the map.
            map.computeIfAbsent(v, k -> new ArrayList<>()).add(i);

            // Increment the frequency count for value 'v'.
            // getOrDefault handles cases where 'v' is not yet in the frequency map.
            fre.put(v, fre.getOrDefault(v, 0)+1);
        }
        
        int min = Integer.MAX_VALUE; // Initialize min distance to a very large value.

        // Second pass: Iterate through each unique value to find the minimum distance.
        for(int v : fre.keySet()) {
            // Only consider values that appear at least 3 times.
            if(fre.get(v) < 3) continue; 
            
            // Get the sorted list of indices for the current value 'v'.
            ArrayList<Integer> ind = map.get(v);

            // Iterate through the list of indices to find triplets of consecutive occurrences.
            // The loop condition `i < ind.size() - 2` ensures that `ind.get(i+2)` is a valid index.
            // This means we are picking (ind[i], ind[i+1], ind[i+2]) as our (a, b, c) triplet.
            for(int i = 0; i<ind.size()-2; i++) {
                int a = ind.get(i);   // Index of the first occurrence in the triplet.
                // int b = ind.get(i+1); // Index of the second occurrence (not directly used in distance calculation).
                int c = ind.get(i+2); // Index of the third occurrence in the triplet.

                // Calculate the distance for this triplet: 2 * (c - a).
                // Update 'min' if this distance is smaller than the current minimum.
                min = Math.min(min, 2*(c-a));
            }
        }

        // After checking all possible triplets, return the minimum distance.
        // If 'min' is still Integer.MAX_VALUE, it means no valid triplet was found, so return -1.
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```

### Time and Space Complexity

#### Time Complexity: O(N)

1.  **First Pass (Populating maps):** The initial `for` loop iterates `N` times, where `N` is the length of the `nums` array. Inside the loop, `map.computeIfAbsent` and `fre.put` (with `getOrDefault`) operations take O(1) time on average for `HashMap`s. Therefore, this part takes O(N) time.
2.  **Second Pass (Calculating minimum distance):**
    *   The outer `for` loop iterates over the `keySet()` of `fre`. In the worst case, all numbers are unique, so it iterates `N` times. In the best case, all numbers are the same, so it iterates once. The number of unique values is at most `N`.
    *   Inside this loop, `map.get(v)` is O(1) on average.
    *   The inner `for` loop iterates `ind.size() - 2` times for each value `v`.
    *   Crucially, the sum of `ind.size()` for all unique values `v` is exactly `N` (because each index `i` from `0` to `N-1` is stored exactly once across all `ArrayList`s in `map`).
    *   Thus, the total number of operations performed by the inner loop across all unique values is proportional to the sum of all `ind.size()` values, which is O(N).
    *   Therefore, the second pass also takes O(N) time.

Combining both passes, the overall time complexity is **O(N)**.

#### Space Complexity: O(N)

1.  **`map` (HashMap<Integer, ArrayList<Integer>>):** In the worst case, all elements in `nums` are distinct. The `HashMap` would store `N` keys, and each `ArrayList` would contain a single index. In another worst case, all elements are the same. The `HashMap` would store 1 key, and its `ArrayList` would contain `N` indices. In either scenario, the total number of integer values stored across all `ArrayList`s is `N`. So, the space for `map` is O(N).
2.  