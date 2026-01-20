class Solution {
    int MOD = (int)1e9 + 7;
    Map<String, Integer> map;
    private int dfs(int i, int[] nums, int cur, int t1, int t2, int turn) {
        cur ^= nums[i];
        if(i == nums.length-1) {
            if(turn == 0) return t1 == cur ? 1 : 0;
            else return t2 == cur ? 1 : 0;
        }

        String key = i+" "+cur+" "+turn;
        if(map.containsKey(key)) return map.get(key);
        
        int cut = 0;
        int nocut = dfs(i+1, nums, cur, t1, t2, turn);

        if(turn == 0) {
            if(cur == t1) {
                cut = dfs(i+1, nums, 0, t1, t2, turn == 0 ? 1 : 0)%MOD;
            }
        } else {
            if(cur == t2) {
                cut = dfs(i+1, nums, 0, t1, t2, turn == 0 ? 1 : 0)%MOD;
            }
        }

        int res = (cut+nocut)%MOD;
        map.put(key, res);
        
        return res;
    }
    
    public int alternatingXOR(int[] nums, int target1, int target2) {
        map = new HashMap<>();        
        return dfs(0, nums, 0, target1, target2, 0)%MOD;
    }
}