class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int l = 1;
        int r = 1;
        for(int i = 1; i<n; ) {
            l = i-1;
            r = i+1;

            while(l>=0 && nums[i] == nums[l]) l--; 
            while(r<n && nums[i] == nums[r]) r++;

            if(l>=0 && r<n) {
                int v1 = nums[l];
                int v2 = nums[i];
                int v3 = nums[r];
                if((v1<v2 && v3<v2) || (v1>v2 && v3>v2)) ans++;
            }

            i = r;
        }
        return ans;
    }
}