class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        ArrayList<int[]>[] adj = new ArrayList[n];
        List<Integer> ans = new ArrayList<>();
        
        int[] time = new int[n];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[0] = 0;
        time[firstPerson] = 0;

        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] p : meetings) {
            int u = p[0];
            int v = p[1];
            int t = p[2];

            adj[u].add(new int[]{v,t});
            adj[v].add(new int[]{u,t});
        }

        adj[0].add(new int[]{firstPerson,0});
        adj[firstPerson].add(new int[]{0,0});

        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        q.offer(new int[]{0, 0});
        q.offer(new int[]{firstPerson, 0});

        while(!q.isEmpty()) {
            int[] p = q.poll();

            int u = p[0];
            int t = p[1];

            for(int[] v : adj[u]) {
                if(v[1] < time[v[0]] && t<=v[1]) {
                    q.offer(new int[]{v[0], v[1]});
                    time[v[0]] = v[1];
                }
            }
        }

        for(int i = 0; i<n; i++) if(time[i] != Integer.MAX_VALUE) ans.add(i);

        return ans;
    }
}