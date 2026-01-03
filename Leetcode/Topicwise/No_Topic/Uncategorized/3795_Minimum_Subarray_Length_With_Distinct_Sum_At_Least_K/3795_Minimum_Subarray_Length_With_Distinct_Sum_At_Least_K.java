class Solution {
    public int minLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        int l = 0;
        for(int i = 0; i<n; i++) {
            if(!map.containsKey(nums[i])) {
                sum += nums[i];
                map.put(nums[i], 1);
            } else map.put(nums[i], map.get(nums[i])+1);
            
            while(sum >= k) {
                ans = Math.min(ans, i-l+1);
                int v = nums[l];
                if(map.get(v) == 1) {
                    sum -= v;
                    map.remove(v);
                } else map.put(v, map.get(v)-1);
                l++;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}