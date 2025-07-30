class Solution {
    public String longestCommonPrefix(String[] strs) {
        String ans = strs[0];
        for(int i = 1 ; i<strs.length ; i++)
        {
            int j = 0;
            for( ; j<strs[i].length() && j<ans.length() ; j++) if(ans.charAt(j)!=strs[i].charAt(j)) break;
            ans = strs[i].substring(0 , j);
        }
        return ans;
    }
}