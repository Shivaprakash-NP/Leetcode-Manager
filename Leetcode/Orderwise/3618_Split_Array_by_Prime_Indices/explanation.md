## LeetCode: Split Array by Prime Indices - Solution Explanation

**1. Problem Understanding:**

The problem asks us to split an array of integers into two subarrays based on the indices.  One subarray contains elements whose indices are prime numbers, and the other contains elements whose indices are not prime numbers. The goal is to find the absolute difference between the sum of elements in these two subarrays.


**2. Approach / Intuition:**

The solution uses a straightforward approach:

1. **Prime Check:** It first defines a helper function `is()` to efficiently determine if a given number is prime.  It uses a standard optimization for primality testing, checking divisibility only up to the square root of the number.

2. **Summation:** It iterates through the input array `nums`. For each element, it checks if its *index* is prime using the `is()` function. If the index is prime, the element is added to sum `A`; otherwise, it's added to sum `B`.

3. **Difference:** Finally, it calculates the absolute difference between `A` and `B` and returns the result. This approach is chosen for its simplicity and readability.  It directly implements the problem's requirements without employing more complex algorithms.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is an array (`int[] nums`) to store the input numbers.
* **Algorithms:** The core algorithm is a simple linear scan of the array. The primality test within the `is()` function uses a basic trial division algorithm.


**4. Code Walkthrough:**

* **`private boolean is(int n)`:** This helper function checks if a number `n` is prime. It handles base cases (0 and 1 are not prime).  The loop iterates from 2 up to the square root of `n`. If any number in this range divides `n` evenly, `n` is not prime, and `false` is returned. Otherwise, `true` is returned indicating that `n` is prime.

* **`public long splitArray(int[] nums)`:** This function is the main solution.
    * It initializes two long variables, `A` and `B`, to store the sums of elements with prime and non-prime indices, respectively.  `long` is used to prevent potential integer overflow if the sums become very large.
    * The `for` loop iterates through the input array `nums`.
    * Inside the loop, `is(i)` checks if the current index `i` is prime.
    * Based on the result of `is(i)`, the corresponding element `nums[i]` is added to either `A` or `B`.
    * Finally, the absolute difference between `A` and `B` is calculated and returned using `Math.abs()`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n√p), where n is the length of the input array and p is the largest index. The dominant factor is the primality test within the loop. While the primality test itself is O(√p) in the worst case (checking divisibility up to the square root of p), the overall time complexity is determined by the loop iterating through the array. Therefore, the overall complexity is approximately O(n√p), as p is a function of n (p ≤ n-1).  In simpler terms, it's roughly linear in relation to n, but with a slight additional factor due to the prime check.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space regardless of the input array size.  The helper function `is()` uses a constant amount of space. The variables `A` and `B` also occupy constant space.
