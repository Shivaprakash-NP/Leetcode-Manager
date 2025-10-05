class Solution {
    List<String> ans = new ArrayList<>();

    private void rec(String s, int i, Map<Integer, String> map, StringBuilder sb) {
        if(i == s.length()) {
            if(sb.length() != 0) ans.add(sb.toString());
            return;
        }

        for(char c : map.get(s.charAt(i)-'0').toCharArray()) {
            sb.append(c);
            rec(s, i+1, map, sb);
            sb.setLength(sb.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        rec(digits, 0, map, new StringBuilder());
        return ans;

    }
}