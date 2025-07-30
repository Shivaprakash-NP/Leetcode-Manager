Okay, here's a breakdown of the Java code you provided for calculating the "mirror score" of a string, explained in detail.

## Find Mirror Score of a String

### 1. Problem Understanding

The problem asks us to calculate a "mirror score" for a given string `s`.  The core idea is that each character in the string has a "mirror" character, derived by a specific transformation (in this case, `mirror = (char)(219 - c)`).  The score is accumulated by finding pairs of characters, one being the mirror of the other, and calculating the distance between their positions in the string. Specifically, if a character `c` at index `i` has a mirror character `m` at index `j` where `j < i`, we add `i - j` to the score. The character `m` must have been seen before `c` in the string. If multiple `m` characters have been seen, we want to pair `c` with the *latest* `m`.
### 2. Approach / Intuition

The chosen approach utilizes a hash map (`val`) and priority queues to efficiently keep track of the indices where each character has appeared in the string.

Here's the intuition:

*   **Mirror Pairs:** The crux of the problem lies in efficiently finding mirror pairs.  We need to check if the *mirror* of the current character has already appeared in the string.
*   **Latest Occurrence:** When a mirror character has multiple occurrences, we want to pair the current character with the *latest* previous occurrence.  This is why a max-heap priority queue is employed. The latest occurrence of the mirror is always at the top of the priority queue.
*   **Hash Map for Efficient Lookup:** A hash map is used to quickly determine if the mirror character has already been encountered and to access its priority queue of indices.

Why this approach? This approach avoids repeatedly searching the string for mirror characters. By storing the indices in a priority queue, we can efficiently find the latest (largest) index of the mirror character. The hash map ensures O(1) average-case lookup for the existence of a mirror character and access to its priority queue.

### 3. Data Structures and Algorithms

*   **`HashMap<Character, PriorityQueue<Integer>> val`:**  This is the primary data structure. The keys are characters from the input string. The values are priority queues (max heaps) that store the indices at which the corresponding characters appear in the string.
*   **`PriorityQueue<Integer>`:**  A priority queue is used to store the indices of each character.  The `Collections.reverseOrder()` comparator ensures that it acts as a *max heap*, meaning the largest index (the latest occurrence) is always at the root.
*   **`putIfAbsent`:** Used in hashmap to prevent the priority queue of the character from being override.
*   **`poll`:** Used in the priority queue to get and remove the index of the most recent occurrence of the mirror character.

### 4. Code Walkthrough

```java
import java.util.*;
class Solution {
    public long calculateScore(String s) {
        Map<Character, PriorityQueue<Integer>> val = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char mirror = (char)(219 - c);
            if (val.containsKey(mirror) && !val.get(mirror).isEmpty()) {
                ans += i - val.get(mirror).poll();
            } else {
                val.putIfAbsent(c, new PriorityQueue<>(Collections.reverseOrder()));
                val.get(c).add(i);
            }
        }
        return ans;
    }
}
```

1.  **`Map<Character, PriorityQueue<Integer>> val = new HashMap<>();`**:  This line initializes a hash map named `val`.  The keys are characters from the input string `s`, and the values are priority queues (max heaps) that store the indices at which the corresponding characters appear.
2.  **`long ans = 0;`**: Initializes a `long` variable `ans` to 0. This variable will accumulate the mirror score. Using `long` is important to handle potentially large scores, especially with longer strings.
3.  **`for (int i = 0; i < s.length(); i++) { ... }`**:  This loop iterates through the input string `s`, character by character. `i` represents the current index.
4.  **`char c = s.charAt(i);`**: Gets the character at the current index `i` and stores it in the `c` variable.
5.  **`char mirror = (char)(219 - c);`**:  Calculates the "mirror" character of `c`. The formula `219 - c` performs a specific character transformation.
6.  **`if (val.containsKey(mirror) && !val.get(mirror).isEmpty()) { ... }`**: This `if` condition checks two things:
    *   `val.containsKey(mirror)`:  Checks if the hash map `val` contains the mirror character as a key. This means we've seen the mirror character before.
    *   `!val.get(mirror).isEmpty()`:  Checks if the priority queue associated with the mirror character is not empty. This is important because even if we've seen the mirror character before, all of its occurrences might have already been paired.
7.  **`ans += i - val.get(mirror).poll();`**: If both conditions in the `if` statement are true, this line calculates the distance between the current index `i` and the index of the latest occurrence of the mirror character, and adds it to the `ans`. The `val.get(mirror).poll()` removes the latest occurrence (largest index) from the mirror character's priority queue.
8.  **`else { ... }`**: If the `if` condition is false (i.e., we haven't seen the mirror character before or there are no available mirrors), this `else` block is executed.
9.  **`val.putIfAbsent(c, new PriorityQueue<>(Collections.reverseOrder()));`**: Ensures that a priority queue exists for the character `c` in the `val` hashmap. It only puts a new priority queue, ordered to be a max heap, if `c` is not already a key in the hash map.  This prevents overwriting existing priority queues.
10. **`val.get(c).add(i);`**:  Adds the current index `i` to the priority queue associated with the character `c`.  This records the occurrence of the character `c` at index `i`.
11. **`return ans;`**:  After iterating through the entire string, the function returns the accumulated mirror score `ans`.

### 5. Time and Space Complexity

*   **Time Complexity:** O(N log N), where N is the length of the string `s`.
    *   The loop iterates N times.
    *   `val.containsKey()` and `val.get()` are, on average, O(1) operations for a hash map.
    *   `PriorityQueue.poll()` and `PriorityQueue.add()` take O(log K) time, where K is the number of times a specific character appears in the string. In the worst case, K can be proportional to N, so it is O(log N).
    *   Therefore, the dominant operation inside the loop is the priority queue operations which gives an overall time complexity of O(N log N).

*   **Space Complexity:** O(N), where N is the length of the string `s`.
    *   The `HashMap val` can, in the worst case, store information about all unique characters in the string and their indices. If all characters are distinct, this can be proportional to N.
    *   Each `PriorityQueue` can, in the worst case, store all indices of a given character, contributing to a space complexity of O(N) in total.
