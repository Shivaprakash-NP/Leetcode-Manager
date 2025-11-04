class Solution {
    public int xsum(int[] arr, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]) return b[0] - a[0];
            else return b[1] - a[1];
        });

        for(int v : arr) map.put(v, map.getOrDefault(v, 0) + 1);

        for(int v : map.keySet()) pq.add(new int[]{map.get(v), v});

        while(x-- > 0 && !pq.isEmpty()) {
            int[] a = pq.poll();
            sum += a[0]*a[1];
        }

        return sum;
    }

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n-k+1];

        for(int i = 0; i<n-k+1; i++) {
            ans[i] = xsum(Arrays.copyOfRange(nums, i, i+k), x);
        }

        return ans;
    }
}