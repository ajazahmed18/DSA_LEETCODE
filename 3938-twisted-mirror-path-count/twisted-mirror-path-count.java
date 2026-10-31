class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1000000007;
        int size = m * n;

        int[] right = new int[size];
        int[] down = new int[size];

        // Precompute mirror movements
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int idx = i * n + j;

                if (grid[i][j] == 0) {
                    right[idx] = idx;
                    down[idx] = idx;
                } else {
                    right[idx] = (i + 1 < m) ? down[(i + 1) * n + j] : -1;
                    down[idx] = (j + 1 < n) ? right[i * n + j + 1] : -1;
                }
            }
        }

        long[] dp = new long[size];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;

                if (dp[idx] == 0) {
                    continue;
                }

                long ways = dp[idx];

                // Move right
                if (j + 1 < n) {
                    int dest = right[idx + 1];

                    if (dest != -1) {
                        dp[dest] = (dp[dest] + ways) % mod;
                    }
                }

                // Move down
                if (i + 1 < m) {
                    int dest = down[idx + n];

                    if (dest != -1) {
                        dp[dest] = (dp[dest] + ways) % mod;
                    }
                }
            }
        }

        return (int) dp[size - 1];
    }
}