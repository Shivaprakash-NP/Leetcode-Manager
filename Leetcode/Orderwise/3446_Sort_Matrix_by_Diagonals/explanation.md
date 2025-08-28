## LeetCode: Sort Matrix Diagonals - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to sort the elements of a square matrix along its diagonals.  Diagonals running from top-left to bottom-right should be sorted in ascending order, while diagonals running from top-right to bottom-left should be sorted in descending order. The sorting happens in-place, meaning we modify the original matrix directly.


**2. Approach / Intuition:**

The solution cleverly uses a `HashMap` to group elements belonging to the same diagonal and a `PriorityQueue` to efficiently sort them.  

* **Diagonal Identification:** Each diagonal can be uniquely identified by the difference between its row and column indices ( `i - j` ).  Diagonals with positive differences run from top-left to bottom-right, while those with negative or zero differences run from top-right to bottom-left.

* **PriorityQueue for Sorting:**  `PriorityQueue` is used because it provides efficient retrieval of the smallest (or largest) element. For diagonals running top-left to bottom-right, a min-heap (`PriorityQueue<Integer>`) is used, and for diagonals running top-right to bottom-left, a max-heap (`PriorityQueue<Integer, Comparator.reverseOrder()`) is used.

* **HashMap for Grouping:** The `HashMap` stores the diagonals as keys ( `i - j` ), and the value is a `PriorityQueue` containing the elements of that diagonal. This allows us to sort each diagonal independently.

* **In-place Modification:** After sorting the elements in each `PriorityQueue`, the sorted elements are placed back into the original matrix in the same diagonal order, effectively sorting the matrix by diagonals.


This approach is chosen because it efficiently handles the sorting of multiple diagonals concurrently.  A naive approach of sorting each diagonal individually would be less efficient.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `HashMap<Integer, PriorityQueue<Integer>>`:  A hash map to store diagonals (identified by `i - j`) and their corresponding priority queues.
    * `PriorityQueue<Integer>`: A min-heap priority queue to sort diagonals running top-left to bottom-right.
    * `PriorityQueue<Integer, Comparator.reverseOrder()>`: A max-heap priority queue to sort diagonals running top-right to bottom-left.

* **Algorithms:**
    * **Hashing:** Used to store and retrieve diagonals efficiently based on their indices.
    * **Heap Sort (implicit):**  The `PriorityQueue` implicitly uses a heap-based sorting algorithm.


**4. Code Walkthrough:**

```java
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>(); // Initialize HashMap to store diagonals and their PriorityQueues

        for(int i = 0; i<n; i++) { // Iterate through the matrix
            for(int j = 0; j<n; j++) {
                int key = i-j; // Calculate diagonal identifier
                if(i<j) { // Diagonals from top-right to bottom-left (descending order)
                    map.computeIfAbsent(key, x -> new PriorityQueue<>(Comparator.reverseOrder())).offer(grid[i][j]);
                } else { // Diagonals from top-left to bottom-right (ascending order)
                    map.computeIfAbsent(key, x -> new PriorityQueue<>()).offer(grid[i][j]);
                }
            }
        }

        for(int i = 0; i<n; i++) { // Iterate through the matrix again
            for(int j = 0; j<n; j++) {
                grid[i][j] = map.get(i-j).poll(); // Retrieve and place the smallest/largest element from the PriorityQueue
            }
        }

        return grid;
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N^2 log N), where N is the dimension of the square matrix.  This is dominated by the insertion and extraction of elements from the priority queues.  Each element is inserted and extracted once, and the heap operations in the `PriorityQueue` take O(log N) time.

* **Space Complexity:** O(N^2). In the worst case, all elements might belong to different diagonals, resulting in a `HashMap` that stores N priority queues, each potentially containing N elements.  However, in practice, it could be less than O(N^2) if the diagonals have varying lengths and fewer elements.  The space also depends on the implementation of `PriorityQueue`.