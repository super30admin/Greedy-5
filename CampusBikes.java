// Time Complexity : O(m * n)
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : NA


// Your code here along with comments explaining your approach

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
            return new int[] {};
        }
        
        List<int[]> [] liArray = new List[2000];
        
        for(int i = 0; i < workers.length; i++) {
            for(int j = 0; j < bikes.length; j++) {
                int dist = computeDistance(workers[i], bikes[j]);
                if(liArray[dist] == null) {
                    liArray[dist] = new ArrayList<>();
                }
                liArray[dist].add(new int[] {i, j});
            }
        }
        
        int[] result = new int[workers.length];
        Arrays.fill(result, -1);
        boolean[] allottedBikes = new boolean[bikes.length];
        
        for(int dist = 0; dist < 2000; dist++) {
            List<int[]> li = liArray[dist];
            
            if(li != null) {
                for(int[] item : li) {
                    int worker = item[0];
                    int bike = item[1];
                    if(result[worker] == -1 && allottedBikes[bike] == false) {
                        result[worker] = bike;
                        allottedBikes[bike] = true;
                    }
                }
            }
        }
        return result;
    }
    
    private int computeDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}