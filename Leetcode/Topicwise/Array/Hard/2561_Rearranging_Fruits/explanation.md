## Rearranging Fruits: A Detailed Explanation

**1. Problem Understanding:**

The problem "Rearranging Fruits" presents two baskets containing fruits represented by integers.  The goal is to rearrange the fruits in the baskets such that each basket contains the same number of each type of fruit. The cost of moving a fruit is the value of the fruit itself. If it's impossible to achieve equal distribution (odd number of a fruit type), the function should return -1. Otherwise, it returns the minimum cost to achieve an even distribution across both baskets.

**2. Approach / Intuition:**

The solution uses a greedy approach combined with efficient data structures to minimize the cost. The core logic is as follows:

1. **Count Fruits:**  First, it counts the occurrences of each fruit type in both baskets, storing them in `map`, `m1`, and `m2`. `map` stores the total count of each fruit, `m1` stores counts for basket1 and `m2` for basket2.

2. **Check for Feasibility:** It checks if an even distribution is possible by verifying that the total count of each fruit type is even. If not, it returns -1.

3. **Identify Excess Fruits:** It identifies which fruits have more than half their total count in either basket. These "excess" fruits are stored in `v1` and `v2` lists for basket1 and basket2 respectively.

4. **Sort for Optimization:** The `v1` list is sorted in ascending order, and `v2` in descending order. This sorting is crucial for the greedy strategy.

5. **Greedy Minimization:** The code iterates through the `v1` and `v2` lists. For each pair of excess fruits, the minimum cost is determined: either swapping them directly (`min(v1[i], v2[i])`) or moving both to the cheaper basket using the minimal element (`2 * minElement`). The minimum of these costs is added to the total cost (`ans`). This greedy approach ensures we always choose the cheapest option for each fruit pair.

**3. Data Structures and Algorithms:**

* **HashMap (`map`, `m1`, `m2`):** Used to efficiently count the occurrences of each fruit type.  Hashmaps provide O(1) average time complexity for insertion and retrieval.
* **ArrayList (`v1`, `v2`):** Used to store lists of excess fruits in each basket.
* **Sorting (`Collections.sort`):** Used to sort the lists of excess fruits for optimal cost calculation.  The time complexity of sorting is typically O(n log n).
* **Greedy Algorithm:** The core algorithm is a greedy approach to minimize the cost by making locally optimal choices at each step.


**4. Code Walkthrough:**

* **Lines 4-7:** Initialization of HashMaps and ArrayLists.
* **Lines 9-14:** Counts fruit occurrences in both baskets and stores them in the appropriate HashMaps.
* **Line 16:** Checks if the count of each fruit is even; returns -1 if not.
* **Lines 18-27:** Iterates through fruit types. Identifies excess fruits for each type and adds them to the corresponding list.
* **Lines 29-30:** Sorts the lists of excess fruits.
* **Line 32:** Finds the minimum fruit value across all fruits.
* **Lines 34-37:**  Iterates through excess fruits. For each pair, calculates the minimum cost between direct swap and moving both to the basket with the cheapest fruit. Adds the minimum cost to `ans`.
* **Line 39:** Returns the total minimum cost.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the total number of fruits. This is dominated by the sorting of `v1` and `v2` lists.  The other operations (hashmap operations, iterations) are O(N) in average case.

* **Space Complexity:** O(N). The space is mainly used by the HashMaps (`map`, `m1`, `m2`) and ArrayLists (`v1`, `v2`), which in the worst case, store all the unique fruit types and potentially all fruits if the distribution is heavily skewed.


**Potential Improvements:**

While the code works correctly, a minor optimization could be made.  Instead of using three HashMaps (`map`, `m1`, `m2`), we could manage with just two: one for the total counts and another for basket1. Basket2 counts can be derived from the total counts and basket1 counts. This slightly reduces the space complexity.  The algorithm itself is already quite efficient.
