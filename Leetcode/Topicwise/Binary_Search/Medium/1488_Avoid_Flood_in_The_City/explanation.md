## Avoid Flood in The City: Detailed Explanation

### 1. Problem Understanding:

The problem describes a scenario where we have `n` days and are given an array `rains` of length `n`. `rains[i]` represents the amount of rain falling on day `i`. If `rains[i] > 0`, it means it rained on lake `rains[i]`. If `rains[i] == 0`, it means it didn't rain, and we can choose a lake to dry up on that day.  The goal is to return an array `ans` of length `n`. If `rains[i] > 0`, then `ans[i] = -1`. If `rains[i] == 0`, then `ans[i]` should be the lake number we choose to dry up on day `i`. We must avoid flooding any lake. A lake floods if it rains on it on two different days without an intermediate day when it's dried up. If it's impossible to avoid flooding, return an empty array.

### 2. Approach / Intuition:

The core idea is to simulate the process day by day. We iterate through the `rains` array and maintain two key data structures:

*   A `map` (HashMap) to store the *next* day each lake will rain. `map<lake, nextRainDay>`.  This helps us efficiently find the next time a particular lake will rain, allowing us to proactively dry it up before it floods.
*   A `dry` list (ArrayList) to store the indices of the days that we can choose to dry up a lake. This needs to be maintained in sorted order so we can efficiently find the *earliest* possible dry day that can prevent a future flood.

The approach is greedy: When we encounter a rainy day for a lake that we've seen before, we search for the *earliest* dry day *after* the previous rain day for that lake, and dry it up *then*. This ensures we avoid a flood. If we can't find such a dry day, it means we can't avoid a flood, and we return an empty array.  If we encounter a dry day, we just add the index to `dry` list.

Why this approach?  By drying the lake *as late as possible*, we keep our options open for dealing with other lakes that might need drying in the future. This is the greedy aspect and crucial to ensuring we can find a valid drying schedule if one exists.

### 3. Data Structures and Algorithms:

*   **`HashMap` (`map`):**  Used to store the next day a particular lake will rain, enabling efficient lookups in O(1) average time.
*   **`ArrayList` (`dry`):**  Used to store the indices of dry days. We need to keep it sorted, and ArrayLists allow insertion and removal of elements.
*   **`Collections.binarySearch()`:** Used to efficiently find the earliest dry day after the previous rain day for a specific lake. Binary search has a time complexity of O(log n).

### 4. Code Walkthrough:

```java
class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;

        int[] ans = new int[n]; // Result array to store the drying schedule
        Map<Integer, Integer> map = new HashMap<>(); // Map: lake -> next rain day index
        List<Integer> dry = new ArrayList<>(); // List: indices of dry days (days when rains[i] == 0), kept sorted

        for(int i = 0; i<n; i++) {
            if(rains[i] == 0) dry.add(i); // Add the index of the dry day
            else {
                ans[i] = -1; // Mark rainy day with -1
                if(map.containsKey(rains[i])) { // If this lake has rained before
                    int j = map.get(rains[i]); // Get the previous rain day for this lake
                    int ind = Collections.binarySearch(dry, j); // Find the earliest dry day after previous rain day
                    if(ind < 0) ind = -ind-1; // Adjust index to insertion point if not found

                    if(ind == dry.size()) return new int[]{}; // No dry day available, flooding is unavoidable

                    int dryInd = dry.get(ind); // Get the actual index of the earliest usable dry day
                    ans[dryInd] = rains[i]; // Dry this lake on the found dry day
                    dry.remove(ind); // Remove the used dry day

                }
                map.put(rains[i], i); // Update the next rain day of the lake
            }
        }

        for(int i = 0; i<n; i++) if(ans[i] == 0) ans[i] = 1; // Fill remaining dry days with any lake (default 1)

        return ans;
    }
}
```

1.  **Initialization:**
    *   `n = rains.length`: Stores the length of the `rains` array.
    *   `ans = new int[n]`: Creates the result array `ans` of the same length as `rains`.
    *   `map = new HashMap<>()`: Creates a HashMap to store the next rain day for each lake. Key is the lake number, and value is the index of the next rain day.
    *   `dry = new ArrayList<>()`: Creates an ArrayList to store the indices of dry days.

2.  **Main Loop:**
    *   Iterates through the `rains` array from `i = 0` to `n - 1`.
    *   **If `rains[i] == 0` (Dry Day):**
        *   `dry.add(i)`: Adds the index `i` to the `dry` list. The list is maintained in sorted order through the binary search and remove logic that follows.
    *   **Else (Rainy Day):**
        *   `ans[i] = -1`: Sets the corresponding element in `ans` to `-1` (as required by the problem).
        *   **`if (map.containsKey(rains[i]))`:** Checks if the current lake (`rains[i]`) has rained before.
            *   `j = map.get(rains[i])`: Retrieves the index `j` of the *previous* rain day for this lake from the `map`.
            *   `ind = Collections.binarySearch(dry, j)`: Performs a binary search on the `dry` list to find the *smallest* index that is greater than or equal to `j` (the previous rain day). This helps us find the earliest available dry day *after* the previous rain day.
            *   `if (ind < 0) ind = -ind-1`: If `j` wasn't found, the binary search returns `-(insertion point) - 1`.  We convert this to the insertion point. This gives us the index where `j` *should* be inserted to maintain the sorted order. Therefore, the value at `ind` is the smallest index that is *greater* than `j`.
            *   `if (ind == dry.size()) return new int[]{};`: If `ind` is equal to the size of `dry`, it means there are no available dry days after the previous rain day `j`. This means we cannot avoid flooding, so we return an empty array.
            *   `dryInd = dry.get(ind)`: Retrieves the actual dry day index from the `dry` list.
            *   `ans[dryInd] = rains[i]`: Sets the corresponding element in `ans` at the dry day index to the lake number (`rains[i]`), indicating that we will dry this lake on that day.
            *   `dry.remove(ind)`: Removes the used dry day from the `dry` list. This is important to avoid re-using the same dry day and potentially causing future floods.
        *   `map.put(rains[i], i)`: Updates the `map` to store the current rain day (`i`) as the *next* rain day for the lake `rains[i]`.

3.  **Filling Remaining Dry Days:**
    *   Iterates through the `ans` array.
    *   `if (ans[i] == 0)`: If an element in `ans` is still `0`, it means that it was a dry day, and we didn't use it to dry any specific lake.
        *   `ans[i] = 1`: Sets the element to `1` (or any other valid lake number).  The problem specifies that we can choose any lake in this case.

4.  **Return:**
    *   Returns the `ans` array containing the drying schedule.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n log n)
    *   The main loop iterates `n` times.
    *   Inside the loop, the `Collections.binarySearch()` operation takes O(log n) time.
    *   `dry.remove(ind)` takes O(n) time in worst case where we need to shift all elements. However, since binary search is the bottleneck, it can still be regarded as O(n log n). The HashMap operations (`containsKey`, `get`, `put`) take O(1) on average.
*   **Space Complexity:** O(n)
    *   The `ans` array takes O(n) space.
    *   The `map` (HashMap) can store at most `n` entries in the worst case (if all lakes are different).
    *   The `dry` list can store at most `n` entries (if all days are dry).
