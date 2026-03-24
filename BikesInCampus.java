// Time Complexity : O(wb)
// Space Complexity : O(wb)
// Did this code successfully run on Leetcode : NA
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// for every w-b pair we calculate manhattan distance and add it to hashmap
// keep track of min and max dist and iterate over the hashmap and assign the first founded shortest dist
// check if both the worker and bike is not assigned before

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = calculateDistance(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());

                map.get(dist).add(new int[] { i, j });
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        int[] result = new int[workers.length];
        int count = 0;
        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];

        for (int i = min; i <= max; i++) {
            if (!map.containsKey(i))
                continue;

            List<int[]> list = map.get(i);
            for (int[] wb : list) {
                int worker = wb[0];
                int bike = wb[1];

                if (!workersAssigned[worker] && !bikesAssigned[bike]) {
                    result[worker] = bike;
                    workersAssigned[worker] = true;
                    bikesAssigned[bike] = true;
                    count++;
                    if (count == workers.length)
                        return result;
                }
            }
        }
        return result;
    }

    private int calculateDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
