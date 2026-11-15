class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod=1000000007;
        int odd=0;
        int even=1;
        int sum=0;
        int count=0;
        for(int num:arr) {
            sum+=num;
            if(sum%2==0) {
                count=(count+odd)%mod;
                even++;
            }
            else {
                count=(count+even)%mod;
                odd++;
            }
        }
return count;
    }
}