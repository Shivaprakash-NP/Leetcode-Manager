class Solution {
    public int maximumSum(int[] nums) {
        List<Integer> zero = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();

        for(int v : nums) {
            if(v%3 == 0) zero.add(v);
            else if(v%3 == 1) one.add(v);
            else two.add(v);
        }

        long max = 0;
        
        Collections.sort(one, Collections.reverseOrder());
        Collections.sort(zero, Collections.reverseOrder());
        Collections.sort(two, Collections.reverseOrder());

        if(zero.size() >= 3) {
            max = zero.get(0)+zero.get(1)+zero.get(2);
        }

        if(one.size() >= 3) {
            max = Math.max(max, one.get(0)+one.get(1)+one.get(2));
        }   

        if(two.size() >= 3) {
            max = Math.max(max, two.get(0)+two.get(1)+two.get(2));
        }     

        if(two.size() >= 1 && one.size() >= 1 && zero.size() >= 1) {
            max = Math.max(max, one.get(0)+two.get(0)+zero.get(0));
        }

        return (int)max;
    }
}