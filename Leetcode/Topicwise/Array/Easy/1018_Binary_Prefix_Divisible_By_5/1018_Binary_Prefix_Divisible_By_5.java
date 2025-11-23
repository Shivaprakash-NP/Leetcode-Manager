class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> res = new ArrayList<>();

        int mod = 0;
        for(int bit : nums) {
            mod = (2*mod+bit)%5;
            res.add(mod == 0);
        }
        
        return res;
    }
}