class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Map<Integer, Integer> fre = new HashMap<>();
        for(int i = 0; i<n; i++) {
            int v = nums[i];
            map.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
            fre.put(v, fre.getOrDefault(v, 0)+1);
        }
        
        int min = Integer.MAX_VALUE;
        for(int v : fre.keySet()) {
            if(fre.get(v) < 3) continue;
            ArrayList<Integer> ind = map.get(v);
            for(int i = 0; i<ind.size()-2; i++) {
                int a = ind.get(i);
                int b = ind.get(i+1);
                int c = ind.get(i+2);
                min = Math.min(min, 2*(c-a));
            }
        }

        return min == Integer.MAX_VALUE?-1:min;
    }
}