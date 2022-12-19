// Time complexity: O(m * n log(m*n) + m*n)
// Space complexity: O(m * n + m + n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) return new int[] {};

        int n = workers.length;
        int m = bikes.length;

        TreeMap<Integer, List<int []>> map = new TreeMap<>();

        for (int i =0; i <n; i++) {
            int[] worker = workers[i];
            for (int j=0; j < m; j++) {
                int[] bike = bikes[j];
                int distance = findManhattanDistance(worker, bike);
                if (!map.containsKey(distance)) {
                    map.put(distance, new ArrayList<>());
                }
                map.get(distance).add(new int[] {i, j});
            }
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);
        boolean[] assignedBikes = new boolean[m];
        int count = 0;

        for (int distance: map.keySet()) {
            List<int[]> workerBikePair = map.get(distance);
            for (int[] wb : workerBikePair) {
                int worker = wb[0];
                int bike = wb[1];
                if (result[worker] == -1) {
                    if(assignedBikes[bike] == false) {
                        assignedBikes[bike] = true;
                        result[worker] = bike;
                        count++;
                        if (count == n) {
                            return result;
                        }
                    }
                }
            }
        }
        return result;

    }

    private int findManhattanDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}