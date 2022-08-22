// Time Complexity :O(w*b) where w is no of workers and b is no of bikes
// Space Complexity :O(wb)
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
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                minDist = Math.min(minDist, dist);
                maxDist = Math.max(maxDist, dist);
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] { i, b });
            }
        }
        boolean[] worker = new boolean[wn];
        boolean[] bike = new boolean[bn];
        for (int i = minDist; i <= maxDist; i++) {
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
        return result;
    }
} 