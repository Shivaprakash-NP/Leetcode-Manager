class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int r = letters.length-1;
        char ans = letters[0];

        while(l <= r) {
            int m = l + (r-l)/2;
            if(letters[m]-'a' > target-'a') {
                ans = letters[m];
                r = m-1;
            } else l = m+1;
        }

        return ans;
    }
}