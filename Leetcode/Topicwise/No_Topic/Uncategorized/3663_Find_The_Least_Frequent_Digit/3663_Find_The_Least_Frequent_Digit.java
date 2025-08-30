class Solution {
    public int getLeastFrequentDigit(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int ans = -1;
        while(n != 0) {
            map.put(n%10, map.getOrDefault(n%10, 0)+1);
            n/=10;
        }
        for(int v : map.keySet()) {
            if(map.get(v) == min) {
                ans = Math.min(ans, v);
            } else if(map.get(v) < min) {
                min = map.get(v);
                ans = v;
            }
        }
        return ans;
    }
}