class Solution {
    public int maxDepth(String s) {
        int ans = 0;
        int max = 0;
        for(char c : s.toCharArray())
        {
            if(c=='(') ans++;
            else if(c==')') ans--;
            max = Math.max(max , ans);
        }
        return max;
    }
}