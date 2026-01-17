### Problem Understanding

The problem asks us to find the maximum total points achievable by selecting exactly one cell from each row of a given 2D integer array `points`. When we select a cell `(r, c1)` from row `r` and then a cell `(r+1, c2)` from the next row `r+1`, a "cost" is incurred. This cost is equal to the absolute difference between their column indices, `|c1 - c2|`. This cost is subtracted from the points obtained from the cell `(r+1, c2)`. We want to maximize the sum of points from all chosen cells, minus the sum of all incurred costs between adjacent rows.

In essence, we are looking for a sequence of column indices `c