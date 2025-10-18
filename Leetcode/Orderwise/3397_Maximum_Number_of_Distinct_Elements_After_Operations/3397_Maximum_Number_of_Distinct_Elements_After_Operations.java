class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] arr = new int[n];

        arr[0] = nums[0]-k;

        for(int i = 1; i<n; i++) {
            int l = nums[i]-k;
            int h = nums[i]+k;

            arr[i] = Math.max(arr[i-1]+1, l);

            if(arr[i] > h) arr[i] = h;
        }

        Set<Integer> set = new HashSet();
        for(int v : arr) set.add(v);

        return set.size();
    }
}