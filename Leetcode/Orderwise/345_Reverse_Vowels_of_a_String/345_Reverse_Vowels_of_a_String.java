class Solution {
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder(s);
        Set<Character> vowels = new HashSet<>(Arrays.asList(
    'a', 'e', 'i', 'o', 'u',
    'A', 'E', 'I', 'O', 'U'
));
        int l = 0;
        int r = s.length()-1;
        while(l<r) {
            while(l<r && !vowels.contains(s.charAt(l))) l++;
            while(l<r && !vowels.contains(s.charAt(r))) r--;
            if(l>=r) break;
            char tem = s.charAt(l);
            sb.setCharAt(l, s.charAt(r));
            sb.setCharAt(r, tem);
            l++;
            r--;
        }
        return sb.toString();
    }
}