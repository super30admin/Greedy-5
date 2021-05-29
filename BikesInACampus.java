class BikesInACampus {    
    // Time Complexity: O(n*m*log(n*m)) (where n -> no. of workers and m -> no. of bikes)
    // Space Complexity: O(log(n*m))
    
    // Function to calculate Manhattan Distance
    private int calculateDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0)
            return new int[]{-1};

        int n = workers.length;
        int m = bikes.length;
        
        // Populating a map based on distance between (worker,bike) pairs in ascending order for each distance
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int dist = calculateDistance(workers[i][0], workers[i][1], bikes[j][0], bikes[j][1]);
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i,j});
            }
        }
        
        int[] result = new int[n];
        boolean[] wA = new boolean[n];
        boolean[] bA = new boolean[m];
        
        for(List<int[]> pairs : map.values()){
            for(int[] pair : pairs){
                int worker = pair[0];
                int bike = pair[1];

                // If the worker and bike are both unoccupied then only we assign the bike to the worker
                if(!wA[worker] && !bA[bike]){
                    wA[worker] = true;
                    bA[bike] = true;
                    result[worker] = bike;
                }
            }
        }
        
        return result;
    }
}