class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        int c = 0;
        HashMap<Long,Integer> v1 = new HashMap<>();
        HashMap<Long,Integer> v2 = new HashMap<>();
        for(int i = 0 ; i < nums1.length ; i++)
        {
            long v = 1L*nums1[i]*nums1[i];
            v1.put(v , v1.getOrDefault(v , 0)+1);
        }
        for(int i = 0 ; i < nums2.length ; i++)
        {
            long v = 1L*nums2[i]*nums2[i];
            v2.put(v , v2.getOrDefault(v , 0)+1);
        }
        for(int i = 0 ; i < nums1.length ; i++)
            for(int j = i+1 ; j<nums1.length ; j++)
                c+=(v2.get(1L*nums1[i]*nums1[j])==null)?0:(v2.get(1L*nums1[i]*nums1[j]));
        for(int i = 0 ; i < nums2.length ; i++)
            for(int j = i+1 ; j<nums2.length ; j++)
                c+=(v1.get(1L*nums2[i]*nums2[j]) == null)?0:(v1.get(1L*nums2[i]*nums2[j]));
        return c;
    }
}