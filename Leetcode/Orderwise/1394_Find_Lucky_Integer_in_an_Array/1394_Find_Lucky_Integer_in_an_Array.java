class Solution {
    public int findLucky(int[] arr) {
        int[] map = new int[501];
        for(int v : arr) map[v]++;
        int ans = -1;
        for(int i = 1 ; i<501 ; i++) {
            if(map[i] == i) ans = i;
        }
        return ans;
    }
}