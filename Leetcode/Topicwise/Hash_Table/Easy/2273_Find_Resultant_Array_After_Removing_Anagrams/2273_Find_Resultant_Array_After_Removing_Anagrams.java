class Solution {
    private boolean is(String s, String t) {
        int[] m1 = new int[26];
        int[] m2 = new int[26];

        for(char c : s.toCharArray()) m1[c-'a']++;
        for(char c : t.toCharArray()) m2[c-'a']++;

        for(int i = 0; i<26; i++) if(m1[i] != m2[i]) return false;

        return true;
    }

    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);

        for(int i = 0; i<n; i++) if(!is(words[i], ans.get(ans.size()-1))) ans.add(words[i]);

        return ans;
    }
}