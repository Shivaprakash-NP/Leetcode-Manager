class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;
        if(intervals.length == 0) return ans;
        Arrays.sort(intervals , (a,b) -> Integer.compare(a[1] , b[1]));
        int[] last = intervals[0];
        for(int i = 1 ; i<intervals.length ; i++) {
            if(intervals[i][0] < last[1]) {
                ans++;
            } else last = intervals[i];
        }
        return ans;
    }
}