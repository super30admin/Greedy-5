// Time Complexity : O(m * n)
// Space Complexity : O(1)

class Solution {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null || workers.length == 0 || bikes.length == 0)
            return new int [0];

        int n = workers.length; int m = bikes.length;
        int [] result = new int [n];
        List<int[]> [] distances = new List[2000];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int d = calculateDist(workers[i], bikes[j]);
                if(distances[d] == null){
                    distances[d] = new ArrayList<>();
                }
                distances[d].add(new int []{i, j});
            }
        }

        boolean [] assigned = new boolean [n]; 
        boolean [] occupied = new boolean [m];
        for(List<int[]> li : distances){
            if(li == null) 
                continue;
            for(int [] wbp: li){
                int w = wbp[0]; 
                int b = wbp[1];
                if(!assigned[w] && !occupied[b]){
                    assigned[w] = true; 
                    occupied[b] = true;
                    result[w] = b;
                }
            }
        }

        return result;
    }

    private int calculateDist(int[] worker, int[] bike){
        return Math.abs(worker[1] - bike[1]) + Math.abs(worker[0] - bike[0]);
    }
}