class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int ncnt = 0;
        int ycnt = 0;

        for(char c : customers.toCharArray()) if(c == 'Y') ycnt++;
    
        int minval = ycnt;
        int ind = -1;

        for(int i = 0; i<n; i++) {
            if(customers.charAt(i) == 'Y') ycnt--;
            if(ycnt+ncnt < minval) {
                minval = ycnt+ncnt;
                ind = i;
            }
            if(customers.charAt(i) == 'N') ncnt++;
        }

        return ind+1;
    }
}