class Solution {
    private int reverse(int n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }
    
    public int minMirrorPairDistance(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++) {
            int re = reverse(nums[i]);
            if(map.containsKey(nums[i])) {
                ans = Math.min(ans, i-map.get(nums[i]));
            }
            map.put(re, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}