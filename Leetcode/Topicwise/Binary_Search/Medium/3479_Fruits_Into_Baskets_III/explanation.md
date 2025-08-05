## LeetCode: Fruits Into Baskets III (Explained)

**1. Problem Understanding:**

The problem simulates placing fruits into baskets.  We have a list of fruits (`fruits`) and a limited number of baskets (`baskets`), each basket capable of holding only one type of fruit at a time.  The goal is to determine how many fruits cannot be placed in any of the available baskets.  The `baskets` array indicates the capacity of each basket type. For instance, if `baskets = [2, 1]`, it means we have one basket that can hold at most two fruits of a certain type, and one basket that can hold at most one fruit of a different type.


**2. Approach / Intuition:**

The solution cleverly uses a segment tree to efficiently track the available capacity of each basket type.  A segment tree is a binary tree data structure used for range queries and updates. In this case:

* **Segment Tree Representation:** Each node in the segment tree stores the maximum number of available slots for a specific fruit type within a range of baskets.
* **Build:**  Initially, the segment tree is built to reflect the initial basket capacities.
* **Query:** For each fruit, a query is performed on the segment tree to find the first basket with sufficient capacity.  If found, the basket's capacity is updated.
* **Update:** The `update` function reduces the capacity of the chosen basket by one.
* **Unplaced Fruits Count:** If no suitable basket is found (`query` returns -1), the fruit is considered unplaced, and a counter is incremented.

This approach is efficient because both query and update operations on a segment tree take O(log n) time, where n is the number of basket types.  A naive approach would require O(n) time for each fruit, leading to a less efficient O(n^2) overall complexity.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * **Segment Tree:** A binary tree-based data structure used for efficient range queries and updates.
    * **Array:** Used to store the `fruits` and `baskets` information.
* **Algorithms:**
    * **Segment Tree Build:** Recursive algorithm to construct the segment tree.
    * **Segment Tree Query:** Recursive algorithm to find the first basket with sufficient capacity.
    * **Segment Tree Update:** Recursive algorithm to update the basket capacity after placement.


**4. Code Walkthrough:**

* **`Segment` Class:** This class implements the segment tree.
    * `tree`: Array to store the segment tree.  Its size is 4*n because a balanced binary tree can have at most 4n nodes.
    * `build()`: Recursively builds the segment tree, storing the maximum capacity in each node.
    * `query()`: Recursively searches for the first basket with capacity >= the fruit's type. Returns the index if found, otherwise -1.
    * `update()`: Recursively updates the segment tree to reflect the reduced capacity of a basket.

* **`Solution` Class:**
    * `numOfUnplacedFruits()`:
        * Creates a `Segment` object to represent the baskets.
        * Builds the segment tree using `st.build()`.
        * Iterates through each fruit in `fruits`:
            * Performs a `query()` to find a suitable basket.
            * If a basket is found, updates its capacity using `st.update()`.
            * If no basket is found, increments the `unplaced` counter.
        * Returns the total number of unplaced fruits.

**5. Time and Space Complexity:**

* **Time Complexity:** O(m log n), where m is the number of fruits and n is the number of basket types.  Building the segment tree takes O(n) time. Each query and update operation takes O(log n) time, and there are m such operations.
* **Space Complexity:** O(n), dominated by the space used for the segment tree (`tree` array in the `Segment` class).  The space used for `fruits` and `baskets` is O(m) and O(n) respectively, which is subsumed by the segment tree space complexity when n is large.

**In summary:** The solution efficiently solves the "Fruits Into Baskets III" problem using a segment tree.  This data structure allows for logarithmic time complexity for both query and update operations, making the overall solution significantly faster than a naive approach. The code is well-structured and easy to understand, making it a good example of applying advanced data structures to solve a practical problem.
