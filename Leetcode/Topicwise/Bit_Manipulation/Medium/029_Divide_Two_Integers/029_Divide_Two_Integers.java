class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) 
            return Integer.MAX_VALUE;
        
        if(dividend == Integer.MAX_VALUE && divisor == 1)
            return Integer.MAX_VALUE;
        
        int sign = ((dividend < 0) == (divisor < 0)) ? 1 : -1;
        
        if(Math.abs(divisor)==1)
            return divisor*dividend;
            
        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);
        
        int quotient = 0;
        while (lDividend >= lDivisor) {
            lDividend -= lDivisor;
            quotient++;
        }
        
        return sign * quotient;
    }
}
