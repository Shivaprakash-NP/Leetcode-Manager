class Solution {
    final int MOD = 1000000007;
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n+1];
        int[] dp = new int[n+1];

        pre[0] = 1;
        dp[0] = 1;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        int j = 0;

        for(int i = 0; i<n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            while(j <= i && map.lastKey() - map.firstKey() > k) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if(map.get(nums[j]) == 0) map.remove(nums[j]);
                j++;
            }

            if(j > 0) 
                dp[i+1] = (pre[i] - pre[j-1] + MOD)%MOD;
            else 
                dp[i+1] = pre[i];

            pre[i+1] = (pre[i] + dp[i+1])%MOD;
        }

        return dp[n]%MOD;
    }
}
