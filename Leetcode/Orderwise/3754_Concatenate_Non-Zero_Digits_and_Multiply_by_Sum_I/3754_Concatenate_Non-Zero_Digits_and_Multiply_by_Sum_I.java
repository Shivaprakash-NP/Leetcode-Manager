class Solution {
    public long sumAndMultiply(int n) {
        if(n == 0) return 0;
        String s = n+"";
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++) {
            if(s.charAt(i) == '0') continue;
            sb.append(s.charAt(i));
            sum += s.charAt(i)-'0';
        }
        if(sb.length() == 0)return 0;
        return sum*Long.parseLong(sb.toString());
    }
}