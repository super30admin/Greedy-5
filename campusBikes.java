// Time Complexity : O(mxn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution{
    public int[] assignBikes(int[][] workers, int[][] bikes){
        // null
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int[]{};
        }
        HashMap<Integer, List<int[]>> map = new HashMap<>();
	    int min = Integer.MAX_VALUE; int max = Integer.MIN_VALUE;
	    
        for(int i=0; i<workers.length; i++){
            for(int j=0; j<bikes.length; j++){
                int dist = calculateDist(workers[i], bikes[j]);
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                List<int[]> li = map.getOrDefault(dist, new ArrayList<>());
                li.add(new int[]{i,j});
                map.put(dist, li);
            }
        }
        boolean[] assigned = new boolean[workers.length];
        boolean[] alloted = new boolean[bikes.length];
        int[] result = new int[workers.length];
        int count=0;

        for(int dist=min; dist<=max; dist++){
            List<int[]> li = map.get(dist);
            if(li == null) continue;
            for(int[] wb: li){
                int currW = wb[0];
                int currB = wb[1];
                if(!assigned[currW] && !alloted[currB]){
                    assigned[currW] = true;
                    alloted[currB] = true;
                    result[currW] = currB;
		            count++;
                }
		        if(count == workers.length) return result;
            }
        }
        return result;
    }
    private int calculateDist(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}