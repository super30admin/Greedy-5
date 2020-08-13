// Time Complexity - O(mnlog(distance(m,n)) where m is the number of workers and n is the number of bikes
// Space Complexity - O(k+mn) where k is the number of distances andm is the number of workers and n is the number of bikes
// This Solution worked on LeetCode

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length ==0 || bikes == null || bikes.length ==0)
            return new int[0];
        Map<Integer,List<int[]>> map = new TreeMap<>();
        int m = workers.length;
        int n = bikes.length;
        int[] result = new int[m];
        boolean[] assigned = new boolean[m];
        boolean[] occupied = new boolean[n];
        for(int i=0; i< m;i++){
            for(int j=0; j < n;j++){
                int dist = CalculateDistance(workers[i],bikes[j]);
                if(!map.containsKey(dist))
                    map.put(dist,new ArrayList<>());
                map.get(dist).add(new int[]{i,j});
            }    
        }
        for(int dist : map.keySet()){
            List<int[]> li = map.get(dist);
            for(int[] wbp : li){
                int worker = wbp[0];
                int bike = wbp[1];
               if(!assigned[worker] && !occupied[bike]){
                   assigned[worker] = true;
                   occupied[bike] = true;
                   result[worker] = bike;
               } 
            }
        }
        return result;
        
    }
    
    private int CalculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1] - bike[1]);    
    }
}
