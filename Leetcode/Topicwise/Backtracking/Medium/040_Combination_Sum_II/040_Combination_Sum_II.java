class Solution {
    private Set<List<Integer>> ans;

    private void find(int ind , int t , int[] arr , List<Integer> ds)
    {
        if(t == 0) 
        {
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i = ind ; i<arr.length ; i++)
        {
            if(i>ind && arr[i]==arr[i-1]) continue;
            else
            {
                if(arr[i]<=t)
                {
                    ds.add(arr[i]);
                    find(i+1 , t-arr[i] , arr , ds);
                    ds.remove(ds.size() - 1);
                }
                else break;
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new HashSet<>();
        Arrays.sort(candidates);
        find(0 , target , candidates , new ArrayList<>());
        return new ArrayList<>(ans);
    }
}