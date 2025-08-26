## LeetCode: Diagonal Traverse - Detailed Solution Explanation

**1. Problem Understanding:**

The problem asks us to traverse a given matrix diagonally, starting from the top-left corner.  The traversal should zigzag— moving up and to the right on odd-numbered diagonals and down and to the right on even-numbered diagonals. The output should be a 1D array containing the elements in the order they were visited.


**2. Approach / Intuition:**

The solution employs a clever use of a `TreeMap` to group elements along diagonals.  Each diagonal is identified by the sum of its row and column indices (`i + j`).  The `TreeMap` automatically sorts the diagonals based on their sum, ensuring we process them in the correct order.  We then reverse the order of elements within even-numbered diagonals to achieve the zigzag pattern. Finally, we concatenate the elements from all diagonals into the resulting array. This approach is efficient because it directly maps diagonal elements together, avoiding redundant iterations.


**3. Data Structures and Algorithms:**

* **`TreeMap<Integer, List<Integer>> map`:** A `TreeMap` is used to store the elements of each diagonal. The key represents the diagonal number (sum of row and column indices), and the value is a `List` containing the elements along that diagonal. The `TreeMap`'s sorted nature is crucial for maintaining the correct diagonal traversal order.
* **`ArrayList<Integer>`:**  Used as the value in the `TreeMap` to hold elements of each diagonal.
* **`Collections.reverse()`:**  Used to reverse the order of elements in even-numbered diagonals.
* **Basic array operations:**  Used to create and populate the output array.


**4. Code Walkthrough:**

```java
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        Map<Integer, List<Integer>> map = new TreeMap<>(); //1. Initialize TreeMap to store diagonals
        for(int i = 0; i<n; i++) {  //2. Iterate through the matrix
            for(int j = 0; j<m; j++) {
                int k = i+j;      //3. Calculate diagonal number
                map.computeIfAbsent(k, x -> new ArrayList<>()).add(mat[i][j]); //4. Add element to appropriate diagonal list
            }
        }

        int[] ans = new int[n*m];  //5. Initialize output array
        int ptr = 0;              //6. Initialize pointer for output array
        for(int key : map.keySet()) { //7. Iterate through diagonals (sorted by TreeMap)
            if((key&1) == 0) {     //8. Check if diagonal number is even
                Collections.reverse(map.get(key)); //9. Reverse even diagonals
            }

            for(int val : map.get(key)) ans[ptr++] = val; //10. Add elements to the output array
        }

        return ans; //11. Return the result
    }
}
```


**5. Time and Space Complexity:**

* **Time Complexity: O(N*M log(N+M))** -  The nested loops iterate through the entire matrix (O(N*M)). The `TreeMap` operations (`computeIfAbsent` and `keySet` iteration) have a time complexity of O(log(N+M)) in the average case due to its balanced tree structure. The `Collections.reverse` operation takes linear time with respect to the length of the list, but this is dominated by the O(N*M) matrix iteration.

* **Space Complexity: O(N*M)** - The `TreeMap` in the worst case may store all the elements of the matrix if all elements are on different diagonals.  The output array `ans` also takes O(N*M) space. Therefore, the space complexity is dominated by these two data structures, resulting in O(N*M) space complexity.


In summary, this solution efficiently solves the Diagonal Traverse problem by leveraging the properties of a `TreeMap` to group and sort elements along diagonals, enabling a streamlined zigzag traversal.  While the space complexity is linear with respect to the input size, it offers a clear and relatively efficient way to solve this problem compared to other approaches involving complex index manipulations.
