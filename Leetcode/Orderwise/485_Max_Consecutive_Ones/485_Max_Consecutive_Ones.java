class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int c = 0;
        int m = 0;
        for(int v : nums)
        {
            if(v==1) c++;
            else
            {
                m = Math.max(m,c);
                c = 0;
            }
        }
        m = Math.max(m , c);
        return m;
    }
}