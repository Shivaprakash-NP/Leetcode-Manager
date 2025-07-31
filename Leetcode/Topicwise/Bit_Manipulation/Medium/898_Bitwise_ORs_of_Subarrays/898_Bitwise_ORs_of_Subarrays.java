class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ansSet = new HashSet<>();
        Set<Integer> curSet = new HashSet<>();

        for(int x : arr) {
            Set<Integer> tem = new HashSet<>();
            tem.add(x);
            for(int y : curSet) tem.add(x | y);
            ansSet.addAll(tem);
            curSet = tem;
        }

        return ansSet.size();
    }
}