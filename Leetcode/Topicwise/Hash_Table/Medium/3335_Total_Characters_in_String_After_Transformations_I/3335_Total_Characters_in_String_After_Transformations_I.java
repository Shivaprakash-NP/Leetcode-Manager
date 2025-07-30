class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int[] val = new int[26];
        final int MOD = 1_000_000_007;

        for (char c : s.toCharArray()) {
            val[c - 'a']++;
        }

        for (int i = 0; i < t; i++) {
            int[] next = new int[26];

            for (int j = 0; j < 25; j++) {
                if (val[j] > 0) {
                    next[j + 1] = (next[j + 1] + val[j]) % MOD;
                }
            }
            if (val[25] > 0) {
                next[0] = (next[0] + val[25]) % MOD;
                next[1] = (next[1] + val[25]) % MOD;
            }

            val = next;
        }

        int len = 0;
        for (int v : val) {
            len = (len + v) % MOD;
        }

        return len;
    }
}
