class Segment {
    int[] tree;
    int n;

    public Segment(int[] arr) {
        n = arr.length;
        tree = new int[4*n];
    }

    public void build(int[] basket, int l, int r, int ind) {
        if(l == r) {
            tree[ind] = basket[l];
            return;
        }

        int m = (l+r)/2;
        build(basket, l, m, 2*ind);
        build(basket, m+1, r, 2*ind+1);

        tree[ind] = Math.max(tree[2*ind], tree[ind*2+1]);
    }

    public int query(int val, int low, int high, int l, int r, int ind) {
        if(tree[ind] < val || l>high || r<low) return -1;

        if(high == low) return low;

        int m = (low+high)/2;
        int left = query(val, low, m, l, r, 2*ind);
        if(left != -1) return left;
        return query(val, m+1, high, l, r, 2*ind+1);
    }

    public void update(int low, int high, int ind, int pos) {
        if(low == high) {
            tree[ind] = Integer.MIN_VALUE;
            return;
        }

        int m = (low+high)/2;
        if(pos<=m) update(low, m, 2*ind, pos);
        else update(m+1, high, 2*ind+1, pos);

        tree[ind] = Math.max(tree[2*ind], tree[2*ind+1]);
    }
}

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        Segment st = new Segment(baskets);
        int n = fruits.length;
        st.build(baskets, 0, n-1, 1);

        int unplaced = 0;
        for(int v : fruits) {
            int idx = st.query(v, 0, n-1, 0, n-1, 1);
            if(idx == -1) unplaced++;
            else st.update(0, n-1, 1, idx);
        }

        return unplaced;
    }
}