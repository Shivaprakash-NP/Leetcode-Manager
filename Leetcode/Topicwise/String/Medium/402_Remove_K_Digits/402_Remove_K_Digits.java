class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() <= k) return "0";

        int n = num.length();
        Stack<Character> st = new Stack<>();

        for(char c : num.toCharArray()) {
            while(!st.isEmpty() && k>0 && st.peek() > c) {
                st.pop();
                k--;
            }
            st.push(c);
        }

        while(k-->0 && !st.isEmpty()) st.pop();

        StringBuilder sb = new StringBuilder();
        for(char v : st) {
            if(sb.length() == 0 && v == '0') {
                continue;
            } 
            sb.append(v);
        }
        return (sb.length()==0)?"0":sb.toString();
    }
}