//TC - M * N
//SC - O(n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0)    return new int[] {};
        
        int m = workers.length; int n = bikes.length;
        
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int minDist = 1000, maxDist = -1000;
        
        for(int i = 0; i< m; i++){
            for(int j = 0; j< n; j++){
                int[] worker = workers[i];
                int[] bike = bikes[j];
                
                int dist = getDist(worker, bike); // get distance beetween worker and bike
                
                minDist = Math.min(minDist, dist);
                maxDist = Math.max(maxDist, dist);
                //Update min and max distances
                
                if(!map.containsKey(dist)){// No pair for dist in map, create it
                    map.put(dist, new ArrayList<>());
                }
                
                List<int[]> list = map.get(dist);
                list.add(new int[]{i, j});
            }
        }
        
        boolean[] assignedToWorker = new boolean[m];
        boolean[] occupiedBike = new boolean[n];
        
        int[] result = new int[m];
        int c = 0;
        for(int i = minDist; i <= maxDist; i++){
            List<int[]> li = map.get(i);
            
            if(li != null){
                for(int[] pair : li){
                    int wk = pair[0];
                    int bk = pair[1];
                    if(!assignedToWorker[wk] && !occupiedBike[bk]){
                        assignedToWorker[wk] = true;
                        occupiedBike[bk] = true;
                        result[wk] = bk;
                        c++;
                    }
                    if(c == m)  return result;
                }
            }
        }
        
        return result;
    }
    public int getDist(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
