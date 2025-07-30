class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] bin = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i<s.length() ; i++) {
            if(s.charAt(i) == '(') st.push(i);
            else if(!st.isEmpty()) {
                bin[i] = 1;
                bin[st.pop()] = 1;
            }
        }
        int c = 0;
        int ans = 0;
        for(int v : bin) {
            if(v == 1) c++;
            else {
                ans = Math.max(ans , c);
                c = 0;
            }
        }
        ans = Math.max(ans , c);
        return ans;
    }
}