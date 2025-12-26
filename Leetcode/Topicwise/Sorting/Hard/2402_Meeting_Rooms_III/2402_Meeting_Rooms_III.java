class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int[] cnt = new int[n];

        Arrays.sort(meetings, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        PriorityQueue<Integer> free = new PriorityQueue<>();
        for(int i = 0; i<n; i++) free.offer(i);

        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        for(int[] m : meetings) {
            while(!busy.isEmpty() && busy.peek()[0] <= m[0]) {
                long[] p = busy.poll();
                int ind = (int)p[1];
                free.offer(ind);
            }

            if(free.isEmpty()) {
                long[] p = busy.poll();
                int ind = (int)p[1];
                long delay = (long)m[1] + (p[0]-(long)m[0]);
                cnt[ind]++;
                busy.offer(new long[]{delay, (long)ind});
            } else {
                int ind = free.poll();
                cnt[ind]++;
                busy.offer(new long[]{(long)m[1], (long)ind});
            }
        }

        int max = 0;
        int ind = -1;

        for(int i = 0; i<n; i++) {
            if(cnt[i] > max) {
                max = cnt[i];
                ind = i;
            }
        }

        return ind;
    }
}