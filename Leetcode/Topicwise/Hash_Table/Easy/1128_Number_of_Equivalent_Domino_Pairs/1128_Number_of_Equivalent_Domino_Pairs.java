class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        HashMap<ArrayList<Integer> , Integer> val = new HashMap<>();
        int c = 0;
        for(int i = 0 ; i<dominoes.length ; i++) {
            ArrayList<Integer> v = new ArrayList<>();
            v.add(Math.min(dominoes[i][0],dominoes[i][1]));
            v.add(Math.max(dominoes[i][0],dominoes[i][1]));
            c+=(val.get(v)==null)?0:val.get(v);
            val.put(v , val.getOrDefault(v , 0)+1);
        }
        return c;
    }
}