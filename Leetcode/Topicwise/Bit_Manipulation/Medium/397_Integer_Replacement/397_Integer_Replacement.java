class Solution {
    public int integerReplacement(int n) {
        int ans = 0;
        long nn = n;

        while(nn!=1) {
            if(nn == 3) {
                ans+=2;
                break;
            }

            if(nn%2 == 0) nn /= 2;
            else if(((nn-1)/2)%2 == 0) nn--;
            else nn++;

            ans++;
        }

        return ans;
    }
}