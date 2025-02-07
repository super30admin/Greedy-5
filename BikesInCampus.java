// Approach : Worker with smaller distance to bike is assigned for food delivery. Need to make sure we only have less unhappy customers
// Manhattan distance is nothing but sum of abs difference b/w x and y
// * For all workers identify the distance of all bikes. Example: W0 --> B0,B1,B2,B3
// Time : O(m*n) + O(m*n)
// Space : O(m*n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer , List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Go over all the workers and bikes 
        // calculate the distances and store the information in HashMap
        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                int dist = calculateDist(workers[i],bikes[j]);// worker and bike position
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i,j});// worker bike pair
                min = Math.min(min , dist);
                max = Math.max(max , dist);
            }
        }
        boolean[] workerAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];
        int[] result = new int[workers.length];
        int count =0;
        for(int i=min;i<=max;i++){
            // we are iterating the distances 1 by 1 from min to max. We might not have every distance stored in map
            if(!map.containsKey(i)) continue;
            // get list of worker bike pairs for that distance
            List<int[]> wbPairs = map.get(i);
            // go through all the worker bike pairs and check if already assigned
            for(int[] wb: wbPairs){
                int worker = wb[0];
                int bike = wb[1];
                if(!workerAssigned[worker] && !bikesAssigned[bike]){
                    result[worker] = bike;
                    workerAssigned[worker] = true;
                    bikesAssigned[bike] = true;
                    count++; 
                    if(count == workers.length) return result;
                }
            }
        }

        return result;

    }
    private int calculateDist(int[] worker , int[] bike){
        // Manhattan distance
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}