class Solution {
    private boolean is(String s) {
        if(s.length() <= 0) return false;
        for(char c : s.toCharArray()) {
            if((c>='a' && c<='z') || (c<='Z' && c>='A') || (c<='9' && c>='0') || (c=='_')) continue;
            else return false;
        }
        return true;
    }
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> ans = new ArrayList<>();
        Map<String, List<String>> map = new TreeMap<>();
        int n = code.length;
        for(int i = 0 ; i<n ; i++) {
            if(isActive[i] == false) continue;
            if(!(businessLine[i].equals("electronics") || businessLine[i].equals("grocery") || businessLine[i].equals("pharmacy") || businessLine[i].equals("restaurant"))) continue;
            if(is(code[i])) {
                if(map.get(businessLine[i]) == null) {
                    List<String> dum = new ArrayList<>();
                    dum.add(code[i]);
                    map.put(businessLine[i], dum);
                } else map.get(businessLine[i]).add(code[i]);
            }
        }
        if(map.get("electronics") != null) {
            Collections.sort(map.get("electronics"));
            for(String s : map.get("electronics")) ans.add(s);
        }
        if(map.get("grocery") != null) {
            Collections.sort(map.get("grocery"));
            for(String s : map.get("grocery")) ans.add(s);
        }
        if(map.get("pharmacy") != null) {
            Collections.sort(map.get("pharmacy"));
            for(String s : map.get("pharmacy")) ans.add(s);
        }
        if(map.get("restaurant") != null) {
            Collections.sort(map.get("restaurant"));
            for(String s : map.get("restaurant")) ans.add(s);
        }
        
        return ans;
    }
}