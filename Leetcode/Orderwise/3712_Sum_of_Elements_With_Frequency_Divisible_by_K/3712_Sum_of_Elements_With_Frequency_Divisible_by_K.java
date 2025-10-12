class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int v : nums) map.put(v, map.getOrDefault(v, 0)+1);
        int sum = 0;
        for(int v : map.keySet()) if(map.get(v) % k == 0) sum += v*map.get(v);

        return sum;
    }
}