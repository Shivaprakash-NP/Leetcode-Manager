class Solution {
    public boolean isPalindrome(int x) {
        boolean bool = false;
        int temp = x;
        int s = 0;
        while (x>0)
        {
            int d = x%10;
            s = s*10+d;
            x/=10;
        }
        if(temp==s) bool = true;
        return bool;
    }
}