class Solution {
    public String robotWithString(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        int n = s.length();
        char[] min = new char[n];
        min[n-1] = s.charAt(n-1);

        for(int i = n-2 ; i>=0 ; i--) {
            min[i] = (char) Math.min(s.charAt(i) , min[i+1]);
        }

        for(int i = 0; i < n ; i++) {
            st.push(s.charAt(i));
            while(!st.isEmpty() && (i == n-1 || st.peek() <= min[i+1])) sb.append(st.pop());
        }

        while(!st.isEmpty()) sb.append(st.pop());

        return sb.toString();
    }
}