class Solution {
    public int addDigits(int num) {
        int sum = 0;
        int tem = num;
        while(tem != 0) {
            int d = tem%10;
            sum += d;
            int q = sum/10;
            sum = q + sum%10;
            tem/=10;
        }
        int q = sum/10;
        sum = q + sum%10;
        return sum;
    }
}