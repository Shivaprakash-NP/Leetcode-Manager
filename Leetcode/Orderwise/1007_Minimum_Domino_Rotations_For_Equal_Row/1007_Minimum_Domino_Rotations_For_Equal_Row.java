class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        if(tops.length != bottoms.length) return -1;
        for(int i = 1 ; i<=6 ; i++) {
            boolean valid = true;
            for(int j = 0 ; j < tops.length ; j++) {
                if(tops[j] != i && bottoms[j] != i) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                int m1 = 0;
                int m2 = 0;
                for(int k = 0 ; k<tops.length ; k++) {
                    if(tops[k] != i) m1++;
                    if(bottoms[k] != i) m2++;
                }
                return Math.min(m1 , m2);
            }
        }
        return -1;
    }
}