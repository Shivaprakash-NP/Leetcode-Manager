class Solution {
    private int solve(String s, char c1, char c2, int v1, int v2) {
        int g = 0;
        Stack<Character> st = new Stack<>();

        for(char c : s.toCharArray()) {
            if(!st.isEmpty() && c==c2 && st.peek()==c1) {
                st.pop();
                g+=v1;
            } else {
                st.push(c);
            }
        }

        Stack<Character> st1 = new Stack<>();
        while(!st.isEmpty()) {
            char ch = st.pop();
            if(!st1.isEmpty() && ch==c2 && st1.peek()==c1) {
                st1.pop();
                g+=v2;
            } else st1.push(ch);
        }

        return g;
    }

    public int maximumGain(String s, int x, int y) {
        if(x>y) return solve(s, 'a', 'b', x, y);
        else return solve(s, 'b', 'a', y, x);
    }
}