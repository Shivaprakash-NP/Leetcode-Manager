## LeetCode Problem: Compute Decimal Representation - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to take a non-negative integer `n` as input and return an array of integers representing the decimal representation of `n`. In simpler terms, we need to decompose the given number into its constituent powers of 10 and return them in reverse order. For example, if `n = 2030`, the output should be `[30, 2000]`.

### 2. Approach / Intuition:

The high-level strategy is to iteratively extract the last digit of the input number `n` and multiply it by the appropriate power of 10 based on its position. We store these individual powers of 10 in a list.  We then reverse the order of elements in the list and convert it to an integer array, which will be our final output.

The reason for choosing this approach is its simplicity and ease of implementation. We can directly extract the digits and their corresponding place values without needing complex mathematical operations. The process mirrors how we manually decompose a decimal number. The `ArrayList` is dynamically sized, making it suitable for storing an unknown number of components. The final reversal ensures the output is in the specified format. The skipping of digits with zero in the ones place ensures a simpler final result by omitting '0' values.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `ArrayList<Integer>`: Used to store the decimal components before converting them to an array.  Its dynamic size is beneficial as the number of components is not known beforehand.
    *   `int[]`: The final output array to store the decimal components in the desired order.
*   **Algorithms:**
    *   **Iteration (while loop):** To extract digits from the input number.
    *   **Modulo Operator (%):**  To get the last digit of a number.
    *   **Integer Division (/):** To remove the last digit of a number.
    *   **Exponentiation (Math.pow()):** To calculate the power of 10 based on the digit's position.
    *   **Reverse Order Traversal:** To ensure the returned array is properly formatted (e.g. input 2030 should return [30, 2000] and not [2000, 30]).

### 4. Code Walkthrough:

```java
class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> ans = new ArrayList<>(); // Initialize an ArrayList to store the results
        for(int i = 0; n!=0; i++) { // Loop until n becomes 0 (all digits processed)
            if(n%10 == 0) {
                n/=10;
                continue;
            }
            ans.add(n%10*(int)Math.pow(10, i)); // Extract last digit, multiply by 10^i, add to list
            n/=10; // Remove the last digit
        }

        int len = ans.size(); // Get the size of the ArrayList
        int[] res = new int[len]; // Initialize the result array
        for(int i = 0; i<len; i++) res[i] = ans.get(len-1-i); // Populate the result array in reverse order
        return res; // Return the result array
    }
}
```

*   **`List<Integer> ans = new ArrayList<>();`**: Creates an `ArrayList` called `ans` to store the calculated decimal components.  We use `ArrayList` because we don't know beforehand how many components will be generated.

*   **`for(int i = 0; n!=0; i++) { ... }`**: This `for` loop iterates as long as the input number `n` is not zero. The loop variable `i` represents the position of the digit (0 for the ones place, 1 for the tens place, and so on).

*   **`if(n%10 == 0) { n/=10; continue; }`**:  This conditional statement checks if the last digit of `n` is 0. If it is, we simply divide `n` by 10 to remove the last digit and continue to the next iteration. This optimization avoids adding unnecessary 0 values into the `ans` list.

*   **`ans.add(n%10*(int)Math.pow(10, i));`**: This line does the core calculation.
    *   `n % 10`:  Gets the last digit of `n`.
    *   `Math.pow(10, i)`: Calculates 10 raised to the power of `i`, representing the place value of the digit.
    *   `n % 10 * (int)Math.pow(10, i)`:  Multiplies the digit by its place value.
    *   `ans.add(...)`: Adds the result to the `ans` list. The `(int)` cast is required because `Math.pow` returns a `double`, and the list is defined to hold `Integer` objects.

*   **`n /= 10;`**:  Removes the last digit from `n` by performing integer division.

*   **`int len = ans.size();`**: Gets the number of elements in the `ans` list (the number of decimal components).

*   **`int[] res = new int[len];`**:  Creates an integer array `res` of the appropriate size to store the final result.

*   **`for(int i = 0; i<len; i++) res[i] = ans.get(len-1-i);`**:  This loop iterates through the `ans` list and populates the `res` array in *reverse* order. This is done because the components in `ans` were added in ascending order of their place values (ones, tens, hundreds, etc.), but the problem requires the output to be in descending order.  `ans.get(len - 1 - i)` retrieves elements from the `ans` list starting from the last element and working backwards.

*   **`return res;`**: Returns the `res` array containing the decimal components in the correct order.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(log<sub>10</sub> n), where n is the input number.  The `while` loop iterates once for each digit in `n`. The number of digits in `n` is approximately log<sub>10</sub> n.  The final loop to reverse the list and create the output array takes O(k) time, where k is the number of components, which is at most log<sub>10</sub> n.  Therefore, the overall time complexity is dominated by O(log<sub>10</sub> n).

*   **Space Complexity:** O(log<sub>10</sub> n). The `ArrayList` `ans` stores at most log<sub>10</sub> n elements (the number of digits in `n`). The integer array `res` also stores at most log<sub>10</sub> n elements. Therefore, the space complexity is O(log<sub>10</sub> n).
