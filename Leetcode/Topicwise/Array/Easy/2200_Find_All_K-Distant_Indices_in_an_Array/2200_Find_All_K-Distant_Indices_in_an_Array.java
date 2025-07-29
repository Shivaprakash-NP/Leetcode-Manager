class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> ans = new TreeSet<>();

        for(int i = 0 ; i < nums.length ; i++) {
            if(nums[i] == key) {
                int s = Math.max(0 , i-k);
                int e = Math.min(nums.length-1 , i+k);
                for(int j = s ; j<=e ; j++) ans.add(j);
            }           
        }
        
        return new ArrayList<>(ans);
    }
}