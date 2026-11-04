
import java.util.*;

class Solution {
    class Window {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void addMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void removeMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addMap(left, val);
                leftSize++;
                leftSum += val;
            } else {
                addMap(right, val);
                rightSize++;
                rightSum += val;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeMap(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                removeMap(right, val);
                rightSize--;
                rightSum -= val;
            }
            balance();
        }

        void balance() {
            while (leftSize < rightSize) {
                int val = right.firstKey();
                removeMap(right, val);
                rightSize--;
                rightSum -= val;

                addMap(left, val);
                leftSize++;
                leftSum += val;
            }

            while (leftSize > rightSize + 1) {
                int val = left.lastKey();
                removeMap(left, val);
                leftSize--;
                leftSum -= val;

                addMap(right, val);
                rightSize++;
                rightSum += val;
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                    + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int windows = n - x + 1;

        // Calculate the cost of every window.
        Window window = new Window();
        long[] cost = new long[windows];

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);
            cost[i - x + 1] = window.getCost();
        }

        // DP: minimum cost to select j windows from index i onward.
        long INF = Long.MAX_VALUE / 4;

        // Selecting zero windows always costs zero.
        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = n - 1; i >= 0; i--) {
                // Skip current position.
                curr[i] = curr[i + 1];

                // Select window starting at i.
                if (i + x <= n) {
                    curr[i] = Math.min(
                        curr[i],
                        cost[i] + prev[i + x]
                    );
                }
            }

            prev = curr;
        }

        return prev[0];
    }
}
