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

        int max = 0;
        int sum = 0;
        int ans = 0;

        // if(list.size() == 1) return list.get(0);
        
        
        for(int i = 0; i<list.size(); i++) {
            if(sum+list.get(i) > max) {
                ans += cnt;
                max = -max+sum+list.get(i);
                sum = 0;
                cnt = max;
            } else {
                sum += list.get(i);
            } 
        }
    
        ans += cnt;    
        
        return ans;
    }
}