class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        TreeSet<String> set = new TreeSet<>((x,y) -> x.compareTo(y));

        Queue<String> q = new LinkedList<>();
        q.offer(s);

        while(!q.isEmpty()) {
            String num = q.poll();

            StringBuilder sb = new StringBuilder(num);

            for(int i = 0; i<n; i++) {
                if((i&1) == 1) {
                    int v = sb.charAt(i) - '0';
                    v = (v+a)%10;
                    sb.setCharAt(i, (char) (v+'0'));
                }
            }

            if(!set.contains(sb.toString())) {
                q.offer(sb.toString());
                set.add(sb.toString());
            }

            String s2 = num.substring(n-b)+num.substring(0, n-b);
            if(!set.contains(s2)) {
                q.offer(s2);
                set.add(s2);
            }
        }

        return set.first();
    }
}