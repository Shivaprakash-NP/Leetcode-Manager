class Solution {
    final int MOD = 1_000_000_007;

    public int specialTriplets(int[] nums) {
        HashMap<Integer, Long> m1 = new HashMap<>();
        HashMap<Integer, Long> m2 = new HashMap<>();

        for (int v : nums)
            m2.put(v, m2.getOrDefault(v, 0L) + 1);

        long c = 0;
        m1.put(nums[0], 1L);
        m2.put(nums[0], m2.get(nums[0]) - 1);

        for (int i = 1; i < nums.length - 1; i++) {
            m2.put(nums[i], m2.get(nums[i]) - 1);

            long c1 = m1.getOrDefault(2 * nums[i], 0L);
            long c2 = m2.getOrDefault(2 * nums[i], 0L);

            c = (c + (c1 * c2) % MOD) % MOD;

            m1.put(nums[i], m1.getOrDefault(nums[i], 0L) + 1);
        }

        return (int) c;
    }
}
