class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int gt = Integer.MIN_VALUE;
        for(int v : candies) gt = Math.max(gt , v);
        ArrayList<Boolean> ans = new ArrayList<>();
        for(int i = 0 ; i<candies.length ; i++)
        {
            ans.add((candies[i]+extraCandies >= gt)?true:false);
        }
        return ans;
    }
}