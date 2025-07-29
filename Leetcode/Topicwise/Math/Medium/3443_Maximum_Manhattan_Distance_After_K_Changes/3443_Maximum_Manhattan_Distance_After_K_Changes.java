class Solution {
    int ans = 0;
    private int sol(char a , char b , int k , String s) {
        int dis = 0;
        int n = 0;
        int S = 0;
        int e = 0;
        int w = 0;
        for(char c : s.toCharArray()) {
            if(c == 'N') {
                if(c==a || c==b || k==0) n++;
                else {
                    S++;
                    k--;
                }
            }
            else if(c == 'S') {
                if(c==a || c==b || k==0) S++;
                else {
                    n++;
                    k--;
                }
            }
            else if(c == 'E') {
                if(c==a || c==b || k==0) e++;
                else {
                    w++;
                    k--;
                }
            }
            else {
                if(c==a || c==b || k==0) w++;
                else {
                    e++;
                    k--;
                }
            }
            dis = Math.max(dis , Math.abs(n-S)+Math.abs(e-w));
        }
        return dis;
    }
    public int maxDistance(String s, int k) {
        ans = Math.max(ans , sol('N' , 'E' , k , s));
        ans = Math.max(ans , sol('N' , 'W' , k , s));
        ans = Math.max(ans , sol('S' , 'E' , k , s));
        ans = Math.max(ans , sol('S' , 'W' , k , s));
        return ans;
    }
}