### Problem Understanding

The problem "Set Intersection Size At Least Two" asks us to find the minimum possible size of a set of integers, let's call it `S`, such that for every given interval `[a, b]` in a collection `intervals`, the intersection of `S` with `[a, b]` contains at least two points. In simpler terms, we need to pick the fewest integer points possible, such that every interval `[a, b]` from the input list contains at least two of our chosen points.

For example, if `intervals = [[1, 3], [1, 4], [2, 5]]`:
*   If we pick `S = {2, 3, 4}`:
    *   `[1, 3]` intersects with `{2, 3}` (size 2) - OK
    *   `[1, 4]` intersects with `{2, 3, 4}` (size 3) - OK
    *   `[2, 5]` intersects with `{2, 3, 4}` (size 3) - OK
    *   The size of `S` is 3.
This is indeed the minimum for this example.

### Approach / Intuition

This problem can be solved using a greedy approach. The core idea is to process the intervals in a specific order and, for each interval, make the most "helpful" local choice to cover it with two points, maximizing the chance that these points also cover subsequent intervals.

1.  **Sorting Strategy:**
    *   When dealing with intervals, sorting is often the first step. The key is to find the right sorting criteria.
    *   If we sort by the *end point* of the intervals in ascending order, we ensure that when we consider an interval `[a, b]`, its end point `b` is the earliest possible among the remaining intervals that haven't been fully covered. This allows us to make greedy choices that extend as far right as possible.
    *   What if two intervals have the same end point? For example, `[1, 5]` and `[3, 5]`. If we sort `[3, 5]` *before* `[1, 5]` (i.e., by start point in *descending* order for ties in end points), we process the "tighter" interval first. If we pick points `4` and `5` for `[3, 5]`, these points are also guaranteed to cover `[1, 5]` because `1 < 3`. This strategy ensures that points chosen for a shorter, right-aligned interval are more likely to cover longer intervals that end at the same