class Solution {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        long ans = 0L;
        int n = technique1.length;
        
        for(int v : technique2) ans += v;
        
        List<Integer> dif = new ArrayList<>();
        for(int i = 0; i<n; i++) {
            dif.add(technique1[i]-technique2[i]);
        }

        Collections.sort(dif, Collections.reverseOrder());

        for(int i = 0; i<k || (i<n&&dif.get(i)>0); i++) ans += dif.get(i);

        return ans;
    }
}