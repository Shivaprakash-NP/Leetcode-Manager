class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] hash = new int[n];
        int[] dp = new int[n];
        List<Integer> list = new ArrayList<>();
        int max = 0;
        int st = 0;
        
        for(int i = 0; i<n; i++) {
            hash[i] = i;
            for(int j = 0; j<i; j++) {
                if(nums[i]%nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = 1+dp[j];
                    hash[i] = j;
                } 
            }

            if(dp[i] > max) {
                max = dp[i];
                st = i;
            }
        }

        list.add(nums[st]);

        while(hash[st] != st) {
            st = hash[st];
            list.add(nums[st]);
        }

        Collections.reverse(list);
        return list;
    }
}