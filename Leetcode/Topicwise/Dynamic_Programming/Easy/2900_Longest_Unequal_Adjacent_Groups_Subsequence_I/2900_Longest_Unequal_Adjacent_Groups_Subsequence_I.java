class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        ArrayList<String> val = new ArrayList<>();
        int last = groups[0];
        val.add(words[0]);
        for(int i = 1 ; i < groups.length ; i++) {
            if(last!=groups[i]) {
                val.add(words[i]);
                last = groups[i];
            }
        }
        return val;
    }
}