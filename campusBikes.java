class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int numWorkers = workers.length;
        int numBikes = bikes.length;
        
        // Create a priority queue to store pairs (distance, worker, bike)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int cmp = Integer.compare(a[0], b[0]); // Compare distances
            if (cmp == 0) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]); // Compare worker indices
                }
                return Integer.compare(a[1], b[1]); // Compare worker indices
            }
            return cmp;
        });
        
        // Populate the priority queue with all possible pairs
        for (int i = 0; i < numWorkers; i++) {
            for (int j = 0; j < numBikes; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                pq.offer(new int[]{distance, i, j});
            }
        }
        
        int[] result = new int[numWorkers];
        Arrays.fill(result, -1); // Initialize result array
        
        Set<Integer> usedBikes = new HashSet<>(); // To track used bikes
        
        // Process the pairs from the priority queue
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int worker = pair[1];
            int bike = pair[2];
            
            if (result[worker] == -1 && !usedBikes.contains(bike)) {
                result[worker] = bike;
                usedBikes.add(bike);
            }
        }
        
        return result;
    }
}

