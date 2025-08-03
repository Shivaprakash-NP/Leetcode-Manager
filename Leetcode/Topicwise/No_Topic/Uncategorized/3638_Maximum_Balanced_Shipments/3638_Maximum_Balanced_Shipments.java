class Solution {
    public int maxBalancedShipments(int[] weight) {
        int max = Integer.MIN_VALUE;
        int parcel = 0;
        int n = weight.length;

        for(int i = 0; i<n; i++) {
            max = Math.max(max, weight[i]);
            if(max > weight[i]) {
                parcel++;
                max = Integer.MIN_VALUE;
            }
        }

        return parcel;
    }
}