class Solution {
    public int maxFreqSum(String s) {
        if(s.length() == 1) return 1;
        HashMap<Character , Integer> val = new HashMap<>();
        List<Character> chk = List.of('a' , 'e' , 'i' , 'o' , 'u'); 
        for(char c : s.toCharArray()) {
            val.put(c , val.getOrDefault(c , 0)+1);
        }
        int vc = 0;
        int cc = 0;
        for(char c : val.keySet()) {
            if(chk.contains(c)) vc = Math.max(vc , val.get(c));
            else cc = Math.max(cc , val.get(c));
        }
        return vc + cc;
    }
}