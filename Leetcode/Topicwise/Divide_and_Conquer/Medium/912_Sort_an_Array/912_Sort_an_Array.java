class Solution {
    public void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        
        while (i <= m && j <= r) {
            if (arr[i] < arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[l + x] = temp[x];
        }
    }


    public void mergesort(int[] arr , int l , int r) {
        if(l>=r) return;
        int m = (l+r)/2;
        mergesort(arr , l , m);
        mergesort(arr , m+1 , r);
        merge(arr , l , m , r);
    }
    public int[] sortArray(int[] nums) {
        mergesort(nums , 0 , nums.length-1);
        return nums;
    }
}