class Solution {
    public int maxDifference(String s) {
        int[] map = new int[26];
        int a1 = Integer.MIN_VALUE;
        int a2 = Integer.MAX_VALUE;
        for(char c : s.toCharArray()) map[c-'a']++;
        for(int v : map) {
            if(v!=0) {
                if(v%2 == 0) a2 = Math.min(a2 , v);
                else a1 = Math.max(a1 , v);
            }
        }
        return a1-a2;
    }
}