## LeetCode Problem: Triangle - Detailed Solution Explanation

**1. Problem Understanding:**

The "Triangle" problem asks us to find the minimum path sum from the top to the bottom of a triangle represented as a list of lists of integers.  Each step moves from a number in one row to one directly below it or to its right.  The goal is to find the path that results in the smallest sum.


**2. Approach / Intuition:**

This solution utilizes dynamic programming to efficiently solve the problem.  Instead of exploring all possible paths (which would be exponential in time complexity), it builds a solution bottom-up.  We maintain an array `pre` which stores the minimum path sums to reach each node in the previous row.  Then, for each node in the current row, we compute the minimum path sum to reach that node by taking the minimum of the path sums from the two nodes above it in the previous row and adding the current node's value. This process iterates through each row of the triangle until the minimum path sum to the bottom row is found.  The space optimization of only using two arrays (`pre` and `cur`) makes it particularly efficient in memory usage compared to a more naive approach that stores the entire DP table.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `List<List<Integer>> triangle`: A list of lists representing the triangle.  Each inner list represents a row in the triangle.
    * `int[] pre`, `int[] cur`: Integer arrays used to store the minimum path sums to reach each node in the previous row (`pre`) and current row (`cur`). This is a space optimization technique.

* **Algorithms:**
    * **Dynamic Programming:** The core algorithm is dynamic programming, building the solution from subproblems (minimum path sums to each node).


**4. Code Walkthrough:**

* **`int n = triangle.size();`**: Gets the number of rows in the triangle.
* **`if(n == 1) return triangle.get(0).get(0);`**: Handles the base case of a single-row triangle.
* **`int[] pre = new int[n]; pre[0] = triangle.get(0).get(0);`**: Initializes `pre` array with the first row's element.
* **`for(int i = 1; i<n; i++) { ... }`**: Iterates through each row of the triangle, starting from the second row.
* **`int[] cur = new int[n];`**: Creates a temporary array `cur` to store minimum path sums for the current row.
* **`cur[0] = pre[0]+triangle.get(i).get(0);`**: Calculates minimum path sum to the first node of the current row.
* **`for(int j = 1; j<triangle.get(i).size()-1; j++) { ... }`**: Iterates through the inner nodes of the current row.
* **`cur[j] = Math.min(pre[j], pre[j-1])+triangle.get(i).get(j);`**: Calculates the minimum path sum to the current node using the minimum of the two nodes above it.
* **`cur[triangle.get(i).size()-1] = pre[triangle.get(i).size()-2]+triangle.get(i).get(triangle.get(i).size()-1);`**: Calculates minimum path sum to the last node of the current row.
* **`pre = cur;`**: Updates `pre` with the computed minimum path sums for the current row.
* **`int ans = pre[0]; for(int v : pre) ans = Math.min(ans, v);`**: Finds the minimum path sum among all nodes in the last row.
* **`return ans;`**: Returns the minimum total path sum.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the total number of nodes in the triangle.  We iterate through each node exactly once.
* **Space Complexity:** O(N) in the worst case.  Although we use two arrays `pre` and `cur`,  at any point we only need space proportional to the size of the largest row which is proportional to N in the worst-case scenario (where each row is roughly the same size).  A true O(1) space solution would require in-place modification which is possible but adds complexity to the code.

This dynamic programming solution is significantly more efficient than a brute-force approach which would have exponential time complexity.  The space optimization keeps memory usage linear, making it suitable for relatively large triangles.
