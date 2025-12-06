class Solution {
    private boolean is(String s) {
        int l = 0;
        int r = s.length()-1;

        while(l<r) {
            if(s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
    
    private int op(int n) {
        int cost = Integer.MAX_VALUE;

        for(int i = n; ; i++) {
            String s = Integer.toBinaryString(i);
            if(is(s)) {
                cost = Math.min(cost, i-n);
                break;
            }
        }

        for(int i = n; i>=0; i--) {
            String s = Integer.toBinaryString(i);
            if(is(s)) {
                cost = Math.min(cost, n-i);
                break;
            }
        }

        return cost;
    }
    
    public int[] minOperations(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for(int i = 0; i<n; i++) {
            ans[i] = op(nums[i]);
        }

        return ans;
    }
}