class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() == t.length())
        {
            int[] arr = new int[26];
            for(int i = 0 ; i < s.length() ; i++)
            {
                arr[s.charAt(i) - 97] +=1;
                arr[t.charAt(i) - 97] -=1;
            }
            for(int x : arr) if(x!=0) return false;
            return true;
        }
        return false;
    }
}