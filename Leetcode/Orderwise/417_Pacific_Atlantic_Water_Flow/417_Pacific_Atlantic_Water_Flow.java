class Solution {
    private void bfs(int[][] hei, Set<String> set, Queue<int[]> q, boolean[][] vis) {

        int m = hei.length;
        int n = hei[0].length;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();
            set.add(p[0]+" "+p[1]);

            for(int[] d : dir) {
                int i = p[0]+d[0];
                int j = p[1]+d[1];
                if(i>=0 && j>=0 && i<m && j<n && !vis[i][j] && hei[p[0]][p[1]] <= hei[i][j]) {
                    q.offer(new int[]{i, j});
                    vis[i][j] = true;
                }
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        Set<String> P = new HashSet<>();
        Set<String> A = new HashSet<>();

        Queue<int[]> Pq = new LinkedList<>();
        Queue<int[]> Aq = new LinkedList<>();

        boolean[][] Pvis = new boolean[m][n];
        boolean[][] Avis = new boolean[m][n];

        for(int i = 0; i<m; i++) {
            Pq.offer(new int[]{i, 0});
            Aq.offer(new int[]{i, n-1});
            Pvis[i][0] = true;
            Avis[i][n-1] = true;
        }

        for(int i = 0; i<n; i++) {
            Pq.offer(new int[]{0, i});
            Aq.offer(new int[]{m-1, i});
            Pvis[0][i] = true;
            Avis[m-1][i] = true;
        }

        bfs(heights, P, Pq, Pvis);
        bfs(heights, A, Aq, Avis);

        List<List<Integer>> ans = new ArrayList<>();
        for(String p : A) {
            if(P.contains(p)) {
                String[] s = p.split(" ");
                List<Integer> t = new ArrayList<>();
                t.add(Integer.parseInt(s[0]));
                t.add(Integer.parseInt(s[1]));
                ans.add(t);
            }
        }        

        return ans;
    }
}