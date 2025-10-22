class Solution {
    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;  
    }

    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left - 1; 
    }


    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        int ans = 0;

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for(int v : nums) map.put(v, map.getOrDefault(v, 0) + 1);

        for(int v : nums) {
            set.add(v);
            set.add(v-k);
            set.add(v+k);
        }

        Arrays.sort(nums);

        for(int i : set) {
            int v1 = i-k;
            int v2 = i+k;

            int i1 = lowerBound(nums, v1);
            int i2 = upperBound(nums, v2);

            int tot = Math.max(0, i2-i1+1);
            int cnt = Math.min(numOperations, tot-map.getOrDefault(i, 0));

            ans = Math.max(ans, cnt+map.getOrDefault(i, 0));
        }

        return ans;
    }
}