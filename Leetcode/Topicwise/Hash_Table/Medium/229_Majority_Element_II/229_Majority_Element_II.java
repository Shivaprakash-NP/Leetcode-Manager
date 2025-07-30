class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int t = nums.length/3;
        int c1 = 0;
        int c2 = 0;
        int e1 = Integer.MIN_VALUE;
        int e2 = Integer.MIN_VALUE;
        for (int v : nums) 
        {
            if (v == e1) c1++;
            else if (v == e2) c2++;
            else if (c1 == 0) 
            {
                e1 = v;
                c1 = 1;
            }
            else if (c2 == 0) 
            {
                e2 = v;
                c2 = 1;
            }
            else 
            {
                c1--;
                c2--;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        c1 = 0;
        c2 = 0;
        for(int v : nums)
        {
            if(v==e1) c1++;
            if(v==e2) c2++;
        }
        if(c1>t) ans.add(e1);
        if(c2>t) ans.add(e2);
        return ans;
    }
}