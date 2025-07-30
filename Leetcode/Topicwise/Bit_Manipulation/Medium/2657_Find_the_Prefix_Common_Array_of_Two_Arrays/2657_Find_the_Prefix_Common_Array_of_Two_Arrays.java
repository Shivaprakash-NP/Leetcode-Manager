class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        HashMap<Integer , Integer> val = new HashMap<>();
        int n = A.length;
        int[] C = new int[n];
        C[n-1] = n;
        for(int i = 0 ; i<n-1 ; i++) {
            val.put(A[i] , val.getOrDefault(A[i] , 0) + 1);
            val.put(B[i] , val.getOrDefault(B[i] , 0) + 1);
            int c = 0;
            for(int v : val.keySet()) {
                if(val.get(v) == 2) c++;
            }
            C[i] = c;
        }
        return C;
    }
}