class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        Stack<Character> st = new Stack<>();
        int ans = 0;

        int sat = -1;
        int ed = -1;
        for(int i = 0; i<n; i++) {
            if(directions.charAt(i) != 'L') {
                sat = i;
                break;
            }
        }

        for(int i = n-1; i>=0; i--) {
            if(directions.charAt(i) != 'R') {
                ed = i;
                break;
            }
        }

        if(sat == -1 || ed == -1) return 0;

        
        for(int i = sat; i<=ed; i++) {
            char c = directions.charAt(i);
            if(c != 'S') ans++;
        }

        return ans;
    }
}