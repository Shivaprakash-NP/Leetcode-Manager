class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int v : nums) map.put(v, map.getOrDefault(v, 0) + 1);

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for(int v : map.keySet()) q.offer(new int[]{map.get(v), v});

        int ind = 0;
        int[] res = new int[k];

        while(ind < k) res[ind++] = q.poll()[1];

        return res;
    }
}