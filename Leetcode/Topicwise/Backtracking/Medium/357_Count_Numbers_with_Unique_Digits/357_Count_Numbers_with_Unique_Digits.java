class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        if(n == 1) return 10;

        long a = 7;
        long pro = 8;
        long ans = 1;
        for(int i = 3; i<=n; i++) {
            ans = ans + pro;
            pro *= a;
            a--;
        }

        return (int)(ans*81L + 10L);
    }
}