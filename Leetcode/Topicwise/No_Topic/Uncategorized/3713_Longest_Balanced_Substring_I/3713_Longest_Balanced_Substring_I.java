class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        if(n == 1) return 1;
        int ans = 0;
        
        for(int i = 0; i<n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int j = 0;
            for( j = i; j<n; j++) {
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0)+1);
                Set<Integer> set = new HashSet<>(map.values());
                if(set.size() == 1) ans = Math.max(ans, j-i+1);
            }
        }
        
        int l = 0;
        return ans;
    }
}