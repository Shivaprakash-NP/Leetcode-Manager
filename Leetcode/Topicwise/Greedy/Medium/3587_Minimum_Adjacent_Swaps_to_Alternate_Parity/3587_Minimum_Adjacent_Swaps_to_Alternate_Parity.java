class Solution {
    public int minSwaps(int[] nums) {
        int o = 0, e = 0;
        for (int v : nums) {
            if ((v & 1) == 1) o++;
            else e++;
        }

        int n = nums.length;
        if (n % 2 == 0) {
            if (o != e) return -1;
        } else {
            if (Math.abs(o - e) != 1) return -1;
        }

        int ans = Integer.MAX_VALUE;

        if (e >= o) {
            int[] arr = getTargetArray(nums, true);
            ans = Math.min(ans, countInversions(arr));
        }

        if (o >= e) {
            int[] arr = getTargetArray(nums, false);
            ans = Math.min(ans, countInversions(arr));
        }

        return ans;
    }

    private int[] getTargetArray(int[] nums, boolean evenStart) {
        int n = nums.length;
        int[] arr = new int[n];
        int evenPos = evenStart ? 0 : 1;
        int oddPos = evenStart ? 1 : 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                arr[i] = evenPos;
                evenPos += 2;
            } else {
                arr[i] = oddPos;
                oddPos += 2;
            }
        }
        return arr;
    }

    private int countInversions(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length - 1);
    }

    private int mergeSortAndCount(int[] arr, int left, int right) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        int count = mergeSortAndCount(arr, left, mid);
        count += mergeSortAndCount(arr, mid + 1, right);
        count += merge(arr, left, mid, right);
        return count;
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        int invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                invCount += (mid - i + 1);
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
        return invCount;
    }
}
