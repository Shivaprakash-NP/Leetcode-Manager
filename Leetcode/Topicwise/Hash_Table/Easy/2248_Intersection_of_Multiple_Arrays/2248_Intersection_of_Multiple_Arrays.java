class Solution {
    public List<Integer> intersection(int[][] nums) {
        HashMap<Integer , Integer> val = new HashMap<>();
        int n = nums.length;
        for(int[] a : nums) 
            for(int v : a) 
                val.put(v , val.getOrDefault(v , 0)+1);

        ArrayList<Integer> ans = new ArrayList<>();
        for(int v : val.keySet()) 
            if(val.get(v) == n) ans.add(v);
        Collections.sort(ans);
        return ans;
    }
}