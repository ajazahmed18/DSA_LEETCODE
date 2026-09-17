import java.util.*;
class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for (String c : operations) {
            if  (c.equals("+")) {
                 int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b);
                    stack.push(a);
                    stack.push(a + b);
            }
            else if (c.equals("D")) {
                int a = stack.peek();
                stack.push(2 * a);
            }
            else if (c.equals("C")) {
                stack.pop();
            }
            else {
                stack.push(Integer.parseInt(c));
            }
        }
        int sum = 0;
        for (int x : stack) {
            sum += x;
        }
        return sum;
    }
}