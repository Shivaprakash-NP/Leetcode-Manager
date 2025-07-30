## LeetCode "Type of Triangle" Problem Explanation

### 1. Problem Understanding:

The problem requires us to determine the type of a triangle given the lengths of its three sides. The possible types are:

*   "equilateral": All three sides are equal.
*   "isosceles": Exactly two sides are equal.
*   "scalene": All three sides are different.
*   "none": The given side lengths cannot form a valid triangle.

### 2. Approach / Intuition:

The approach is straightforward and based on the fundamental properties of triangles:

1.  **Triangle Inequality Theorem:** For any triangle, the sum of the lengths of any two sides must be greater than the length of the third side. This is crucial for determining if the given lengths can even form a valid triangle. If this condition isn't met, the triangle is "none".
2.  **Side Length Comparisons:** Once we confirm that the input can form a triangle, we compare the side lengths to classify the type.
    *   If all three sides are equal, it's "equilateral".
    *   If exactly two sides are equal, it's "isosceles".
    *   If all sides are different, it's "scalene".

This approach is chosen for its simplicity and direct mapping to the mathematical definition of triangle types. There's no need for complex algorithms or data structures.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The input is an integer array `nums` of size 3, representing the lengths of the sides. No other data structures are used.
*   **Algorithms:**  The algorithm primarily involves comparisons and conditional statements. No advanced algorithms are needed.

### 4. Code Walkthrough:

```java
class Solution {
    public String triangleType(int[] nums) {
        if(nums[0]+nums[1] <= nums[2] || nums[0]+nums[2] <= nums[1] || nums[1]+nums[2] <= nums[0]) return "none";
        if(nums[0]==nums[1] && nums[1]==nums[2]) return "equilateral";
        else if(nums[0]==nums[1] || nums[1]==nums[2] || nums[0]==nums[2]) return "isosceles";
        else return "scalene";
    }
}
```

1.  **`public String triangleType(int[] nums)`:** This is the method definition. It takes an integer array `nums` as input and returns a string representing the type of triangle.

2.  **`if(nums[0]+nums[1] <= nums[2] || nums[0]+nums[2] <= nums[1] || nums[1]+nums[2] <= nums[0]) return "none";`:** This is the core logic for validating the triangle. It checks if the triangle inequality theorem holds. If the sum of any two sides is less than or equal to the third side, the triangle is invalid, and the function returns "none".  The `||` (OR) operator ensures that if *any* of the three conditions are true, the entire `if` condition is true.

3.  **`if(nums[0]==nums[1] && nums[1]==nums[2]) return "equilateral";`:** If the triangle is valid, this checks if all three sides are equal. If they are, it's an equilateral triangle, and the function returns "equilateral". The `&&` (AND) operator ensures that both `nums[0]==nums[1]` and `nums[1]==nums[2]` must be true for the whole expression to be true.

4.  **`else if(nums[0]==nums[1] || nums[1]==nums[2] || nums[0]==nums[2]) return "isosceles";`:** If it's not equilateral, this checks if exactly two sides are equal. If they are, it's an isosceles triangle, and the function returns "isosceles". The `||` (OR) operator ensures that if *any* of the three conditions are true, the entire `else if` condition is true.

5.  **`else return "scalene";`:** If none of the above conditions are met, it means all three sides are different, making it a scalene triangle.

### 5. Time and Space Complexity:

*   **Time Complexity: O(1)**  The code performs a fixed number of comparisons and conditional checks regardless of the input size. Therefore, the time complexity is constant.
*   **Space Complexity: O(1)** The code uses a constant amount of extra space. No additional data structures that scale with the input size are used.
