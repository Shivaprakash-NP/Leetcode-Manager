class Solution {
    public int lengthOfLastWord(String s) {
        String[] val = s.split(" ");
        return val[val.length-1].length();
    }
}