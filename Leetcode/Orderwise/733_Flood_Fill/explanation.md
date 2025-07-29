## LeetCode: Flood Fill - Detailed Explanation

**1. Problem Understanding:**

The "Flood Fill" problem asks us to replace a connected component of a given color in a 2D image with a new color.  The connected component includes all pixels of the same initial color that are horizontally or vertically adjacent.  The input consists of a 2D integer array representing the image, the row and column coordinates of a starting pixel, and the new color.  The output is the modified image.

**2. Approach / Intuition:**

This solution uses Breadth-First Search (BFS) to traverse the connected component.  BFS is chosen because it guarantees that we'll explore all pixels of the same color that are connected to the starting pixel before moving to pixels further away.  This ensures we correctly fill the entire connected component.  The algorithm starts at the given pixel, changes its color, and then explores its neighbors recursively (using a queue for BFS).  It only changes the color of a neighbor if it has the original color. This prevents accidentally overflowing into other differently colored regions.  The added check `if(image[sr][sc] == color)` at the beginning handles a trivial edge case where the starting pixel already has the target color, saving unnecessary processing.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * A 2D integer array (`image`) represents the image.
    * A queue (`q`) of type `LinkedList<int[]>` is used to implement the BFS algorithm. Each element in the queue is an integer array representing the row and column coordinates of a pixel.

* **Algorithms:**
    * Breadth-First Search (BFS) is the core algorithm used to traverse the connected component.


**4. Code Walkthrough:**

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color) return image; //Handle edge case: already the target color
        Queue<int[]> q = new LinkedList<>();
        int n = image.length;
        int m = image[0].length;
        int val = image[sr][sc]; //Store the original color
        image[sr][sc] = color; //Change the starting pixel's color
        q.offer(new int[]{sr, sc}); //Add starting pixel to the queue

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; //Directions: down, up, right, left

        while(!q.isEmpty()) {
            int size = q.size(); //Process all elements added in the previous iteration
            for(int i = 0 ; i<size ; i++) {
                int[] p = q.poll(); //Get the next pixel from the queue
                for(int[] d : dir) { //Check all four neighbors
                    int ni = d[0]+p[0];
                    int nj = d[1]+p[1];
                    if(ni>=0 && nj>=0 && ni<n && nj<m && image[ni][nj]==val) { //Check bounds and color
                        image[ni][nj] = color; //Change the neighbor's color
                        q.offer(new int[]{ni, nj}); //Add the neighbor to the queue
                    }
                }
            }
        }
        return image;
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of pixels in the image. In the worst case, we might visit every pixel in the image if the entire image is a single connected component of the same color.  The `while` loop iterates at most N times (number of pixels in the connected component). The nested loop iterates at most 4 times per pixel.

* **Space Complexity:** O(N) in the worst case. The queue can store up to N elements if the entire image is a single connected component. The `dir` array is constant space.


In summary, this solution efficiently solves the Flood Fill problem using Breadth-First Search, ensuring correctness and a good time and space complexity suitable for most LeetCode problem constraints.  The use of a queue avoids recursion depth issues that might arise with a recursive Depth-First Search (DFS) approach, especially with large images.
