class Solution {
    boolean ans = false;
    double eps = 0.001;

    private void rec(List<Double> arr) {
        if(ans) return;
        if(arr.size() == 1) {
            if(Math.abs(arr.get(0)-24.0) < eps) ans = true;
            return;
        }

        for(int i = 0; i<arr.size(); i++) {
            for(int j = 0; j<i; j++) {
                double v1 = arr.get(i);
                double v2 = arr.get(j);

                List<Double> nxt = new ArrayList<>();
                nxt.add(v1+v2);
                nxt.add(v1-v2);
                nxt.add(v2-v1);
                nxt.add(v1*v2);
                if(Math.abs(v1) > eps) nxt.add(v2/v1);
                if(Math.abs(v2) > eps) nxt.add(v1/v2);

                arr.remove(i);
                arr.remove(j);

                for(double v : nxt) {
                    arr.add(v);
                    rec(arr);
                    arr.remove(arr.size() - 1);
                }

                arr.add(j, v2);
                arr.add(i, v1);
            }
        } 
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> arr = new ArrayList<>();
        for(int v : cards) arr.add((double)v);
        rec(arr);
        return ans;    
    }
}