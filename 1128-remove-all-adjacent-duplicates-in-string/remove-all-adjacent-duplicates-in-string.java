import java.util.*;

class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(c);
            }
            else if(c == stack.peek()) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        String ans = "";
        for(char c : stack) {
            ans += c;
        }
        return ans;
    }
}