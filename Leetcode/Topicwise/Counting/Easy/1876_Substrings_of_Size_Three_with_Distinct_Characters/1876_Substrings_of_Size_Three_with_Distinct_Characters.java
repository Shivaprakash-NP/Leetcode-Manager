class Solution {
    public int countGoodSubstrings(String s) {
        int n = s.length();
        int l = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int r = 0; r<n; r++) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0)+1);
            while(r-l+1 > 3) {
                char lc = s.charAt(l++);
                map.put(lc, map.get(lc)-1);
                if(map.get(lc) == 0) map.remove(lc);
            }
            if(r-l+1 == 3) if(map.size() == 3) ans++;
        }

        return ans;
    }
}