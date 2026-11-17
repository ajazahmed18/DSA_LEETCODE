class Solution {
    public int minimumChanges(String s,int k) {
        int n=s.length();
        int[][] cost=new int[n][n];
        for(int len=2;len<=n;len++) {
            for(int i=0;i+len<=n;i++) {
                int j=i+len-1;
                cost[i][j]=Integer.MAX_VALUE;
                for(int d=1;d<len;d++) {
                    if(len%d!=0) {
                        continue;
                    }
                    int changes=0;
                    for(int x=0;x<d;x++) {
                        int left=i+x;
                        int right=i+len-d+x;

                        while(left<right) {
                            if(s.charAt(left)!=s.charAt(right)) {
                                changes++;
                            }
                            left+=d;
                            right-=d;
                        }
                    }
                    cost[i][j]=Math.min(cost[i][j],changes);
                }
            }
        }
        int[][] dp=new int[k+1][n+1];
        for(int[] row:dp) {
            java.util.Arrays.fill(row,1000000);
        }
        dp[0][0]=0;
        for(int parts=1;parts<=k;parts++) {
            for(int i=parts*2;i<=n;i++) {
                for(int j=(parts-1)*2;j<=i-2;j++) {
                    dp[parts][i]=Math.min(dp[parts][i],
                        dp[parts-1][j]+cost[j][i-1]);
                }
            }
        }
        return dp[k][n];
    }
}