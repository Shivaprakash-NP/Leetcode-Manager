class Solution {
    int[] par;
    int[] size;
    public int removeStones(int[][] stones) {
        int r = 0;
        int c = 0;

        for(int[] s : stones) {
            r = Math.max(r, s[1]);
            c = Math.max(c, s[0]);
        }

        par = new int[r+c+2];
        size = new int[r+c+2];

        for(int i = 0; i<r+c+2; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for(int[] s : stones) {
            int a = s[1];
            int b = r+s[0]+1;
            union(a, b);
        }

        Set<Integer> set = new HashSet<>();
        for(int[] s : stones) {
            int a = s[1];
            int b = r+s[0]+1;
            set.add(find(par[a]));
            set.add(find(par[b]));
        }

        return stones.length-set.size();
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
            par[a] = b;
            size[b]+=size[a];
        }
    }
}