// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
            return new int[] {};
        }
        
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        
        int m = workers.length;
        int n = bikes.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int[] worker = workers[i];
                int[] bike = bikes[j];
                
                int distance = getManhattanDistance(worker, bike);
                
                if(!map.containsKey(distance)) {
                    map.put(distance, new ArrayList<>());
                }
                
                map.get(distance).add(new int[] {i, j});
                
                min = Math.min(min, distance);
                max = Math.max(max, distance);
            }
        }
        
        int[] result = new int[workers.length];
        boolean[] allocatedBikes = new boolean[bikes.length];
        int count = 0;
        Arrays.fill(result, -1);
        for(int distance = min; distance <= max; distance++) {
            List<int[]> pairs = map.get(distance);
            
            if(pairs != null) {
                for(int[] pair: pairs) {
                    int workerIndex = pair[0];
                    int bikeIndex = pair[1];

                    if(result[workerIndex] == -1 && allocatedBikes[bikeIndex] == false) {
                        result[workerIndex] = bikeIndex;
                        allocatedBikes[bikeIndex] = true;
                        count++;
                    }

                    if(count == m) {
                        return result;
                    }
                }
            }

        }
        
        return result;
    }
    
    private int getManhattanDistance(int[] worker, int[] bike) {
        
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}