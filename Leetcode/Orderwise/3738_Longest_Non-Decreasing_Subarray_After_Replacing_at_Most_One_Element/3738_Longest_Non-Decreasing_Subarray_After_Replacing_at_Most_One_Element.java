class Solution {
    private int v1(int[] nums) {
        int n = nums.length;
        List<Integer> ind = new ArrayList<>();
        ind.add(0);
        int max = 0;
        
        for(int i = 0; i<n-1; i++) {
            if(nums[i] > nums[i+1]) ind.add(i+1);
        }

        ind.add(n);
        if(ind.size() == 2) return n;
        
        for(int i = 1; i<ind.size()-1; i++) {
            int v = ind.get(i);
            if(v != n-1 && nums[v+1] >= nums[v-1]) {
                int val = ind.get(i+1)-ind.get(i-1);
                max = Math.max(max, val);
                System.out.println(val);
            } else {
                max = Math.max(max, v-ind.get(i-1)+1);
                System.out.println(ind.get(i-1)+" "+v);
            }
            
        }

        return max;
    }

    private int v2(int[] nums) {
        int n = nums.length;
        int l = 0; 
        int r = n-1;
        while(l<r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            l++;
            r--;
        }
        
        List<Integer> ind = new ArrayList<>();
        ind.add(0);
        int max = 0;
        
        for(int i = 0; i<n-1; i++) {
            if(nums[i] < nums[i+1]) ind.add(i+1);
        }

        ind.add(n);
        if(ind.size() == 2) return n;
        
        for(int i = 1; i<ind.size()-1; i++) {
            int v = ind.get(i);
            if(v != n-1 && nums[v+1] <= nums[v-1]) {
                int val = ind.get(i+1)-ind.get(i-1);
                max = Math.max(max, val);
                System.out.println(val);
            } else {
                max = Math.max(max, v-ind.get(i-1)+1);
                System.out.println(ind.get(i-1)+" "+v);
            }
            
        }

        return max;
    }
    public int longestSubarray(int[] nums) {
        return Math.max(v1(nums), v2(nums));
    }
}