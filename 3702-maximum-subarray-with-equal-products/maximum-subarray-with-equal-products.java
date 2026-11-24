class Solution {
    public int maxLength(int[] nums) {
        int n=nums.length;
        int ans=2;
        for(int i=0;i<n-1;i++) {
            boolean valid=true;
            for(int j=i+1;j<n;j++) {
                for(int k=i;k<j;k++) {
                    if(gcd(nums[j],nums[k])!=1) {
                        valid=false;
                        break;
                    }}
                if(!valid) {
                    break;
                }

                ans=Math.max(ans,j-i+1);
            }
        }
        return ans;
    }
    public int gcd(int a,int b) {
        while(b!=0) {
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
}