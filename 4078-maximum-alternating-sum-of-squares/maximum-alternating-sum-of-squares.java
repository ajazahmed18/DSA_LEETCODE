
import java.util.*;
class Solution {
    public long maxAlternatingSum(int[] nums) {
        long squares[]=new long[nums.length];
       for(int i=0;i<nums.length;i++){
         squares[i]=(long)nums[i]*nums[i];
       }
       Arrays.sort(squares);
       long sum=0;
       int n=nums.length;
       for(int i=0;i<n;i++){
        if(i<n/2){
            sum-=squares[i];
        }
        else{
            sum+=squares[i];
        }
       }
return sum;
    }
}