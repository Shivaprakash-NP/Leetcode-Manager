class Solution {
    public String answerString(String word, int numFriends) {
        if(numFriends == 0 || numFriends == 1) return word;
        int len = word.length()-numFriends+1;
        String ans = "";

        for(int i = 0 ; i < word.length() ; i++) {
            int end = Math.min(word.length() , i+len);
            String sub = word.substring(i , end);
            if(sub.compareTo(ans) > 0) ans = sub;
        }

        return ans;
    }
}