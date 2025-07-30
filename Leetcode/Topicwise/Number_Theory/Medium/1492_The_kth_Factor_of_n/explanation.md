## LeetCode Problem: The kth Factor of n

### 1. Problem Understanding:

The problem asks us to find the *k*th factor of a given positive integer *n*. A factor of *n* is an integer that divides *n* evenly (i.e., with a remainder of 0). We need to return the *k*th factor in the sorted order of all factors. If *n* has fewer than *k* factors, we should return -1.

### 2. Approach / Intuition:

The most straightforward approach is to iterate through all numbers from 1 to *n* and check if each number is a factor of *n*. We can keep a count of the factors we've found so far.  When the count reaches *k*, we've found the *k*th factor, and we can return it. If we iterate through all numbers from 1 to *n* and haven't found *k* factors, it means *n* has fewer than *k* factors, so we return -1.

This approach is chosen for its simplicity and ease of implementation. While there might be more optimized approaches for larger values of n, this solution is generally sufficient and easy to understand, especially given the constraints often associated with this type of problem.

### 3. Data Structures and Algorithms:

*   **Data Structures:** No explicit data structures are used.
*   **Algorithms:** Iteration (for loop), Conditional statement (if).

### 4. Code Walkthrough:

```java
class Solution {
    public int kthFactor(int n, int k) {
        int c = 0; // Initialize a counter to keep track of the number of factors found.
        for(int i = 1 ; i<=n ; i++) { // Iterate through all numbers from 1 to n (inclusive).
            if(n%i == 0) { // Check if i is a factor of n. The modulo operator (%) returns the remainder of the division. If the remainder is 0, then i is a factor of n.
                c++; // Increment the factor count.
                if(c==k) return i; // If the current factor is the kth factor, return it.
            }
        }
        return -1; // If we reach the end of the loop without finding the kth factor, it means n has fewer than k factors, so return -1.
    }
}
```

**Explanation:**

1.  **`int c = 0;`**:  A variable `c` is initialized to 0. This variable serves as a counter to keep track of how many factors we've encountered so far.

2.  **`for(int i = 1 ; i<=n ; i++) { ... }`**: This loop iterates through all integers from 1 to *n*.  Each integer *i* within this range is a potential factor of *n*.

3.  **`if(n%i == 0) { ... }`**: This conditional statement checks if *i* is a factor of *n*. The modulo operator `%` calculates the remainder of the division `n / i`.  If the remainder is 0, it means *i* divides *n* evenly, and therefore *i* is a factor of *n*.

4.  **`c++;`**: If *i* is a factor of *n*, the factor counter `c` is incremented.

5.  **`if(c==k) return i;`**: This condition checks if the current factor we've found is the *k*th factor. If `c` is equal to *k*, it means we've found the *k*th factor, so we return *i*.

6.  **`return -1;`**:  If the loop completes without finding the *k*th factor (i.e., `c` never becomes equal to *k*), it means that *n* has fewer than *k* factors. In this case, the function returns -1, as specified in the problem description.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n). The loop iterates from 1 to *n*, so the time complexity is directly proportional to *n*.
*   **Space Complexity:** O(1). The solution uses only a constant amount of extra space (for the `c` and `i` variables), regardless of the size of *n*.
