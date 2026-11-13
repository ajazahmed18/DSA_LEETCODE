
class Solution {
    public long countNonDecreasingSubarrays(int[] nums,int k) {
        int n=nums.length;
        int[] ng=new int[n];
        int[] stack=new int[n];
        int top=-1;

        // Find next greater element
        for(int i=n-1;i>=0;i--) {
            while(top>=0 && nums[stack[top]]<=nums[i]) {
                top--;
            }

            ng[i]=(top==-1)?n:stack[top];
            stack[++top]=i;
        }

        // Build prefix sum
        long[] prefix=new long[n+1];

        for(int i=0;i<n;i++) {
            prefix[i+1]=prefix[i]+nums[i];
        }

        // Binary lifting
        int LOG=1;

        while((1<<LOG)<=n) {
            LOG++;
        }

        int[][] up=new int[LOG][n];
        long[][] sum=new long[LOG][n];

        for(int i=0;i<n;i++) {
            up[0][i]=ng[i];
            sum[0][i]=(long)nums[i]*(ng[i]-i);
        }

        for(int j=1;j<LOG;j++) {
            for(int i=0;i<n;i++) {
                int mid=up[j-1][i];

                if(mid<n) {
                    up[j][i]=up[j-1][mid];
                    sum[j][i]=sum[j-1][i]+sum[j-1][mid];
                }
                else {
                    up[j][i]=n;
                    sum[j][i]=sum[j-1][i];
                }
            }
        }

        // Sliding window
        long ans=0;
        int left=0;

        for(int right=0;right<n;right++) {
            while(left<=right) {
                int node=left;
                long total=0;

                for(int j=LOG-1;j>=0;j--) {
                    if(up[j][node]<=right) {
                        total+=sum[j][node];
                        node=up[j][node];
                    }
                }

                total+=(long)nums[node]*(right-node+1);

                long operations=total-(prefix[right+1]-prefix[left]);

                if(operations<=k) {
                    break;
                }

                left++;
            }

            ans+=right-left+1;
        }

return ans;
    }
}
