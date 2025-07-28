class Solution {
    public long maximumMedianSum(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for(int v : nums) q.offer(v);
        int n = nums.length/3;
        long ans = 0;
        while(n-->0) {
            q.poll();
            ans+=q.poll();
        }
        return ans;
    }
}