class Solution {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;

        for(int n = num1; n<=num2; n++) {
            if(n/100 == 0) continue;
            String s = ""+n;
            int len = s.length();
            for(int i = 1; i<len-1; i++) {
                if(s.charAt(i) < s.charAt(i-1) && s.charAt(i) < s.charAt(i+1)) ans++;
                if(s.charAt(i) > s.charAt(i-1) && s.charAt(i) > s.charAt(i+1)) ans++;
            }
        }

        return ans;
    }
}