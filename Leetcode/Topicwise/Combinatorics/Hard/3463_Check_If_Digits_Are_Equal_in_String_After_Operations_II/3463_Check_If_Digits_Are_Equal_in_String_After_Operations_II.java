class Solution {
    private int nCr(int n, int k) {
        if (k > n) return 0;
        if (k == 0 || k == n) return 1;
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    private long calc(String s) {
        int n = s.length();
        long sum = 0;

        for(int i = 0; i<n; i++) {

            int mod_2 = (((n-1)&i) == i)?1:0;

            int mod_5 = 1;

            int tem_n = n-1;
            int tem_i = i;

            while(tem_n > 0) {
                int N = tem_n%5;
                int K = tem_i%5;

                mod_5 = (mod_5*nCr(N,K)%5)%5;

                tem_n /= 5;
                tem_i /= 5;
            }
            
            int tem = 1;
            for(int v = 0; v<10; v++) {
                if(v%2 == mod_2 && v%5 == mod_5) {
                    tem = v;
                    break;
                }
            }
            
            tem = tem*(s.charAt(i)-'0')%10;
            sum += tem;
            sum %= 10;
        }

        return sum%10;
    }

    public boolean hasSameDigits(String s) {
        return calc(s.substring(0, s.length()-1)) == calc(s.substring(1));
    }
}