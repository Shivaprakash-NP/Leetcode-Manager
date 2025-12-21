### Problem Understanding

The problem "Minimum Deletion Cost to Make All Characters Equal" asks us to find the minimum total cost to delete characters from a given string `s` such that the remaining string consists of only one type of character. For example, if the original string is "abacaba", the goal is to transform it into a string like "aaaa", "bb", or "c" (containing only 'a's, 'b's, or 'c's respectively) by deleting other characters, with the minimum possible total deletion cost. We are provided with the string `s` and an integer array `cost`, where `cost[i]` represents the cost to delete the character `s.charAt(i)`.

### Approach / Intuition

The core idea behind solving this problem efficiently is to realize that to make all characters in the final string identical, we must choose *one specific character type* (e.g., 'a', or 'b', or 'c') and delete *all other character types* from the original string.

For example, if we decide that the final string should only contain 'a's, we must delete all 'b's, 'c's, 'd's, etc., that appear in the original string. The cost incurred in this scenario would be the sum of costs of all characters that are *not* 'a'.

Let's break this down:
1.  **Total Cost of All Characters:** First, calculate the sum of deletion costs for *all* characters in the original string. Let's call this `TotalCost`.
2.  **Cost of Keeping a Specific Character Type:** For each unique character type present in the string (e.g., 'a', 'b', 'c', ...), calculate the sum of costs of *all its occurrences*. Let's call this `CostOfKeeping_X` for character 'X'.
3.  **Deletion Cost for Keeping 'X':** If we choose to keep all occurrences of character 'X' and delete everything else, the total deletion cost will be `TotalCost - CostOfKeeping_X`.
4.  **Minimizing Deletion Cost:** Our goal is to minimize `TotalCost - CostOfKeeping_X`. Since `TotalCost` is a fixed value for a given input, minimizing this expression is equivalent to *maximizing* `CostOfKeeping_X`.

Therefore, the optimal strategy is:
*   Calculate the `TotalCost` of deleting all characters.
*   For each unique character ('a' through 'z'), calculate the sum of costs of all its occurrences in the string.
*   Identify the character type whose total sum of costs (`CostOfKeeping_X`) is the *maximum*. Let's call this `MaxCostToKeep`.
*   The minimum deletion cost required to make all characters in the string equal to this chosen character type will be `TotalCost - MaxCostToKeep`.

This approach ensures we select the character type that contributes the most to the overall cost, thereby minimizing the cost of removing all other characters.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `HashMap<Integer, Long> map`: This hash map is used to efficiently store and retrieve the accumulated sum of costs for each unique character type found in the string.
        *   The `Integer` key represents the character, typically obtained by `s.charAt(i) - 'a'` (0 for 'a', 1 for 'b', ..., 25 for 'z').
        *   The `Long` value stores the sum of deletion costs for all occurrences of that specific character type. `Long` is used to prevent potential integer overflow, as