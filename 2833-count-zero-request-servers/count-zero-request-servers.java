import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int m = queries.length;
        int[] ans = new int[m];
        int[] freq = new int[n + 1];
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b) -> queries[a] - queries[b]);
        int left = 0;
        int right = 0;
        int active = 0;
        for (int i : idx) {
            int start = queries[i] - x;
            int end = queries[i];
            while (right < logs.length && logs[right][1] <= end) {
                int server = logs[right][0];
                if (freq[server] == 0) {
                    active++;
                }
                freq[server]++;
                right++;
            }
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];
                freq[server]--;
                if (freq[server] == 0) {
                    active--;
                }
                left++;
            }
            ans[i] = n - active;
        }
return ans;
    }
}