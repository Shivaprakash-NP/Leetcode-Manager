class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        TreeMap<Long, Long> minmap = new TreeMap<>();
        TreeMap<Long, Long> exmap = new TreeMap<>();
        PriorityQueue<Long> minq = new PriorityQueue<>();

        for(int i = 1; i<dist+2; i++) minq.offer((long)nums[i]);

        long sum = 0L;
        long minsize = k-1;
        for(int i = 1; i<dist+2; i++) {
            long v = minq.poll();
            if(i<k) {
                minmap.put(v, minmap.getOrDefault(v, 0L)+1L);
                sum += v;
            } else exmap.put(v, exmap.getOrDefault(v, 0L)+1L);
        }

        long min = sum;
        for(int i = dist+2; i<n; i++) {
            long l = nums[i-dist-1];
            long r = nums[i];

            if(minmap.containsKey(l)) {
                sum -= l;
                minsize--;
                minmap.put(l, minmap.get(l)-1);
                if(minmap.get(l) == 0) minmap.remove(l);
            } else {
                exmap.put(l, exmap.get(l)-1);
                if(exmap.get(l) == 0) exmap.remove(l);
            }

            exmap.put(r, exmap.getOrDefault(r, 0L)+1L);
            long exf = exmap.firstKey();
            if(minsize < k-1) {
                sum += exf;
                minsize++;
                minmap.put(exf, minmap.getOrDefault(exf, 0L)+1L);
                exmap.put(exf, exmap.get(exf)-1);
                if(exmap.get(exf) == 0) exmap.remove(exf);
            } else {
                long minl = minmap.lastKey();
                if(minl > exf) {
                    sum -= minl;
                    sum += exf;
                    minmap.put(exf, minmap.getOrDefault(exf, 0L)+1L);
                    exmap.put(exf, exmap.get(exf)-1);
                    if(exmap.get(exf) == 0) exmap.remove(exf);
                    exmap.put(minl, exmap.getOrDefault(minl, 0L)+1L);
                    minmap.put(minl, minmap.get(minl)-1);
                    if(minmap.get(minl) == 0) minmap.remove(minl); 
                }
            }

            min = Math.min(min, sum);
        }

        return min+nums[0];
    }
}