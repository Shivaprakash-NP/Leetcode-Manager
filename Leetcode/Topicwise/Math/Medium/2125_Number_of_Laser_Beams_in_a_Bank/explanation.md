### Problem Understanding

The problem asks us to calculate the total number of secure laser beams that can be established in a bank vault, represented by a grid of strings (`bank`). Each string is a row, where '1' signifies a security device and '0' signifies empty space.

A laser beam can be established between two devices located in different rows ($R_i$ and $R_j$) if and only if there are *no* security devices (no '1's) in any row strictly between $R_i$ and $R_j$. This means we only care about connections between rows that are sequentially adjacent after filtering out the completely empty rows.

If Row A has $A$ devices and Row B has $B$ devices, and Row A and Row B are adjacent (in the laser beam sense), the total number of beams between them is $A \times B$. Our goal is to sum these products across all such adjacent pairs.

### Approach / Intuition

The naive approach would be to iterate through every pair of rows ($i, j$) and check if all intervening rows are empty ($O(N^3)$ or $O(N^2 \cdot M)$ depending on how counts are calculated, where $N$ is rows and $M$ is columns).

The key constraint simplification is that beams only connect the *closest* non-empty rows. This suggests a **linear scan** approach where we only need to keep track of the device count from the *last non-empty row* encountered.

**Core Logic:**

1.  We iterate through the `bank` array row by row.
2.  We maintain a variable, let's call it `pre`, storing the number of devices in the previously successful (non-empty) row. Initially, `pre = 0`.
3.  For the current row, we count the number of devices, `cur`.
4.  If `cur` is 0, the row is empty, and we simply ignore it and move to the next row, keeping `pre` unchanged.
5.  If `cur` is greater than 0, we have found a non-empty row.
    *   If `pre` is also greater than 0, this new row forms an adjacent pair with the row represented by `pre`. We add the product (`cur * pre`) to our total answer.
    *   After calculation, we update `pre` to equal `cur`, setting up the current row as the reference point for the *next* non-empty row we encounter.

This iterative process ensures that every non-empty row is paired exactly once with the non-empty row immediately preceding it in the bank layout, correctly ignoring any intervening empty rows.

### Data Structures and Algorithms

1.  **Data Structures:**
    *   **Array of Strings:** Used for the input (`bank`).
    *   **Integers:** Used for storing counts (`pre`, `cur`, `ans`).

2.  **Algorithms:**
    *   **Linear Scan (Traversal):** The primary algorithm involves iterating through the input array once.
    *   **Counting:** A sub-process involves a linear scan of each individual row string to count the number of '1's.

### Code Walkthrough

The provided solution uses a helper function for counting and the main function for calculation.

#### 1. Helper Function: `count(String s)`
```java
    private int count(String s) {
        int cnt = 0;
        for(char c : s.toCharArray()) if(c == '1') cnt++;
        return cnt;
    } 
```
This simple utility function takes a string (a bank row) and returns the total number of security devices ('1's) present in that row. It performs a standard linear character traversal.

#### 2. Main Function: `numberOfBeams(String[] bank)`
```java
    public int numberOfBeams(String[] bank) {
        int pre = 0;
        int ans = 0;
        
        for(String s : bank) {
            int cur = count(s);
            
            // 1. Skip empty rows
            if(cur == 0) continue; 
            
            // 2. Calculate beams and update previous count
            else { 
                // Calculate beams: pre*cur. (If pre=0, this is the first non-empty row, 0 beams added)
                ans += cur*pre; 
                
                // Update pre to the current count for the next iteration
                pre = cur; 
            }
        }

        return ans;
    }
```

1.  **Initialization:**
    *   `pre = 0`: Stores the device count of the previous non-empty row. Starting at 0 handles the case of the very first non-empty row (where no previous row exists to form a beam with).
    *   `ans = 0`: Stores the cumulative total number of beams.

2.  **Iteration:** The code iterates through each row string `s` in the `bank`.

3.  **Current Count:** `int cur = count(s);` determines how many devices are in the current row.

4.  **Handling Empty Rows:** `if(cur == 0) continue;`
    If the current row has no devices, it cannot participate in forming beams. We skip it entirely, ensuring that `pre` retains the count of the last non-empty row.

5.  **Calculating and Updating:** `else { ... }`
    If `cur > 0`, we have found a row that can form beams:
    *   `ans += cur * pre;`: This is the core calculation. If `pre` was 5 and `cur` is 3, we add $5 \times 3 = 15$ beams to the total. If this is the very first non-empty row, `pre` is still 0, and 0 beams are added, correctly skipping calculation until a pair is found.
    *   `pre = cur;`: The critical update. The current row count (`cur`) now becomes the *previous* count (`pre`) for the remaining rows in the bank, establishing the connection reference for the next non-empty row.

6.  **Return:** After processing all rows, `ans` holds the total number of laser beams.

### Time and Space Complexity

Let $N$ be the number of rows in the `bank` (i.e., `bank.length`) and $M$ be the maximum length of any row string.

#### Time Complexity: $O(N \cdot M)$

The solution involves a single linear traversal of the input array (`bank`). Inside this loop, we call the `count` helper function, which iterates over all characters of the current row string.

Since we read every character of every row exactly once, the total time complexity is proportional to the total number of characters in the entire input grid. If the grid is rectangular, this is $O(N \cdot M)$.

#### Space Complexity: $O(1)$

The space used is purely auxiliary. We only use a fixed, constant number of integer variables (`pre`, `ans`, `cur`, and `cnt` inside the helper function) regardless of the size of the input bank. We do not use any dynamic data structures whose size scales with $N$ or $M$.