## LeetCode Problem: Count Largest Group - Explanation

### 1. Problem Understanding:

The problem asks us to group the numbers from 1 to `n` based on the sum of their digits. We need to find the size of the largest group (the group with the most numbers) and then return the number of groups that have that largest size.

Example:

If n = 13, the groups are:
- Group 1: [1, 10] (1 + 0 = 1)
- Group 2: [2, 11] (1 + 1 = 2)
- Group 3: [3, 12] (1 + 2 = 3)
- Group 4: [4, 13] (1 + 3 = 4)
- Group 5: [5]
- Group 6: [6]
- Group 7: [7]
- Group 8: [8]
- Group 9: [9]

The largest group size is 2 (groups 1,2,3, and 4). There are 4 groups of size 2. So the answer is 4.

### 2. Approach / Intuition:

The core idea is to calculate the digit sum for each number from 1 to `n`. Then, we use a hash map to store the count of numbers for each digit sum.  After processing all numbers, we find the maximum count in the hash map. Finally, we iterate through the hash map again and count how many groups have the maximum count.

Why this approach?

- **Grouping by Digit Sum:** The problem explicitly states the grouping criterion. Calculating and using the digit sum is a direct implementation of this requirement.
- **Hash Map for Counting:** A hash map (`HashMap`) is perfect for efficiently counting the occurrences of different digit sums. It allows us to store and retrieve counts in O(1) average time.
- **Finding the Maximum Count:** We need to identify the maximum group size.  Iterating through the hash map to find the maximum count is a straightforward way to achieve this.
- **Counting Largest Groups:** Once we have the maximum count, another iteration allows us to count how many groups have that specific count.

### 3. Data Structures and Algorithms:

- **Data Structure:**
    - `HashMap`: Used to store the frequency of each digit sum. The keys are the digit sums, and the values are the counts of numbers that produce that sum.
- **Algorithms:**
    - **Iteration:**  Looping through numbers from 1 to `n`.
    - **Digit Sum Calculation:**  A `while` loop is used to extract digits and calculate their sum.
    - **Frequency Counting:** Using `HashMap` to keep track of counts of digit sums.
    - **Finding Maximum Value:** Iterating through the `HashMap`'s values to find the maximum frequency.

### 4. Code Walkthrough:

```java
class Solution {
    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> val = new HashMap<>(); // HashMap to store digit sum frequencies
        int ans = 0; // Counter for the number of largest groups
        for(int i = 1 ; i  <= n ; i++) // Iterate from 1 to n
        {
            int v = i; // Temporary variable to calculate digit sum
            int sum = 0; // Variable to store the digit sum
            while(v > 0) // Calculate digit sum
            {
                sum+=v%10; // Add the last digit to the sum
                v/=10; // Remove the last digit
            }
            val.put(sum , val.getOrDefault(sum , 0)+1); // Update the frequency of the digit sum in the HashMap
        }
        int max = 0; // Variable to store the maximum frequency
        for(int v : val.keySet()) // Iterate through the digit sums in the HashMap
        {
            max = Math.max(max , val.get(v)); // Find the maximum frequency
        }
        for(int v : val.keySet()) // Iterate through the digit sums again
        {
            if(val.get(v) == max) ans++; // If the frequency is equal to the maximum, increment the counter
        }
        return ans; // Return the number of largest groups
    }
}
```

**Detailed explanation:**

1.  **`HashMap<Integer, Integer> val = new HashMap<>();`**:  Initializes a `HashMap` called `val`.  This map will store the sum of digits as the key and the number of integers that have that digit sum as the value (frequency).

2.  **`int ans = 0;`**:  Initializes an integer variable `ans` to 0. This variable will store the count of groups with the largest size.

3.  **`for(int i = 1 ; i  <= n ; i++)`**:  This loop iterates through all the numbers from 1 to `n`.

4.  **`int v = i;`**:  Creates a temporary variable `v` to hold the value of `i`. This is to avoid modifying the original `i` during digit sum calculation.

5.  **`int sum = 0;`**: Initializes a variable `sum` to 0. This variable will store the sum of digits for the current number.

6.  **`while(v > 0)`**:  This loop calculates the sum of the digits of the number `v`.

7.  **`sum+=v%10;`**:  Gets the last digit of `v` using the modulo operator (`%`) and adds it to `sum`.

8.  **`v/=10;`**:  Removes the last digit of `v` by integer division (`/`).

9.  **`val.put(sum , val.getOrDefault(sum , 0)+1);`**: Updates the count of the digit sum in the `HashMap`.  `val.getOrDefault(sum, 0)` retrieves the current count of `sum`. If `sum` is not already a key in the map, it returns 0 (default value). Then, it adds 1 to the count and puts the updated count back into the map.

10. **`int max = 0;`**:  Initializes an integer variable `max` to 0. This variable will store the maximum frequency of any digit sum.

11. **`for(int v : val.keySet())`**:  This loop iterates through all the digit sums (keys) in the `HashMap`.

12. **`max = Math.max(max , val.get(v));`**:  Compares the current `max` value with the frequency of the current digit sum (`val.get(v)`) and updates `max` if the frequency is larger.

13. **`for(int v : val.keySet())`**:  This loop iterates through all the digit sums (keys) in the `HashMap` again.

14. **`if(val.get(v) == max) ans++;`**:  Checks if the frequency of the current digit sum (`val.get(v)`) is equal to the maximum frequency (`max`). If it is, it increments the counter `ans`.

15. **`return ans;`**: Returns the final count `ans`, which represents the number of groups with the largest size.

### 5. Time and Space Complexity:

- **Time Complexity:** O(N * log10(N)).
    - The outer loop iterates from 1 to `n`: O(N).
    - The inner `while` loop calculates the digit sum.  In the worst case (when `n` is a number with many digits like 99999), the number of iterations in the inner loop is proportional to the number of digits in `n`, which is roughly log10(N).
    - The other two loops iterate through the keys of the HashMap which are at most N.
    - Therefore, the overall time complexity is dominated by O(N * log10(N)).

- **Space Complexity:** O(N).
    - The `HashMap` `val` stores the frequency of each digit sum. In the worst-case scenario, each number from 1 to `n` has a unique digit sum. Therefore, the space required to store the frequencies in the `HashMap` could be proportional to `n`, leading to O(N) space complexity. The distinct sums can be at most 9 * number of digits in n. Since the number of digits is log(n), the number of distinct sums is log(n). However, in the problem, each number has a unique digit sum. Therefore the space complexity is O(N).
