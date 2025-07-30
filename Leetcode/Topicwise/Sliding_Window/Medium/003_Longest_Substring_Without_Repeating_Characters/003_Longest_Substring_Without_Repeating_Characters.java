class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character , Integer> map = new HashMap<>();
        int len = 0;
        int l = 0, r = 0;
        while(r<s.length()) {
            if(map.containsKey(s.charAt(r)) && l<=map.get(s.charAt(r))) 
                l = map.get(s.charAt(r)) + 1;
            len = Math.max(len , r-l+1);
            map.put(s.charAt(r) , r++);
        }
        return len;
    }
}