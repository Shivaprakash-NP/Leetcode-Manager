class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] arr = new int[n + 1];

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            arr[l]++;
            arr[r + 1]--;
        }

        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] - arr[i] > 0) return false;
        }

        return true;
    }
}
