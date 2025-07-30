class Solution {
    private List<List<Integer>> ans;
    private void find(int ind , int[] arr , int t , List<Integer> ds)
    {
        if(ind == arr.length)
        {
            if(t == 0) ans.add(new ArrayList<>(ds));
            return;
        }

        if(arr[ind]<=t)
        {
            ds.add(arr[ind]);
            find(ind , arr , t - arr[ind] , ds);
            ds.remove(ds.size() - 1);
        }
        find(ind+1 , arr , t , ds);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        find(0 , candidates , target , new ArrayList<>());
        return ans;
    }
}