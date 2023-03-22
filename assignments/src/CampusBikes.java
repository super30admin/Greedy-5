// Approach: Counting sort
// 1. Create a hashmap of key: distance, value: int[] pairs of (bike,worker)
// Time: O(mn)
// Space: O(mn)

import java.util.*;

class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // null check
        if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
            return new int[]{};
        }
        Map<Integer, List<int[]>> map = new HashMap<>();
        int m = workers.length;
        int n = bikes.length;
        int min = Integer.MAX_VALUE, max = 0;
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                int dist = calculateDist(workers[i], bikes[j]);
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }
        int count = 0;
        boolean[] assignedWorker = new boolean[m];
        boolean[] occupiedBikes = new boolean[n];
        int[] result = new int[m];
        for (int dist = min; dist<=max; dist++) {
            List<int[]> li = map.get(dist);
            if (li == null) continue;
            for (int[] wb : li) {
                int w = wb[0];
                int b = wb[1];
                if (!assignedWorker[w] && !occupiedBikes[b]) {
                    assignedWorker[w] = true;
                    occupiedBikes[b] = true;
                    result[w] = b;
                    count++;
                    if (count == n) return result;
                }
            }
        }
        return result;
    }
    private int calculateDist(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}