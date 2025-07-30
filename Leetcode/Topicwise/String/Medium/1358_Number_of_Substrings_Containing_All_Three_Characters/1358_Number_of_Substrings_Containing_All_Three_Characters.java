class Solution {
    public int numberOfSubstrings(String s) {
        int[] map = new int[3];
        Arrays.fill(map , -1);
        int ans = 0;
        for(int i = 0 ; i<s.length() ; i++) {
            map[s.charAt(i)-'a'] = i;
            ans+=(Math.min(Math.min(map[0] , map[1]) , map[2]) + 1);
        }
        return ans;
    }
}