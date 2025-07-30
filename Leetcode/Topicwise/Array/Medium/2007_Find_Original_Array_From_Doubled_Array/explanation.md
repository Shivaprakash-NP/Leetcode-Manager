```markdown
## LeetCode Problem: Find Original Array From Doubled Array - Explanation

### 1. Problem Understanding:

The problem states that we are given an array `changed`, which is a doubled array. This means that the original array (let's call it `original`) has been doubled, such that each element `x` in `original` is also present as `2*x` in `changed`.  The `changed` array is simply a shuffling of the `original` array combined with its doubled version.

Our task is to reconstruct the `original` array from the `changed` array. If the `changed` array is not a doubled array (i.e., it's impossible to reconstruct `original`), we should return an empty array.

**Example:**

`changed = [1, 3, 4, 2, 6, 8]`

`original = [1, 3, 4]` because `2 * [1, 3, 4] = [2, 6, 8]`.

### 2. Approach / Intuition:

The core idea is based on the fact that if `changed` is indeed a doubled array, for every element `x` in `original`, there must be a corresponding `2*x` also present in `changed`. We can leverage this property to reconstruct the `original` array.

Here's the breakdown of the approach:

1. **Check for even length:**  A doubled array must have an even number of elements. If the input array `changed` has an odd length, it can't be a doubled array, so we immediately return an empty array.

2. **Count element frequencies:** We use a `TreeMap` (or a `HashMap` and then sort the keys) to store the frequency of each element in `changed`. The `TreeMap` is crucial because it sorts the keys (the numbers in the array) in ascending order. This is important because we want to process smaller numbers first.  If we encounter a smaller number and its double exists, we know we can include the smaller number in the result.

3. **Iterate through sorted elements:** We iterate through the keys (sorted elements) of the `TreeMap`. For each element `v`, we check if there are enough occurrences of `2*v` to form the doubled array.

4. **Reconstruct `original` array:** If the frequency of `v` is greater than the frequency of `2*v`, it implies that the input is invalid and we cannot form the `original` array, hence we return an empty array. Otherwise, we add `v` to the `original` array as many times as it appears in `changed`, decrementing the count of `2*v` in the `TreeMap` with each addition. This ensures that each `v` has a corresponding `2*v`.

**Why this approach?**

- Sorting using `TreeMap` (or sorting keys after using `HashMap`) ensures that we process smaller numbers before their doubles. This is important because if we encounter a smaller number `x` and `2*x` exists, we know that `x` belongs to the `original` array, not `2*x`. Processing smaller numbers first guarantees correctness.
- Using a frequency map simplifies counting element occurrences and checking the presence of doubles.

### 3. Data Structures and Algorithms:

- **`TreeMap` (or `HashMap`):** Used to store the frequency of each element in the `changed` array. `TreeMap` provides automatic sorting of the keys, which is important for the algorithm's logic. If `HashMap` is used, we need to sort the keys separately before iterating through them.
- **Array (`ans`):** Used to store the reconstructed `original` array.
- **Iteration:** The code involves iterating through the `changed` array and the keys of the `TreeMap`.
- **Greedy Approach:** The solution takes a greedy approach by adding elements to the `original` array whenever a corresponding double is found. The order of elements is guaranteed by the TreeMap.

### 4. Code Walkthrough:

```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        Map<Integer, Integer> val = new TreeMap<>(); // TreeMap to store element frequencies, sorted by key
        int n = changed.length; // Length of the input array
        int x = 0; // Index for the original array
        int[] ans = new int[n/2]; // Initialize the original array with half the length of changed
        if(n%2 == 1) return new int[]{}; // If the length is odd, it cannot be a doubled array
        for(int v : changed) {
            val.put(v , val.getOrDefault(v , 0)+1); // Count the frequency of each element
        }
        for(int v : val.keySet()) { // Iterate through the sorted keys (elements)
            if(val.get(v) > val.getOrDefault(v*2 , 0)) { // If frequency of v > frequency of 2v, invalid
                return new int[]{};
            }
            for(int i = 0 ; i < val.get(v) ; ++i) { // For each occurrence of v
                ans[x++] = v; // Add v to the original array
                val.put(v*2 , val.get(v*2)-1); // Decrement the count of 2v
            }
        }
        return ans; // Return the original array
    }
}
```

**Line-by-line explanation:**

1. `Map<Integer, Integer> val = new TreeMap<>();`: Creates a `TreeMap` called `val` to store the frequency of each number in the `changed` array. The key is the number, and the value is its frequency.
2. `int n = changed.length;`: Stores the length of the input array `changed`.
3. `int x = 0;`: Initializes an index `x` to keep track of the current position in the `ans` array (the `original` array).
4. `int[] ans = new int[n/2];`: Creates an integer array `ans` of size `n/2` to store the reconstructed `original` array.  Since `changed` is a doubled array, the `original` array will have half the number of elements.
5. `if(n%2 == 1) return new int[]{};`: Checks if the length of `changed` is odd. If it is, it means it cannot be a doubled array, so an empty array is returned.
6. `for(int v : changed) { val.put(v , val.getOrDefault(v , 0)+1); }`: This loop iterates through the `changed` array and populates the `val` map with the frequency of each element.  `val.getOrDefault(v, 0)` retrieves the current frequency of `v` or returns 0 if `v` is not yet in the map. Then, we increment the frequency by 1.
7. `for(int v : val.keySet()) { ... }`: This loop iterates through the keys of the `val` map (which are the unique numbers from the `changed` array). The `TreeMap` ensures that the keys are iterated in sorted order.
8. `if(val.get(v) > val.getOrDefault(v*2 , 0)) { return new int[]{}; }`: This condition checks if the frequency of `v` is greater than the frequency of `2*v`. If it is, it means that there are more occurrences of `v` than there are of its double (`2*v`).  This indicates that the `changed` array is not a valid doubled array, and we return an empty array. `val.getOrDefault(v*2 , 0)` retrieves the frequency of `2*v` or returns 0 if `2*v` is not in the map.
9. `for(int i = 0 ; i < val.get(v) ; ++i) { ... }`: This inner loop iterates `val.get(v)` times, which is the frequency of `v`.  For each occurrence of `v`, we add it to the `ans` array and decrement the frequency of `2*v` in the `val` map.
10. `ans[x++] = v;`: Adds the current number `v` to the `ans` array at index `x`, and then increments `x`.
11. `val.put(v*2 , val.get(v*2)-1);`: Decrements the frequency of `2*v` in the `val` map by 1. This indicates that we have used one occurrence of `2*v` to pair with `v`.
12. `return ans;`: After iterating through all the numbers, the `ans` array should contain the reconstructed `original` array. The function returns this array.

### 5. Time and Space Complexity:

- **Time Complexity:** O(n log n), where n is the length of the `changed` array. This is primarily due to the use of `TreeMap`, which has a logarithmic time complexity for insertion and retrieval. Building the frequency map takes O(n log n). Iterating through the keys and decrementing frequencies takes O(n) time. If a `HashMap` is used then sorting the keys will take O(n log n) to sort. Therefore overall time complexity is O(n log n).

- **Space Complexity:** O(n), where n is the length of the `changed` array. This is because the `TreeMap` (or `HashMap`) can potentially store all the unique elements of `changed` and their frequencies. Also, the `ans` array stores `n/2` elements.
