// Time Complexity : O(m*n + (max-min)*k) - k is number of worker,bikes pairs.
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Approach : As the minimum distance one's should be assigned to the worker first, we start by cpturing the distances as key and worker,bike cominations as value
// in a hashmap along with the minimum and maximum distance. We start looping from minimum to maximum distance, and if the distance is present in hashmap,
// we get the corresponding worker bike combination and assign them. We also maintain a worker and bike boolean arrays to capture the status of worker and bike
// if they are already assigned.

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>(); //hashmap to capture distances as key and worker,bike cominations as value
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<workers.length; i++){
            for(int j=0; j<bikes.length; j++){
                int dist = calculateDistance(workers[i], bikes[j]);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j}); //worker, bike
                min = Math.min(min, dist); //capture min distance
                max = Math.max(max, dist); //capture max distance
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

                if(!workersAssigned[worker] && !bikesAssigned[bike]){ //check if already assigned
                    result[worker] = bike; //assign the bike
                    workersAssigned[worker] = true; //update the boolean array of worker
                    bikesAssigned[bike] = true; //update the boolean array of bike
                    count++;
                    if(count == workers.length){
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private int calculateDistance(int[] worker, int[] bike){ //calculate manhattan distance
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}
