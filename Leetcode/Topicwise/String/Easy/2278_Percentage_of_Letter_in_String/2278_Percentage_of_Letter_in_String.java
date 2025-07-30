class Solution {
    public int percentageLetter(String s, char letter) {
        int c = 0;
        for(char cc : s.toCharArray()) if(cc == letter) c++;
        return (c*100)/s.length();
    }
}