class Solution {
    private boolean is(int n) {
        if(n == 1) return false;
        
        for(int i = 2; i*i<=n; i++) {
            if(n%i == 0) return false;
        }

        return true;
    }
    
    public boolean completePrime(int num) {
        String s = num+"";
        if(s.length() == 1) return is(num);
        // System.out.println(s.charAt(0));
        
        int n = s.length();
        for(int i = 1; i<=n; i++) {
            int v = Integer.parseInt(s.substring(0,i));
            if(!is(v)) return false;
        }

        for(int i = n-1; i>=0; i--) {
            int v = Integer.parseInt(s.substring(i,n));
            if(!is(v)) return false;
        }

        return true;
    }
}