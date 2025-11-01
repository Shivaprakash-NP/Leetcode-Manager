class Solution {
    public int smallestNumber(int n) {
        int len = 32 - Integer.numberOfLeadingZeros(n);
        return (1<<len) - 1;
    }
}