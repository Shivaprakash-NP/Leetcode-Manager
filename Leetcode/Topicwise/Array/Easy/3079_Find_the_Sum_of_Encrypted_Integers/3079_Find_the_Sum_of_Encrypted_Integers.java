class Solution {
    private int find(int n) {
        int tem = n;
        int digits = 0;
        int maxDigit = 0;

        while (tem != 0) {
            maxDigit = Math.max(maxDigit, tem % 10);
            digits++;
            tem /= 10;
        }

        int result = 0;
        for (int i = 0; i < digits; i++) {
            result = result * 10 + maxDigit;
        }

        return result;
    }

    public int sumOfEncryptedInt(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans += find(num);
        }
        return ans;
    }
}
