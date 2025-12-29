class Solution {
    Map<String, List<Character>> map;
    private boolean dfs(String bottom, String current, int ind) {
        if(bottom.length() == 2) return map.containsKey(bottom);
        if(ind == bottom.length() - 1) return dfs(current, "", 0);

        String key = bottom.charAt(ind)+""+bottom.charAt(ind+1);
        for(char c : map.getOrDefault(key, new ArrayList<>())) {
            if(dfs(bottom, current+c, ind+1)) return true;
        }

        return false;
    } 

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        map = new HashMap<>();
        for(String s: allowed) {
            String key = s.charAt(0)+""+s.charAt(1);
            map.computeIfAbsent(key, x -> new ArrayList<>()).add(s.charAt(2));
        }

        return dfs(bottom, "", 0);
    }
}