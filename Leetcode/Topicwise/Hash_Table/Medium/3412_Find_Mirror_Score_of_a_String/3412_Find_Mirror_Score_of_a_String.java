class Solution {
    public long calculateScore(String s) {
        Map<Character, PriorityQueue<Integer>> val = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char mirror = (char)(219 - c);
            if (val.containsKey(mirror) && !val.get(mirror).isEmpty()) {
                ans += i - val.get(mirror).poll();
            } else {
                val.putIfAbsent(c, new PriorityQueue<>(Collections.reverseOrder()));
                val.get(c).add(i);
            }
        }
        return ans;
    }
}
