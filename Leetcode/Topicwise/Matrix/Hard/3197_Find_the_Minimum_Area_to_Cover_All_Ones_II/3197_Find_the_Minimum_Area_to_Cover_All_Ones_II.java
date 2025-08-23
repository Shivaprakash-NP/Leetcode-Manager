import java.util.Arrays;

class Solution {
    private int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int[][] ans = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[j][n - 1 - i] = arr[i][j];
            }
        }
        return ans;
    }
    
    private int[][] createSubmatrix(int[][] original, int r1, int c1, int r2, int c2) {
        int height = r2 - r1 + 1;
        int width = c2 - c1 + 1;
        int[][] sub = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sub[i][j] = original[r1 + i][c1 + j];
            }
        }
        return sub;
    }

    private int min(int[][] a) {
        if (a.length == 0 || a[0].length == 0) return 0;

        int n = a.length;
        int m = a[0].length;
        
        int t = n, b = -1, l = m, r = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1) {
                    t = Math.min(t, i);
                    b = Math.max(b, i);
                    l = Math.min(l, j);
                    r = Math.max(r, j);
                }
            }
        }
        
        if (b == -1) {
            return 0;
        }

        return (r - l + 1) * (b - t + 1);
    }

    public int minimumSum(int[][] a) {
        int ans = Integer.MAX_VALUE;

        for (int rot = 0; rot < 4; rot++) {
            int n = a.length;
            int m = a[0].length;

            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int a1 = min(Arrays.copyOfRange(a, 0, i));
                    int a2 = min(Arrays.copyOfRange(a, i, j));
                    int a3 = min(Arrays.copyOfRange(a, j, n));
                    if (a1 > 0 && a2 > 0 && a3 > 0) { 
                         ans = Math.min(ans, a1 + a2 + a3);
                    }
                }
            }
            
            for (int i = 1; i < n; i++) { 
                int a1 = min(Arrays.copyOfRange(a, 0, i));
                int[][] bottomPart = Arrays.copyOfRange(a, i, n);
                
                for (int j = 1; j < m; j++) { 
                    int a2 = min(createSubmatrix(bottomPart, 0, 0, bottomPart.length - 1, j - 1));
                    int a3 = min(createSubmatrix(bottomPart, 0, j, bottomPart.length - 1, m - 1));
                    if (a1 > 0 && a2 > 0 && a3 > 0) { 
                        ans = Math.min(ans, a1 + a2 + a3);
                    }
                }
            }

            a = rotate(a);
        }

        return ans;
    }
}