class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;

        Map<String, ArrayList<String>> map = new HashMap<>();
        Set<String> vis = new HashSet<>();

        for(String s : wordList) {
            for(int i = 0; i<s.length(); i++) {
                String pt = s.substring(0,i)+"*"+s.substring(i+1);
                map.computeIfAbsent(pt, x -> new ArrayList<>()).add(s);
            }
        }

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        vis.add(beginWord);
        int step = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-->0) {
                String s = q.poll();
                if(s.equals(endWord)) return step;

                for(int i = 0; i<s.length(); i++) {
                    String pt = s.substring(0, i)+"*"+s.substring(i+1);
                    List<String> list = map.getOrDefault(pt, new ArrayList<>());
                    for(String ss : list) {
                        if(!vis.contains(ss)) {
                            q.offer(ss);
                            vis.add(ss);
                        }
                    }
                }
            }
            step++;
        }
        return 0;
    }
}