class Solution {
    List<List<String>> ans = new LinkedList<>();
    Map<String, Integer> lmap = new HashMap<>();
    String bw;

    private void dfs(String s, List<String> path) {
        if (s.equals(bw)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp); 
            ans.add(temp);
            return;
        }

        int l = lmap.getOrDefault(s, -1);
        for(int i = 0 ; i<s.length() ; i++) {
            for(char ch = 'a' ; ch <= 'z' ; ch++) {
                String ns = s.substring(0, i)+ch+s.substring(i+1);
                if(lmap.containsKey(ns) && lmap.get(ns) == l-1) {
                    path.add(ns);
                    dfs(ns, path);
                    path.remove(path.size()-1);
                }
            }
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return ans;

        Set<String> ws = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();

        bw = beginWord;
        q.offer(beginWord);
        ws.remove(beginWord);
        lmap.put(beginWord, 0);
        int size = beginWord.length();

        while(!q.isEmpty()) {
            String word = q.poll();
            int step = lmap.get(word);
            if(word.equals(endWord)) break;

            for(int i = 0 ; i<size ; i++) {
                for(char ch = 'a' ; ch <= 'z' ; ch++) {
                    String ns = word.substring(0, i)+ch+word.substring(i+1);
                    if(ws.contains(ns)) {
                        q.offer(ns);
                        ws.remove(ns);
                        lmap.put(ns, step+1);
                    }
                }
            }
        }

        if(lmap.get(endWord) != null) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, path);
        }

        return ans;
    }
}