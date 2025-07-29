class Solution {
    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder("a");

        while (sb.length() <= k) {
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                char nc = (char) ((sb.charAt(i) - 'a' + 1) % 26 + 'a');
                sb1.append(nc);
            }
            sb.append(sb1);
        }

        return sb.charAt(k-1);
    }
}
