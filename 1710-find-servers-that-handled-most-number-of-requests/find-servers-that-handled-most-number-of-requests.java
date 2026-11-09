
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            // Release servers whose requests are completed
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                long[] server = busy.poll();
                available.add((int) server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            int start = i % k;

            // Find next available server
            Integer server = available.ceiling(start);

            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;

            // Store completion time and server ID
            busy.offer(new long[]{
                (long) arrival[i] + load[i], server
            });
        }

        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
