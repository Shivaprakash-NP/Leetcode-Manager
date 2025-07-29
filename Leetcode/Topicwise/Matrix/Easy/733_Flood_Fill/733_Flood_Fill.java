class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] == color) return image;
        Queue<int[]> q = new LinkedList<>();
        int n = image.length;
        int m = image[0].length;
        int val = image[sr][sc];
        image[sr][sc] = color;
        q.offer(new int[]{sr, sc});

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0 ; i<size ; i++) {
                int[] p = q.poll();
                for(int[] d : dir) {
                    int ni = d[0]+p[0];
                    int nj = d[1]+p[1];
                    if(ni>=0 && nj>=0 && ni<n && nj<m && image[ni][nj]==val) {
                        image[ni][nj] = color;
                        q.offer(new int[]{ni, nj});
                    }
                }
            }
        }
        return image;
    }
}