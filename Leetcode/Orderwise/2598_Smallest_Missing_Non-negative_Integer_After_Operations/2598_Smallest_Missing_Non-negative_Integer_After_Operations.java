class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] map = new int[value];
        
        for(int v : nums) {
            if(v<0) map[(value-(Math.abs(v)%value))%value]++;
            else map[v%value]++;
        }

        int st = 0;
        while(true) {
            if(map[st%value] == 0) return st;
            map[st%value]--; 
            st++;
        }

        // return -1;
    }
}