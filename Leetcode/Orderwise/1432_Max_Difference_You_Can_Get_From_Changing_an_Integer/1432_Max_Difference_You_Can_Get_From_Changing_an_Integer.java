class Solution {
    public int maxDiff(int num) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String original = String.valueOf(num);

        for (char i = '0'; i <= '9'; i++) {
            for (char j = '0'; j <= '9'; j++) {
                String replaced = original.replace(i, j);
                if (replaced.charAt(0) == '0' || i == j) continue;  
                int val = Integer.parseInt(replaced);
                min = Math.min(min, val);
                max = Math.max(max, val);
            }
        }

        return max - min;
    }
}
