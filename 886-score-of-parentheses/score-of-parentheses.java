import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Character> open = new Stack<>();
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open.push('(');
            }

        else if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    score += Math.pow(2, open.size() - 1);
                }
                open.pop();
            }
        }
return score;
    }
}