class Solution {
    public long minCost(String s, int[] cost) {
        Map<Integer, Long> map = new HashMap<>();
        long c = 0L;

        int n = cost.length;

        for(int i = 0; i<n; i++) {
            c += cost[i];

            map.put(s.charAt(i)-'a', map.getOrDefault(s.charAt(i)-'a', 0L)+cost[i]);
        }

        long max = Long.MIN_VALUE;
        for(int ch : map.keySet()) max = Math.max(max, map.get(ch));

        return c - max;
    }
}