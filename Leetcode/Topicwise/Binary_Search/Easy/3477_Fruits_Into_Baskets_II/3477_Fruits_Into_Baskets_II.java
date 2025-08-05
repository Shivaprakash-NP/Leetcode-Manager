class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] isplaced = new boolean[n];
        Arrays.fill(isplaced, false);

        for(int i = 0; i<n; i++) 
            for(int j = 0; j<n; j++) 
                if(!isplaced[j] && fruits[i]<=baskets[j]) {
                    isplaced[j] = true;
                    break;
                }

        int notplaced = 0;
        for(boolean b : isplaced) if(!b) notplaced++;

        return notplaced;
    }
}