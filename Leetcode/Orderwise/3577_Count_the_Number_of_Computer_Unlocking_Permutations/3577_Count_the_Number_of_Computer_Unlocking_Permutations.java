class Solution {
    long mod = (long)1e9 + 7;
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        int v = complexity[0];
        for(int i = 1; i<n; i++) if(v >= complexity[i]) return 0;

        long ans = 1L;
        for(long i = 2; i<=n-1; i++) ans = (ans*i)%mod;

        return (int)(ans%mod);
    }
}