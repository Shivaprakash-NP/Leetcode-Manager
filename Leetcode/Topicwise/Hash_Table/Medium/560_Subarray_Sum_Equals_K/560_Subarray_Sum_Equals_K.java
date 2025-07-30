class Solution {
    public int subarraySum(int[] nums, int k) {
        int c = 0, sum = 0;
        Map<Integer, Integer> val = new HashMap<>();
        val.put(0, 1);
        for (int num : nums) 
        {
            sum += num;
            c += val.containsKey(sum-k) ? val.get(sum-k) : 0;
            val.put(sum, val.containsKey(sum) ? val.get(sum) + 1 : 1);
        }
        return c;
    }
}