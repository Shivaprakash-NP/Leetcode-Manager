class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int ind = n;
        int ptr = 0;

        for(int i = 0; i<n; i++) {
            if(nums[i] >= 0) {
                ind = i;
                break;
            }
        }

        int l = ind-1; 
        int r = ind;

        while(l>=0 && r<n) {
            if(Math.abs(nums[l]) < Math.abs(nums[r])) {
                ans[ptr++] = nums[l]*nums[l];
                l--;
            } else {
                ans[ptr++] = nums[r]*nums[r];
                r++;
            }
        }

        while(l>=0) {
            ans[ptr++] = nums[l]*nums[l];
            l--;
        }

        while(r<n) {
            ans[ptr++] = nums[r]*nums[r];
            r++;
        }

        return ans;
    }
}