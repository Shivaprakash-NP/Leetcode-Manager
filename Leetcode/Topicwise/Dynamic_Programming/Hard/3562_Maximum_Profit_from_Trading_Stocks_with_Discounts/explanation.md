### Problem Understanding

The problem asks us to find the maximum profit achievable by trading a selection of stocks, given a limited budget. We are provided with the current (`present`) and future (`future`) prices for `n` stocks. The stocks are organized in a hierarchical (tree) structure, where some stocks are parents of others.

The core rule is about discounts:
*   If a stock `i` is purchased, and its parent stock `p` is also purchased, then stock `i` can be bought at half its `present` price.
*   Otherwise (if stock `i`'s parent `p` is not purchased, or if stock `i` is the root and has no parent), stock `i` must be bought at its full `present` price.

