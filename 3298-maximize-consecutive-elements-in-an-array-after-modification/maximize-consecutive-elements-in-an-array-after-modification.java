
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer,Integer> dp=new HashMap<>();
        int ans=0;
        for(int num:nums) {
            int len=dp.getOrDefault(num,0)+1;
            int next=dp.getOrDefault(num+1,0)+1;
            dp.put(num+1,Math.max(dp.getOrDefault(num+1,0),len));
            dp.put(num+2,Math.max(dp.getOrDefault(num+2,0),next));
            ans=Math.max(ans,Math.max(dp.get(num+1),dp.get(num+2)));
        }
        return ans;
    }
}
