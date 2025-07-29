class Solution {
    public List<String> partitionString(String s) {
        Set<String> seen = new HashSet<>();
        List<String> result = new ArrayList<>();
        int n = s.length();
        int i = 0;

        while (i < n) {
            StringBuilder sb = new StringBuilder();
            while (i < n) {
                sb.append(s.charAt(i));
                String segment = sb.toString();
                if (!seen.contains(segment)) {
                    result.add(segment);
                    seen.add(segment);
                    i++;  
                    break;
                }
                i++;
            }
        }

        return result;
    }
}
