class Solution {
    public int minimumDeletions(String word, int k) {
        int[] map = new int[26];
        int min = Integer.MAX_VALUE;
        Set<Character> set = new HashSet<>();
        
        for(char c : word.toCharArray()) {
            map[c-'a']++;
            set.add(c);
        }

        List<Character> list = new ArrayList<>(set);

        for(int i = 0 ; i < list.size() ; i++) {
            int ans = 0;
            int x = map[list.get(i)-'a'];
            for(int j = 0 ; j < 26 ; j++) {
                if(map[j]==0) continue;
                if(map[j] < x) ans+=map[j];
                else if(map[j]>x+k) ans+=map[j]-k-x;
            }
            min = Math.min(min , ans);
        }

        return min;
    }
}