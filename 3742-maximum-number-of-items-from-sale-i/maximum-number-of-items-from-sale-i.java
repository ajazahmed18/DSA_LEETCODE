
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n=items.length;
        int[] dp=new int[budget+1];
        int minPrice=Integer.MAX_VALUE;

        for(int[] item:items) {
            minPrice=Math.min(minPrice,item[1]);

            int count=0;

            for(int[] other:items) {
                if(other[0]%item[0]==0) {
                    count++;
                }
            }

            for(int j=budget;j>=item[1];j--) {
                dp[j]=Math.max(dp[j],dp[j-item[1]]+count);
            }
        }

        int ans=0;

        for(int i=0;i<=budget;i++) {
            ans=Math.max(ans,dp[i]+(budget-i)/minPrice);
        }
return ans;
    }
}
