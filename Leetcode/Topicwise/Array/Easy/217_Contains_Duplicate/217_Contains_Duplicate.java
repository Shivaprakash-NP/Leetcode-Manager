class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> val = new HashSet<>();
        for(int v : nums) if(!val.add(v)) return true;
        return false;
    }
}