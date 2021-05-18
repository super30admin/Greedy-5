class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        int w = workers.length;
        int b = bikes.length;
        
        int[] output = new int[w];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        
        //calculating manhattan distance for each worker with each bike and storing it in map
        for(int i = 0; i < w; i++){
            for(int j = 0; j < b; j++){
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i,j});
            }
        }
        
        //boolean arrays for workers and bikes to keep tarck if they are already assigned or not
        boolean[] workerbool = new boolean[w];
        boolean[] bikebool = new boolean[b];
        
        for(List<int[]> pairs : map.values()){
            for(int i = 0; i < pairs.size(); i++){
                int[] pair = pairs.get(i);
                int worker = pair[0];
                int bike = pair[1];
                
                //if both worker anb bike are not assigned, we assign them and update the boolean array
                if(!workerbool[worker] && !bikebool[bike]){
                    workerbool[worker] = true;
                    bikebool[bike] = true;
                    output[worker] = bike;
                }
            }
        }
        
        // return output
        return output;
    }
}

 //Time Complexity: wblog(wb)
 //space Complexity: log(wb)