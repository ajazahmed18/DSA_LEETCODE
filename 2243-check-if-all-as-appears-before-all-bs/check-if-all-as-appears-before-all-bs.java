
class Solution {
    public boolean checkString(String s) {
        int lastA=s.lastIndexOf('a');
        int firstB=s.indexOf('b');
        return lastA<firstB || firstB==-1;
    }
}
