```markdown
## 3Sum Problem Explanation

### 1. Problem Understanding:

The 3Sum problem asks us to find all unique triplets (sets of three numbers) in a given array `nums` that sum to zero.  The output should be a list of lists, where each inner list represents a unique triplet that sums to zero.

### 2. Approach / Intuition:

The primary challenge of this problem is efficiently finding all possible combinations of three numbers and avoiding duplicate triplets in the result. The chosen approach employs the following strategy:

1.  **Sorting:**  The array `nums` is first sorted. Sorting enables us to use a two-pointer approach effectively and easily skip duplicate numbers.

2.  **Two-Pointer Technique:** After sorting, we iterate through the array with a single pointer `i`. For each element `nums[i]`, we use two additional pointers, `j` and `k`, initialized to `i + 1` and `n - 1` respectively (where `n` is the length of `nums`). These two pointers move towards each other within the remaining portion of the array.  We calculate the sum of `nums[i]`, `nums[j]`, and `nums[k]`.

3.  **Sum Evaluation:**
    *   If the sum is less than zero, it means we need a larger number, so we increment `j` to move towards potentially larger values.
    *   If the sum is greater than zero, it means we need a smaller number, so we decrement `k` to move towards potentially smaller values.
    *   If the sum is equal to zero, we have found a valid triplet. We add this triplet to the result list and then move both `j` and `k` to potentially find more unique triplets.

4.  **Duplicate Handling:** Crucially, after finding a valid triplet, we skip over any duplicate numbers to ensure that we only include unique triplets in the result. We skip duplicates for both `j` and `k` before incrementing/decrementing them. Also, we skip duplicate numbers for the `i` pointer in the outer loop.

The approach is efficient because sorting allows us to use the two-pointer technique, which helps us to find triplets in O(n^2) time, which is significantly faster than brute-force O(n^3) solutions. Duplicate handling is also vital to fulfill the problem requirements.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `List<List<Integer>>`: Used to store the resulting list of triplets.  Specifically, `ArrayList` is used for its dynamic resizing capabilities.
*   **Algorithms:**
    *   **Sorting:** `Arrays.sort()` is used to sort the input array.  Java's `Arrays.sort()` uses a variant of quicksort, which has an average time complexity of O(n log n).
    *   **Two-Pointer Technique:** Used to efficiently find triplets that sum to zero after the array is sorted.

### 4. Code Walkthrough:

```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // Sort the input array. O(n log n)
        int n = nums.length;
        List<List<Integer>> val = new ArrayList<>(); // Initialize the result list

        for (int i = 0; i < n - 2; i++) 
        { 
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicate numbers for the first element of the triplet
            // if (nums[i] > 0) break; // Optimization: if the first number is positive, the sum will always be positive. This line is commented out.
            int j = i + 1, k = n - 1; // Initialize two pointers: j starts from i+1, k starts from the end of array
            while (j < k) 
            {
                int s = nums[i] + nums[j] + nums[k]; // Calculate the sum of the three numbers
                if (s < 0) 
                    j++; // If sum is less than zero, increment j to increase the sum
                else if (s > 0)
                    k--; // If sum is greater than zero, decrement k to decrease the sum
                else 
                { 
                    val.add(Arrays.asList(nums[i], nums[j], nums[k])); // Found a triplet that sums to zero
                    while (j < k && nums[j] == nums[j + 1]) j++; // Skip duplicate numbers for the second element
                    while (j < k && nums[k] == nums[k - 1]) k--; // Skip duplicate numbers for the third element
                    j++; // Move j to the next different number
                    k--; // Move k to the previous different number
                }
            }
        }
        return val; // Return the list of triplets
    }
}
```

*   **`Arrays.sort(nums)`:** Sorts the input array `nums` in ascending order. This is crucial for the two-pointer approach to work effectively.
*   **`int n = nums.length;`:** Gets the length of the array.
*   **`List<List<Integer>> val = new ArrayList<>();`:** Initializes an `ArrayList` called `val` to store the triplets that sum to zero.
*   **`for (int i = 0; i < n - 2; i++)`:** This loop iterates through the array, picking the first number of the triplet. The loop stops at `n - 2` because we need at least three numbers to form a triplet.
*   **`if (i > 0 && nums[i] == nums[i - 1]) continue;`:** This condition skips duplicate numbers for the first element of the triplet. If the current number is the same as the previous number, we skip it to avoid duplicate triplets.
*   **`int j = i + 1, k = n - 1;`:** Initializes two pointers: `j` starts from `i + 1` and `k` starts from the end of the array.
*   **`while (j < k)`:** This loop uses the two-pointer technique to find the other two numbers of the triplet.
*   **`int s = nums[i] + nums[j] + nums[k];`:** Calculates the sum of the three numbers.
*   **`if (s < 0) j++;`:** If the sum is less than zero, we need to increase the sum, so we increment `j`.
*   **`else if (s > 0) k--;`:** If the sum is greater than zero, we need to decrease the sum, so we decrement `k`.
*   **`else { ... }`:** If the sum is equal to zero, we have found a triplet that sums to zero.
    *   **`val.add(Arrays.asList(nums[i], nums[j], nums[k]));`:** Adds the triplet to the result list.  `Arrays.asList` creates a fixed-size list from the given elements.
    *   **`while (j < k && nums[j] == nums[j + 1]) j++;`:** Skips duplicate numbers for the second element of the triplet.
    *   **`while (j < k && nums[k] == nums[k - 1]) k--;`:** Skips duplicate numbers for the third element of the triplet.
    *   **`j++; k--;`:** Moves `j` and `k` to the next different numbers.
*   **`return val;`:** Returns the list of triplets.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n log n + n^2) = O(n^2)
    *   Sorting the array takes O(n log n) time.
    *   The outer loop iterates `n` times, and the inner two-pointer loop takes O(n) time in the worst case. Thus the nested loop takes O(n^2) time.
    *   Therefore, the overall time complexity is dominated by the nested loop, which is O(n^2).
*   **Space Complexity:** O(log n) to O(n)
    *   The space complexity is dominated by the sorting algorithm. In the case of quicksort (often used by `Arrays.sort()`), the space complexity is O(log n) in the average case and O(n) in the worst case due to recursion depth.
    *   The `val` list stores the triplets, and in the worst-case scenario (highly unlikely, where almost all triplets sum to zero and are unique), the number of triplets could approach O(n^2). However, since the problem statement generally assumes distinct sets, the average number of triplets is much smaller, making the space complexity largely dependent on the sorting algorithm's space usage. Ignoring the result list, the space complexity is O(1) if an in-place sorting algorithm is used. But `Arrays.sort()` in Java doesn't guarantee in-place sorting and typically takes O(log n) extra space due to its recursive nature.
```