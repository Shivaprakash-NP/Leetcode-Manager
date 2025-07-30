```markdown
## LeetCode Problem: Find Players With Zero or One Losses - Explained

Here's a detailed explanation of the provided Java code that solves the "Find Players With Zero or One Losses" LeetCode problem:

### 1. Problem Understanding:

The problem asks us to analyze a set of matches where each match is represented as a pair `[winner, loser]`.  We need to identify two groups of players:

*   Players who have *never* lost a match.
*   Players who have lost *exactly one* match.

The output should be a list of two lists: the first list containing players with zero losses, and the second list containing players with one loss, both sorted in ascending order.

### 2. Approach / Intuition:

The core idea is to use a map (specifically, a `TreeMap` to ensure sorted output) to store the number of losses for each player.  We iterate through the `matches` array and:

1.  For each winner, we record their presence in the map (initializing their loss count to 0 if they haven't been seen before).
2.  For each loser, we increment their loss count in the map.

After processing all matches, we iterate through the map to identify players with zero losses and players with one loss, adding them to their respective lists. Finally, we combine these lists into the required output format.

The `TreeMap` is used for automatic sorting and the `Map.putIfAbsent()` method avoids unnecessary operations.

### 3. Data Structures and Algorithms:

*   **`Map (TreeMap)`:** This is the primary data structure.  It stores player IDs as keys and the number of losses as values. `TreeMap` is used so that when we iterate the keys, we get them in sorted order.
*   **`ArrayList`:** Used to store the lists of players with zero losses and one loss, respectively.
*   **`Iteration`:** The algorithm primarily relies on iterating through the input array (`matches`) and then iterating through the entries of the `TreeMap`.
*   **`Map.getOrDefault()`:** Efficiently retrieves the current count or default value.
*   **`Map.putIfAbsent()`:** Efficiently adds a value if it's missing without overwriting an existing value.

### 4. Code Walkthrough:

```java
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer , Integer> val = new TreeMap<>(); // Use TreeMap for sorted keys

        for(int[] v : matches) {
            int w = v[0];  // Winner of the match
            int l = v[1];  // Loser of the match

            val.putIfAbsent(w , 0); // Winner has 0 losses if they haven't appeared before
            val.put(l , val.getOrDefault(l , 0)+1); // Loser's loss count incremented by 1
        }

        ArrayList<Integer> one = new ArrayList<>(); // List to store players with zero losses
        ArrayList<Integer> two = new ArrayList<>(); // List to store players with one loss

        for(int v : val.keySet()) { // Iterate through the sorted player IDs
            if(val.get(v) == 0) one.add(v);  // Add to 'one' if player has zero losses
            if(val.get(v) == 1) two.add(v);  // Add to 'two' if player has one loss
        }

        List<List<Integer>> ans = new ArrayList<>(); // Result list
        ans.add(one);
        ans.add(two);

        return ans;
    }
}
```

*   **`Map<Integer, Integer> val = new TreeMap<>();`**:  A `TreeMap` called `val` is created to store player IDs (integers) as keys and their corresponding number of losses (integers) as values. The use of `TreeMap` ensures that the player IDs will be sorted automatically.
*   **`for(int[] v : matches)`**: This loop iterates through each match in the `matches` array.
*   **`int w = v[0]; int l = v[1];`**:  For each match, `w` is assigned the winner's ID, and `l` is assigned the loser's ID.
*   **`val.putIfAbsent(w, 0);`**: If the winner's ID (`w`) is not already in the `val` map, it's added with a value of 0 (representing zero losses).  This ensures that all players are represented, even if they haven't lost any matches.
*   **`val.put(l, val.getOrDefault(l, 0) + 1);`**:  The loss count for the loser (`l`) is incremented. `val.getOrDefault(l, 0)` retrieves the current loss count for the loser. If the loser's ID is not already in the map, it returns a default value of 0, and then 1 is added to it.
*   **`ArrayList<Integer> one = new ArrayList<>();`**: An `ArrayList` named `one` is created to store players who have not lost any matches.
*   **`ArrayList<Integer> two = new ArrayList<>();`**: An `ArrayList` named `two` is created to store players who have lost only one match.
*   **`for(int v : val.keySet())`**:  This loop iterates through the keys (player IDs) in the `val` map. Since `val` is a `TreeMap`, the player IDs are processed in sorted order.
*   **`if(val.get(v) == 0) one.add(v);`**: If the number of losses for the current player (`v`) is 0, the player's ID is added to the `one` list.
*   **`if(val.get(v) == 1) two.add(v);`**: If the number of losses for the current player (`v`) is 1, the player's ID is added to the `two` list.
*   **`List<List<Integer>> ans = new ArrayList<>();`**: A list of lists `ans` is created to hold the final result.
*   **`ans.add(one); ans.add(two);`**: The `one` and `two` lists are added to the `ans` list.
*   **`return ans;`**: The `ans` list (containing the two lists of players) is returned.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(M + N log N), where M is the number of matches (the length of the `matches` array) and N is the number of distinct players.

    *   Iterating through the `matches` array takes O(M) time.
    *   Adding and retrieving elements from the `TreeMap` take O(log N) time on average for each player. In the worst case, inserting each distinct player may take O(log N). The total number of distinct players is at most `M` in the given problem's constraint so in the worst case inserting distinct players and then iterating distinct players can amount to O(M log M)
    *   Iterating through the keys of the `TreeMap` takes O(N) time, where N is the number of distinct players.
    *   Since N <= M, the overall complexity is dominated by O(M + N log N) since N log N >= N.

*   **Space Complexity:** O(N), where N is the number of distinct players. This is because the `TreeMap` `val` stores information for each distinct player.  The `one` and `two` lists can also potentially store up to N players.  The space used is proportional to the number of distinct players.
