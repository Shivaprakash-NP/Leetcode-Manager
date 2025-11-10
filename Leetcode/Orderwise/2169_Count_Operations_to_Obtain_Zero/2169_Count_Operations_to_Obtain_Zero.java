class Solution {
    public int countOperations(int num1, int num2) {
        if(num1 == 0 || num2 == 0) return 0;

        int c = 0;
        while(num1 != 0) {
            if(num1 < num2) {
                int tem = num1;
                num1 = num2;
                num2 = tem;
            }
            int q = num1/num2;
            num1 -= q*num2;
            c += q;
        }
        
        return c;
    }
}