// Time Complexity : O(m*n) where m and n are lengths of arrays workers and bikes respectively
// Space Complexity : O(m*n) where m and n are lengths of arrays workers and bikes respectively
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// calculate distance of each worker with each bike. Put in a treemap {distance : (worker, bike)}
// now iter. thru treemap which is in inc. order of distance and assign bike to worker if both bike and 
// worker are unassigned

class campusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers.length == 0 || bikes.length == 0) return new int[] {};
        int[] ans = new int[workers.length];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < workers.length; i++) {
            for(int j = 0; j < bikes.length; j++) {
                int dist = distance(workers[i], bikes[j]);
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] {i, j});
            }
        }
        boolean[] workerAssigned = new boolean[workers.length];
        boolean[] bikeAssigned = new boolean[bikes.length];
        for (int dist : map.keySet()) {
            List<int[]> list = map.get(dist);
            for (int[] pair : list) {
                int worker = pair[0];
                int bike = pair[1];
                if (!workerAssigned[worker] && !bikeAssigned[bike]) {
                    ans[worker] = bike;
                    workerAssigned[worker] = true;
                    bikeAssigned[bike] = true;
                }
            }
        }
        return ans;
    }
    private int distance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}