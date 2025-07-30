class Solution {
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for(int v : nums) {
            int sum1 = 0;
            int c = 0;
            for(int i = 1 ; i*i<=v ; i++) {
                if(v%i == 0) {
                    c+=2;
                    sum1+=(v/i);
                    sum1+=(i);
                    if(v/i == i) 
                    {
                        sum1-=i;
                        c--;
                    }
                }
            }
            if(c == 4) sum+=sum1;
        }
        return sum;
    }
}