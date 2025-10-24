class Solution {
    private void generate(int n, int[] count, List<Integer> list) {
        if(n > 0 && is(n, count)) list.add(n);
        if(n > 1224444) return;

        for(int d = 1; d<=7; d++) {
            if(count[d] < d) {
                count[d]++;
                generate(10*n + d, count, list);
                count[d]--;
            }
        }
    }

    private boolean is(int n, int[] count) {
        for(int i = 1; i<=7; i++) {
            if(count[i] != 0 && count[i] != i) return false;
        }
        return true;
    }

    public int nextBeautifulNumber(int n) {
        List<Integer> list = new ArrayList<>();

        generate(0, new int[10], list);
        Collections.sort(list);

        for(int v : list) if(v > n) return v;
        return 0;
    }
}