class Solution {
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> val = new ArrayList<>();
        if(nums.length == 0) return val;
        int l = nums[0]; 
        for(int i = 1 ; i<nums.length ; i++)
        {
            if(nums[i] != nums[i-1]+1)
            {
                if(l == nums[i-1]) val.add(String.valueOf(l));
                else val.add(l+"->"+nums[i-1]);
                l = nums[i];
            }
        }
        if(l==nums[nums.length-1]) val.add(l+"");
        else val.add(l+"->"+nums[nums.length-1]);
        return val;
    }
}