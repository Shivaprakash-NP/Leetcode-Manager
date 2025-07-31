class Solution {
    public int prefixCount(String[] words, String pref) {
        int c = 0;
        int s = pref.length();
        for(String v : words)
        {
            if(v.length()>=s)
            {
                String v2 = v.substring(0, s);
                if(pref.equals(v2))
                    c++;
            }
        }
        return c;
    }
}