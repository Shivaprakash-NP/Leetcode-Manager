class Solution {
    public int maxProduct(int n) {
        int n1 = n;
        int m1 = 0;
        int m2 = 0;
        int c = 0;
        while(n1 != 0) {
            int d = n1%10;
            if(d >= m1) 
                m1 = d;
            n1/=10;
        }
        n1 = n;
        while(n1!=0) {
            if(n1%10 == m1) c++;
            n1/=10;
        }
        if(c > 1) return m1*m1;
        n1 = n;
        while(n1 != 0) {
            int d = n1%10;
            if(d > m2 && d != m1)
                m2 = d;
            n1/=10;
        }
        return m1*m2;
    }
}