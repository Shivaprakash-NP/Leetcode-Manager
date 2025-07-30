class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer , Integer> val = new TreeMap<>();

        for(int[] v : matches) {
            int w = v[0];
            int l = v[1];

            val.putIfAbsent(w , 0);
            val.put(l , val.getOrDefault(l , 0)+1);
        }

        ArrayList<Integer> one = new ArrayList<>();
        ArrayList<Integer> two = new ArrayList<>();

        for(int v : val.keySet()) {
            if(val.get(v) == 0) one.add(v);
            if(val.get(v) == 1) two.add(v);
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(one);
        ans.add(two);

        return ans;
    }
}