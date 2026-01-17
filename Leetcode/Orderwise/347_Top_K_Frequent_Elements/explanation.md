### Problem Understanding

The problem "Top K Frequent Elements" asks us to identify and return the `k` most frequently occurring elements from a given integer array `nums`. The order of the returned elements does not matter. For example, if `nums = [1,1,1,2,2,3]` and `k = 2`, the output should be `[1,2]` because `1` appears 3 times, `2` appears 2 times, and `3` appears 1 time, making `1` and `2` the two most frequent elements.

### Approach / Intuition

The core idea to solve this problem involves two main steps:

1.  **Count Frequencies:** First, we need to determine how many times each unique number appears in the input array `nums`. A hash map (or dictionary) is an ideal data structure for this, as it allows us to store key-value pairs where the key is the number and the value is its frequency, with efficient lookup and update operations.

2.  **Identify Top K:** Once we have the frequencies of all numbers, we need to select the `k` numbers that have the highest frequencies.
    *   A straightforward way would be to convert the map entries into a list and sort them based on frequency. However, sorting all unique elements might be less efficient than necessary, especially if `k` is small compared to the total number of unique elements.
    *   A more optimized approach, especially in competitive programming, is to use a **Priority Queue (Min-Heap or Max-Heap)**.
        *   **Max-Heap Approach (as implemented in the code):** We can insert all unique numbers along with their frequencies into a max-heap. A max-heap will always keep the element with the highest frequency at the top. Then, we simply extract (poll) the top element `k` times to get the `k` most frequent elements. This is simple and effective.
        *   **Min-Heap Approach (alternative, often more efficient for small k):** We could maintain a min-heap of size `k`. As we iterate through the unique numbers and their frequencies, if the heap size is less than `k`, we add the element. If the heap is full (size `k`) and the current element's frequency is greater than the frequency of the smallest element in the heap (the heap's root), we remove the smallest element and add the current one. This ensures the heap always contains the `k` most frequent elements seen so far.

The provided code implements the **Max-Heap Approach**. It creates a max-heap where elements are ordered by their frequency in descending order.

### Data Structures and Algorithms

1.  **`HashMap<Integer, Integer>`:** Used to store the frequency of each number.
    *   Key: The number itself (`Integer`).
    *   Value: Its frequency/count (`Integer`).
2.  **`PriorityQueue<int[]>`:** Used as a **Max-Heap** to store pairs of `[frequency, number]`.
    *   The `int[]` array holds two values: `[frequency, number]`.
    *   The custom comparator `(a, b) -> b[0] - a[0]` ensures that the priority queue orders elements based on their *first element* (`a[0]`, which is the frequency) in *descending* order. This makes it a max-heap with respect to frequency.
3.  **Iteration/Looping:** Used to traverse the input array `nums` and the keyset of the frequency map.

### Code Walkthrough

Let's break down the provided Java code section by section:

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Create a HashMap to store the frequency of each number.
        Map<Integer, Integer> map = new HashMap<>();

        // 2. Iterate through the input array 'nums' to populate the frequency map.
        for(int v : nums) {
            // For each number 'v', increment its count.
            // If 'v' is not already in the map, getOrDefault(v, 0) returns 0,
            // so it starts with a count of 1. Otherwise, it increments the existing count.
            map.put(v, map.getOrDefault(v, 0) + 1);
        }

        // 3. Create a PriorityQueue (Max-Heap) to store [frequency, number] pairs.
        // The comparator (a, b) -> b[0] - a[0] ensures that elements with higher frequencies (a[0])
        // are prioritized (come first in the queue).
        // If b[0] > a[0], b[0] - a[0] is positive, meaning 'b' has higher priority than 'a'.
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // 4. Iterate through the unique numbers (keys) in the frequency map.
        for(int v : map.keySet()) {
            // For each unique number 'v', create an array [frequency, number]
            // and add it to the priority queue.
            q.offer(new int[]{map.get(v), v});
        }

        // 5. Initialize an array 'res' of size 'k' to store the result.
        int ind = 0; // Index for the result array
        int[] res = new int[k];

        // 6. Extract the 'k' most frequent elements from the priority queue.
        while(ind < k) {
            // q.poll() removes and returns the element with the highest frequency (due to max-heap).
            // The element is an int[] array like [frequency, number].
            // We only need the number itself, which is at index 1 of this array.
            res[ind++] = q.poll()[1];
        }

        // 7. Return the array containing the 'k' most frequent elements.
        return res;
    }
}
```

### Time and Space Complexity

#### Time Complexity

Let `N` be the number of elements in the input array `nums`, and `U` be the number of unique elements in `nums`.

1.  **Populating the `HashMap`:**
    *   We iterate through `N` elements in `nums`.
    *   Each `map.put()` or `map.getOrDefault()` operation takes O(1) average time.
    *   **Time: O(N)**

2.  **Populating the `PriorityQueue`:**
    *   We iterate through `U` unique elements (the keyset of the map).
    *   For each unique element, `q.offer()` (inserting into the priority queue) takes O(log P) time, where `P` is the current number of elements in the priority queue. In the worst case, `P` can grow up to `U`.
    *   **Time: O(U log U)**

3.  **Extracting `k` elements from the `PriorityQueue`:**
    *   We perform `k` `q.poll()` operations.
    *   Each `q.poll()` (removing the top element) takes O(log P) time, where `P` is the current number of elements in the priority queue. In the worst case, `P` is `U`.
    *   **Time: O(k log U)**

**Total Time Complexity:** O(N + U log U + k log U).
Since `U` can be at most `N` (when all elements are unique), and `k` can be at most `U`, the dominant term is typically `O(N + N log N)`, which simplifies to **O(N log N)** in the worst case (e.g., if all elements are unique, `U=N`, and `k=N`).

#### Space Complexity

1.  **`HashMap`:**
    *   Stores `U` unique elements and their frequencies.
    *   **Space: O(U)**

2.  **`PriorityQueue`:**
    *   Stores `U` `int[]` pairs `[frequency, number]`.
    *   **Space: O(U)**

3.  **Result Array `res`:**
    *   Stores `k` integers.
    *   **Space: O(k)**

**Total Space Complexity:** O(U + k).
Since `U` can be at most `N`, the space complexity is **O(N)** in the worst case.