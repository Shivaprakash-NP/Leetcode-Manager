## Lexicographically Smallest String After Applying Operations Solution Explanation

### 1. Problem Understanding

The problem asks us to find the lexicographically smallest string that can be obtained from a given string `s` by applying two types of operations any number of times:

1.  **Addition:** Add `a` to all odd-indexed digits of the string. Digits wrap around (e.g., 9 + 1 = 0).
2.  **Rotation:** Rotate the string to the right by `b` positions.

Our goal is to explore all possible strings reachable from the initial string `s` using these operations and then return the lexicographically smallest among them.

### 2. Approach / Intuition

The core idea is to use Breadth-First Search (BFS) to explore the state space of all possible strings obtainable through the given operations.  Here's the breakdown:

*   **BFS for Exploration:** BFS guarantees that we explore the reachable strings level by level. Each level represents the strings reachable after applying a certain number of operations.
*   **Set for Visited States:** We use a `TreeSet` to store the strings we've already visited.  This serves two crucial purposes:
    *   **Avoiding Cycles:** Prevents infinite loops by not re-visiting strings we've already processed.
    *   **Automatic Lexicographical Ordering:** `TreeSet` maintains the strings in lexicographical order, making it easy to retrieve the smallest one at the end.
*   **Operations Simulation:**  Within each BFS step, we simulate both the addition and rotation operations, generating new candidate strings.
*   **Lexicographical Minimization:** By using `TreeSet` at the end, after having reached all possible combinations of operations, we simply pick the `first()` element, which is guaranteed to be the lexicographically smallest.

Why this approach?

*   **Exhaustive Search:**  We need to consider all possible combinations of addition and rotation to guarantee finding the smallest string. BFS provides a systematic way to explore the possibilities.
*   **Lexicographical Ordering:** The `TreeSet` simplifies the task of finding the minimum by maintaining order and efficiently checking for previously visited states.

### 3. Data Structures and Algorithms

*   **`String`:**  The input string and the strings generated during the process.
*   **`Queue<String>` (LinkedList):** Used for BFS to store the strings to be processed.
*   **`TreeSet<String>`:** Used to store visited strings and automatically maintain them in lexicographical order.
*   **BFS (Breadth-First Search):**  The core algorithm for exploring the possible string states.

### 4. Code Walkthrough

```java
class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        TreeSet<String> set = new TreeSet<>((x,y) -> x.compareTo(y));

        Queue<String> q = new LinkedList<>();
        q.offer(s);

        while(!q.isEmpty()) {
            String num = q.poll();

            StringBuilder sb = new StringBuilder(num);

            for(int i = 0; i<n; i++) {
                if((i&1) == 1) {
                    int v = sb.charAt(i) - '0';
                    v = (v+a)%10;
                    sb.setCharAt(i, (char) (v+'0'));
                }
            }

            if(!set.contains(sb.toString())) {
                q.offer(sb.toString());
                set.add(sb.toString());
            }

            String s2 = num.substring(n-b)+num.substring(0, n-b);
            if(!set.contains(s2)) {
                q.offer(s2);
                set.add(s2);
            }
        }

        return set.first();
    }
}
```

1.  **`findLexSmallestString(String s, int a, int b)`:**
    *   `n = s.length();`: Stores the length of the input string `s`.
    *   `TreeSet<String> set = new TreeSet<>((x,y) -> x.compareTo(y));`: Initializes a `TreeSet` called `set` to store visited strings, using the default `compareTo` method for lexicographical ordering.
    *   `Queue<String> q = new LinkedList<>();`: Initializes a `Queue` called `q` to perform BFS, starting with the original string `s`.
    *   `q.offer(s);`: Adds the initial string `s` to the queue.

2.  **`while(!q.isEmpty())`:**
    *   This loop continues until the queue is empty, meaning all reachable strings have been explored.
    *   `String num = q.poll();`: Retrieves (and removes) the next string from the queue to process.

3.  **Addition Operation:**
    *   `StringBuilder sb = new StringBuilder(num);`: Creates a `StringBuilder` from the current string to allow for modification.
    *   `for(int i = 0; i<n; i++)`: Iterates through each character (digit) of the string.
    *   `if((i&1) == 1)`: Checks if the index `i` is odd.  `i&1` is a bitwise AND operation that efficiently determines if `i` is odd.
    *   `int v = sb.charAt(i) - '0';`: Converts the character at the odd index `i` to its integer value.
    *   `v = (v+a)%10;`: Adds `a` to the digit and takes the modulo by 10 to handle wrapping around.
    *   `sb.setCharAt(i, (char) (v+'0'));`: Converts the updated digit back to a character and updates the `StringBuilder`.

4.  **Adding the Added string to the Queue (if not visited):**
    *   `if(!set.contains(sb.toString()))`: Checks if the modified string (after addition) is already in the `TreeSet`.
    *   `q.offer(sb.toString());`: Adds the modified string to the queue for further processing.
    *   `set.add(sb.toString());`: Adds the modified string to the `TreeSet` to mark it as visited.

5.  **Rotation Operation:**
    *   `String s2 = num.substring(n-b)+num.substring(0, n-b);`: Performs the rotation. It takes the last `b` characters of `num` and appends them to the beginning of the string. This effectively rotates the string `b` positions to the right.

6.  **Adding the Rotated string to the Queue (if not visited):**
    *   `if(!set.contains(s2))`: Checks if the rotated string is already in the `TreeSet`.
    *   `q.offer(s2);`: Adds the rotated string to the queue for further processing.
    *   `set.add(s2);`: Adds the rotated string to the `TreeSet` to mark it as visited.

7.  **Returning the Lexicographically Smallest String:**
    *   `return set.first();`: After the BFS is complete, the `TreeSet` `set` contains all reachable strings in sorted order. The `first()` method returns the smallest (lexicographically) string in the set.

### 5. Time and Space Complexity

*   **Time Complexity:** O(N * K * log(K)), where N is the length of the string `s` and K is the number of reachable strings.
    * The `while` loop and operations within contribute to O(K * N), since we may have K reachable string states and each time we apply our operation and string manipulation will be O(N).
    * The `set.contains()` operation in a TreeSet takes O(log(K)). Since this is nested in the `while` loop, this factor is multiplied to the existing complexity. In the worst case, where we add every combination of strings, we're effectively comparing every single one through all possible string combinations. Hence, the log(K) contribution.
*   **Space Complexity:** O(K), where K is the number of reachable strings.  This is because the `TreeSet` and `Queue` can store up to K strings in the worst-case scenario, where every visited string is unique.
