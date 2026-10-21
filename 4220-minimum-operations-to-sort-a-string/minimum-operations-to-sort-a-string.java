class Solution {
    public int minOperations(String s) {
        int n=s.length();
        char mn='z',mx='a';
        for(int i=0;i<n;i++){
            mn=(char)Math.min(mn,s.charAt(i));
            mx=(char)Math.max(mx,s.charAt(i));
        }
            if(isSorted(s)){
            return 0;
    }
        if(s.charAt(0)==mn||s.charAt(n-1)==mx){
            return 1;
        }
        if(n==2){
            return -1;
        }
        if(s.indexOf(mn)<n-1||s.lastIndexOf(mx)>0){
            return 2;
        }
        return 3;
    }
    private boolean isSorted(String s){
        for(int i=1;i<s.length();i++){
        if(s.charAt(i)<s.charAt(i-1)){
                return false;
            }
        }
return true;
    }
}