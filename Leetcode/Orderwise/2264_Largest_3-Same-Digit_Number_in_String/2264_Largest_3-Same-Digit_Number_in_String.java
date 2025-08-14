class Solution {
    public String largestGoodInteger(String num) {
        for(int i = 9; i>=0; i--) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(i);
            sb.append(i);
            if(num.contains(sb.toString())) return sb.toString();
        }

        return "";
    }
}