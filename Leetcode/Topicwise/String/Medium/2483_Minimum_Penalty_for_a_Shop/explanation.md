### Problem Understanding

The problem asks us to find the optimal closing time for a shop to minimize a specific "penalty". We are given a string `customers` where each character represents an hour: 'Y' means a customer visited, and 'N' means no customer visited. The length of the string `n` corresponds to `n` hours, indexed from 0 to `n-1`.

If the shop closes at hour `j` (where `j` can range from 0 to `n`):
*   The shop is considered open during hours `0, 1, ..., j-1`.
*   The shop is considered closed during hours `j, j+1, ..., n-1`.

The penalty is calculated as follows:
*   For every hour the