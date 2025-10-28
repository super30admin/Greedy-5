
// Time Complexity: O(N * M + D) where N is number of workers, M is number of bikes and D is the range of distances
// Space Complexity: O(N * M) for the hashmap storing all worker-bike pairs based on their distances


//We use a hashmap to group all possible worker-bike pairs by their Manhattan distances.
//We calculate the Manhattan distance for each worker-bike pair and store them in the hashmap.
//We also keep track of the minimum and maximum distances encountered.
//Next, we iterate through the distances from minimum to maximum.
//For each distance, we check if there are any worker-bike pairs in the hashmap.
//If there are, we try to assign bikes to workers based on the current distance, ensuring that each worker and bike can only be assigned once.
//We maintain two boolean arrays to track which workers and bikes have already been assigned.
//Once all workers have been assigned bikes, we return the result array.

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<workers.length; i++){
            for(int j=0; j<bikes.length; j++){
                int dist = calculateDistance(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());

                map.get(dist).add(new int[]{i,j}); //worker, bike
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        int[] result = new int[workers.length];
        int count = 0;
        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];

        for(int i=min; i<=max; i++){
            if(!map.containsKey(i)) continue;
            
            List<int[]> list = map.get(i);
            for(int[] wb: list){
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
        }
        return result;
    }

    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}
