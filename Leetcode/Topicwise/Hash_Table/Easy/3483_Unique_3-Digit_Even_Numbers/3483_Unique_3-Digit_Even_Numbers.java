class Solution {
    public int totalNumbers(int[] digits) {
        HashMap<Integer , Integer> val = new HashMap<>();
        int c = 0;
        for(int v : digits) val.put(v , val.getOrDefault(v , 0)+1);
        for(int i = 100 ; i<999 ; i+=2) {
            int d1 = i%10;
            int d2 = i%100 / 10;
            int d3 = i%1000 / 100;
            HashMap<Integer , Integer> tem = new HashMap<>(val);
            if(tem.get(d1) != null){
                tem.put(d1 , tem.get(d1)-1);
                if(tem.get(d1) == 0) tem.remove(d1);
                if(tem.get(d2) != null) {
                    tem.put(d2 , tem.get(d2)-1);
                    if(tem.get(d2) == 0) tem.remove(d2);
                    if(tem.get(d3) != null) {
                        c++;
                    }
                }
            }
        }
        return c;
    }
}