class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int v : nums) {
            min = Math.min(min, v);
            max = Math.max(max, v);
        }

        Set<Integer> set = new HashSet<>();
        for(int v : nums)set.add(v);
        List<Integer> ans = new ArrayList<>();
        for(int i = min; i<=max; i++) {
            if(!set.contains(i)) ans.add(i);
        }

        return ans;
    }
}