class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int l = 0;
        int r = matrix[0].length-1;
        int t = 0;
        int b = matrix.length-1;
        ArrayList<Integer> val = new ArrayList<>();

        while(l<=r && t<=b)
        {
            for(int i = l ; i<=r ; i++)
                val.add(matrix[t][i]);
            t++;
            for(int i = t ; i<=b ; i++)
                val.add(matrix[i][r]);
            r--;
            if(t<=b)
            {
                for(int i = r ; i>=l ; i--)
                    val.add(matrix[b][i]);
                b--;
            }
            if(l<=r)
            {
                for(int i = b ; i>=t ; i--)
                    val.add(matrix[i][l]);
                l++;
            }
        }
        return val;
    }
}