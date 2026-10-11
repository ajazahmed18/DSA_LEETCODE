class Solution {
    public int sumOfSquares(int[] nums) {
        int n=nums.length;
        int arr[]=new int[n];
        int j=0;
        for(int i=1;i<=n;i++){
            if(n%i==0){
                int square=nums[i-1]*nums[i-1];
                arr[j++]=square;
            }
        }
        
        int sum=0;
        
        for(j=0;j<arr.length;j++){
            sum=sum+arr[j];
        }
return sum;
    }
}