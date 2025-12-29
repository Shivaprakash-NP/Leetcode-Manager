class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long sep = (long)cost1*(long)need1 + (long)cost2*(long)need2;
        long com = 0;

        if(need1 <= need2) {
            com += ((long)need1*(long)costBoth);
            com += ((long)(need2-need1)*(long)Math.min(cost2, costBoth));
        } else {
            com += ((long)need2*(long)costBoth);
            com += ((long)(need1-need2)*(long)Math.min(cost1, costBoth));
        }
        
        return Math.min(sep, com);
    }
}