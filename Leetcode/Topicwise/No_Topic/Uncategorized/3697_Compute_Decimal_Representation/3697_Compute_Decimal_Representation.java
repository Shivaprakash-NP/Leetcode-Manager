class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; n!=0; i++) {
            if(n%10 == 0) {
                n/=10;
                continue;
            }
            ans.add(n%10*(int)Math.pow(10, i));
            n/=10;
        }

        int len = ans.size();
        int[] res = new int[len];
        for(int i = 0; i<len; i++) res[i] = ans.get(len-1-i);
        return res;
    }
}