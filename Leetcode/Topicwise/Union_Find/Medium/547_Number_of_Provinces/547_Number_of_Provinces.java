class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        int[] vis = new int[n+1];

        for(int i = 1 ; i<= n ; i++) {
            if(vis[i] == 0) {
                vis[i] = 1;
                ans++;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);

                while(!q.isEmpty()) {
                    int nn = q.size();
                    for(int k = 0 ; k<nn ; k++) {
                        int cn = q.poll();
                        for(int j = 0 ; j<n ; j++) {
                            if(cn-1 != j && isConnected[cn-1][j] == 1 && vis[j+1] == 0) {
                                q.offer(j+1);
                                vis[j+1] = 1;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}