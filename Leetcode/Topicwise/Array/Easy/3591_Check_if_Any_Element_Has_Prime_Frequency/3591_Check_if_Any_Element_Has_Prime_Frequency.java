class Solution {
    private boolean isprime(int x) {
        if(x == 0 || x == 1) return false;
        for(int i = 2 ; i*i <= x ; i++) {
            if(x%i == 0) return false;
        }
        return true;
    }
    
    public boolean checkPrimeFrequency(int[] nums) {
        Map<Integer , Integer> map = new HashMap<>();
        for(int v : nums) map.put(v , map.getOrDefault(v , 0)+1);

        for(int v : map.keySet()) if(isprime(map.get(v))) return true;

        return false;
    }
}