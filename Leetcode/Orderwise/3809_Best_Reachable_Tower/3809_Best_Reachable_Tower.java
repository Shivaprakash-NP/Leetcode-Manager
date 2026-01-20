class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int max = 0;
        List<int[]> list = new ArrayList<>();


        for(int[] t : towers) {
            int xx = t[0];
            int yy = t[1];
            int q = t[2];

            if(Math.abs(center[0]-xx)+Math.abs(center[1]-yy) <= radius) {
                max = Math.max(max, q);
            }
        }

        for(int[] t : towers) {
            int xx = t[0];
            int yy = t[1];
            int q = t[2];

            if(Math.abs(center[0]-xx)+Math.abs(center[1]-yy) <= radius) {
                if(max == q) list.add(new int[]{xx, yy});
            }
        }

        Collections.sort(list, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        if(list.isEmpty()) return new int[]{-1, -1};
        return list.get(0);

    }
}