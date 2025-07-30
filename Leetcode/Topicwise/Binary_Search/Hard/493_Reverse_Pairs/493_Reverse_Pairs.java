class Solution {
    private int ans = 0;
    public void merge(int[] nums, int l, int m, int r) {
        int j = m + 1;
        for (int i = l; i <= m; i++) 
        {
            while (j <= r && nums[i] > 2L * nums[j]) j++;
            ans += (j - (m + 1));
        }
        int[] temp = new int[r - l + 1];
        int left = l, right = m + 1, k = 0;
        while (left <= m && right <= r) 
        {
            if (nums[left] <= nums[right]) temp[k++] = nums[left++];
            else temp[k++] = nums[right++];
        }
        while (left <= m) temp[k++] = nums[left++];
        while (right <= r) temp[k++] = nums[right++];
        System.arraycopy(temp, 0, nums, l, temp.length);
    }

    public void mergesort(int[] nums, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergesort(nums, l, m);
        mergesort(nums, m + 1, r);
        merge(nums, l, m, r);
    }

    public int reversePairs(int[] nums) {
        ans = 0;
        mergesort(nums, 0, nums.length - 1);
        return ans;
    }
}