class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        int n = workers.length;
        int m = bikes.length;
        Map<Integer, List<int[]>> map = new HashMap<>();
        int max = 0, min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dist = findDistance(workers[i], bikes[j]);

                // Add pair to the Map
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] { i, j }); // worker, bike index.
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }
        // result array
        int[] result = new int[n];

        // visited arrays for Bike and Workers
        boolean[] assignedBikes = new boolean[n];
        boolean[] assignedWorkers = new boolean[n];

        int count = 0;
        for (int k = min; k <= max; k++) {
            if (!map.containsKey(k))
                continue;

            // fetch the List
            List<int[]> li = map.get(k);
            // Assign the first value fro =m the list and mark it assigned in visited array
            for (int[] pair : li) {
                int wrker = pair[0];
                int bik = pair[1];

                if (!assignedWorkers[wrker] && !assignedBikes[bik]) {
                    assignedBikes[bik] = true;
                    assignedWorkers[wrker] = true;
                    result[wrker] = bik;

                    count++;
                    if (count == n)
                        return result;
                }
            }
        }
        return result;
    }

    // calculate Manhattan Distance
    private int findDistance(int[] worker, int[] bike) {
        return (Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]));
    }
}