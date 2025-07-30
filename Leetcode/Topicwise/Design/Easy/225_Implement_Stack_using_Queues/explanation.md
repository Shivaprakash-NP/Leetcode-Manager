## LeetCode Problem: Implement Stack using Queues - Explained

Here's a breakdown of the Java code provided, implementing a stack data structure using queues:

### 1. Problem Understanding:

The problem asks us to simulate the behavior of a stack (LIFO - Last In, First Out) using only queue data structures (FIFO - First In, First Out).  We need to implement the following stack operations:

*   `push(x)`:  Pushes element `x` onto the stack.
*   `pop()`: Removes the element on top of the stack and returns it.
*   `top()`: Returns the element on top of the stack without removing it.
*   `empty()`: Returns `true` if the stack is empty, `false` otherwise.

### 2. Approach / Intuition:

The key idea is to use a single queue and rearrange its elements during the `push` operation to mimic the stack's LIFO behavior.  Here's how the `push` operation achieves this:

1.  **Enqueue the new element:** The new element `x` is added to the back of the queue.
2.  **Rotate the queue:** All existing elements in the queue (except the newly added one) are moved from the front of the queue to the back of the queue. This effectively makes the newly added element the *first* element in the queue, which corresponds to the *top* of the stack.

Why this approach? We need to somehow re-arrange the order of elements to enforce the LIFO principle of the stack when using the FIFO nature of queues.  By moving existing elements behind the newly pushed element, we bring the last pushed element to the front, effectively acting like a stack.  Using a single queue is generally more memory efficient than using two queues.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `Queue` (specifically, `LinkedList` implementation in Java)
*   **Algorithm:** Queue manipulation (enqueueing, dequeueing, and rotation).  The `push` operation effectively implements a rotation.

### 4. Code Walkthrough:

```java
class MyStack {
    Queue<Integer> q = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        q.offer(x); // Enqueue the new element at the rear of the queue
        int n = q.size();
        for(int i = 0 ; i<n-1 ; i++) q.offer(q.poll()); // Move existing elements to the rear
    }

    public int pop() {
        return q.poll(); // Dequeue from the front of the queue (which is the top of the stack)
    }

    public int top() {
        return q.peek(); // Return the element at the front of the queue (top of the stack)
    }

    public boolean empty() {
        return q.isEmpty(); // Check if the queue is empty
    }
}
```

*   **`MyStack()` (Constructor):**
    *   Initializes an empty `LinkedList` to act as our queue `q`.

*   **`push(int x)`:**
    *   `q.offer(x);`: Adds the new element `x` to the rear (end) of the queue.
    *   `int n = q.size();`: Stores the current size of the queue.  Crucially, we get the size *after* adding the new element.
    *   `for(int i = 0 ; i<n-1 ; i++) q.offer(q.poll());`: This loop is the heart of the stack implementation.  It performs the rotation:
        *   `q.poll()`: Removes the element at the front of the queue.
        *   `q.offer(q.poll())`: Adds the removed element to the rear of the queue.
        *   The loop runs `n-1` times, moving all existing elements (before `x` was pushed) to the back of the queue. This makes the newly added element `x` the first element in the queue, simulating the top of the stack.

*   **`pop()`:**
    *   `return q.poll();`: Removes and returns the element at the front of the queue. This directly corresponds to popping the top element from the stack.

*   **`top()`:**
    *   `return q.peek();`: Returns the element at the front of the queue *without* removing it.  This is equivalent to peeking at the top of the stack.

*   **`empty()`:**
    *   `return q.isEmpty();`: Returns `true` if the queue is empty, and `false` otherwise. This indicates whether the stack is empty.

### 5. Time and Space Complexity:

*   **Time Complexity:**
    *   `push(x)`: O(n), where n is the number of elements in the queue before the push.  This is because of the loop that moves the elements to the back of the queue.
    *   `pop()`: O(1), because `poll()` on a `LinkedList` (queue) is a constant-time operation.
    *   `top()`: O(1), because `peek()` on a `LinkedList` (queue) is a constant-time operation.
    *   `empty()`: O(1), because `isEmpty()` on a `LinkedList` (queue) is a constant-time operation.

*   **Space Complexity:**
    *   O(n), where n is the maximum number of elements that can be stored in the stack (and thus, the queue).  We only use a single queue, so the space is linear with the number of elements.
