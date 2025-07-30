class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int tem = x;
        int sum = 0;
        while(tem != 0) {
            sum += tem%10;
            tem/=10;
        }
        return (x%sum==0)?sum:-1;
    }
}