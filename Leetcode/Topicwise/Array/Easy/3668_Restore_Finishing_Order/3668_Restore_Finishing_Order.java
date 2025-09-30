class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        int n = friends.length;
        int[] ans = new int[n];
        int ind = 0;
        Set<Integer> set = new HashSet<>();
        for (int f : friends) {
            set.add(f);
        }

        for(int i = 0; i<order.length; i++) {
            if(set.contains(order[i])) ans[ind++] = order[i];
        }
        return ans;
    }
}