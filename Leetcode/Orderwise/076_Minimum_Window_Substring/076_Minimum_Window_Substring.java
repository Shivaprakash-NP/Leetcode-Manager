class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";

        HashMap<Character , Integer> mapt = new HashMap<>();
        HashMap<Character , Integer> mapw = new HashMap<>();

        for(char c : t.toCharArray()) mapt.put(c , mapt.getOrDefault(c , 0)+1);

        int l = 0;
        int c = 0;
        int len = Integer.MAX_VALUE;
        int ind = 0;
        for(int r = 0 ; r<s.length() ; r++) {
            char ch = s.charAt(r);
            mapw.put(ch , mapw.getOrDefault(ch , 0) + 1);
            if(mapt.containsKey(ch) && mapt.get(ch).intValue() == mapw.get(ch).intValue()) c++;
            while(c==mapt.size()) {
                if(r-l+1 < len) {
                    len = r-l+1;
                    ind = l;
                }
                char lc = s.charAt(l++);
                mapw.put(lc , mapw.get(lc)-1);
                if(mapt.containsKey(lc) && mapw.get(lc) < mapt.get(lc)) c--;
            }
        }

        return (len == Integer.MAX_VALUE)?"":s.substring(ind , ind+len);
    }
}