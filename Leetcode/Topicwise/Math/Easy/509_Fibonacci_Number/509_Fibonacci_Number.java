class Solution {
    public int fib(int n) {
        int a = -1;
        int b = 1;
        int t = 0;
        int ans = 0;
        if(n==1) return 1;
        for(int i = 1 ; i<=n ; i++)
        {
            t = a+b;
            if(i==n-1) ans=t;
            a = b;
            b = t;
        }
        ans += t;
        return ans;
    }
}