class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int mDepth = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                depth++;
                if(depth > mDepth) {
                    mDepth = depth;
                }
            }
            if(s.charAt(i) == ')') {
                depth--;
            }
        }
return mDepth;
    }
}