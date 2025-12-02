class Solution {
    public int maxDistinct(String s) {
        int[] map = new int[26];

        for(char c : s.toCharArray()) map[c-'a']++;
        int cnt = 0;

        for(int v : map) if(v > 0) cnt++;

        return cnt;
    }
}