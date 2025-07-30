class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder(); 
        int m = 0; 
        for (char c : s.toCharArray()) 
        {
            if (c == ')') m--;  
            if (m > 0) sb.append(c);  
            if (c == '(') m++; 
        }

        return sb.toString();
    }
}
