class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    private void rec(int i, int cnt, int k, List<Integer> list, int sum, int n) {
        if(sum > n || cnt > k) return; 
        if(i > 9) {
            if(sum == n && cnt == k) 
                ans.add(new ArrayList<>(list));
            return;
        }
        list.add(i);
        rec(i+1, cnt+1, k, list, sum+i, n);
        list.remove(list.size()-1);

        rec(i+1, cnt, k, list, sum, n);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> set = new ArrayList<>();
        rec(1, 0, k, set, 0, n);
        return ans;
    }
}