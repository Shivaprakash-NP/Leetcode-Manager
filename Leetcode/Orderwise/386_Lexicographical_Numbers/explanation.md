## LeetCode: Lexicographical Numbers Explained

**1. Problem Understanding:**

The problem asks us to generate a list of integers from 1 to `n` (inclusive) in lexicographical order.  Lexicographical order is essentially dictionary order;  1, 10, 11, 12... come before 2, 20, 21 etc.  The output should be a list of these numbers sorted this way.

**2. Approach / Intuition:**

The solution employs an iterative approach that cleverly traverses the numbers in lexicographical order without explicit sorting.  Instead of sorting, it directly generates the sequence.  This is more efficient than sorting a list of numbers, especially for large values of `n`.

The core idea is to start at 1 and increment the current number (`c`).  If multiplying the current number by 10 remains within the range (1 to `n`), it means we can continue exploring numbers starting with the same prefix (e.g., from 1 to 19). If multiplying by 10 exceeds `n`, we need to find the next lexicographically larger number by repeatedly dividing `c` by 10 until we find a number that allows for a valid increment.

This approach is chosen because it avoids the O(n log n) complexity typically associated with sorting algorithms.  It directly constructs the lexicographically sorted sequence in a single pass.


**3. Data Structures and Algorithms:**

* **Data Structure:** `ArrayList<Integer>` is used to store the resulting lexicographically ordered list of integers.  An ArrayList is chosen for its dynamic resizing capabilities.
* **Algorithm:** The core algorithm is an iterative approach that cleverly generates the lexicographical sequence. It doesn't use any standard sorting algorithms.


**4. Code Walkthrough:**

```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> ans = new ArrayList<>(); // Initialize an ArrayList to store the result.
        int c = 1; // Start with the number 1.

        for(int i = 0 ; i < n ; i++) { // Iterate 'n' times to generate 'n' numbers.
            ans.add(c); // Add the current number 'c' to the list.

            if(c*10 <= n) c*=10; // If multiplying by 10 stays within the range, extend the prefix.
            else {
                while(c%10 == 9 || c+1 > n) c/=10; // Otherwise, find the next lexicographical number.
                //The condition `c%10 == 9` handles the case when we reach the end of a prefix (e.g., from 9 to 10).
                //The condition `c+1 > n` prevents us from going beyond the upper limit 'n'.

                c++; // Increment to get the next number.
            }
        }

        return ans; // Return the lexicographically ordered list.
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n). The code iterates through the loop `n` times, generating and adding each number to the list in a single pass.  The operations inside the loop are all constant time operations (multiplication, division, modulo, increment).

* **Space Complexity:** O(n). The `ArrayList` `ans` stores `n` integers. Therefore, the space used is proportional to the input `n`.  The space used by other variables (`c`, `i`) is constant and negligible compared to the size of the list.


In summary, this solution provides an efficient and elegant way to generate lexicographically ordered numbers, avoiding the overhead of sorting algorithms. Its linear time complexity makes it suitable for handling large input values of `n`.
