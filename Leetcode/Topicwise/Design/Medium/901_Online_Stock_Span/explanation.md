## LeetCode: Online Stock Span - Detailed Explanation

**1. Problem Understanding:**

The "Online Stock Span" problem asks us to design a data structure that efficiently calculates the span of stock prices.  The span of a stock's price on a given day is the maximum number of consecutive days (including the current day) for which the price on the current day is less than or equal to the price on the previous days.  We need to process stock prices one by one and return the span for each price immediately.


**2. Approach / Intuition:**

The most efficient approach to solve this problem is using a stack.  We store pairs of `(price, index)` in the stack. The stack maintains prices in decreasing order.  When a new price arrives:

* We pop all prices from the stack that are less than or equal to the current price.  This is because these popped prices cannot contribute to the span of the current price.
* The span of the current price is then the difference between its index and the index of the top element of the stack (or -1 if the stack is empty). This difference represents the number of consecutive days with prices less than or equal to the current price.
* Finally, we push the current price and its index onto the stack, maintaining the decreasing order property.

This approach avoids iterating through all previous prices for each new price, resulting in a significantly optimized solution compared to a brute-force approach.


**3. Data Structures and Algorithms:**

* **Data Structure:** Stack.  A `Stack<Pair>` is used to store pairs of `(price, index)`. The `Pair` class is a custom class to hold both the price and its index.
* **Algorithm:**  The core algorithm involves using the stack to maintain a monotonically decreasing sequence of prices. This allows us to efficiently calculate the span of each new price using stack operations.


**4. Code Walkthrough:**

* **`Pair` class:** A simple class to store a pair of integers (price and index).

* **`StockSpanner` class:**
    * **`StockSpanner()` constructor:** Initializes an empty stack `st` and an index `i` to -1.
    * **`next(int price)` method:**
        * Increments the index `i`.
        * **`while(!st.isEmpty() && st.peek().first <= price) st.pop();`:** This loop pops elements from the stack as long as the stack is not empty and the top element's price is less than or equal to the current price.
        * **`int ans = i - (st.isEmpty()?-1:st.peek().second);`:** This line calculates the span. If the stack is empty, the span is `i - (-1) = i + 1`. Otherwise, the span is the difference between the current index `i` and the index of the top element in the stack.
        * **`st.push(new Pair(price , i));`:** Pushes the current price and its index onto the stack.
        * **`return ans;`:** Returns the calculated span.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N) in total for N calls to `next()`.  Although each call to `next()` can potentially pop multiple elements from the stack, each element is pushed onto the stack and popped from the stack at most once.  Therefore, the amortized time complexity per `next()` call is O(1).

* **Space Complexity:** O(N) in the worst case.  The stack can grow up to size N if all prices are monotonically increasing. In this scenario, we will store all the `Pair` objects in the stack.


In summary, the provided Java code offers an efficient and elegant solution to the Online Stock Span problem using a stack.  Its use of a stack allows for amortized constant time complexity per query, making it highly suitable for handling a large stream of stock prices.
