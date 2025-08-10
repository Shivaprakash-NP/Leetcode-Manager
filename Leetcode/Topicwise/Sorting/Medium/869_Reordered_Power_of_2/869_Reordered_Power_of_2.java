class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = String.valueOf(n);  
        char[] arr = s.toCharArray(); 
        Arrays.sort(arr);
        s = new String(arr);

        for(int i = 0; i<=30; i++) {
            int nn = (int) Math.pow(2, i);
            String ss = String.valueOf(nn);  
            char[] arrr = ss.toCharArray(); 
            Arrays.sort(arrr);
            ss = new String(arrr);
            if(s.equals(ss)) return true;
        }

        return false;
    }
}