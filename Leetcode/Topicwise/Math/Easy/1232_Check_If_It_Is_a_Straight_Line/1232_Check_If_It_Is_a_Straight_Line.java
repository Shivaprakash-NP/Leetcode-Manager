class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        for(int i = 0; i<coordinates.length-2 ; i++) {
            int v1 = (coordinates[i+1][1]-coordinates[i][1])*(coordinates[i+2][0]-coordinates[i+1][0]);
            int v2 = (coordinates[i+2][1]-coordinates[i+1][1])*(coordinates[i+1][0]-coordinates[i][0]);
            if(v1 != v2) return false;
        }
        return true;
    }
}