class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> unmatched = new HashMap<>();
        int palindromePairs = 0;
        int[] sameCharPairs = new int[26]; 
        boolean hasOddCenter = false;

        for (String word : words) {
            char a = word.charAt(0), b = word.charAt(1);
            if (a == b) {
                sameCharPairs[a - 'a']++;
            } else {
                String reversed = new StringBuilder(word).reverse().toString();
                if (unmatched.getOrDefault(reversed, 0) > 0) {
                    palindromePairs += 2;
                    unmatched.put(reversed, unmatched.get(reversed) - 1);
                } else {
                    unmatched.put(word, unmatched.getOrDefault(word, 0) + 1);
                }
            }
        }

        int centerUsed = 0;
        for (int count : sameCharPairs) {
            palindromePairs += (count / 2) * 2;
            if (count % 2 == 1) {
                centerUsed = 1; 
            }
        }

        return (palindromePairs + centerUsed) * 2;
    }
}
