class Solution {
    public int minLengthAfterRemovals(String s) {
        int a = 0;
        int b = 0;
        int n = s.length();

        for(char c : s.toCharArray()) {
            if(c == 'a') a++;
            else b++;
        }

        return Math.abs(a-b);
    }
}