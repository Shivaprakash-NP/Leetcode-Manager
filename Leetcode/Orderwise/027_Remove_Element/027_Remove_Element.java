class Solution {
    public int removeElement(int[] nums, int val) {
        ArrayList<Integer> ans = new ArrayList();
        for(int i = 0 ; i<nums.length ; i++)
        {
            if(nums[i]!=val) ans.add(nums[i]);
        }
        for(int i = 0 ; i<nums.length ; i++)
        {
            if(i<ans.size())
                nums[i] = ans.get(i);
            else
                nums[i] = 0;
        }
        return ans.size();
    }
}