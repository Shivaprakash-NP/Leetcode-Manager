class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;

        Arrays.sort(potions);
        int[] ans = new int[n];

        for(int i = 0; i<n; i++) {
            int t = (int)(Math.ceil((double) success / spells[i]));
            int l = 0;
            int r = m;

            while(l<r) {
                int mid = (l+r)/2;
                if(potions[mid] < t) l = mid+1;
                else r = mid;
            }

            ans[i] = m-l;
        }

        return ans;
    }
}