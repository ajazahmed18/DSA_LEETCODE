import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int count=0;
        for(int i=0;i<nums.size();i++){
            HashSet<Integer>st=new HashSet<>();
            for(int j=i;j<nums.size();j++){
                st.add(nums.get(j));
                int size=st.size();
                count+=size*size;
            }
        }
        return count;
    }
}