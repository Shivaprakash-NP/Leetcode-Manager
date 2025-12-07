class Solution {
    public int largestPrime(int n) {
        if(n == 1) return 0;
        
        boolean[] sieve = new boolean[n+1];
        Arrays.fill(sieve, true);

        for(int i = 2; i*i<=n; i++) {
            if(sieve[i]) {
                for(int j = i*i; j<=n; j+=i) {
                    sieve[j] = false;
                }
            }
        }

        List<Integer> prime = new ArrayList<>();
        for(int i = 2; i<=n; i++) {
            if(sieve[i]) prime.add(i);
        }

        Set<Integer> set = new HashSet<>();

        int sum = prime.get(0);
        set.add(prime.get(0));
        
        for(int i = 1; i<prime.size(); i++) {
            sum += prime.get(i);
            set.add(sum);
        }

        for(int i = prime.size()-1; i>=0; i--) {
            if(set.contains(prime.get(i))) return prime.get(i);
        }
        
        return 0;
    }
}