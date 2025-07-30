class Solution {
    private int rev(int num) {
        int r = 0;
        while(num > 0) {
            r = r*10+num%10;
            num/=10;
        }
        return r;
    }

    public boolean sumOfNumberAndReverse(int num) {
        if(num == 0) return true;
        for(int i = 1 ; i<=num ; i++) {
            if(i + rev(i) == num) return true;
        }
        return false;
    }
}