## Minimum Deletions for At Most K Distinct Characters

Here's a detailed explanation of the Java code provided for the LeetCode problem "Minimum Deletions for At Most K Distinct Characters":

### 1. Problem Understanding:

The problem asks us to find the minimum number of characters we need to delete from a given string `s` so that the resulting string contains at most `k` distinct characters.

### 2. Approach / Intuition:

The core idea is to count the frequency of each character in the string. Then, if the number of distinct characters is greater than `k`, we need to delete some characters to reduce the number of distinct characters to `k`. The key insight is that we should delete the least frequent characters first. This is because deleting a least frequent character removes a distinct character with the minimum impact on the total number of deletions.

Specifically, the algorithm does the following:

1.  Counts the frequencies of all characters in the string.
2.  Calculates the number of distinct characters we need to remove (`rem = val.size() - k`).
3.  If `rem` is zero or negative (meaning the number of distinct characters is already less than or equal to `k`), then we don't need to delete anything, so return 0.
4.  Otherwise, creates a list of character frequencies from the `HashMap` values.
5.  Sorts the frequencies in ascending order.
6.  Iterates through the `rem` smallest frequencies and sums them up. This sum represents the minimum number of characters that need to be deleted.

This approach is chosen because it greedily removes the least frequent characters. Sorting character counts enables this.

### 3. Data Structures and Algorithms:

*   **`HashMap`:** Used to store the frequency of each character in the string. This allows for efficient lookups and updates of character counts (O(1) on average).
*   **`ArrayList`:** Used to store the frequencies of the characters. This is necessary for sorting.
*   **`Collections.sort()`:** Used to sort the frequencies in ascending order. This is crucial for identifying the least frequent characters.
*   **Greedy Algorithm:**  The overall approach is a greedy algorithm because at each step, it makes the locally optimal choice (deleting the least frequent character) with the hope of finding a global optimum.

### 4. Code Walkthrough:

```java
class Solution {
    public int minDeletion(String s, int k) {
        HashMap<Character , Integer> val = new HashMap<>(); // HashMap to store character frequencies
        int ans = 0; // Initialize the answer (number of deletions) to 0
        for(char c : s.toCharArray()) val.put(c , val.getOrDefault(c , 0)+1); // Count character frequencies
        int rem = val.size() - k; // Calculate the number of distinct characters we need to remove
        if(rem <= 0) return ans; // If no deletions are needed, return 0
        List<Integer> min = new ArrayList<>(val.values()); // Create a list of frequencies
        Collections.sort(min); // Sort the frequencies in ascending order
        for(int i = 0 ; i<rem ; i++) ans+=min.get(i); // Sum the 'rem' smallest frequencies
        return ans; // Return the minimum number of deletions
    }
}
```

1.  **`HashMap<Character, Integer> val = new HashMap<>();`**:  This line creates a HashMap called `val` to store the frequency of each character. The key is the character itself (type `Character`), and the value is its frequency (type `Integer`).

2.  **`int ans = 0;`**: This initializes an integer variable `ans` to 0. This variable will store the minimum number of deletions needed.

3.  **`for(char c : s.toCharArray()) val.put(c , val.getOrDefault(c , 0)+1);`**: This loop iterates through each character `c` in the string `s`. For each character, it updates its frequency in the `val` HashMap.  `val.getOrDefault(c, 0)` retrieves the current frequency of character `c`. If `c` is not already in the map, it defaults to 0.  Then, 1 is added to the frequency, and the updated frequency is stored back into the map using `val.put(c, ...)`.

4.  **`int rem = val.size() - k;`**: This line calculates the number of distinct characters that need to be removed. `val.size()` gives the number of distinct characters in the string, and `k` is the maximum allowed distinct characters. The difference `rem` represents the number of excess distinct characters.

5.  **`if(rem <= 0) return ans;`**: If `rem` is less than or equal to 0, it means that the number of distinct characters is already less than or equal to `k`. In this case, no deletions are needed, so the function returns `ans`, which is 0.

6.  **`List<Integer> min = new ArrayList<>(val.values());`**: This line creates an `ArrayList` called `min` and populates it with the values (frequencies) from the `val` HashMap.  `val.values()` returns a Collection of the frequencies, and the `ArrayList` constructor takes this Collection as input.

7.  **`Collections.sort(min);`**: This line sorts the `min` list in ascending order.  After this line, the `min` list contains the frequencies of the characters sorted from smallest to largest.

8.  **`for(int i = 0 ; i<rem ; i++) ans+=min.get(i);`**: This loop iterates `rem` times (the number of distinct characters to remove). In each iteration, it adds the smallest frequency (from the `min` list) to the `ans` variable.  `min.get(i)` retrieves the i-th smallest frequency.

9.  **`return ans;`**: Finally, the function returns the `ans`, which represents the minimum number of characters that need to be deleted to have at most `k` distinct characters.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   Creating the `HashMap`: O(n), where n is the length of the string `s`.
    *   Calculating `rem`: O(1).
    *   Creating the `ArrayList`: O(d), where d is the number of distinct characters.
    *   Sorting the `ArrayList`: O(d log d), where d is the number of distinct characters.
    *   Calculating the sum: O(d), where d is the number of distinct characters.
    *   Overall: O(n + d log d), where n is the length of the string and d is the number of distinct characters. In the worst case, d can be equal to n, in which case the time complexity becomes O(n log n).

*   **Space Complexity:**
    *   `HashMap`: O(d), where d is the number of distinct characters.
    *   `ArrayList`: O(d), where d is the number of distinct characters.
    *   Overall: O(d), where d is the number of distinct characters. In the worst case, d can be equal to n, in which case the space complexity becomes O(n).
