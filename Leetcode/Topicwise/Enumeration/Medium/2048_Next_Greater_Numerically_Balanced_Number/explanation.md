### Problem Understanding

The goal of this problem is to find the smallest integer $M$ such that $M > N$ and $M$ is a "numerically balanced number."

A number is numerically balanced if, for every distinct digit $D$ present in the number, the digit $D$ appears exactly $D$ times.

**Examples:**
*   $1$ is balanced (1 appears 1 time).
*   $22$ is balanced (2 appears 2 times).
*   $1333$ is balanced (1 appears 1 time, 3 appears 3 times).
*   $1224444$ is balanced (1 appears 1 time, 2 appears 2 times, 4 appears 4 times).

Since the input $N$ is relatively small (typically up to $10^6$ in LeetCode constraints for this problem), the crucial insight is that the required next balanced number will also be small, limiting the search space significantly.

### Approach / Intuition

The core strategy is **Generate and Search**. Since the set of all numerically balanced numbers is very sparse and the required answer is constrained to a small range (up to roughly $1.25$ million), we can generate *all* relevant balanced numbers first, sort them, and then perform a simple linear search for the smallest number greater than $N$.

**Why this approach?**
1.  **Small Search Space:** The length of a balanced number using digits 1 through 7 is $1+2+3+4+5+6+7 = 28$. However, the smallest balanced number with 8 digits is greater than $10^7$. Given the input constraints, we only need to worry about numbers up to length 7. The total number of unique numerically balanced numbers up to $10^7$ is fewer than 50. Generating them all is fast.
2.  **Backtracking/DFS:** We use a backtracking (Depth First Search) algorithm to systematically construct all possible balanced numbers digit by digit. This allows us to enforce the strict requirement that a digit $D$ can only be used $D$ times.

**Core Logic in Generation:**
The `generate` function builds the number $N$ recursively. It uses a `count` array to maintain the current usage of each digit (1-7). Before adding a digit $D$, we check if `count[D] < D`. If this condition holds, we can append $D$ and recurse. If we reach a state where the current number is constructed from a valid set of required digits (checked by the `is` function), we add it to our list.

### Data Structures and Algorithms

1.  **Algorithm:**
    *   **Backtracking / Depth First Search (DFS):** Used in the `generate` function to systematically construct all possible balanced numbers.
    *   **Sorting:** Used via `Collections.sort` to order the generated balanced numbers, allowing for efficient lookup of the smallest number $> N$.

2.  **Data Structures:**
    *   `int[] count`: An array of size 10 (indexed 0-9) used to store the frequency count of digits currently used in the recursive path. This serves as the state variable for backtracking.
    *   `List<Integer> list`: Stores all numerically balanced numbers found during the generation process.

### Code Walkthrough

#### 1. `is(int n, int[] count)`

This helper function checks if the set of digits used to form the number $n$ satisfies the balancing property.

```java
private boolean is(int n, int[] count) {
    for(int i = 1; i<=7; i++) {
        // If digit i was used (count[i] != 0), 
        // it must have been used exactly i times.
        if(count[i] != 0 && count[i] != i) return false;
    }
    return true;
}
```
The key insight here is that `is` is only called when we are *potentially* finished constructing a balanced number. It verifies that for all digits $i$ that participated in the construction path (i.e., `count[i] != 0`), their required count $i$ has been met.

#### 2. `generate(int n, int[] count, List<Integer> list)`

This is the backtracking function responsible for building the numbers.

```java
private void generate(int n, int[] count, List<Integer> list) {
    // 1. Base Case 1: Found a potential balanced number
    if(n > 0 && is(n, count)) list.add(n);
    
    // 2. Pruning: Stop searching if the number exceeds the practical boundary.
    // 1224444 is one of the largest balanced numbers required for N up to 10^6.
    if(n > 1224444) return;

    // 3. Recursive Step: Try appending digits 1 through 7
    for(int d = 1; d<=7; d++) {
        // Constraint Check: Can only use digit 'd' if we haven't reached its required count 'd'.
        if(count[d] < d) {
            
            // Action (Make Move)
            count[d]++;
            
            // Recurse (Append digit d to n)
            generate(10*n + d, count, list);
            
            // Backtrack (Undo Move)
            count[d]--;
        }
    }
}
```
Note that the `generate` function finds numbers where the digits used *could* form a balanced number (i.e., the counts match the required $i$ values). Since the recursion builds numbers sequentially (e.g., $1 \rightarrow 12 \rightarrow 122$), the `is` check correctly identifies when a path has completed a valid balanced combination of digits.

#### 3. `nextBeautifulNumber(int n)`

This is the main driver function.

```java
public int nextBeautifulNumber(int n) {
    List<Integer> list = new ArrayList<>();

    // 1. Generate all relevant balanced numbers
    generate(0, new int[10], list);
    
    // 2. Sort the generated list
    Collections.sort(list);

    // 3. Find the smallest element strictly greater than n
    for(int v : list) if(v > n) return v;
    
    // Should generally not be reached if the pruning limit is sufficient
    return 0; 
}
```
After generation and sorting, the solution iterates through the small, ordered list and immediately returns the first balanced number found that exceeds the input $n$.

### Time and Space Complexity

#### Time Complexity: $O(1)$

The time complexity is effectively constant, $O(1)$. Here is the breakdown:

1.  **Generation:** The search space for generating numerically balanced numbers is extremely small due to the strict constraints (a digit $D$ must appear $D$ times). The number of nodes explored in the DFS is fixed and independent of the input $n$ (as long as $n$ is within the bounds leading to an answer less than $1.25$ million). The maximum number of generated balanced numbers ($K$) is constant (around 40-50).
2.  **Sorting:** Sorting the generated list takes $O(K \log K)$. Since $K$ is a small constant, this is also $O(1)$.
3.  **Search:** The final linear search takes $O(K)$, which is $O(1)$.

Because the number of operations is fixed and small, regardless of the magnitude of $N$ (within the typical bounds), the complexity is considered constant time.

#### Space Complexity: $O(1)$

The space complexity is also constant, $O(1)$.

1.  **Storage:** The size of the `list` storing the balanced numbers is $K$ (a constant, typically $K < 50$).
2.  **Auxiliary Structures:** The `count` array is size 10, and the recursion depth is capped at 7. These contribute only constant space.

Therefore, the space complexity is dominated by the constant storage required to hold the fixed, small set of balanced numbers.