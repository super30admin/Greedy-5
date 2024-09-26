/*
Problem: https://leetcode.com/problems/campus-bikes/
*/
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || bikes == null || workers.length == 0 || bikes.length == 0)
            return new int[0];
        
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        
        for (int i = 0; i < workers.length; ++i) {
            for (int j = 0; j < bikes.length; ++j) {
                int worker[] = workers[i];
                int bike[] = bikes[j];
                int distance = getManhattanDistance(worker, bike);
                
                if (!map.containsKey(distance)) {
                    map.put(distance, new ArrayList<>());
                }
                map.get(distance).add(new int[]{i, j});
            }
        }
        
        boolean[] usedBikes = new boolean[bikes.length];
        int result[] = new int[workers.length];
        Arrays.fill(result, -1);
        
        int assignedWorkers = 0;
        
        for (int distance : map.keySet()) {
            List<int[]> workerBikePair = map.get(distance);
            
            for (int i = 0; i < workerBikePair.size(); ++i) {
                int worker = workerBikePair.get(i)[0];
                int bike = workerBikePair.get(i)[1];
                
                if (result[worker] == -1 && usedBikes[bike] == false) {
                    result[worker] = bike;
                    usedBikes[bike] = true;
                    ++assignedWorkers;
                    if (assignedWorkers == result.length)
                        break;
                }
            }
        }
        
        return result;
    }
    
    private int getManhattanDistance(int worker[], int bike[]) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}