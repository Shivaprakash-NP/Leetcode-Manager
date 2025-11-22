class Solution {
    public int minimumFlips(int n) {
        int tem = n;
        StringBuilder sb = new StringBuilder();

        while(tem != 0) {
            sb.append(tem%2);
            tem /= 2;
        }
        String ss = sb.toString();
        String s = sb.reverse().toString();
        
        int cnt = 0;
        for(int i = 0; i<s.length(); i++) {
            if(s.charAt(i) != ss.charAt(i)) cnt++;
        }
        System.out.println(sb.toString()+" "+s);
        return cnt;
    }
}