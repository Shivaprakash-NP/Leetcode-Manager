```markdown
## Exam Scores Tracker: Detailed Explanation

### 1. Problem Understanding:

The problem requires us to design a data structure that efficiently tracks exam scores recorded at different times and allows us to query the total score within a specific time range (startTime, endTime). We need to implement two methods: `record(time, score)` to store a new score at a given time, and `totalScore(startTime, endTime)` to return the sum of scores recorded between `startTime` and `endTime` (inclusive of scores recorded *at* those exact times).

### 2. Approach / Intuition:

The core idea behind this solution is to use a combination of a list to store the timestamps and a map (or prefix sum array, conceptually) to store the cumulative sum of scores up to each recorded time.

*   **Recording:** Each new score is recorded along with its timestamp. The cumulative sum is updated by adding the new score to the previous cumulative sum.
*   **Querying:** To find the total score between `startTime` and `endTime`, we first find the indices in the `list` (of times) corresponding to `startTime` and `endTime` using binary search. Then, we subtract the cumulative sum *before* `startTime` from the cumulative sum at `endTime` to obtain the total score within the desired range.

**Why this approach?**

*   **Efficient Querying:** Binary search provides logarithmic time complexity for finding the indices of `startTime` and `endTime`. This is significantly faster than a linear search, especially when dealing with a large number of records.
*   **Constant Time Cumulative Sum Calculation:**  Storing cumulative sums allows us to calculate the sum within a range with just one subtraction after we have the start and end indices.

### 3. Data Structures and Algorithms:

*   **`List<Integer> list`:**  An `ArrayList` is used to store the timestamps (`time` values) of each recorded score.  It's crucial for the binary search to find the correct indices.
*   **`Map<Integer, Long> map`:** A `HashMap` is used to store the cumulative sum of scores. The key is the index (representing the order in which the score was recorded) and the value is the cumulative score up to that index.  We use `Long` instead of `Integer` to prevent potential integer overflow when the scores are large or when summing a large number of scores.
*   **`int ind`:** A simple integer variable to keep track of the number of scores recorded.
*   **Algorithms:**
    *   **Binary Search:** The `Collections.binarySearch()` method is used to find the indices of `startTime` and `endTime` in the `list` of times.

### 4. Code Walkthrough:

```java
class ExamTracker {

    List<Integer> list;
    Map<Integer, Long> map;
    int ind;

    public ExamTracker() {
        list = new ArrayList<>();
        map = new HashMap<>();
        ind = 0;
    }
```

*   **`ExamTracker()` Constructor:** This initializes the `list` and `map` and sets the index `ind` to 0.

```java
    public void record(int time, int score) {
        if(ind == 0) map.put(ind, (long)score);
        else map.put(ind, map.get(ind-1)+score);

        ind++;
        list.add(time);
    }
```

*   **`record(int time, int score)`:**
    *   This method records the `score` at the given `time`.
    *   `if(ind == 0) map.put(ind, (long)score);`: If it's the first score being recorded (index is 0), the cumulative sum at index 0 is simply the `score` itself.  We cast the `score` to a `long` to avoid potential integer overflow issues later on.
    *   `else map.put(ind, map.get(ind-1)+score);`: Otherwise, the cumulative sum at the current index (`ind`) is calculated by adding the current `score` to the cumulative sum at the previous index (`ind-1`).
    *   `ind++;`: The index `ind` is incremented to point to the next available position.
    *   `list.add(time);`: The `time` is added to the `list`.

```java
    public long totalScore(int startTime, int endTime) {
        int s = Collections.binarySearch(list, startTime);
        int e = Collections.binarySearch(list, endTime);

        if(s < 0) s = -s-1;
        if(e < 0) e = -e-2;

        return map.getOrDefault(e, 0L)-map.getOrDefault(s-1, 0L);
    }
}
```

*   **`totalScore(int startTime, int endTime)`:**
    *   `int s = Collections.binarySearch(list, startTime);`: Performs a binary search on the `list` to find the index of `startTime`. The result `s` can be either:
        *   The index of `startTime` if it exists in the `list`.
        *   A negative value `-(insertion point) - 1` if `startTime` is not found. The `insertion point` is the index where `startTime` should be inserted to maintain sorted order.
    *   `int e = Collections.binarySearch(list, endTime);`:  Same as above, but for `endTime`.
    *   `if(s < 0) s = -s-1;`: If `startTime` was not found, this line converts the negative result `s` to the correct insertion point. This gives us the index of the first time *greater than* or equal to `startTime`.
    *   `if(e < 0) e = -e-2;`:  If `endTime` was not found, this line converts the negative result `e` to the index of the element *just before* the insertion point.  This gives us the index of the last time *less than* or equal to `endTime`.
    *   `return map.getOrDefault(e, 0L)-map.getOrDefault(s-1, 0L);`: This line calculates the total score.
        *   `map.getOrDefault(e, 0L)`: Retrieves the cumulative sum up to index `e` (the last score within the `endTime`). If `e` is out of bounds, it returns 0.
        *   `map.getOrDefault(s-1, 0L)`: Retrieves the cumulative sum up to index `s-1` (the score immediately *before* the `startTime`). If `s-1` is out of bounds, it returns 0.  Subtracting this from the previous cumulative sum effectively calculates the sum of scores between `startTime` and `endTime`.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   `record(time, score)`: O(1) - Adding to an ArrayList and HashMap takes constant time on average.
    *   `totalScore(startTime, endTime)`: O(log n) - Dominated by the binary search on the `list`, which takes logarithmic time.  The rest of the operations (map lookups and subtraction) are O(1).
*   **Space Complexity:** O(n) - The `list` and `map` both store up to n elements, where n is the number of scores recorded. Therefore the space complexity grows linearly with the number of records.
