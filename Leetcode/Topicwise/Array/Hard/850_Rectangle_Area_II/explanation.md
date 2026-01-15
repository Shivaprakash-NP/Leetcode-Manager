### Problem Understanding

The problem "Rectangle Area II" asks us to calculate the total area covered by a given set of rectangles. The rectangles can overlap, and we need to find the area of their union. Since the area can be very large, the result should be returned modulo $10^9 + 7$. This is a classic computational geometry problem often solved using a sweep-line algorithm.

### Approach / Intuition

The core idea behind solving the union of rectangles problem efficiently is the **Sweep Line Algorithm**.

1.  **Transform to 1D Problem:** Instead of directly dealing with 2D overlaps, we can imagine a vertical "sweep line" moving across the plane from bottom to top (along the y-axis). As this line sweeps, the set of active rectangles (