class Solution {
    public int minSwaps(int[] nums, int[] forbidden) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> fre = new HashMap<>();
        Map<Integer, Integer> fre2 = new HashMap<>();

        for(int v : forbidden) fre.put(v, fre.getOrDefault(v, 0)+1);
        for(int v : nums) fre2.put(v, fre2.getOrDefault(v, 0)+1);

        int n = forbidden.length;
        int cnt = 0;

        for(int i = 0; i<n; i++) {
            if(forbidden[i] == nums[i]) {
                if(fre.get(nums[i])+fre2.get(nums[i]) > n) return -1;
                map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            }
        }

        List<Integer> list = new ArrayList<>();
        
        for(int v : map.values()) list.add(v);
        Collections.sort(list, Collections.reverseOrder());

        if(list.isEmpty()) return 0;

        int sum = 0;
        for(int v : list) sum += v;
        int max = list.get(0);   
        
        return Math.max((sum+1)/2, max);
    }
}