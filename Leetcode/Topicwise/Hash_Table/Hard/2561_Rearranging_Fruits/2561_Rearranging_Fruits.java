class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        List<Integer> v1 = new ArrayList<>();
        List<Integer> v2 = new ArrayList<>();
        long ans = 0;

        for(int v : basket1) {
            map.put(v, map.getOrDefault(v, 0)+1);
            m1.put(v, m1.getOrDefault(v, 0)+1);
        }
        for(int v : basket2) {
            map.put(v, map.getOrDefault(v, 0)+1);
            m2.put(v, m2.getOrDefault(v, 0)+1);
        }

        for(int v : map.keySet()) if(map.get(v)%2 == 1) return -1;

        for(int v : map.keySet()) {
            int tot = map.get(v);
            int pre = tot/2;
            int n = pre;
            while(m1.getOrDefault(v, 0) > pre) {
                v1.add(v);
                m1.put(v, m1.getOrDefault(v, 0)-1);
            }
            while(m2.getOrDefault(v, 0) > pre) {
                v2.add(v);
                m2.put(v, m2.getOrDefault(v, 0)-1);
            }        
        }

        Collections.sort(v1);
        Collections.sort(v2, Collections.reverseOrder());

        int minElement = Collections.min(map.keySet());
        for(int i = 0; i < v1.size(); i++) {
            ans += Math.min((long)Math.min(v1.get(i), v2.get(i)), 2L * minElement);
        }
        
        return ans;
    }
}