class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int np = players.length;
        int nt = trainers.length;
        int l = 0;
        int r = 0;
        int ans = 0;

        while(l<np && r<nt) {
            while(r<nt && players[l]>trainers[r]) r++;
            if(r>=nt) break;
            ans++;
            l++;
            r++;
        }

        return ans;
    }
}