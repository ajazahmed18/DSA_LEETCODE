
import java.util.*;



class Solution {
    static final int MOD = 1000000007;
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long ans = 0;
        long[] sum = new long[4 * n];
        long[] sumSq = new long[4 * n];
        long[] lazy = new long[4 * n];
        int[] last = new int[100001];
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]] + 1;
            update(1, 0, n - 1, left, i, sum, sumSq, lazy);
            last[nums[i]] = i;
            ans = (ans + query(1, 0, n - 1, 0, i, sumSq, lazy, sum)) % MOD;
        }

        return (int) ans;
    }

    void apply(int node, int l, int r, long[] sum,
               long[] sumSq, long[] lazy, long val) {
        long len = r - l + 1;
        sumSq[node] = (sumSq[node] + 2 * val % MOD * sum[node] % MOD
                + val * val % MOD * len) % MOD;
        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }
    void push(int node, int l, int r, long[] sum,
              long[] sumSq, long[] lazy) {
        if (lazy[node] == 0 || l == r) return;
        int mid = (l + r) / 2;
        apply(node * 2, l, mid, sum, sumSq, lazy, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, sum, sumSq, lazy, lazy[node]);
        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr,
                long[] sum, long[] sumSq, long[] lazy) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, sum, sumSq, lazy, 1);
            return;
        }
        push(node, l, r, sum, sumSq, lazy);
        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr, sum, sumSq, lazy);
        update(node * 2 + 1, mid + 1, r, ql, qr, sum, sumSq, lazy);
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
                }
    long query(int node, int l, int r, int ql, int qr,
               long[] sumSq, long[] lazy, long[] sum) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return sumSq[node];
        push(node, l, r, sum, sumSq, lazy);
        int mid = (l + r) / 2;
        return (query(node * 2, l, mid, ql, qr, sumSq, lazy, sum)
                + query(node * 2 + 1, mid + 1, r, ql, qr, sumSq, lazy, sum)) % MOD;
    }
}
