class Solution {
    private void dfs(int[] ind, char[][] mat) {
        int i = ind[0];
        int j = ind[1];
        if(i<0 || i>=mat.length || j<0 || j>=mat[0].length) return;
        if(mat[i][j] == 'O') {
            mat[i][j] = 'S';
            dfs(new int[]{i, j+1}, mat);
            dfs(new int[]{i, j-1}, mat);
            dfs(new int[]{i+1, j}, mat);
            dfs(new int[]{i-1, j}, mat);
        }
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i<m ; i++) {
            if(board[0][i] == 'O') dfs(new int[]{0, i}, board);
            if(board[n-1][i] == 'O') dfs(new int[]{n-1, i}, board);
        }

        for(int i = 0; i<n ; i++) {
            if(board[i][0] == 'O') dfs(new int[]{i, 0}, board);
            if(board[i][m-1] == 'O') dfs(new int[]{i, m-1}, board);
        }

        for(int i = 0; i<n ; i++) {
            for(int j = 0; j<m ;j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == 'S') board[i][j] = 'O';
            }
        }

    }
}