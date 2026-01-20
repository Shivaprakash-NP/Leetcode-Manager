class Solution {
    private int getFirstZeroIndex(int n) {
        int k = 0;
        while(n != 0) {
            if(n%2 == 0) return k-1;
            n /= 2;
            k++;
        }

        return k-1;
    }

    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for(int i = 0; i<n; i++) {
            int val = nums.get(i);
            if(val == 2) {
                ans[i] = -1;
                continue;
            }
            int k = getFirstZeroIndex(val);
            int x = val^(1<<k);
            ans[i] = x;
        }

        return ans;
    }
}