class Solution {
    public int[] productExceptSelf(int[] nums) {
        int pro = 1;
        for(int v : nums) pro*=v;
        int[] answer = new int[nums.length];
        for(int i = 0 ; i<nums.length ; i++)
        {
            if(nums[i]==0)
            {
                int ans = 1;
                for(int j = 0 ; j<nums.length ; j++)
                {
                    if(j!=i) ans*=nums[j];
                }
                answer[i] = ans;
            }
            else answer[i] = pro/nums[i];
        }
        return answer;
    }
}