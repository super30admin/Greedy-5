class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0) return new int[] {}; //if the workers is null or the length of workers is 0, we return an empty array list.
        HashMap<Integer, List<int[]>> map = new HashMap<>(); //we take a tree map with name map
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < workers.length; i++) { //we go through all the workers
            for(int j = 0; j < bikes.length; j++) { //we go through all the bikes
                int distance = calculateDistance(workers[i], bikes[j]); //calculating the distance for all the workers with all the bikes
                if(!map.containsKey(distance)) { //if the map doesnt contain the distance
                    map.put(distance, new ArrayList<>()); //we put that distance to the map and initialize a new arraylist.
                }
                min = Math.min(min, distance);
                max = Math.max(max, distance);
                map.get(distance).add(new int[] {i, j}); //here we get that distance and we add the worker and bike
            }
        }
        //workers who have been assigned bikes
        boolean[] assigned = new boolean[workers.length];
        //bikes which have been allotted
        boolean[] allotted = new boolean[bikes.length];

        int[] result = new int[workers.length]; //these are the workers who are getting the bikes
        int count = 0; //this keeps the count of the total bikes allotted

        for(int distance = min; distance <= max; distance++) { //we get all the keys from the treemap, which is the distance
            List<int[]> workerBikepairs = map.get(distance); // we get the list of integers which are the workerbike pairs from the treemap
            if(workerBikepairs == null) continue;
            for(int [] wb : workerBikepairs) { //we iterate through all the worker bike pairs
                int worker = wb[0]; //if assign each of the values to the 2 different variables
                int bike = wb[1];
                if(assigned[worker] == false) { //if assigned worker is false
                    if(allotted[bike] == false) { //if allotted bike is false
                        assigned[worker] = true; //we assign the worker
                        allotted[bike] = true; //we assign the bike
                        result[worker] = bike; //we assign the bike to the worker
                        count++; //whenever a bike is allotted, we increment the count
                    }
                }
                if(count == workers.length) return result;
            }
        }
        return result; //in the end, we return the result
    }
    private int calculateDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]); //computing the manhattan distance
    }
}
//tc and sc - O(nm)