class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        int cnt = 0;

        char[][] a = new char[m][n];
        for (int i = 0; i < m; i++) {
            a[i] = strs[i].toCharArray();
        }

        boolean[] equal = new boolean[m]; 
        boolean[] done  = new boolean[m]; 

        for (int i = 0; i < n; i++) {
            boolean[] prevEqual = equal.clone();
            boolean[] prevDone  = done.clone();

            boolean bad = false;

            for (int j = 1; j < m; j++) {
                if (done[j]) continue;

                if (a[j - 1][i] == a[j][i]) {
                    equal[j] = true;
                } 
                else if (a[j - 1][i] > a[j][i]) {
                    cnt++;
                    bad = true;
                    break;
                } 
                else {
                    equal[j] = false;
                    done[j] = true;
                }
            }

            if (bad) {
                equal = prevEqual;
                done = prevDone;
            }
        }

        return cnt;
    }
}
