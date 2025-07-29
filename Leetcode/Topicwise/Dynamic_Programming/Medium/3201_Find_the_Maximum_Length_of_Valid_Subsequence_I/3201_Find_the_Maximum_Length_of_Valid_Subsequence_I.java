class Solution {
    private int find(int[] nums, int p) {
        int c = 0;
        for(int v : nums) {
            if((v&1) == p) {
                c++;
                p ^= 1;
            }
        }
        return c;
    }
    public int maximumLength(int[] nums) {
        int o = 0;
        int e = 0;
        int oe = 0;
        int eo = 0;

        for(int v : nums) {
            if((v&1) == 1) o++;
            else e++;
        }

        oe = find(nums, 0);
        eo = find(nums, 1);

        return Math.max(Math.max(o, e), Math.max(oe, eo));
    }
}