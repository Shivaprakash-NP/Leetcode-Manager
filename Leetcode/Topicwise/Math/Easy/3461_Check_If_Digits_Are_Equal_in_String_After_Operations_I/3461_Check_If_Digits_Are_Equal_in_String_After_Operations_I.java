class Solution {
    public boolean hasSameDigits(String s) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() > 2) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < sb.length() - 1; i++) {
                int v1 = sb.charAt(i) - '0';
                int v2 = sb.charAt(i + 1) - '0';
                temp.append((char) ((v1 + v2) % 10 + '0'));
            }
            sb = temp;
        }
        return sb.charAt(0) == sb.charAt(1);
    }
}
