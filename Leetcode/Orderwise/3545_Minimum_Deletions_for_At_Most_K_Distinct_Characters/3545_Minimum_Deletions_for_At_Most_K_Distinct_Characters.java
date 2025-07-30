class Solution {
    public int minDeletion(String s, int k) {
        HashMap<Character , Integer> val = new HashMap<>();
        int ans = 0;
        for(char c : s.toCharArray()) val.put(c , val.getOrDefault(c , 0)+1);
        int rem = val.size() - k;
        if(rem == 0) return ans;
        List<Integer> min = new ArrayList<>(val.values());
        Collections.sort(min);
        for(int i = 0 ; i<rem ; i++) ans+=min.get(i);
        return ans;
    }
}