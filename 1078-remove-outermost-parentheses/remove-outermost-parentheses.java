import java.util.*;

class Solution {
    public String removeOuterParentheses(String s) {
        Stack<Character> open = new Stack<>();
        StringBuilder s1 = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                open.push('(');
                if(open.size() > 1)
                    s1.append('(');
            }
            else {
                if(open.size() > 1)
                    s1.append(')');
                open.pop();
            }
        }
        return s1.toString();
    }
}