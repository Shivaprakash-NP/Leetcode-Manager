class Solution {
    public long numOfSubsequences(String s) {
        int n = s.length();
        if(n == 1) return 0;
        
        long l = 0;
        long lc = 0;
        long lct = 0;
        
        for(char ch : s.toCharArray()) {
            if(ch == 'L') l++;
            else if(ch == 'C') lc+=l;
            else if(ch == 'T') lct+=lc;
        }

        int[] preL = new int[n+1];
        int[] preLC = new int[n+1];
        int[] sufT = new int[n+1];
        int[] sufCT = new int[n+1];

        preL[0] = (s.charAt(0) == 'L')?1:0;
        sufT[n-1] = (s.charAt(n-1) == 'T')?1:0;
        
        for(int i = 1; i < n; i++) {
            preL[i] = preL[i-1];
            preLC[i] = preLC[i-1];
            if(s.charAt(i) == 'L') preL[i]++;
            else if(s.charAt(i) == 'C') preLC[i] += preL[i];
        }

        for(int i = n-2; i>=0; i--) {
            sufT[i] = sufT[i+1];
            sufCT[i] = sufCT[i+1];
            if(s.charAt(i) == 'T') sufT[i]++;
            if(s.charAt(i) == 'C') sufCT[i]+=sufT[i];
        }

        long max = 0;
        for(int i = 1; i<n; i++) {
            max = Math.max(max, 1L*sufCT[i-1]);
            max = Math.max(max, 1L*preL[i-1]*sufT[i]);
            max = Math.max(max, 1L*preLC[i-1]);
        }
        max = Math.max(max, 1L*sufCT[0]);
        max = Math.max(max, 1L*preLC[n-1]);
        return lct+max;
    }
}