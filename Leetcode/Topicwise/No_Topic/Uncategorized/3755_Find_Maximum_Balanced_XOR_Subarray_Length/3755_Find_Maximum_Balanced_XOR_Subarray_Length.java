class Solution {
    public int maxBalancedSubarray(int[] nums) {
        int n = nums.length;
        int len = 0;
        Map<String, Integer> map = new HashMap<>();
        map.put("0_0", -1);
        
        int xor = 0;
        int cnt = 0;
        
        for(int i = 0; i<n; i++) {
            xor ^= nums[i];

            if(nums[i]%2 == 0) cnt++;
            else cnt--;

            String k = xor+"_"+cnt;
            
            if(map.containsKey(k)) {
                len = Math.max(len, i-map.get(k));
            } else map.put(k, i);
        }
        
        return len;
    }
}