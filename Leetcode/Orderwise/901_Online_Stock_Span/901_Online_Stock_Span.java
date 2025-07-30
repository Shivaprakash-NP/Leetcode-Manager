class Pair {
    int first;
    int second;
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class StockSpanner {
    Stack<Pair> st = new Stack<>();
    int i = -1;
    public StockSpanner() {
        st.clear();
        i = -1;
    }
    
    public int next(int price) {
        i++;
        while(!st.isEmpty() && st.peek().first <= price) st.pop();
        int ans = i - (st.isEmpty()?-1:st.peek().second);
        st.push(new Pair(price , i));
        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */