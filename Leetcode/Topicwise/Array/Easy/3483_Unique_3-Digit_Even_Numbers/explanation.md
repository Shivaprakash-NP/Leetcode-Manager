## LeetCode Problem: Unique 3-Digit Even Numbers - Explanation

### 1. Problem Understanding:

The problem asks us to find the number of unique 3-digit even numbers that can be formed using digits from a given integer array `digits`. Each digit in the array can be used only as many times as it appears in the array.  We need to return the total count of these unique numbers. For example, if `digits = [2,1,3,0,4,5,6]`, then we can form numbers like `102`, `124`, `346`, etc. but we cannot form numbers like `111` or `789` (because 1 and 7/8/9 don't exist in the input).
### 2. Approach / Intuition:

The core idea is to iterate through all possible 3-digit even numbers (from 100 to 998 with a step of 2) and check if each number can be formed using the provided digits. We use a hash map to keep track of the frequency of each digit in the input array. For each 3-digit even number, we extract its digits and check if they are available in sufficient quantities in the frequency map. If all three digits are present (with enough count), we increment a counter.  A brute-force approach to generating and validating is reasonable because the problem constraints limit the range of possible 3-digit numbers.

This approach is chosen due to its simplicity and clarity. Since the number of 3-digit even numbers is limited (450), a direct iteration and validation strategy is computationally feasible and easier to understand than more complex approaches like backtracking or combinations generation.

### 3. Data Structures and Algorithms:

*   **HashMap:** Used to store the frequency of each digit in the input array. This allows for efficient lookup and updating of digit counts.
*   **Iteration:**  The solution iterates through all possible 3-digit even numbers and then iterates through the digits of each of those numbers.

### 4. Code Walkthrough:

```java
class Solution {
    public int totalNumbers(int[] digits) {
        HashMap<Integer , Integer> val = new HashMap<>(); // Stores the frequency of each digit in the input array.
        int c = 0; // Counter for the number of unique 3-digit even numbers found.

        // Count the occurrences of each digit in the input array.
        for(int v : digits) val.put(v , val.getOrDefault(v , 0)+1);

        // Iterate through all possible 3-digit even numbers (100 to 998, incrementing by 2).
        for(int i = 100 ; i<999 ; i+=2) {
            int d1 = i%10;          // Extract the units digit.
            int d2 = i%100 / 10;     // Extract the tens digit.
            int d3 = i%1000 / 100;   // Extract the hundreds digit.

            HashMap<Integer , Integer> tem = new HashMap<>(val); // Create a copy of the digit frequency map.
                                                               // We use a copy because we don't want to modify the original map.

            // Check if all three digits (d1, d2, d3) are present in the 'tem' map with sufficient counts.
            if(tem.get(d1) != null){ // Check if units digit is present
                tem.put(d1 , tem.get(d1)-1); // Reduce the count of the units digit.
                if(tem.get(d1) == 0) tem.remove(d1); // if count becomes 0, remove the key
                if(tem.get(d2) != null) { // Check if tens digit is present
                    tem.put(d2 , tem.get(d2)-1); // Reduce the count of the tens digit.
                    if(tem.get(d2) == 0) tem.remove(d2); // if count becomes 0, remove the key
                    if(tem.get(d3) != null) { // Check if hundreds digit is present
                        c++; // If all three digits are present, increment the counter.
                    }
                }
            }
        }
        return c; // Return the total count of unique 3-digit even numbers found.
    }
}
```

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N + 450 * 3) which simplifies to O(N), where N is the length of the `digits` array. The initial loop to populate the HashMap takes O(N). The outer loop iterates a maximum of 450 times (all possible 3-digit even numbers) and the inner checks are at most 3 hashmap lookups and updates which are O(1). Therefore, the dominant factor is N.

*   **Space Complexity:** O(1). The HashMap `val` stores a maximum of 10 entries (digits 0-9). The copy `tem` also has a maximum of 10 entries. Therefore, the space complexity is constant and independent of the input size.
