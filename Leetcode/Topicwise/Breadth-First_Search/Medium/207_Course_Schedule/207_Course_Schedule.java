class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean vis[] = new boolean[numCourses];
        ArrayList<Integer> adj[] = new ArrayList[numCourses];
        int[] ind = new int[numCourses];
        int c = 0;

        for(int i = 0 ; i<numCourses ; i++) adj[i] = new ArrayList<>();

        for(int[] e : prerequisites) {
            adj[e[1]].add(e[0]);
            ind[e[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<numCourses; i++) {
            if(ind[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int node = q.poll();

            for(int v : adj[node]) {
                ind[v]--;
                if(ind[v] == 0) q.offer(v);
            }

            c++;
        }

        return numCourses==c;
    }
}