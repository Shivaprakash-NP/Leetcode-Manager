class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int c = 0;
        int rem = 0;
        while(numBottles != 0) {
            c+=numBottles;
            int tem = (numBottles+rem)%numExchange;
            numBottles = (numBottles+rem)/numExchange;
            rem = tem;
        }
        return c;
    }
}