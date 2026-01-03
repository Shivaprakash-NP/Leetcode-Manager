### Problem Understanding

The problem asks us to find the total number of ways to paint an `N` row by 3 column grid. We have three available colors (let's say Red, Green, Blue, or 0, 1, 2). The constraint is that no two adjacent cells (horizontally or vertically) can have the same color. We need to return the total count modulo `10^9 + 7`.

For example, if N=1, we have a 1x3 grid.
A valid row pattern must have `color[0] != color[1]` and `color[1] != color[2]`.
Also, `color[0]` can be the same as `color[2]` (e.g., RGR)