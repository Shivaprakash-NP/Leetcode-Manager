class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean ans = true;
        for(int i = 0; i<9; i++) {
            Set<Character> set1 = new HashSet<>();
            Set<Character> set2 = new HashSet<>();
            for(int j = 0; j<9; j++) {
                if(board[i][j] != '.') {
                    if(set1.contains(board[i][j])) return false;
                    set1.add(board[i][j]);
                }
                if(board[j][i] != '.') {
                    if(set2.contains(board[j][i])) return false;
                    set2.add(board[j][i]);
                }
            }
        }

        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                Set<Character> set = new HashSet<>();
                for(int k = 0; k<3; k++) {
                    for(int l = 0; l<3; l++) {
                        if(board[i*3+k][j*3+l] == '.') continue;
                        if(set.contains(board[i*3+k][j*3+l])) return false;
                        set.add(board[i*3+k][j*3+l]);
                    }
                }
            }
        }
        return true;
    }
}