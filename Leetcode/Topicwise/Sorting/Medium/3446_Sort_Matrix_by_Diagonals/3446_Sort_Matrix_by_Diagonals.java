class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                int key = i-j;
                if(i<j) map.computeIfAbsent(key, x -> new PriorityQueue<>()).offer(grid[i][j]);
                else map.computeIfAbsent(key, x -> new PriorityQueue<>(Comparator.reverseOrder())).offer(grid[i][j]);
            }
        }

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                grid[i][j] = map.get(i-j).poll();
            }
        }

        return grid;
    }
}