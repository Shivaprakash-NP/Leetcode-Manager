class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long x = 0;
        long min = 0;
        long max = 0;
        for(int v : differences)
        {
            x += v;
            min = Math.min(min , x);
            max = Math.max(max , x);
        }
        return (((upper-max)-(lower-min)+1) <= 0)?0:((int)((upper-max)-(lower-min)+1));
    }
}