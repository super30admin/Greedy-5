// Time Complexity : O(w*b + K) where w = no. of workers, b = no. of bikes
// Space Complexity : O(w*b)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create a treemap of integer and list of pair
// Integer would represent manhattan distance
// List of pairs would represent the list indices of worker and bike having that distance
// Now we can iterate over treemap (it would ordered as its treemap)
// For each list of pairs that we get we will check if the worker or bike is already assigned
// If not we will add to the result (here we have reused the array workersAssigned instead of creating new array)
// We will finally return the result.
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, List<Pair>> tm = new TreeMap<>();
        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
                int manDist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if(!tm.containsKey(manDist)){
                    tm.put(manDist, new ArrayList<Pair>());
                }
                tm.get(manDist).add(new Pair(i, j));
            }
        }
        boolean[] bikesAssigned = new boolean[bikes.length];
        int[] workersAssigned = new int[workers.length];
        Arrays.fill(workersAssigned, -1);
        for(int dist: tm.keySet()){
            List<Pair> listWithCurDist = tm.get(dist);
            for(Pair pair: listWithCurDist){
                if(workersAssigned[pair.worker] == -1 && bikesAssigned[pair.bike] == false){
                    workersAssigned[pair.worker] = pair.bike;
                    bikesAssigned[pair.bike] = true;
                }    
            }
            
        }
        return workersAssigned;
    }
}
class Pair{
        int worker;
        int bike;
        Pair(int worker, int bike){
            this.worker = worker;
            this.bike = bike;
        }
    }