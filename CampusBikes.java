


/**
 * Assigns bikes to workers by computing Manhattan distances for each worker-bike pair.
 * Groups pairs by distance and assigns the closest available bike to each worker.
 * Returns an array where the index is the worker and the value is the assigned bike.
 */
// Time Complexity: O(M * N + D), where M = workers, N = bikes, and D = max distance range
// Space Complexity: O(M * N), for storing all worker-bike pairs in the hashmap
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = calculateDistance(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());

                map.get(dist).add(new int[] { i, j }); //worker, bike
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }
        int[] result = new int[workers.length];
        int count = 0;

        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[workers.length];

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

    public int calculateDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);

    }
    
}
