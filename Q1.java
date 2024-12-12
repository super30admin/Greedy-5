Time Complexity (TC): O(m * n)
Space Complexity (SC): O(m * n)
class Solution {
    private int calcDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int m = bikes.length;
        int n = workers.length;

        // Find distances of all pairs and store them in a bucket sort manner
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dist = calcDistance(bikes[i], workers[j]);
                map.computeIfAbsent(dist, k -> new ArrayList<>()).add(new int[]{i, j});
                min = Math.min(dist, min);
                max = Math.max(dist, max);
            }
        }

        // Fill the result
        HashSet<Integer> bikesSet = new HashSet<>();
        HashSet<Integer> workersSet = new HashSet<>();
        int[] res = new int[n];

        for (int dist = min; dist <= max; dist++) {
            if (!map.containsKey(dist)) continue;
            ArrayList<int[]> curr = map.get(dist);

            for (int[] pair : curr) {
                int bike = pair[0];
                int worker = pair[1];
                if (bikesSet.contains(bike) || workersSet.contains(worker)) continue;
                
                res[worker] = bike;
                workersSet.add(worker);
                bikesSet.add(bike);
            }
        }

        return res;
    }
}
