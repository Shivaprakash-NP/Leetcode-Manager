class Solution {
    private int com(int n) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));
        sb.reverse();
        return Integer.parseInt(sb.toString(), 2);
    }
    
    public int[] sortByReflection(int[] nums) {
        List<int[]> list = new ArrayList<>();
        int n = nums.length;

        for(int i = 0; i<n; i++) {
            int[] a = new int[2];
            a[0] = com(nums[i]);
            a[1] = nums[i];
            list.add(a);
        }

        Collections.sort(list, (a, b) -> {
            if(a[0] != b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });

        int[] ans = new int[n];
        for(int i = 0; i<n; i++) ans[i] = list.get(i)[1];

        return ans;
    }
}