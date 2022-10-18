//O(MxN logmn) solution

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        TreeMap<Integer, List<int []>> map = new TreeMap<>();
        int m = workers.length;
        int n = bikes.length;
        for(int i=0;i<m;i++){
            for (int j =0 ;j<n ;j++){
                int [] w = workers[i]; //takes coordinates of worker at index i
                int [] b = bikes[j]; //takes coordinates of bikes at index j
                int dist = manhattanDist(w,b);
                if (!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] {i,j});
            }
        }
        boolean[] bikesAssigned = new boolean[m];
        boolean[] workersOccupied = new boolean[n];
        int[] result = new int[m]; //which worker is assigned which bike
        int count =0;// if count become m we can stop
        for(int dist: map.keySet()){
            List<int []> li = map.get(dist); //getting list of worker bike pairs
            for(int [] wb: li){
                int w = wb[0]; //worker
                int b = wb[1]; //bike
                if (!workersOccupied[b] && !bikesAssigned[w]){
                    workersOccupied[b] = true;
                    bikesAssigned[w] = true;
                    result[w] = b;
                    count++;
                    if (count == m) return result;
                }
            }
        }
    return result;    
    }
    private int manhattanDist(int[] worker, int[] bike){
        return Math.abs(worker[1]-bike[1]) + Math.abs(worker[0] - bike[0]);
    } 
}
