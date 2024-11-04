//TC: O(n * m * log(n * m))
//SC: O(n * m)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        
        // List to store all worker-bike pairs along with their distances
        List<int[]> allPairs = new ArrayList<>();
        
        // Step 1: Calculate Manhattan distance for all pairs of workers and bikes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                allPairs.add(new int[] {dist, i, j});  // Store (distance, worker_index, bike_index)
            }
        }
        
        // Step 2: Sort pairs by distance, worker index, and bike index
        allPairs.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];  // Sort by distance
            if (a[1] != b[1]) return a[1] - b[1];  // If distances are the same, sort by worker index
            return a[2] - b[2];                    // If worker index is the same, sort by bike index
        });
        
        // Step 3: Assign bikes to workers
        int[] result = new int[n];     // Result array where result[i] = bike assigned to worker i
        Arrays.fill(result, -1);       // Initialize with -1 (no assignment)
        boolean[] assignedBikes = new boolean[m];  // Track which bikes have been assigned
        
        // Process the pairs in sorted order
        for (int[] pair : allPairs) {
            int worker = pair[1];
            int bike = pair[2];
            
            if (result[worker] == -1 && !assignedBikes[bike]) {  // If this worker hasn't been assigned and the bike is available
                result[worker] = bike;   // Assign bike to worker
                assignedBikes[bike] = true;  // Mark bike as assigned
            }
        }
        
        return result;
    }
}
