import java.util.*;


class Solution {


    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate(ans, "", n, n);
        return ans;
    }


void generate(List<String> ans, String s, int open, int close) {
        if (open == 0 && close == 0) {
            ans.add(s);
            return;
        }

        if (open > 0) {
            generate(ans, s + "(", open - 1, close);
        }
            if (close > open) {
             generate(ans, s + ")", open, close - 1);
        }
    }
}