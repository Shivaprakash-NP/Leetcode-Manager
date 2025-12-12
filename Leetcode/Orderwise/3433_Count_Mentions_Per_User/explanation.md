### Problem Understanding

The problem, "Count Mentions Per User," asks us to calculate the total number of times each user is "mentioned" based on a chronological sequence of events. We are given the total number of users and a list of events. Each event has a type (either "OFFLINE" or "MESSAGE") and a timestamp.

Here's how mentions are counted:
*   **OFFLINE event:** A user goes offline at a specific timestamp. This event itself doesn't count as a mention but updates the user's last offline time, which is crucial for "HERE" messages.
*   **MESSAGE event:**
    *   If the message content is `"ALL"`, all users are mentioned.
    *   If the message content is `"HERE"`, users are mentioned if they are considered "present". A user is "present" if:
        *   They have never gone offline.
        *   OR, their last "OFFLINE" event occurred at least 60 seconds *before* the current message's timestamp. (e.g., if offline at `T`, they are "present" for messages at `T+60` or later).
    *   If the message content is a space-separated list of user IDs (e.g., "U1 U5 U10"), only those specific users are mentioned.

The key challenge is correctly handling the chronological order of events, especially when multiple events occur at the same timestamp, and accurately applying the "HERE" logic based on a user's last offline status.

### Approach / Intuition

The core intuition behind solving this problem is that events must be processed in the order they occur in time. This is a common pattern for problems involving state changes over time.

1.  **Chronological Processing:** Since the state of a user (specifically, their last offline time) affects how future messages are processed, we must sort all events by their timestamp.
2.  **Tie-breaking for Same Timestamps:** A critical detail arises if an "OFFLINE" event and a "MESSAGE" event occur at the exact same timestamp. For the "HERE" logic to be correct, the user's offline status must be updated *before* any message at that same timestamp tries to determine who is "here". Therefore, "OFFLINE" events should be processed *before* "MESSAGE" events if they share the same timestamp.
3.  **Maintaining User State:** We need an efficient way to store and retrieve the last offline timestamp for each user. An array indexed by user ID is perfect for this.
4.  **Iterative Processing:** After sorting, we iterate through the events.
    *   For "OFFLINE" events, we simply update the `last` offline timestamp for the respective user.
    *   For "MESSAGE" events, we apply the specific logic ("ALL", "HERE", or specific IDs) to increment the mention counts in our result array. The "HERE" logic will consult the `last` offline timestamps.

This approach ensures that at any given point, the `last` array accurately reflects the most recent offline status of users, allowing for correct calculation of "HERE" mentions.

### Data Structures and Algorithms

1.  **`int[] ans`**: An array of integers of size `numberOfUsers`. This acts as our accumulator, where `ans[i]` stores the total mention count for user `i`.
2.  **`int[] last`**: An array of integers of size `numberOfUsers`. `last[i]` stores the timestamp of the last "OFFLINE" event for user `i`. It's initialized to `-1` to indicate that a user has never gone offline.
3.  **`List<List<String>> events`**: The input list of events. Each inner list represents an event with its type, timestamp, and additional data.
4.  **Custom `Comparator` with `Arrays.sort()`**: The `events` list is sorted using a custom comparator.
    *   The primary sorting key is the event timestamp (parsed as an integer).
    *   The secondary sorting key (for events with identical timestamps) prioritizes "OFFLINE" events over "MESSAGE" events.
5.  **String Parsing and Manipulation**: `Integer.parseInt()`, `String.equals()`, `String.split()`, `String.substring()` are used extensively to extract information from the string-based event data.
6.  **Linear Scan**: After sorting, a single loop iterates through all events to process them.

### Code Walkthrough

```java
class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        // 1. Initialize result array and last offline timestamps
        int[] ans = new int[numberOfUsers]; // Stores mention count for each user
        int[] last = new int[numberOfUsers]; // Stores last OFFLINE timestamp for each user

        // Initialize 'last' array. -1 means the user has never gone offline.
        Arrays.fill(last, -1);

        // 2. Sort events chronologically, with a tie-breaking rule
        events.sort((a, b) -> {
            int t1 = Integer.parseInt(a.get(1)); // Timestamp of event 'a'
            int t2 = Integer.parseInt(b.get(1)); // Timestamp of event 'b'

            // Primary sort: by timestamp
            if (t1 != t2) return Integer.compare(t1, t2);

            // Secondary sort (tie-breaker for same timestamps):
            // OFFLINE events come before MESSAGE events.
            // This is crucial so that a user's offline status is updated before
            // a MESSAGE event at the same time uses that status for "HERE" logic.
            if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) return -1; // 'a' (OFFLINE) comes before 'b' (MESSAGE)
            if (a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) return 1;  // 'b' (OFFLINE) comes before 'a' (MESSAGE)

            return 0; // Events are of the same type or order doesn't matter (e.g., MESSAGE-MESSAGE)
        });

        // 3. Process sorted events
        for(List<String> st : events) {
            int t = Integer.parseInt(st.get(1)); // Current event's timestamp

            if(st.get(0).equals("OFFLINE")) {
                // If it's an OFFLINE event, update the last offline timestamp for the user.
                int id = Integer.parseInt(st.get(2)); // User ID is in st.get(2)
                last[id] = t; // Record the time they went offline
            } else { // It's a MESSAGE event
                String s = st.get(2); // Message content is in st.get(2)

                if(s.equals("ALL")) {
                    // If message is "ALL", increment mention count for all users.
                    for(int i = 0; i < numberOfUsers; i++) {
                        ans[i]++;
                    }
                } else if(s.equals("HERE")) {
                    // If message is "HERE", increment mention count for users considered "present".
                    for(int i = 0; i < numberOfUsers; i++) {
                        // A user is "present" if:
                        // 1. They have never gone offline (last[i] == -1)
                        // OR
                        // 2. They went offline at last[i], and the current time 't' is
                        //    at least 60 seconds after their last offline time.
                        //    This implies they are considered "back online" or "available" after 60s of inactivity.
                        if(last[i] == -1 || last[i] + 60 <= t) {
                            ans[i]++;
                        }
                    }
                } else {
                    // If message is a list of specific user IDs (e.g., "U0 U5 U12")
                    String[] id_arr = s.split(" "); // Split the string by space
                    for(String id_str : id_arr) {
                        // Parse user ID from "U<id>" format (e.g., "U123" -> 123)
                        int id = Integer.parseInt(id_str.substring(1)); // Skip 'U' prefix
                        ans[id]++; // Increment mention count for the specific user
                    }
                }
            }
        }

        // 4. Return the final mention counts
        return ans;
    }
}
```

### Time and Space Complexity

Let `N` be `numberOfUsers` and `M` be the number of `events`. Let `L` be the maximum number of specific user IDs in a "MESSAGE" event of type `id_arr` (in the worst case, `L` could be `N`).

#### Time Complexity

1.  **Initialization**:
    *   `ans = new int[numberOfUsers]`: O(N)
    