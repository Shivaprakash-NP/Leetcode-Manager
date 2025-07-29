class Solution {
    public boolean checkDivisibility(int n) {
        int tem = n;
        int sum = 0;
        int pro = 1;
        while(tem!=0) {
            int d = tem%10;
            sum+=d;
            pro*=d;
            tem/=10;
        }
        sum+=pro;
        return (n%sum == 0);
    }
}