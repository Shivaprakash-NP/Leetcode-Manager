class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int sum = 0;
        int ans = 0;
        int l = 0;
        int sh = 0;

        for (int i = 0; i <= n; i++) {
            int gap = 0;

            if (i == 0) {
                gap = startTime[0];
            } else if (i == n) {
                gap = eventTime - endTime[n - 1];
            } else {
                gap = startTime[i] - endTime[i - 1];
            }

            sum += gap;
            sh++;

            while (sh > k + 1) {
                if (l == 0) {
                    sum -= startTime[0];
                } else if (l == n) {
                    sum -= eventTime - endTime[n - 1];
                } else {
                    sum -= startTime[l] - endTime[l - 1];
                }
                sh--;
                l++;
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
