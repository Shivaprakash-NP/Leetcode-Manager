class FindSumPairs {
    Map<Integer, Integer> map = new HashMap<>();
    int[] nums1;
    int[] nums2;
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for(int v : nums2) map.put(v, map.getOrDefault(v, 0)+1);
    }
    
    public void add(int index, int val) {
        int put = nums2[index];
        map.put(put, map.get(put)-1);
        nums2[index]+=val;
        put = nums2[index];
        map.put(put, map.getOrDefault(put, 0) + 1);
    }
    
    public int count(int tot) {
        int c = 0;
        for(int v : nums1) {
            int rem = tot-v;
            c+=map.getOrDefault(rem, 0);
        }
        return c;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */