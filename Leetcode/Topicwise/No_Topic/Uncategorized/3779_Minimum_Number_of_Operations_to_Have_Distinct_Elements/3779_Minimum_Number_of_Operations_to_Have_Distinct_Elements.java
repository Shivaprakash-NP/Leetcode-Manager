class Solution {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        int r = n%3;
        if(r == 1) set.add(nums[n-1]);
        if(r == 2) {
            set.add(nums[n-1]);
            set.add(nums[n-2]);
            if(set.size() == 1) return n/3 + 1;
        }

        int pre = set.size();
        for(int i = n-r-1; i>=0; i-=3) {
            set.add(nums[i]);
            set.add(nums[i-1]);
            set.add(nums[i-2]);
            if(set.size() != pre+3) return (i+1)/3;
            pre = set.size();
        }

        return 0;
    }
}