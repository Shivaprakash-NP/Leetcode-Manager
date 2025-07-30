## Majority Element II - Detailed Explanation

### 1. Problem Understanding

The "Majority Element II" problem asks us to find all elements in an integer array `nums` that appear more than `n / 3` times, where `n` is the length of the array. We need to return these elements in a list. The problem statement guarantees that there can be at most two such elements.

### 2. Approach / Intuition

The solution uses a clever algorithm called **Boyer-Moore Majority Vote Algorithm**, which is typically used for finding the majority element appearing more than `n / 2` times. However, with a slight modification, it can be extended to handle the `n / 3` case.

The core idea is that there can be at most two elements that appear more than `n / 3` times in an array.  Think of it this way: if there were three or more, their combined occurrences would be greater than `3 * (n / 3) = n`, which is impossible.

The approach works as follows:

1.  **Initialization:** We maintain two candidate elements (`e1` and `e2`) and their corresponding counts (`c1` and `c2`). We initialize `e1` and `e2` to `Integer.MIN_VALUE` and `c1` and `c2` to 0.

2.  **First Pass (Finding Potential Candidates):**  We iterate through the array. For each element `v`:
    *   If `v` matches `e1`, we increment `c1`.
    *   If `v` matches `e2`, we increment `c2`.
    *   If `v` is different from both `e1` and `e2`:
        *   If `c1` is 0, we set `e1` to `v` and `c1` to 1 (we found a new potential candidate).
        *   Else if `c2` is 0, we set `e2` to `v` and `c2` to 1 (we found a new potential candidate).
        *   Otherwise, we decrement both `c1` and `c2`.  This step essentially cancels out one occurrence of `e1`, one occurrence of `e2`, and one occurrence of `v`. This is the crucial part of the Boyer-Moore algorithm that allows us to find the majority element(s) efficiently.

3.  **Second Pass (Verification):** After the first pass, `e1` and `e2` are *potential* candidates for majority elements. We need to verify if they actually appear more than `n / 3` times. We reset `c1` and `c2` to 0 and iterate through the array again. We count the actual occurrences of `e1` and `e2`.

4.  **Result:** Finally, we check if `c1` is greater than `n / 3` and if `c2` is greater than `n / 3`. If they are, we add the corresponding candidate (`e1` or `e2`) to the result list.

**Why this approach?**

The Boyer-Moore algorithm is efficient because it does not require extra space to store the frequency of each element (like using a HashMap). It operates in linear time, making it a suitable solution for this problem.

### 3. Data Structures and Algorithms

*   **Data Structures:**
    *   `int[]`: The input array.
    *   `List<Integer>` (specifically `ArrayList<Integer>`): Used to store the result, which is the list of majority elements.
*   **Algorithms:**
    *   **Boyer-Moore Majority Vote Algorithm (modified):** The core algorithm for finding the potential majority elements.

### 4. Code Walkthrough

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int t = nums.length/3; // Calculate the threshold (n/3)

        int c1 = 0;  // Counter for candidate element e1
        int c2 = 0;  // Counter for candidate element e2

        int e1 = Integer.MIN_VALUE; // Candidate element 1
        int e2 = Integer.MIN_VALUE; // Candidate element 2

        // First pass: Find potential candidates
        for (int v : nums) 
        {
            if (v == e1) c1++; // Increment count if v matches e1
            else if (v == e2) c2++; // Increment count if v matches e2
            else if (c1 == 0)  // If e1 has no count, assign v to e1
            {
                e1 = v;
                c1 = 1;
            }
            else if (c2 == 0) // If e2 has no count, assign v to e2
            {
                e2 = v;
                c2 = 1;
            }
            else  // If v doesn't match e1 or e2, and both have counts, decrement both counts
            {
                c1--;
                c2--;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>(); // Initialize the result list

        c1 = 0; // Reset counter for e1
        c2 = 0; // Reset counter for e2

        // Second pass: Verify the candidates
        for(int v : nums)
        {
            if(v==e1) c1++; // Count actual occurrences of e1
            if(v==e2) c2++; // Count actual occurrences of e2
        }

        // Add candidates to the result if they appear more than n/3 times
        if(c1>t) ans.add(e1);
        if(c2>t) ans.add(e2);

        return ans; // Return the list of majority elements
    }
}
```

**Line-by-line explanation:**

1.  `int t = nums.length/3;`:  Calculates the threshold `n/3`. Any element appearing more than this many times is considered a majority element.
2.  `int c1 = 0; int c2 = 0;`: Initializes the counts for the two candidate elements.
3.  `int e1 = Integer.MIN_VALUE; int e2 = Integer.MIN_VALUE;`: Initializes the candidate elements.  Using `Integer.MIN_VALUE` avoids accidental matches with valid numbers in the input.
4.  `for (int v : nums) { ... }`:  The first loop iterates through the input array `nums`.
5.  `if (v == e1) c1++; else if (v == e2) c2++;`: If the current element `v` matches either of the candidate elements, its count is incremented.
6.  `else if (c1 == 0) { e1 = v; c1 = 1; }`: If `v` doesn't match `e1` or `e2` and `c1` is 0, then `e1` is updated to `v` and `c1` is set to 1. This indicates that `v` is a new potential candidate.
7.  `else if (c2 == 0) { e2 = v; c2 = 1; }`: Similarly, if `v` doesn't match `e1` or `e2` and `c2` is 0, then `e2` is updated to `v` and `c2` is set to 1.
8.  `else { c1--; c2--; }`: If `v` doesn't match `e1` or `e2`, and both `c1` and `c2` are greater than 0, then both `c1` and `c2` are decremented. This is the core logic of the Boyer-Moore algorithm.
9.  `ArrayList<Integer> ans = new ArrayList<>();`: Initializes an `ArrayList` to store the majority elements.
10. `c1 = 0; c2 = 0;`: Resets the counts to 0 before the verification phase.
11. `for(int v : nums) { ... }`: The second loop iterates through the array to count the actual occurrences of `e1` and `e2`.
12. `if(v==e1) c1++; if(v==e2) c2++;`: Updates the counts if the element is equal to `e1` or `e2`.
13. `if(c1>t) ans.add(e1); if(c2>t) ans.add(e2);`:  If the count of `e1` or `e2` is greater than `t` (n/3), then it's added to the result list. Note that it is possible that `e1` and `e2` have the same value. In that case the same value will only be added once, because the if statements prevent duplicates from being added unnecessarily.
14. `return ans;`: Returns the list of majority elements.

### 5. Time and Space Complexity

*   **Time Complexity:** O(n), where n is the length of the input array `nums`. The algorithm iterates through the array twice.
*   **Space Complexity:** O(1). The algorithm uses a constant amount of extra space to store the candidate elements and their counts, regardless of the size of the input array. The output list's space is not considered extra space.
