class Solution {
    int[] parent;
    int[] size;
    
    private int find(int a) {
        if(parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;

        if(size[a] < size[b]) {
            parent[a] = b;
            size[b] += size[a];
        } else {
            parent[b] = a;
            size[a] += size[b];
        }
    }

public int[] processQueries(int c, int[][] connections, int[][] queries) {
        parent = new int[c+1];
        size = new int[c+1];

        for(int i = 0; i<=c; i++) {
            parent[i] = i;
            size[i] = 1;
        }        

        for(int[] p : connections) union(p[0], p[1]);
        
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        for(int i = 1; i<=c; i++) {
            int root = find(i);
            map.computeIfAbsent(root, k -> new TreeSet<>()).add(i);    }

        List<Integer> ans = new ArrayList<>();

        for(int[] q : queries) {
            int opt = q[0];
            int u = q[1];
            int root = find(u);

            if(opt == 2) {
                map.get(root).remove(u);   
                online[u] = false;        
            } else {
                TreeSet<Integer> s = map.get(root);
                if (s.isEmpty()) ans.add(-1);
                else {
                    int first = s.first();
                    ans.add(online[u] ? u : first);
                }
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}