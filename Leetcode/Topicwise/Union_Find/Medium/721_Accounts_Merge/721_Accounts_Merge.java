class Solution {
    int[] par;
    int[] size;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        List<List<String>> ans = new ArrayList<>();

        par = new int[n];
        size = new int[n];

        for(int i = 0; i<n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++) {
            for(int j = 1; j<accounts.get(i).size(); j++) {
                String s = accounts.get(i).get(j);
                if(map.containsKey(s)) {
                    union(map.get(s), i);
                } else map.put(s, i);
            }
        }

        Map<Integer, ArrayList<String>> val = new HashMap<>();
        for (String s : map.keySet()) {            
            int a = find(map.get(s));
            val.computeIfAbsent(a, x -> new ArrayList<>()).add(s);
        }

        for(int v : val.keySet()) {
            List<String> tem = new ArrayList<>();
            tem.add(accounts.get(v).get(0));
            List<String> name = val.get(v);
            Collections.sort(name);
            tem.addAll(name);
            ans.add(tem);
        }

        return ans;
    }

    private int find(int a) {
        if(a != par[a]) par[a] = find(par[a]);
        return par[a];
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return;

        if(size[a] > size[b]) {
            par[b] = a;
            size[a]+=size[b];
        } else {
            par[b] = a;
            size[a]+=size[b];
        }
    }
}