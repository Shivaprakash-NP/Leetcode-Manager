### Problem Understanding

The problem, "Maximum Capacity Within Budget", asks us to select items such that their total cost does not exceed a given `budget`, and their total capacity is maximized. Based on the provided solution's structure, which involves sorting, prefix maximums, and binary search for a complementary item, it's implied that we are looking to select **at most two distinct items**.

Specifically, we are given:
*   `costs`: An array where `costs[i]` is the cost of the i-th item.
*   `capacity`: An array where `capacity[i]` is the capacity of the i-th item, corresponding to `costs[i]`.
*   `budget`: The maximum allowed total cost.

Our goal is to find the maximum possible total capacity by picking either one item or two distinct items, such that the sum of their costs is strictly less than the `budget`.

### Approach / Intuition

The solution tackles the problem by considering two main scenarios:

1.  **Picking a single item:** Iterate through all items. If an item's cost is strictly less than the `budget`, its capacity is a candidate for the maximum capacity. We keep track of the highest capacity found this way.

2.  **Picking two distinct items:** This is the more complex part.
    *   **Sorting by Cost:** To efficiently find pairs, it's beneficial to sort all items based on their cost in ascending order. This allows us