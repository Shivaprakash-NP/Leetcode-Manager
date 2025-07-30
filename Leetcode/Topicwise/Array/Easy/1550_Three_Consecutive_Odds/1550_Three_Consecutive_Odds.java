class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int c = 0;
        for(int i = 0 ; i < arr.length ; i++) {
            if(c >= 3) return true;
            if((arr[i]&1) == 1) c++;
            else c = 0;
        }
        return (c>=3)?true:false;
    }
}