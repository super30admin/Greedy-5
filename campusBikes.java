// Time Complexity : O(len(workers)*len(bikes))
// Space Complexity : O(2000) = O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer,List<int[]>> distMap = new HashMap<>();
        int wLen = workers.length;
        int bLen = bikes.length;
        for(int i = 0 ; i < wLen ; i++){
            int[] worker = workers[i];
            for(int j = 0 ; j < bLen ; j++){
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0]-bike[0])+Math.abs(worker[1]-bike[1]);
                if(!distMap.containsKey(dist)) distMap.put(dist,new ArrayList<int[]>());               
                distMap.get(dist).add(new int[]{i,j});
            }
        }
        
        boolean[] isWorkerFree = new boolean[wLen];
        boolean[] isBikeFree = new boolean[bLen];
        int[] result = new int[wLen];
        int count = 0;
        for(int i = 0 ; i < 2001 ; i++){
            if(distMap.containsKey(i)){        
                for(int[] pair : distMap.get(i)){
                    int worker = pair[0];
                    int bike = pair[1];
                    if(!isWorkerFree[worker] && !isBikeFree[bike]){
                        isWorkerFree[worker] = true;
                        isBikeFree[bike] = true;
                        result[worker] = bike;
                        count++;
                        if(count == wLen) break;
                    }                  
                }
            }
        }
        
        return result;
    }
}