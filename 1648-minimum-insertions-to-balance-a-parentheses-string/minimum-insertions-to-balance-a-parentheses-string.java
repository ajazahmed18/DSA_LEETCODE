import java.util.*;




class Solution {
    public int minInsertions(String s) {
        Stack<Character> st = new Stack<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(ch);
    }
     else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
            } else {
                    count++;
            }
                if (!st.isEmpty()) {
                    st.pop();
                } else {
                    count++;
                }
            }
        }
        count += st.size() * 2;
        return count;
    }
}