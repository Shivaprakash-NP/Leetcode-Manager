```markdown
## Finding 3-Digit Even Numbers: Detailed Explanation

### 1. Problem Understanding:

The problem asks us to find all unique 3-digit even numbers that can be formed using digits from a given array.  We are given an array `digits`, and we need to return an array of all the valid 3-digit even numbers in ascending order. Each digit in the input array can be used at most as many times as it appears in the input array.

### 2. Approach / Intuition:

The core idea is to iterate through all possible 3-digit even numbers (from 100 to 998, incrementing by 2) and check if each number can be formed using the digits from the input array.  We achieve this by checking if we have enough occurrences of each digit required to form the 3-digit number within the input array.

The approach uses a `HashMap` to store the counts of each digit in the input array. For each potential 3-digit even number, we temporarily decrement the counts of the digits required to form the number. If we can successfully decrement the counts of all three digits without the count becoming negative, it means we have enough of each digit, and the number is a valid solution.

**Why this approach?**

*   **Brute-force with pruning:**  Instead of generating all possible combinations of digits (which would be more complex), we iterate through all valid 3-digit even numbers. This significantly reduces the search space.
*   **HashMap for Efficient Counting:**  Using a `HashMap` allows us to efficiently check the availability of digits in the input array and update their counts in O(1) time on average.
*   **Clarity:** While potentially not the absolute most performant, it makes the logic fairly readable and easy to understand.

### 3. Data Structures and Algorithms:

*   **HashMap:** Used to store the counts of each digit in the input array.
*   **ArrayList:** Used to store the valid 3-digit even numbers as they are found.
*   **Iteration:** Iterating through all possible 3-digit even numbers.
*   **Basic Arithmetic:**  Using modulo and division to extract the individual digits from the 3-digit number.

### 4. Code Walkthrough:

```java
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        HashMap<Integer , Integer> val = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int c = 0;
        for(int v : digits) val.put(v , val.getOrDefault(v , 0)+1);
        for(int i = 100 ; i<999 ; i+=2) {
            int d1 = i%10;
            int d2 = i%100 / 10;
            int d3 = i%1000 / 100;
            HashMap<Integer , Integer> tem = new HashMap<>(val);
            if(tem.get(d1) != null){
                tem.put(d1 , tem.get(d1)-1);
                if(tem.get(d1) == 0) tem.remove(d1);
                if(tem.get(d2) != null) {
                    tem.put(d2 , tem.get(d2)-1);
                    if(tem.get(d2) == 0) tem.remove(d2);
                    if(tem.get(d3) != null) {
                        ans.add(i);
                        c++;
                    }
                }
            }
        }
        int[] answer = ans.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
```

1.  **Initialization:**

    *   `HashMap<Integer, Integer> val = new HashMap<>();`:  Creates a HashMap called `val` to store the frequency of each digit in the `digits` array.  The keys are the digits, and the values are their counts.
    *   `ArrayList<Integer> ans = new ArrayList<>();`: Creates an ArrayList called `ans` to store the valid 3-digit even numbers.
    *   `int c = 0;`:  An unused variable. It seems to be a remnant from debugging and can be removed.

2.  **Counting Digit Frequencies:**

    *   `for (int v : digits) val.put(v, val.getOrDefault(v, 0) + 1);`: This loop iterates through the `digits` array. For each digit `v`, it updates the `val` HashMap. `val.getOrDefault(v, 0)` retrieves the current count of digit `v` (or 0 if it's not present).  Then, it increments the count by 1 and stores it back in the `val` HashMap.

3.  **Iterating Through 3-Digit Even Numbers:**

    *   `for (int i = 100; i < 999; i += 2) { ... }`: This loop iterates through all 3-digit even numbers.  It starts from 100 and increments by 2 in each iteration, ensuring that `i` is always even.

4.  **Extracting Digits:**

    *   `int d1 = i % 10;`: Extracts the units digit of `i`.
    *   `int d2 = i % 100 / 10;`: Extracts the tens digit of `i`.
    *   `int d3 = i % 1000 / 100;`: Extracts the hundreds digit of `i`.

5.  **Checking Digit Availability:**

    *   `HashMap<Integer, Integer> tem = new HashMap<>(val);`: Creates a copy of the `val` HashMap called `tem`. We use a copy so that we don't modify the original frequency counts.
    *   The nested `if` statements simulate "using" the digits.
        *   `if (tem.get(d1) != null) { ... }`: Checks if the units digit `d1` is present in the `tem` HashMap (i.e., if we have at least one occurrence of this digit).
        *   `tem.put(d1, tem.get(d1) - 1);`: Decrements the count of digit `d1` in the `tem` HashMap.
        *   `if (tem.get(d1) == 0) tem.remove(d1);`: If the count becomes 0, the digit is removed from the `tem` HashMap to signify that it is fully used.
        * The following `if` blocks repeat the same process for the tens digit `d2` and hundreds digit `d3`.
        * If all three digits can be "used" without running out of any digit, then `ans.add(i);` will add the even number `i` to our result list.

6.  **Converting to int[]:**

    *   `int[] answer = ans.stream().mapToInt(Integer::intValue).toArray();`: Converts the ArrayList `ans` (containing Integer objects) to an int[] array.

7.  **Returning the Result:**

    *   `return answer;`: Returns the int[] array containing all the valid 3-digit even numbers.

### 5. Time and Space Complexity:

*   **Time Complexity:**

    *   `O(N + K)`, where N is the length of the `digits` array and K is the number of 3-digit even numbers (which is a constant: (998-100)/2 + 1 = 450).
    *   `O(N)` for counting digit frequencies in the initial loop.
    *   `O(K)` for iterating through the 3-digit even numbers (100 to 998 with a step of 2).  Inside the loop, the HashMap operations (get, put, remove) take O(1) on average.
    *   `O(K)` for converting the `ArrayList` to an `int[]` array.
    * Since K is a constant, the overall time complexity is O(N).

*   **Space Complexity:**

    *   `O(1)` or `O(D)`, where `D` is the number of distinct digits in the input array.  In this case D is always less than or equal to 10.
    *   `O(D)` for the `val` and `tem` HashMaps (at most 10 entries).
    *   `O(K)` in the worst case for the `ans` ArrayList, where K is the number of valid 3-digit even numbers that can be formed.  However, as K is bounded by 450, this is still considered a limited space.  In practice, the number of valid solutions could be considerably smaller, so the space usage would be less.  If we consider *only* the asymptotic behavior depending on `N`, where the number of valid solutions depends on the input `digits`, this `O(K)` could be interpreted as `O(N^3)` in the worst case where every combination of 3 digits produces a valid solution. However, the problem statement does not state that the output *must* be sorted, and as such we can avoid the `ans` ArrayList entirely and do the sorting after all valid 3-digit numbers have been found, avoiding this potential increase in space usage.
```