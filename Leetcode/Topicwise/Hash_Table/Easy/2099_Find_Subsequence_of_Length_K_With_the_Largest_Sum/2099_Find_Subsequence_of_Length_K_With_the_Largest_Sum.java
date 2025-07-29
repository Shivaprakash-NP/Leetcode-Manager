class Pair {
    int val, ind;
    public Pair(int val, int ind) {
        this.val = val;
        this.ind = ind;
    }
}

class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new Pair(nums[i], i));
        }

        list.sort((a, b) -> Integer.compare(b.val, a.val));
        List<Pair> topK = new ArrayList<>(list.subList(0, k));

        topK.sort((a, b) -> Integer.compare(a.ind, b.ind));

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = topK.get(i).val;
        }
        return ans;
    }
}
