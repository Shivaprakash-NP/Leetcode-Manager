## Longest Consecutive Sequence

### 1. Problem Understanding:

The problem asks us to find the length of the longest consecutive sequence of elements within an unsorted array of integers. For example, given the array `[100, 4, 200, 1, 3, 2]`, the longest consecutive sequence is `[1, 2, 3, 4]`, and the function should return 4.

### 2. Approach / Intuition:

The core idea is to avoid redundant calculations. We want to efficiently explore consecutive sequences starting from a potential beginning. The approach is as follows:

1.  **Use a HashSet for Efficient Lookup:**  The primary reason we use a HashSet is for fast O(1) average-case time complexity `contains()` operations. This lets us quickly check if a number exists in the input array.

2.  **Identify Starting Points:**  Instead of starting the search for a sequence from every single number in the array, we only start searching if the number is the *beginning* of a consecutive sequence. A number is the beginning if `number - 1` is *not* present in the set.

3.  **Extend the Sequence:**  If a number *is* the beginning of a sequence, we then iteratively check if `number + 1`, `number + 2`, and so on, are present in the set, incrementing the length of the sequence as we go.

4.  **Track the Maximum:**  Keep track of the longest consecutive sequence found so far and return it at the end.

This approach avoids unnecessary computations by only extending sequences from their minimum values and skipping numbers that are clearly part of longer sequences started elsewhere.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `HashSet` is used to store the numbers from the input array.  This data structure provides O(1) average-case time complexity for `add()` and `contains()` operations, which are crucial for the efficiency of the algorithm.
*   **Algorithm:** The core algorithm can be described as a modified linear search enhanced by the HashSet for efficient lookups. It iterates through the set to find the start of consecutive sequences and then extends those sequences.

### 4. Code Walkthrough:

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> val = new HashSet<>();
        int maxc = 0;
        for(int v : nums) val.add(v); // Add all numbers to the HashSet. O(n) time.

        for(int v : val) // Iterate through the *unique* numbers in the HashSet.
        {
            int cc = 1; // Current consecutive sequence length, initialized to 1 for each starting element.
            if(!val.contains(v-1)) // Check if 'v' is the start of a sequence (i.e., v-1 is not present)
            {
                for(int i = v+1 ; ; i++) // Start from v+1 and incrementally check for consecutive elements.
                {
                    if(val.contains(i)) cc++; // If 'i' exists in the HashSet, increment the sequence length.
                    else break; // If 'i' doesn't exist, the consecutive sequence ends.
                }
            } 
            maxc = Math.max(maxc , cc); // Update the maximum consecutive sequence length found so far.
        }
        return maxc; // Return the maximum consecutive sequence length.
    }
}
```

*   **`HashSet<Integer> val = new HashSet<>();`**:  Creates a new HashSet called `val` to store the elements from the `nums` array.

*   **`int maxc = 0;`**: Initializes a variable `maxc` to 0. This variable will store the length of the longest consecutive sequence found so far.

*   **`for(int v : nums) val.add(v);`**: This loop iterates through the `nums` array and adds each element to the `val` HashSet. This step takes O(n) time.

*   **`for(int v : val)`**: This outer loop iterates through the elements of the `val` HashSet.  We iterate over the *unique* values in the set.

*   **`int cc = 1;`**: Inside the outer loop, `cc` is initialized to 1 for each number `v` in the set. This variable keeps track of the length of the current consecutive sequence.

*   **`if(!val.contains(v-1))`**:  This is the key optimization. We only attempt to extend the sequence if `v` is the *smallest* number in that sequence.  If `v-1` is present in the `val` HashSet, then `v` is part of a longer sequence that will be found when the algorithm reaches `v-1`.  This check takes O(1) on average due to the HashSet.

*   **`for(int i = v+1 ; ; i++)`**:  If `v` is the start of a sequence, this inner loop iterates starting from `v+1` to find consecutive numbers. It continues indefinitely until a number is *not* found in the `val` HashSet.

*   **`if(val.contains(i)) cc++; else break;`**: Inside the inner loop, it checks if the current number `i` exists in the `val` HashSet. If it does, the current consecutive sequence length `cc` is incremented. If it doesn't, the inner loop breaks because the sequence is finished.

*   **`maxc = Math.max(maxc , cc);`**: After the inner loop finishes (either by finding the end of a sequence or by not starting one at all), the `maxc` variable is updated to store the maximum of the current `maxc` and the length of the current sequence `cc`.

*   **`return maxc;`**: After iterating through all the numbers in the `val` HashSet, the function returns the `maxc`, which represents the length of the longest consecutive sequence found in the input array.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the number of elements in the input array `nums`.

    *   Adding elements to the HashSet takes O(n) time.
    *   The outer loop iterates through the elements of the HashSet, which is at most 'n' times.
    *   The inner loop, although nested, does *not* run for every element. It only runs when a sequence begins at a particular element. Each element is visited at most once during the extension of a sequence.
    *   Thus, the *amortized* time complexity of the nested loops is O(n).

*   **Space Complexity:** O(n), where n is the number of elements in the input array `nums`. This is because the `HashSet` `val` can potentially store all the elements of the input array. In the worst case, where all the numbers in the array are distinct, the HashSet will store all 'n' numbers.
