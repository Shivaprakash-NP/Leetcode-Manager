class Solution {
    List<List<Integer>> ans = new ArrayList<>();

    private void comb(int cur, int k, List<Integer> list) {
        if(list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if(cur < 1) return;

        comb(cur-1, k, list);
        list.add(cur);
        comb(cur-1, k, list);
        list.remove(list.size() - 1);
    }

    public List<List<Integer>> combine(int n, int k) {
        comb(n, k, new ArrayList<>());
        return ans;
    }
}