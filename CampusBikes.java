// TC - O(n X m)

// SC - O(n X m)

// LC - 1057

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
		// Initialize output array
        int[] output = new int[workers.length];
        
		// Create visited array of both workers and bikes
        boolean[] boolWorkers = new boolean[workers.length];
        boolean[] boolBikes = new boolean[bikes.length];
        //Create a treemap as the values should be in sorted order
        Map<Integer, ArrayList<int[]>> map = new TreeMap<>();
        int m = workers.length;
        int n = bikes.length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
				// calculate manhattan distance between bike and worker and put in map
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                map.putIfAbsent(dist, new ArrayList<int[]>());
                map.get(dist).add(new int[]{i, j});
            }
        }
        
		// Iterate over treemap and update output
        for(ArrayList<int[]> entry : map.values()){
            for(int i=0; i<entry.size(); i++){
                int[] pair = entry.get(i);
                int worker = pair[0];
                int bike = pair[1];   
                
                if(!boolWorkers[worker] && !boolBikes[bike]){
                    output[worker] = bike;
                    boolWorkers[worker] = true;
                    boolBikes[bike] = true;
                }
                
            }
        }
        
        return output;
        
    }
}