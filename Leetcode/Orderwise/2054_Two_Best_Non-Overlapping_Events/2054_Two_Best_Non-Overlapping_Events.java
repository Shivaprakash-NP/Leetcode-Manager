class Solution {
    private int bin(int[][] events, int end) {
        int l = 0;
        int r = events.length - 1;
        int ans = -1;

        while(l <= r) {
            int m = (l+r)/2;

            if(events[m][0] > end) {
                ans = m;
                r = m-1;
            } else l = m+1;
        }

        return ans;
    }

    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        int[] pre = new int[n];
        pre[n-1] = events[n-1][2];

        for(int i = n-2; i>=0; i--) pre[i] = Math.max(pre[i+1], events[i][2]);

        int max = 0;
        for(int[] event : events) {
            int ed = event[1];
            int ind = bin(events, ed);
            if(ind != -1) max = Math.max(max, event[2]+pre[ind]);
            else max = Math.max(max, event[2]);
        } 

        return max;
    }
}