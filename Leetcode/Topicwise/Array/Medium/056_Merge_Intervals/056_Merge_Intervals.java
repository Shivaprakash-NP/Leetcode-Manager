class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] cur = intervals[0];
        ArrayList<int[]> ans = new ArrayList<>();
        for(int i = 1 ; i < intervals.length ; i++) {
            if(cur[1] >= intervals[i][0]) {
                cur[1] = Math.max(intervals[i][1] , cur[1]);
            } else {
                ans.add(new int[]{cur[0], cur[1]}); 
                cur = intervals[i];
            }
        }
        ans.add(new int[]{cur[0], cur[1]}); 
        return ans.toArray(new int[ans.size()][]);
    }
}