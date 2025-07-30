```markdown
## Number of Equivalent Domino Pairs - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to find the number of pairs of dominoes that are equivalent. Two dominoes are equivalent if they contain the same numbers, regardless of their order.  For example, `[1, 2]` is equivalent to `[2, 1]`. We are given an array of dominoes represented as a 2D integer array `dominoes`, where each `dominoes[i]` is `[a, b]`. We need to return the number of `i < j` such that `dominoes[i]` is equivalent to `dominoes[j]`.

### 2. Approach / Intuition:

The core idea is to use a hash map (or dictionary) to count the occurrences of each unique domino type. To ensure that dominoes like `[1, 2]` and `[2, 1]` are treated as the same, we normalize each domino by always storing the smaller number first and the larger number second. This way, we can use a list `[min(a,b),max(a,b)]` as a key in the hash map.

Then, for each domino, we:

1.  Normalize it to get its canonical representation (smaller number first).
2.  Check if we've seen this type of domino before. If so, the count in the hash map represents the number of dominoes already encountered that are equivalent to the current domino.  We add that count to our total count of equivalent pairs.
3.  Increment the count of that domino type in the hash map.

The reason for choosing this approach is its efficiency. By using a hash map, we can look up the number of previously seen equivalent dominoes in O(1) average time.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `HashMap` (or dictionary) is used to store the count of each unique domino type.  We use `ArrayList<Integer>` as the key for the `HashMap` to represent a domino pair.
*   **Algorithm:** Counting. We iterate through the dominoes, count the occurrences of each unique domino type, and calculate the number of pairs using the counts.

### 4. Code Walkthrough:

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        HashMap<ArrayList<Integer> , Integer> val = new HashMap<>();
        int c = 0;
        for(int i = 0 ; i<dominoes.length ; i++) {
            ArrayList<Integer> v = new ArrayList<>();
            v.add(Math.min(dominoes[i][0],dominoes[i][1]));
            v.add(Math.max(dominoes[i][0],dominoes[i][1]));
            c+=(val.get(v)==null)?0:val.get(v);
            val.put(v , val.getOrDefault(v , 0)+1);
        }
        return c;
    }
}
```

*   `HashMap<ArrayList<Integer>, Integer> val = new HashMap<>();`: This line declares a hash map called `val`.  The keys are `ArrayList<Integer>` representing the normalized domino pairs, and the values are `Integer` representing the number of times that domino pair has been encountered.

*   `int c = 0;`: Initializes a counter `c` to 0. This variable will store the total number of equivalent domino pairs.

*   `for(int i = 0 ; i<dominoes.length ; i++) { ... }`: This loop iterates through each domino in the input array `dominoes`.

*   `ArrayList<Integer> v = new ArrayList<>();`: Creates a new ArrayList to store the normalized domino.

*   `v.add(Math.min(dominoes[i][0],dominoes[i][1]));`: Adds the smaller of the two numbers in the current domino to the ArrayList.
*   `v.add(Math.max(dominoes[i][0],dominoes[i][1]));`: Adds the larger of the two numbers in the current domino to the ArrayList. The array list `v` represents the normalized form of `dominoes[i]`.

*   `c+=(val.get(v)==null)?0:val.get(v);`: This is the key line.
    *   `val.get(v)`: This attempts to retrieve the count of the domino `v` from the hash map.
    *   `(val.get(v)==null)?0:val.get(v)`: This is a ternary operator. If `val.get(v)` returns `null` (meaning the domino `v` has not been seen before), it evaluates to 0. Otherwise, it evaluates to the count associated with the domino `v` in the hash map.  So we are adding the number of previous occurences of the same domino to the running total `c`.
    *   `c += ...`: This adds the result of the ternary operator to the counter `c`.

*   `val.put(v , val.getOrDefault(v , 0)+1);`: This line updates the count of the domino `v` in the hash map.
    *   `val.getOrDefault(v, 0)`: This attempts to retrieve the count of the domino `v` from the hash map. If the domino `v` is not found, it returns a default value of 0.
    *   `... + 1`: This increments the count by 1.
    *   `val.put(v, ...)`: This puts the updated count back into the hash map, associating it with the domino `v`.

*   `return c;`: After processing all dominoes, the function returns the total number of equivalent domino pairs found.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the number of dominoes. The code iterates through the `dominoes` array once.  The operations inside the loop (min, max, hash map get, hash map put) take O(1) time on average.

*   **Space Complexity:** O(N) in the worst case, where N is the number of dominoes. The `HashMap` `val` can store up to N distinct domino types if all dominoes are unique.  The `ArrayList` used to store the domino pairs is bounded by a constant size (2), so it doesn't contribute to the overall space complexity.
