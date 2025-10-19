class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int v : nums) set.add(v);

        for(int i = 1; ; i++) {
            if(!set.contains(i*k)) return i*k;
        }
    }
}