class MinStack {

    Stack<Long> s = new Stack<>();
    long min = Long.MAX_VALUE;

    public MinStack() {
        
    }
    
    public void push(int val) {
        if(s.isEmpty()) {
            s.push((long)val);
            min = val;
        } else {
            if(val < min) {
                s.push(2*(long)val - min);
                min = val;
            }
            else s.push((long)val);
        }
    }
    
    public void pop() {
        long po = s.pop();
        if(po < min) min = 2*min - po;
        // if(s.isEmpty()) return;
        // int x = s.peek();
        // if(x < min) return 2*min - x;
        // else return x;
    }
    
    public int top() {
        long x = s.peek();
        if(x < min) return (int)min;
        else return (int)x;
    }

    
    public int getMin() {
        return (int)min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */