class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> val = new ArrayList<List<Integer>>();
        int n = nums.length;
        int totmask = 1 << n;
        for(int i = 0 ; i<totmask ; i++)
        {
            List<Integer> v1 = new ArrayList<>();
            for(int j = 0 ; j<n ; j++)
                if((i & (1 << j)) != 0) v1.add(nums[j]);
            val.add(v1);
        }
        return val;
    }
}