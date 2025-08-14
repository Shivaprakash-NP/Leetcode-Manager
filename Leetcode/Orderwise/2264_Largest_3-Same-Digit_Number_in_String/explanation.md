## LeetCode: Largest 3-Same-Digit Number in String

**1. Problem Understanding:**

The problem asks us to find the largest number within a given string that consists of three consecutive identical digits.  For example, if the input string is "677712", the output should be "777". If no such number exists, the output should be an empty string.

**2. Approach / Intuition:**

The solution employs a straightforward and efficient brute-force approach.  It iterates through digits from 9 down to 0. For each digit, it checks if a substring consisting of three repetitions of that digit exists within the input string.  This approach is chosen because it's simple to implement and sufficiently fast for the problem's constraints (the input string is relatively short).  There's no need for more complex algorithms like dynamic programming or greedy approaches because the search space is limited.

**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is a `StringBuilder` to efficiently construct the three-digit substring. The input is a `String`.
* **Algorithms:** The core algorithm is a simple linear scan (iterating through digits) combined with string searching using the `contains()` method.


**4. Code Walkthrough:**

```java
class Solution {
    public String largestGoodInteger(String num) {
        for(int i = 9; i>=0; i--) { // Iterate through digits from 9 to 0
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(i);
            sb.append(i); // Create a string of three identical digits
            if(num.contains(sb.toString())) return sb.toString(); // Check if it exists in num
        }

        return ""; // Return empty string if no such number is found
    }
}
```

* **`for(int i = 9; i>=0; i--)`**: This loop iterates through digits from 9 down to 0.  Starting from 9 ensures that the largest possible 3-same-digit number is found first.

* **`StringBuilder sb = new StringBuilder();`**: A `StringBuilder` is created to efficiently build the string representing three consecutive identical digits.

* **`sb.append(i); sb.append(i); sb.append(i);`**:  The digit `i` is appended three times to the `StringBuilder`.  This creates the string "iii" (where 'i' represents the current digit).

* **`if(num.contains(sb.toString())) return sb.toString();`**: This line checks if the constructed string of three identical digits is present in the input string `num` using the `contains()` method. If it is, the function immediately returns that string.

* **`return "";`**: If the loop completes without finding any three-digit sequence of identical digits, an empty string is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(M*N), where N is the length of the input string `num` and M is the number of digits (10 in this case). The `contains()` method has a time complexity of O(M*N) in the worst case (where M is the length of the substring being searched for and N is the length of the string being searched). Since M is always 3 in this problem, the overall time complexity is dominated by the outer loop iterations and the calls to `contains()`, resulting in O(N) time complexity.


* **Space Complexity:** O(1). The space used is constant regardless of the input string's length.  The `StringBuilder` uses a constant amount of space, and the other variables are of constant size.  The `contains()` method in Java uses a relatively small constant amount of space for its internal operations.  Therefore, the space complexity is O(1).

The solution is highly efficient due to its linear time complexity and constant space usage.  More sophisticated approaches wouldn't offer significant performance improvements given the problem's constraints.
