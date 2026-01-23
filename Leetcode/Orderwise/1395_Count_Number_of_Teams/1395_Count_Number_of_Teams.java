class Solution {
    public int numTeams(int[] rating) {
        int ans = 0;
        int n = rating.length;

        for(int i = 1; i<n-1; i++) {
            int lg = 0;
            int rg = 0;
            int ls = 0;
            int rs = 0;

            for(int j = 0; j<i; j++) {
                if(rating[j] < rating[i]) ls++;
                else if(rating[j] > rating[i]) lg++;
            }

            for(int j = i+1; j<n; j++) {
                if(rating[i] < rating[j]) rg++;
                else if(rating[i] > rating[j]) rs++;
            }

            ans += (ls*rg + lg*rs);
        }

        return ans;
    }
}