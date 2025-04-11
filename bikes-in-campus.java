// TC: O(n^2)
// SC: O(n*m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || bikes == null || workers.length == 0 || bikes.length == 0)
            return new int[0];

        int n = workers.length;
        int m = bikes.length;
        int[] result = new int[n];

        Map<Integer, List<int[]>> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int d = calculateDist(workers[i], bikes[j]);
                if (!map.containsKey(d)) {
                    map.put(d, new ArrayList<>());
                }
                map.get(d).add(new int[]{i, j});
            }
        }

        boolean[] assigned = new boolean[n];
        boolean[] occupied = new boolean[m];

        for (List<int[]> pairList : map.values()) {
            for (int[] pair : pairList) {
                int worker = pair[0];
                int bike = pair[1];
                if (!assigned[worker] && !occupied[bike]) {
                    assigned[worker] = true;
                    occupied[bike] = true;
                    result[worker] = bike;
                }
            }
        }

        return result;
    }

    private int calculateDist(int[] worker, int[] bike) {
        return Math.abs(worker[1] - bike[1]) + Math.abs(worker[0] - bike[0]);
    }
}
