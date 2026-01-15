class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int hcons = 1;
        int hconsmax = 0;
        int hx = hBars[0];
        int hy = hBars[0];
        int thx = hBars[0];

        for(int i = 0; i<hBars.length-1; i++) {
            if(hBars[i] == hBars[i+1]-1) hcons++;
            else {
                if(hcons > hconsmax) {
                    hconsmax = hcons;
                    hcons = 1;
                    hy = hBars[i];
                    hx = thx;
                    thx = hBars[i+1];
                } else {
                    hcons = 1;
                    thx = hBars[i+1]; 
                }
            }
        }

        if(hcons > hconsmax) {
            hy = hBars[hBars.length-1];
            hx = thx;
        }

        int vcons = 1;
        int vconsmax = 0;
        int vx = vBars[0];
        int vy = vBars[0];
        int tvx = vBars[0];

        for(int i = 0; i<vBars.length-1; i++) {
            if(vBars[i] == vBars[i+1]-1) vcons++;
            else {
                if(vcons > vconsmax) {
                    vconsmax = vcons;
                    vcons = 1;
                    vy = vBars[i];
                    vx = tvx;
                    tvx = vBars[i+1];
                } else {
                    vcons = 1;
                    tvx = vBars[i+1]; 
                }
            }
        }

        if(vcons > vconsmax) {
            vy = vBars[vBars.length-1];
            vx = tvx;
        }

        return Math.min(hy-hx+2, vy-vx+2)*Math.min(hy-hx+2, vy-vx+2);
    }
}