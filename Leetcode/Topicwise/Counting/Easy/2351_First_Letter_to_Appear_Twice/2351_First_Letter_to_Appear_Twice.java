class Solution {
    public char repeatedCharacter(String s) {
        Set<Character> val = new HashSet<>();
        for(char c : s.toCharArray()) {
            if(val.contains(c)) return c;
            val.add(c);
        }
        return ' ';
    }
}