class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] netShifts = new int[n + 1]; 
        
        
        for (int[] v : shifts) 
        {
            int start = v[0], end = v[1] + 1; 
            int direction = (v[2] == 0) ? -1 : 1; 
            netShifts[start] += direction;
            netShifts[end] -= direction;
        }
        
        int cumulativeShift = 0;
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < n; i++) 
        {
            cumulativeShift += netShifts[i]; 
            int shift = ((cumulativeShift % 26) + 26) % 26; 
            char newChar = (char) ('a' + (sb.charAt(i) - 'a' + shift + 26) % 26); 
            sb.setCharAt(i, newChar);
        }
        
        return sb.toString();
    }
}
