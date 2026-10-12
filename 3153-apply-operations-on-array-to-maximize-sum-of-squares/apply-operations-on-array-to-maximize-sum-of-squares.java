import java.util.*;




class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long mod = 1000000007;
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bits[i]++;
                }}}
        long ans = 0;
        for (int i = 0; i < k; i++) {
            long num = 0;
            for (int j = 0; j < 32; j++) {
                if (bits[j] > 0) {
                    num |= (1L << j);
                bits[j]--;
                }
    }
            ans = (ans + num * num) % mod;
        }
        return (int) ans;
    }
}