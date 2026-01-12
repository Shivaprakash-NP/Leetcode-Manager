### Problem Understanding

The problem asks us to determine if there exists a continuous subarray within a given array of integers (`nums`) that satisfies two conditions:
1. The subarray must have a length of at least two.
2. The sum of the elements in that subarray must be a multiple of a given integer $k$.

A sum $S$ is a multiple of $k$ if $S \pmod k = 0$.

For example, if `nums = [23, 2, 4, 6, 7]` and $k=6$, the subarray `[2, 4]` sums to 6, which is $1 \times 6$. Since its length is 2, the answer is true.

### Approach / Intuition

A naive approach would be to check every possible continuous subarray (O(N^2)), which is inefficient for large arrays. We can optimize this using the **Prefix Sum** technique combined with **Modulo Arithmetic** and a **Hash Map**.

#### 1. Prefix Sums and Modulo

Let $P[i]$ be the prefix sum up to index $i$. The sum of any subarray from index $j+1$ to $i$ is $P[i] - P[j]$.

We are looking for a subarray sum $S$ such that $S \equiv 0 \pmod k$.
$$ S = P[i] - P[j] $$
If $P[i] - P[j]$ is a multiple of $k$, then:
$$ P[i] - P[j] \equiv 0 \pmod k $$
This implies:
$$ P[i] \equiv P[j] \pmod k $$

**Intuition:** If two different prefix sums, $P[i]$ and $P[j]$ (where $i > j$), have the same remainder when divided by $k$, the elements between index $j+1$ and $i$ form a subarray whose sum is guaranteed to be a multiple of $k$.

#### 2. Using the Hash Map

We use a hash map to store the remainder (`sum % k`) as the key and the index where that remainder was first encountered as the value.

1.  As we iterate and calculate the running prefix sum, we calculate the current remainder, `mod`.
2.  We check if `mod` already exists in the map.
3.  If it exists, we found two indices, $j$ (stored in the map) and $i$ (current index), such that $P[i] \equiv P[j] \pmod k$.
4.  We must ensure the length requirement: the subarray length is $i - j$. We need $i - j \ge 2$.

#### 3. Handling the Base Case

We initialize the map with a crucial entry: `map.put(0, -1)`.
*   Key `0`: Represents a prefix sum that is already a multiple of $k$.
*   Value `-1`: This index serves as a sentinel. If we encounter a prefix sum at index $i$ that is a multiple of $k$ (i.e., `mod == 0`), the subarray from index 0 to $i$ is the valid subarray. The length check becomes $i - (-1) = i + 1$. Since $i$ starts at 0, $i+1$ will be $\ge 2$ if $i \ge 1$. This correctly handles the requirement for a subarray length of at least two.

### Data Structures and Algorithms

| Category | Name | Purpose |
| :--- | :--- | :--- |
| **Algorithm** | Prefix Sums | Efficiently calculate the sum of any continuous subarray. |
| **Algorithm** | Modulo Arithmetic | Used to determine if a sum is a multiple of $k$ by tracking remainders. |
| **Data Structure** | `HashMap<Integer, Integer>` | Stores the mapping of `(remainder, index)` to quickly check if a specific remainder has been seen before. |

### Code Walkthrough

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {        
        Map<Integer, Integer> map = new HashMap<>();
        // Base case: A prefix sum of 0 is encountered before index 0 (at index -1).
        // This handles cases where the subarray starting at index 0 is a multiple of k.
        map.put(0, -1);

        int sum = 0;
        for(int i = 0; i<nums.length; i++) {
            sum += nums[i]; // 1. Calculate the running prefix sum P[i]

            // 2. Calculate the remainder. Note: If k=0, the problem implies sum must be 0.
            // Standard constraints usually ensure k >= 1, or handle k=0 separately.
            // Assuming k >= 1 or k != 0 for standard modulo operation.
            int mod = sum % k; 

            if(map.containsKey(mod)) {
                // 3. If this remainder has been seen before (at index j = map.get(mod)).
                
                // 4. Check the length requirement: i - j >= 2.
                if(i - map.get(mod) >= 2) return true;
                
            } else {
                // 5. If this is the first time we see this remainder, store it.
                // We only store the first occurrence to maximize the distance (i - j) 
                // and satisfy the length requirement if possible.
                map.put(mod, i);
            }
        }

        return false; // No valid subarray found after checking all elements.
    }
}
```

### Time and Space Complexity

#### Time Complexity: $O(N)$

The solution involves a single pass through the input array `nums`, where $N$ is the length of `nums`. Inside the loop, we perform constant-time operations: addition, modulo, and HashMap lookups/insertions (which are $O(1)$ on average). Therefore, the overall time complexity is linear, $O(N)$.

#### Space Complexity: $O(\min(N, K))$

The space complexity is determined by the size of the HashMap.
1.  In the worst case, we store an entry for every element, leading to $O(N)$ space.
2.  However, since we are storing remainders modulo $k$, there are only $k$ possible unique remainders (0 to $k-1$).
The map size is therefore bounded by $k$.

Thus, the space complexity is $O(\min(N, K))$. If $k$ is very large (e.g., $k \approx 10^9$), the complexity is $O(N)$. If $k$ is small (e.g., $k \approx 100$), the complexity is $O(K)$, which is effectively $O(1)$ relative to $N$.