
import java.util.*;
class Solution {
    Set<String> ans = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        int open = 0, close = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                open++;
            else if(s.charAt(i) == ')') {
                if(open > 0)
                    open--;
                else
                    close++;
            }
        }

        dfs(s, 0, open, close);
        return new ArrayList<>(ans);
    }

    void dfs(String s, int index, int open, int close) {
        if(open == 0 && close == 0) {
            if(isValid(s))
                ans.add(s);
            return;
        }
        for(int i = index; i < s.length(); i++) {
            if(i > index && s.charAt(i) == s.charAt(i - 1))
                continue;

            if(open > 0 && s.charAt(i) == '(') {
                String next = s.substring(0, i) + s.substring(i + 1);
                dfs(next, i, open - 1, close);
            }

            if(close > 0 && s.charAt(i) == ')') {
                String next = s.substring(0, i) + s.substring(i + 1);
                dfs(next, i, open, close - 1);
            }
        }
    }

    boolean isValid(String s) {
        int count = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')
                count++;
            else if(s.charAt(i) == ')') {
                count--;
                if(count < 0)
                    return false;
            }
        }
        return count == 0;
    }
}

