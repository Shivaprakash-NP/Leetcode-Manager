class Solution {
    public long countPairs(String[] words) {        
        Map<String, Long> map = new HashMap<>();
        
        long cnt = 0L;

        for(String s : words) {
            int sh = (s.charAt(0)-'a');

            StringBuilder sb = new StringBuilder();
            for(char c : s.toCharArray()) {
                int nc = (c-'a'-sh+26)%26;
                sb.append((nc+'a'));
            }

            String ns = sb.toString();
            map.put(ns, map.getOrDefault(ns, 0L)+1L);
        }

        for(long k : map.values()) {
            cnt += (k*(k-1))/2;
        }
        
        return cnt;
    }
}