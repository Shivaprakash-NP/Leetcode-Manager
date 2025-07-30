```markdown
## Boats to Save People - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to find the minimum number of boats required to save all the people, given an array `people` where `people[i]` is the weight of the i-th person, and a `limit` representing the maximum weight capacity of each boat. Each boat can carry at most two people.

In simpler terms, we want to pair people such that their combined weight doesn't exceed the boat's limit. If a person's weight exceeds the limit, they must take a boat alone.  The goal is to minimize the total number of boats used.

### 2. Approach / Intuition:

The core idea behind this solution is a **greedy approach** using the **two-pointer technique**.

*   **Sorting:** We first sort the `people` array in ascending order. This is crucial for the greedy strategy to work efficiently.

*   **Two Pointers:** We maintain two pointers, `l` and `r`, pointing to the lightest and heaviest person, respectively.

*   **Greedy Pairing:** We iterate while `l <= r`. In each iteration, we check if the lightest and heaviest person can share a boat (i.e., `people[l] + people[r] <= limit`).
    *   If they can share a boat, we increment `l` (move to the next lightest person).
    *   Regardless of whether they can share a boat or not, we decrement `r` (move to the next heaviest person).
    *   In both cases, we increment the boat count `ans`. This is because the current heaviest person (`people[r]`) will definitely take a boat, either alone or with the lightest person.

**Why this works:**

Sorting allows us to consider pairing the lightest person with the heaviest person. If the lightest person and the heaviest person can be together in one boat, it means that the heaviest person can be paired. By doing this, we are potentially saving a boat (instead of having the heaviest person take a boat alone). On the other hand, if the lightest and heaviest person can't fit into a boat together, it means the heaviest person cannot pair with any other persons so the heaviest person takes a boat alone.

The greedy approach ensures that we always try to maximize boat utilization by pairing people whenever possible. By moving the pointers inwards, we systematically consider all possible pairs while minimizing the number of boats required.

### 3. Data Structures and Algorithms:

*   **Data Structure:** The primary data structure is an integer array `people`.
*   **Algorithms:**
    *   **Sorting:** `Arrays.sort()` is used to sort the `people` array. This uses a Dual-Pivot Quicksort algorithm in Java, giving an average time complexity of O(n log n).
    *   **Two Pointers:** The `l` and `r` pointers are used to traverse the sorted array from both ends.
    *   **Greedy Algorithm:** The pairing logic embodies a greedy approach, aiming to make the locally optimal choice (pairing when possible) to achieve the global optimum (minimum number of boats).

### 4. Code Walkthrough:

```java
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people); // Sort the array in ascending order.

        int l = 0; // Pointer to the lightest person.
        int r = people.length-1; // Pointer to the heaviest person.
        int ans = 0; // Counter for the number of boats.

        while(l<=r) { // Iterate as long as there are people left to save.
            if(people[l] + people[r] <= limit) l++; // If the lightest and heaviest can share a boat, move the left pointer.
            r--; // Move the right pointer to the next heaviest person.
            ans++; // Increment the boat count (current right person takes a boat).
        }

        return ans; // Return the total number of boats needed.
    }
}
```

1.  **`Arrays.sort(people);`**:  The input array `people` is sorted in ascending order using `Arrays.sort()`.  This is essential for the two-pointer strategy to function correctly.

2.  **`int l = 0;`**: Initializes the left pointer `l` to the beginning of the sorted array (index 0), representing the lightest person.

3.  **`int r = people.length - 1;`**: Initializes the right pointer `r` to the end of the sorted array (index `people.length - 1`), representing the heaviest person.

4.  **`int ans = 0;`**: Initializes the `ans` variable to 0. This variable will keep track of the number of boats used.

5.  **`while (l <= r)`**: The main loop continues as long as the left pointer is less than or equal to the right pointer. This ensures that all people are considered.

6.  **`if (people[l] + people[r] <= limit) l++;`**: Inside the loop, it checks if the sum of the weights of the lightest (`people[l]`) and heaviest (`people[r]`) person is less than or equal to the boat's weight limit. If they can fit in one boat, the left pointer `l` is incremented, effectively pairing them and moving on to the next lightest person.

7.  **`r--;`**:  Regardless of whether the lightest and heaviest person can share a boat or not, the right pointer `r` is decremented. This is because the current heaviest person (`people[r]`) needs to be saved, either alone or with another person.

8.  **`ans++;`**:  The boat count `ans` is incremented in each iteration of the loop. This is because the right pointer will always need a boat.

9.  **`return ans;`**: Finally, the function returns the total number of boats required, which is stored in the `ans` variable.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   Sorting the array takes O(n log n) time.
    *   The two-pointer loop iterates at most n times, taking O(n) time.
    *   Therefore, the overall time complexity is **O(n log n)**, dominated by the sorting step.

*   **Space Complexity:**
    *   The space complexity is **O(1)**, as the algorithm uses only a constant amount of extra space for the pointers and the boat count. The sorting is done in-place, meaning it doesn't use additional space proportional to the input size (though some sorting algorithms could use O(log n) space for the call stack).
```