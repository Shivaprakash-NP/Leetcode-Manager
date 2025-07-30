class Solution {
    public int minMaxDifference(int num) {
        String n = "" + num;
        char c1 = '\0';
        char c2 = '\0';

        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);
            if (c1 == '\0' && c != '9') c1 = c;
            if (c2 == '\0' && c != '0') c2 = c;
            if (c1 != '\0' && c2 != '\0') break;
        }

        String s1 = n.replace(c1, '9');
        String s2 = n.replace(c2, '0');

        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
}
