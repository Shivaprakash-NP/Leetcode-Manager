## LeetCode: Fruit Into Baskets - Detailed Explanation

**1. Problem Understanding:**

The "Fruit Into Baskets" problem asks us to find the maximum number of fruits you can pick from a tree, given that you can only pick at most two types of fruits at any time.  We are given an array representing the sequence of fruits on the tree, and we need to find the longest subarray containing at most two distinct fruit types.


**2. Approach / Intuition:**

The optimal approach uses a **sliding window** technique. We maintain a window that slides through the `fruits` array.  This window represents a subarray containing at most two types of fruits.  The key idea is to expand the window from the right (`r`) until it contains more than two types of fruits.  Then, we shrink the window from the left (`l`) until it satisfies the condition again.  We track the maximum window size encountered throughout this process.  This approach is chosen because it efficiently explores all possible subarrays while avoiding redundant computations.  A brute-force approach checking all subarrays would have a much higher time complexity.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `HashMap` (or `Map`): Used to efficiently store the count of each fruit type within the current window.  The key is the fruit type (integer), and the value is its frequency.

* **Algorithms:**
    * **Sliding Window:** The core algorithm used to iteratively expand and shrink the window to find the optimal solution.


**4. Code Walkthrough:**

```java
class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>(); // Map to store fruit counts in the window
        int n = fruits.length; // Length of the fruits array
        int ans = 0; // Variable to store the maximum number of fruits
        int l = 0; // Left pointer of the sliding window

        for(int r = 0; r<n; r++) { // Iterate through the fruits array using right pointer
            map.put(fruits[r], map.getOrDefault(fruits[r], 0)+1); // Add the current fruit to the map, incrementing its count

            while(map.size() > 2) { // If the window has more than 2 fruit types
                map.put(fruits[l], map.get(fruits[l])-1); // Decrement the count of the leftmost fruit
                if(map.get(fruits[l]) == 0) map.remove(fruits[l]); // Remove the leftmost fruit if its count becomes 0
                l++; // Move the left pointer to shrink the window
            }
            ans = Math.max(ans, r-l+1); // Update the maximum number of fruits if the current window is larger
        }

        return ans; // Return the maximum number of fruits
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the `fruits` array.  The `for` loop iterates through the array once.  The `while` loop, while nested, only executes a total of O(n) times in the worst case (each element can be added and removed from the map at most once).

* **Space Complexity:** O(1). The `HashMap` stores at most 3 different fruit types (at any given time, the while loop ensures that the number of distinct types is at most 2, plus potentially one more during the addition operation).  Therefore, the space used is constant and does not depend on the input size.  In a strict sense, its space is bound by a small constant, not the input size.  Therefore, it's considered O(1).
