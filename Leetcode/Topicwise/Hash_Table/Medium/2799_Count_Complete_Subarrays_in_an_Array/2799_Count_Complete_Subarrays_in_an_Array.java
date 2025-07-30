class Solution {
    public int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> val = new HashSet<>();
        for(int v : nums) val.add(v);
        int n = val.size();
        int c = 0;
        int i = 0;
        int j = 0;
        HashMap<Integer , Integer> v = new HashMap<>();
        while(j < nums.length)
        {
            v.put(nums[j] , v.getOrDefault(nums[j] , 0)+1);
            while(v.size() == n)
            {
                c += nums.length-j;
                v.put(nums[i] , v.get(nums[i])-1);
                if(v.get(nums[i]) == 0) v.remove(nums[i]);
                i++;
            }
            j++;
        }
        return c;
    }
}