class Solution {
    public int maxVowels(String s, int k) {
        Set<Character> vow = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int n = s.length();
        int l = 0;
        int max = 0;
        int cnt = 0;
        for(int i = 0; i<n; i++) {
            if(vow.contains(s.charAt(i))) cnt++;
            while(i-l+1 > k) {
                if(vow.contains(s.charAt(l))) cnt--;
                l++;
            }
            if(i-l+1 == k) max = Math.max(max, cnt);
        }

        return max;
    }
}