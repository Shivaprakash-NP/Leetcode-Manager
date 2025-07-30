class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int v : asteroids) {
            boolean alive = true;
            while (alive && v < 0 && !stack.isEmpty() && stack.peekLast() > 0) {
                if (stack.peekLast() < -v) stack.pollLast();
                else if (stack.peekLast() == -v) {
                    stack.pollLast();
                    alive = false;
                } else {
                    alive = false;
                }
            }
            if (alive) stack.addLast(v);
        }

        int[] ans = new int[stack.size()];
        int i = 0;
        for (int val : stack) ans[i++] = val;
        return ans;
    }
}
