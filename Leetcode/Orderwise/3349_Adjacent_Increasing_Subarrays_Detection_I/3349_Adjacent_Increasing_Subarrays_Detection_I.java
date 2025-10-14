class Solution {
    private boolean is(List<Integer> list, int l, int r, int k) {
        for(int i = l+1; i<l+k; i++) if(list.get(i-1) >= list.get(i)) return false;

        for(int i = l+k+1; i<=r; i++) if(list.get(i-1) >= list.get(i)) return false;

        return true;
    }

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        if(k == 1) return true;
        int n = nums.size();
        int l = 0;

        for(int i = (2*k)-1; i<n; i++) {
            if(is(nums, l, i, k)) return true;
            l++;
        }
        
        return false;
    }
}