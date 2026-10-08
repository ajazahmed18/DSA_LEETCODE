import java.util.*;

class Solution {
    public String removeOuterParentheses(String s) {
        Stack<Character> open = new Stack<>();
        String s1 = new String();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                open.push('(');
                if(open.size() > 1)
                    s1=s1+'(';
            }
            else {
                if(open.size() > 1)
                    s1=s1+')';
                open.pop();
            }
        }
        return s1.toString();
    }
}