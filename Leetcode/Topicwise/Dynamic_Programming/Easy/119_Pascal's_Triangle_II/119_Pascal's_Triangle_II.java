class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> val = new ArrayList<>();
        val.add(1);
        long r = rowIndex + 1;
        for(long i = 1 ; i<r ; i++)
        {
            val.add((int)(val.get((int)i-1)*(r-i)/i));
        }
        return val;
    }
}