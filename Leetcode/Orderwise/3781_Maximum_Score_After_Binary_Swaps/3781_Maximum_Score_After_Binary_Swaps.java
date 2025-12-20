class Solution {
    public long maximumScore(int[] nums, String s) {
        int n = nums.length;
        int cnt = 0;
        int ind = -1;
        long ans = 0L;
        for(int i = 0; i<n; i++) {
            char c = s.charAt(i);
            if(c == '1') {
                cnt++;
                ans += nums[i];
            } else {
                ind = i;
                break;
            }
        }

        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());

        if(ind == -1) return ans;
        for(int i = ind; i<n; i++) {
            q.offer(nums[i]);
            if(s.charAt(i) == '1') {
                ans += q.poll();
            }
        }

        return ans;
    }
}