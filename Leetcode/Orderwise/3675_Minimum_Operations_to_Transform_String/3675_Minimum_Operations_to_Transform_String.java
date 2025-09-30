class Solution {
    public int minOperations(String s) {
        int[] map = new int[26];
        for(char c : s.toCharArray()) map[c-'a']++;

        int ans = 0;
        for(int i = 1; i<25; i++) {
            if(map[i] != 0) {
                map[i+1] += map[i];
                ans++;
            }
        }

        if(map[25] != 0) ans++;
        return ans;
    }
}