class Solution {
    public int maxEvents(int[][] events) {
        int max = Integer.MIN_VALUE;
        int n = events.length;
        int ans = 0;

        for(int[] v : events) max = Math.max(max, v[1]);
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1, j = 0 ; i<=max ; i++) {
            while(j<n && events[j][0] <= i) {
                pq.offer(events[j][1]);
                j++;
            }
            while(!pq.isEmpty() && pq.peek() < i) pq.poll();
            if(!pq.isEmpty()) {
                ans++;
                pq.poll();
            }
        }
        return ans;
    }
}