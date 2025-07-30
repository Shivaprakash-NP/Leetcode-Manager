class Solution {
    public int[][] divideArray(int[] nums, int k) {
        boolean possible = true;
        int n = nums.length;
        Arrays.sort(nums);
        int j = 0;
        int[][] ans = new int[n/3][3];
        for(int i = 0 ; i<n ; i+=3) {
            int[] arr = Arrays.copyOfRange(nums, i, i + 3);
            if(arr[2]-arr[0] > k) possible = false;
            ans[j++] = arr;
        }
        return (possible)?ans:(new int[][]{});
    }
}