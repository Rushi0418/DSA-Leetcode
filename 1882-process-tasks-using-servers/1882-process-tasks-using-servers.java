import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Available servers: weight, index
        PriorityQueue<int[]> free = new PriorityQueue<>(
            Comparator.comparingInt((int[] a) -> a[0])
                      .thenComparingInt(a -> a[1])
        );

        // Busy servers: finishTime, weight, index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            Comparator.comparingLong((long[] a) -> a[0])
                      .thenComparingLong(a -> a[1])
                      .thenComparingLong(a -> a[2])
        );

        // Initially all servers are free
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        // IMPORTANT: Maintain time outside the loop
        long time = 0;

        for (int i = 0; i < m; i++) {

            // Never move time backwards
            time = Math.max(time, i);

            // Release all completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= time) {

                long[] server = busy.poll();

                free.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // If all servers are busy, jump to earliest completion
            if (free.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers finishing at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {

                    long[] server = busy.poll();

                    free.offer(new int[]{
                        (int) server[1],
                        (int) server[2]
                    });
                }
            }

            // Assign the next queued task
            int[] server = free.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Update server completion time
            busy.offer(new long[]{
                time + tasks[i],
                weight,
                index
            });
        }

        return ans;
    }
}