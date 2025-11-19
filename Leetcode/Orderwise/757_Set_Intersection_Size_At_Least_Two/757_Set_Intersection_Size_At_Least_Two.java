class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, (a,b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int v1 = intervals[0][1]-1;
        int v2 = intervals[0][1];

        int cnt = 2;
        for(int i = 1; i<n; i++) {
            int a = intervals[i][0];
            int b = intervals[i][1];

            if(v2 <= b && v1 >= a) continue;
            else if(a <= v2) {
                cnt++;
                v1 = v2;
                v2 = b;
            } else {
                cnt += 2;
                v1 = b-1;
                v2 = b;
            }
        }

        return cnt;
    }
}