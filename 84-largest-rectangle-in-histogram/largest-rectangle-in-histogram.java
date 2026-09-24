class Solution {
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h;
            if (i == heights.length)
                h = 0;
            else
                h = heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty())
                    width = i;
                else
                    width = i - stack.peek() - 1;

                int area = height * width;

                max = Math.max(max, area);
            }
            stack.push(i);
        }
        return max;
    }
}