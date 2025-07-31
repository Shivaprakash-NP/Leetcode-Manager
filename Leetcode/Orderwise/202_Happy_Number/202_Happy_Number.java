class Solution {
    public boolean isHappy(int n) {
        HashSet<Long> val = new HashSet<>();
        long sum = n; 
        while (sum != 1) 
        {
            if (val.contains(sum))
                return false; 
            val.add(sum); 
            long temp = sum;
            sum = 0;
            while (temp > 0) 
            {
                long d = temp % 10;
                sum += d * d;
                temp /= 10;
            }
        }
        return true; 
    }
}
