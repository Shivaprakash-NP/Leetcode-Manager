class Solution {
    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for(int v : nums) sum += v;
        long target = sum % p;
        int n = nums.length;

        if(target == 0) return 0;

        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);  

        long cur = 0;
        int min = n;

        for(int i = 0; i < n; i++) {
            cur = (cur + nums[i]) % p;
            long need = (cur - target + p) % p;

            if(map.containsKey(need)) {
                min = Math.min(min, i - map.get(need));
            }

            map.put(cur, i);
        }

        return min == n ? -1 : min;
    }
}
