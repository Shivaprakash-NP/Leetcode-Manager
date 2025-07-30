# LeetCode: Lemonade Change - Detailed Explanation

**1. Problem Understanding:**

The problem "Lemonade Change" asks whether a lemonade stand can provide change for every customer given a sequence of bills they receive.  Customers pay only with $5, $10, and $20 bills. The stand starts with no money and must give exact change using only $5 and $10 bills.  The function should return `true` if change can be given to every customer, and `false` otherwise.

**2. Approach / Intuition:**

The solution uses a greedy approach combined with a simple counter.  Instead of exploring all possible change combinations (which would be computationally expensive), it directly checks if sufficient smaller bills are available to make change for each incoming $10 or $20 bill.  This greedy strategy works because it prioritizes using the smallest possible bills for change. If a situation arises where sufficient smaller bills are unavailable, it immediately indicates that change cannot be made.  This makes the approach efficient.


**3. Data Structures and Algorithms:**

* **Data Structure:** An integer array `c` of size 2 is used. `c[0]` counts the number of $5 bills and `c[1]` counts the number of $10 bills.  This is a simple and efficient way to track the available change.
* **Algorithm:** The algorithm is a greedy algorithm with a linear scan. It iterates through the incoming bills and checks the availability of appropriate change for each bill. The algorithm terminates early if it encounters a situation where change cannot be provided.


**4. Code Walkthrough:**

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] c = new int[2]; // c[0] = # of $5 bills, c[1] = # of $10 bills
        for(int v : bills) { // Iterate through each incoming bill
            if(v == 5) c[0]++; // If it's a $5 bill, increment the $5 count
            if(v == 10) { // If it's a $10 bill
                if(c[0]==0) return false; // Check if at least one $5 bill is available
                c[1]++; // Increment the $10 count
                c[0]--; // Decrement the $5 count (used for change)
            }
            if(v == 20) { // If it's a $20 bill
                if(!((c[0]>=1 && c[1]>=1) || (c[0]>=3))) return false; // Check for sufficient change: one $10 and one $5 OR three $5
                if((c[0]>=1 && c[1]>=1)) { // If we have one $10 and one $5
                    c[0]--; // Decrement $5 count
                    c[1]--; // Decrement $10 count
                } else { // Otherwise, we must have at least three $5 bills
                    c[0]-=3; // Decrement $5 count by 3
                }
            }
        }
        return true; // If we reach here, change was possible for all bills
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the number of bills in the input array.  The code iterates through the array once. All operations within the loop are constant time.
* **Space Complexity:** O(1). The space used by the `c` array is constant regardless of the input size.  The space complexity is independent of the input array size.  Only a fixed-size array is used to store the counts of $5 and $10 bills.


This solution efficiently solves the "Lemonade Change" problem using a linear time and constant space approach.  The greedy strategy simplifies the logic and ensures that the algorithm runs quickly, even for a large number of transactions.
