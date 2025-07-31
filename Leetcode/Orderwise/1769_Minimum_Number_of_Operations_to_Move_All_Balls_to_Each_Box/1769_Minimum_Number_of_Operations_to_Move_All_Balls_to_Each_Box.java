class Solution {
    public int[] minOperations(String boxes) {
        ArrayList<Integer> val = new ArrayList<>();
        for(int i = 0 ; i<boxes.length() ; i++)
            if(boxes.charAt(i)=='1')
                val.add(i);
        int[] answer = new int[boxes.length()];
        for(int i = 0  ; i<boxes.length() ; i++)
        {
            for(int v : val)
            {
                answer[i]+=Math.abs(v-i);
            }
        }
        return answer;
    }
}