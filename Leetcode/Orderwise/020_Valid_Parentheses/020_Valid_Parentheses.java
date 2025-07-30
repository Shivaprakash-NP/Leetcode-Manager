class Solution {
    public boolean isValid(String s) {
        Deque<Character> val = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c == ']') {
                if(!val.isEmpty()) {
                    if(val.peek() != '[') return false;
                    else val.pop();
                } else return false;
            } else if(c == ')') {
                if(!val.isEmpty()) {
                    if(val.peek() != '(') return false;
                    else val.pop();
                } else return false;
            } else if(c == '}') {
                if(!val.isEmpty()) {
                    if(val.peek() != '{') return false;
                    else val.pop();
                } else return false;       
            } else val.push(c);
        }
        return (val.size()==0)?true:false;
    }
}