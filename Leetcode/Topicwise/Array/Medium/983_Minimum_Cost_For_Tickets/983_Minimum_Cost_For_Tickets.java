class Solution {
    private int dfs(int i, int[] day, int[] cost) {
        if(i >= 366) return 0;
        if(day[i] == 0) return dfs(i+1, day, cost);

        if(day[i] != -1) return day[i];
        int op1 = dfs(i+1, day, cost) + cost[0];
        int op2 = dfs(i+7, day, cost) + cost[1];
        int op3 = dfs(i+30, day, cost) + cost[2];

        return day[i] = Math.min(Math.min(op1, op2), op3);
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] day = new int[366];
        for(int d : days) day[d] = -1;

        return dfs(0, day, costs);
    }
}