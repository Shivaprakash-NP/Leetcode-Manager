class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] vis = new int[amount+1];
        Arrays.fill(vis, Integer.MAX_VALUE);

        vis[0] = 0;
        Queue<Integer> q = new LinkedList<>();

        q.offer(0);
        while(!q.isEmpty()) {
            int c = q.poll();
            for(int v : coins) {
                if(v <= amount-c && vis[c+v] > vis[c]+1) {
                    q.offer(c+v);
                    vis[c+v] = vis[c]+1;
                }
            }
        }

        return vis[amount] == Integer.MAX_VALUE ? -1 : vis[amount];
    }
}