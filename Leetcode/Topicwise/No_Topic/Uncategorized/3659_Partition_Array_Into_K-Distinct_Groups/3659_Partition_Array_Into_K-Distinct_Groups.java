class Solution {
    public boolean partitionArray(int[] nums, int k) {
        int n = nums.length;
        if(n%k != 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        
        for(int v : nums) {
            map.put(v, map.getOrDefault(v, 0)+1);
            max = Math.max(max, map.get(v));
        }

        if(max > n/k) return false;
        else return true;        
    }
}