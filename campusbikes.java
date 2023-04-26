//TC: O(m*n)
//SC: O(m*n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //null
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int[] {};
        }
        
        int m = workers.length;
        int n = bikes.length;
        int min = 2000;
        int max = 0;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int [] w = workers[i];
                int [] b = bikes[j];
                int dist = calculateDist(w,b);
                 max = Math.max(max, dist);
                 min = Math.min(min, dist);
                
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] {i,j});
            }
        }
        
        boolean [] wassigned = new boolean[m];
        boolean [] boccupied = new boolean[n];
        int count = 0;
        int [] result = new int[m];
        for(int i=min; i<=max; i++){
            List<int[]> li = map.get(i);
            if(li == null) continue;
            for(int[] wb: li){
                int w = wb[0]; int b = wb[1];
                if(!wassigned[w] && !boccupied[b]){
                    wassigned[w] = true;
                    boccupied[b] = true;
                    count++;
                    result[w] = b;
                    if(count == m ) break;
                }
            }
        }
        return result;   
    }
    
     private int calculateDist(int[] worker, int[] bike){
            return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
        }
}
