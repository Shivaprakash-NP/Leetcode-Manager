## LeetCode Problem: Find The Least Frequent Digit (Java Solution Explained)

**1. Problem Understanding:**

The problem asks us to find the digit that appears least frequently in a given integer `n`. If multiple digits have the same minimum frequency, we should return the smallest of those digits.


**2. Approach / Intuition:**

The solution uses a frequency counting approach. We iterate through the digits of the input integer `n`, counting the occurrences of each digit using a `HashMap`.  After counting, we iterate through the `HashMap` to find the digit with the minimum frequency.  If multiple digits share the minimum frequency, we select the smallest among them. This approach is efficient because it allows for direct access to the frequency of each digit using the digit as the key in the HashMap.

**3. Data Structures and Algorithms:**

* **Data Structures:**  A `HashMap` (or `Map`) is used to store the frequency of each digit. The keys are the digits (0-9), and the values are their corresponding frequencies.
* **Algorithms:** The core algorithm is a frequency counting algorithm combined with a linear scan to find the minimum frequency and the smallest digit with that frequency.


**4. Code Walkthrough:**

```java
class Solution {
    public int getLeastFrequentDigit(int n) {
        Map<Integer, Integer> map = new HashMap<>(); // HashMap to store digit frequencies
        int min = Integer.MAX_VALUE; // Initialize minimum frequency to the maximum possible integer value
        int ans = -1; // Initialize the result to -1 (default value if n is 0)

        while(n != 0) { // Iterate through the digits of n
            map.put(n%10, map.getOrDefault(n%10, 0)+1); // Extract the last digit (n%10) and update its frequency in the map.
            n/=10; // Remove the last digit from n
        }

        for(int v : map.keySet()) { // Iterate through the keys (digits) of the HashMap
            if(map.get(v) == min) { // If the frequency of the current digit is equal to the minimum frequency
                ans = Math.min(ans, v); // Choose the smaller digit between the current 'ans' and the current digit 'v'.
            } else if(map.get(v) < min) { //If the frequency of the current digit is less than the minimum frequency
                min = map.get(v); // Update the minimum frequency
                ans = v; // Update 'ans' to the current digit
            }
        }
        return ans; // Return the digit with the minimum frequency.
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N + K), where N is the number of digits in the input integer `n`, and K is the number of unique digits (at most 10). The `while` loop iterates N times, and the `for` loop iterates at most K times. Therefore, the overall time complexity is dominated by the number of digits in the input.

* **Space Complexity:** O(K), where K is the number of unique digits. In the worst case, all digits from 0 to 9 can be present, requiring space to store their frequencies.  The space used by the HashMap is directly proportional to the number of unique digits in the input integer.  This space usage is considered constant as K is at most 10.


**Improvements:**

The code can be slightly improved for readability by using a more descriptive variable name than `v` in the second loop (e.g., `digit`).  Also, handling the case where the input `n` is 0 explicitly could make it slightly more robust.  However, the current solution is already quite efficient and clear.
