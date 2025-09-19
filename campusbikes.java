// Time Complexity: O(W * B * log(W * B)) – generate all worker–bike pairs and push/pop from a min-heap of size up to W*B.
// Space Complexity: O(W * B) – heap stores all pairs; plus O(W + B) for assignment arrays.

// Generate all (worker, bike) pairs, compute Manhattan distance, and push (dist, worker, bike) into a min-heap with tie-breakers (dist → worker → bike).
// Repeatedly pop the smallest; if both worker and bike are unassigned, record the assignment and mark them used.
// Stop when all workers are assigned and return the result mapping.

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{ // 0 - worker, 1 - bike, 2 - dist
            if(a[2] != b[2]) return a[2] - b[2];
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
       
        for(int i=0; i<workers.length; i++){
            for(int j=0; j<bikes.length; j++){
                int dist = calculateDistance(workers[i], bikes[j]);
                pq.add(new int[]{i,j, dist}); //worker, bike, dist
            }
        }

        int[] result = new int[workers.length];
        int count = 0;
        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];

        while(!pq.isEmpty()){
            int[] wb = pq.poll();

            int worker = wb[0];
            int bike = wb[1];

            if(!workersAssigned[worker] && !bikesAssigned[bike]){
                result[worker] = bike;
                workersAssigned[worker] = true;
                bikesAssigned[bike] = true;
                count++;

                if(count == workers.length) return result;
            }
        }
        return result;
    }

    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}
