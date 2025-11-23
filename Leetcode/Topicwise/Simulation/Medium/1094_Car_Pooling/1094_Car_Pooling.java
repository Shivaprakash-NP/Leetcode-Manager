class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a,b) -> {
            if(a[1] != b[1]) return a[1]-b[1];
            else if(a[2] != b[2]) return a[2]-b[2];
            return a[0]-b[0];
        });

        int size = 0;

        for(int[] v : trips) System.out.println(v[0]+" "+v[1]+" "+v[2]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));

        for(int[] p : trips) {
            size += p[0];
            pq.offer(p);
            while(!pq.isEmpty() && pq.peek()[2] <= p[1]) size -= pq.poll()[0];
            if(size > capacity) return false;
        }

        return true;
    }
}