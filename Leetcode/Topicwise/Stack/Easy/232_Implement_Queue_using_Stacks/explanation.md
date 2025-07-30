```markdown
## Implement Queue using Stacks - Detailed Explanation

### 1. Problem Understanding:

The task is to implement a queue data structure using only stacks.  We need to provide the typical queue operations: `push` (enqueue), `pop` (dequeue), `peek` (get the front element), and `empty` (check if the queue is empty). The constraints are that we can only use standard stack operations (push, pop, peek, empty).

### 2. Approach / Intuition:

The core idea is to use two stacks, `s1` and `s2`, to simulate the behavior of a queue.  `s1` is primarily used for enqueueing (pushing) elements. `s2` is used for dequeueing (popping) and peeking.

The reason this approach works is based on how we transfer elements between the two stacks.  When we need to dequeue or peek, we check if `s2` is empty. If it is, we transfer *all* the elements from `s1` to `s2`.  This transfer reverses the order of the elements, effectively simulating the FIFO (First-In, First-Out) behavior of a queue.

Specifically:

*   **`push(x)`:**  Simply push the element `x` onto `s1`.  This adds the element to the "back" of our queue simulation.

*   **`pop()`:** If `s2` is empty, transfer all elements from `s1` to `s2`. This reverses the order, making the first element enqueued now the top of `s2`. Then pop the top element of `s2` and return it.  This is the element that was first enqueued.

*   **`peek()`:**  Similar to `pop()`, if `s2` is empty, transfer all elements from `s1` to `s2`. Then, peek at the top element of `s2` and return it.

*   **`empty()`:** The queue is empty if and only if both `s1` and `s2` are empty.

The advantage of this approach is that `push` is a simple O(1) operation. While `pop` and `peek` can take O(n) time in the worst case (when `s2` is empty and we need to transfer from `s1`), they are amortized O(1) because transferring from `s1` to `s2` only happens when `s2` is empty, and each element is moved at most once from `s1` to `s2`.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `Stack`.  The `java.util.Stack` class is used as the fundamental data structure for implementing the queue.
*   **Algorithm:** The core algorithm is based on the manipulation of elements between the two stacks to reverse the order when necessary to simulate queue behavior. This is essentially a form of *amortized analysis*, where a potentially expensive operation is balanced out by the fact that it doesn't occur very frequently.

### 4. Code Walkthrough:

```java
import java.util.Stack;

class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public MyQueue() {
        
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    public int peek() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
```

*   **`Stack<Integer> s1 = new Stack<>();`**: Declares the first stack `s1` to store integers. This is where we push new elements.
*   **`Stack<Integer> s2 = new Stack<>();`**: Declares the second stack `s2` to store integers. This stack is used to maintain the correct order for `pop` and `peek` operations.
*   **`public MyQueue() {}`**: The constructor. It's empty as no initialization is needed besides creating the stacks.
*   **`public void push(int x) { s1.push(x); }`**:  The `push` method simply pushes the given element `x` onto stack `s1`.  This operation is O(1).
*   **`public int pop() { ... }`**: The `pop` method:
    *   **`if(s2.isEmpty()) { ... }`**: Checks if `s2` is empty. If it is, it means we need to transfer elements from `s1` to `s2` to maintain the queue order.
    *   **`while(!s1.isEmpty()) { s2.push(s1.pop()); }`**: Transfers all elements from `s1` to `s2`. This reverses the order of the elements.
    *   **`return s2.pop();`**:  Pops and returns the top element from `s2`.  This is the element that was first enqueued (FIFO).
*   **`public int peek() { ... }`**: The `peek` method is very similar to `pop`:
    *   **`if(s2.isEmpty()) { ... }`**: Checks if `s2` is empty. If it is, it means we need to transfer elements from `s1` to `s2` to maintain the queue order.
    *   **`while(!s1.isEmpty()) { s2.push(s1.pop()); }`**: Transfers all elements from `s1` to `s2`. This reverses the order of the elements.
    *   **`return s2.peek();`**:  Returns the top element from `s2` without removing it.
*   **`public boolean empty() { return s1.isEmpty() && s2.isEmpty(); }`**:  The `empty` method returns `true` if both `s1` and `s2` are empty, and `false` otherwise.  This correctly reflects whether the queue is empty.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   `push()`: O(1)
    *   `pop()`: Amortized O(1). In the worst case (when `s2` is empty), it's O(n), where n is the number of elements in `s1`. However, this only happens once for each element.  Subsequent `pop()` operations will be O(1) until `s2` is empty again.
    *   `peek()`: Amortized O(1).  Similar reasoning to `pop()`.
    *   `empty()`: O(1)

*   **Space Complexity:**
    *   O(n), where n is the maximum number of elements stored in the queue.  The space is used to store the elements in the two stacks. In the worst case, all n elements might be in `s1`.
```