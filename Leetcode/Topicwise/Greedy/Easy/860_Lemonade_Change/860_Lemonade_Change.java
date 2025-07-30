class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] c = new int[2];
        for(int v : bills) {
            if(v == 5) c[0]++;
            if(v == 10) {
                if(c[0]==0) return false;
                c[1]++;
                c[0]--;
            }
            if(v == 20) {
                if(!((c[0]>=1 && c[1]>=1) || (c[0]>=3))) return false;
                if((c[0]>=1 && c[1]>=1)) {
                    c[0]--;
                    c[1]--;
                } else {
                    c[0]-=3;
                }
            }
        }
        return true;
    }
}