class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int i = 0;
        long s = 0;
        int[] chk = new int[values.length];
        while(i>=0 && i<values.length)
        {
            if(chk[i] == 1) break;
            chk[i] = 1;
            if(instructions[i].equals("add")) 
            {
                s+=values[i];
                i++;
            }
            else
            {
                i+=values[i];
            }
        }
        return s;
    }
}