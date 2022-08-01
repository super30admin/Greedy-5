// Time Complexity :O(w*b) where w is no of workers and b is no of bikes
// Space Complexity :O(wb)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int wn = workers.length;
        int bn = bikes.length;
        int[] result = new int[wn];
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        int maxDist = 0;
        for (int i = 0; i < wn; i++) {// for each worker
            int[] worker = workers[i];
            for (int b = 0; b < bn; b++) {
                int[] bike = bikes[b];
                // find manhattan distance
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                // update min and max
                minDist = Math.min(minDist, dist);
                maxDist = Math.max(maxDist, dist);
                // add worker bike pair in map
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] { i, b });
            }
        }
        // visited workers and bikes
        boolean[] worker = new boolean[wn];
        boolean[] bike = new boolean[bn];
        for (int i = minDist; i <= maxDist; i++) {
            // we'll start from min distance, so min distance assignment will be done prior
            // to other and we are starting from worker 0 so we'll always be getting smaller
            // worker index first and then smallest bike index
            if (map.containsKey(i)) {
                List<int[]> temp = map.get(i);
                for (int[] curr : temp) {
                    int w = curr[0];
                    int b = curr[1];
                    if (!worker[w] && !bike[b]) {
                        result[w] = b;
                        worker[w] = true;
                        bike[b] = true;
                    }
                }
            }
        }
        // System.out.println(map);
        return result;
    }
}