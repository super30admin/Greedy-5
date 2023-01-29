// Time Complexity : O(n*m)
// Space Complexity : O(n*m)  
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//1057. Campus Bikes

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null || workers.length == 0) return new int[] {};
        
        int n = workers.length;
        int m = bikes.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        
        for(int i = 0; i < n; i++){
            int[] worker = workers[i];
            for(int j = 0; j < m; j++){
                int[] bike = bikes[j];
                int dist = mDist(worker, bike);
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] {i,j});
            }
        }
        int[] result = new int[n];
        Arrays.fill(result, -1);
        boolean[] assgBikes = new boolean[m];
        int count = 0;
        
        for(int i = min; i <= max; i++){
            List<int[]> l = map.get(i);
            if(l == null) continue;
            for(int[] wb : l){
                int w = wb[0];
                int b = wb[1];
                if(result[w] == -1){
                    if(assgBikes[b] == false){
                        result[w] = b;
                        assgBikes[b] = true;
                        count++;
                        if(count == n) return result;
                    }
                }
            }
        }
        return result;
    }
    private int mDist(int[] worker, int[] bike){
        if(worker == null || bike == null) return 0;
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]); 
    }
}