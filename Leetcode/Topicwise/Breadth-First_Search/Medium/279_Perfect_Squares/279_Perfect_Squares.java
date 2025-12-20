class Solution {
    List<Integer> list;
    int min;
    private void rec(int n, int cnt) {
        if(n == 0) {
            min = Math.min(cnt, min);
        }
        if(cnt >= min) return;

        for(int v : list) {
            if(v > n) continue;
            rec(n%v, cnt+n/v);
        }

    } 

    public int numSquares(int n) {
        list = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for(int i = 1; i<=n; i++) {
            if(((int)Math.sqrt(i)*(int)Math.sqrt(i)) == i) list.add(i);
        }

        rec(n, 0);
        return min;
    }
}