class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for(int v : nums) set.add(v);

        while(set.contains(original)) original *= 2;

        return original;
    }
}