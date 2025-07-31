class Solution {
    public int reverse(int x) {
        int s = (x>=0)?1:-1;
        x = Math.abs(x);
        long ans = 0;
        while(x>0)
        {
            int d = x%10;
            ans = ans*10+d;
            x/=10;
        }
        if(s*ans>Integer.MAX_VALUE || s*ans<Integer.MIN_VALUE) return 0;
        return s*(int)ans;
    }
}