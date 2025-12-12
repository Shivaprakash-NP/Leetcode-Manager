class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] ans = new int[numberOfUsers];
        int[] last = new int[numberOfUsers];

        Arrays.fill(last, -1);

        events.sort((a, b) -> {
            int t1 = Integer.parseInt(a.get(1));
            int t2 = Integer.parseInt(b.get(1));

            if (t1 != t2) return Integer.compare(t1, t2);

            if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) return -1;
            if (a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE")) return 1;

            return 0;
        });

        for(List<String> st : events) {
            int t = Integer.parseInt(st.get(1));
            if(st.get(0).equals("OFFLINE")) {
                int id = Integer.parseInt(st.get(2));
                last[id] = t;
            } else {
                String s = st.get(2);
                if(s.equals("ALL")) {
                    for(int i = 0; i<numberOfUsers; i++) ans[i]++;
                } else if(s.equals("HERE")) {
                    for(int i = 0; i<numberOfUsers; i++) {
                        if(last[i] == -1 || last[i]+60 <= t) ans[i]++;
                    }
                } else {
                    String[] id_arr = s.split(" ");
                    for(String id : id_arr) {
                        int i = Integer.parseInt(id.substring(2));
                        ans[i]++;
                    }
                }
            }
        }

        return ans;
    }
}