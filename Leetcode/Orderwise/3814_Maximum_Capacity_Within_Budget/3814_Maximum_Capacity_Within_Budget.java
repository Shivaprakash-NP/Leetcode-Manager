class Solution {
    private int bs(List<int[]> list, int r, int tar) {
        int l = 0;
        int ans = -1;

        while(l <= r) {
            int m = l+(r-l)/2;
            if(list.get(m)[0] <= tar) {
                ans = m;
                l = m+1;
            } else r = m-1;
        }

        return ans;
    }
    
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0]-a[0]);

        int n = costs.length;
        int max = 0;
        List<int[]> list = new ArrayList<>();
        
        for(int i = 0; i<n; i++) {
            if(costs[i] < budget) max = Math.max(max, capacity[i]);
            list.add(new int[]{costs[i], capacity[i]});
        }

        Collections.sort(list, (a, b) -> a[0]-b[0]);

        int[] pre = new int[n];
        pre[0] = list.get(0)[1];
        for(int i = 1; i<n; i++) pre[i] = Math.max(pre[i-1], list.get(i)[1]);

        for(int i = 0; i<n; i++) {
            int curcost = list.get(i)[0];
            int tar = budget-curcost-1;

            if(tar >= list.get(0)[0]) {
                int ind = bs(list, i-1, tar);
                if(ind != -1) max = Math.max(max, list.get(i)[1]+pre[ind]);
            }
        }

        return max;
    }
}