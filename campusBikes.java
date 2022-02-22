// Time Complexity : O(nm)
// Space Complexity : O(nm)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// Note: We can use a TreeMap instead of HashMap which will give the keys in a sorted order, but using a TreeMap would add a log factor to the time complexity
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0) return new int[]{};
        HashMap<Integer, List<int[]>> map = new HashMap<>();

        int[] result = new int[workers.length];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // fill the map, with distance : <[worker,bike]>
        for (int i=0; i<workers.length; i++) {
            for (int j=0; j<bikes.length; j++) {
                int dist = distance(workers[i], bikes[j]);
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }

                map.get(dist).add(new int[]{i, j});
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        boolean[] takenWorkers = new boolean[workers.length];
        boolean[] takenBikes = new boolean[bikes.length];
        int count=0;

        for (int k=min; k<=max; k++) {
            List<int[]> wbList = map.get(k);
            if (wbList == null) continue;

            for (int[] pair: wbList) {
                int worker = pair[0];
                int bike = pair[1];

                if (takenWorkers[worker] == false) {
                    if (takenBikes[bike] == false) {
                        takenWorkers[worker] = true;
                        takenBikes[bike] = true;
                        result[worker] = bike;
                        count++;
                    }
                }
                if (count == workers.length) {
                    return result;
                }
            }
        }

        return result;
    }

    // Manhatten Distance
    private int distance(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0])+Math.abs(worker[1]-bike[1]);
    }
}