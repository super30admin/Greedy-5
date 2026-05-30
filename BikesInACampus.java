// Time Complexity : O(m*n + (max-min)*K), where K = avg number of worker-bike pairs per distance
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
Maintain a map of distance to worker-biker pairs list by computing manhattan distance. Also maintain minimum
and maximum values of distance and iterate from min to max and check each worker-biker pair, if not assigned,
assign biker to worker. Stop once all workers are assigned a bike.
 */
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0 ; i < workers.length ; i++) {
            for(int j = 0 ; j < bikes.length ; j++) {
                int dist = calculateDistance(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[] {i, j});

                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        int[] result = new int[workers.length];
        boolean[] isWorkerAssigned = new boolean[workers.length];
        boolean[] isBikerAssigned = new boolean[bikes.length];
        int count = 0;

        for(int i = min ; i <= max ; i++) {
            if(!map.containsKey(i))
                continue;
            List<int[]> list = map.get(i);
            for(int[] wb : list) {
                int worker = wb[0];
                int biker = wb[1];

                if(!isWorkerAssigned[worker] && !isBikerAssigned[biker]) {
                    result[worker] = biker;
                    isBikerAssigned[biker] = true;
                    isWorkerAssigned[worker] = true;
                    count++;
                    if(count == workers.length)
                        return result;
                }

            }
        }
        return result;
    }

    private int calculateDistance(int[] workers , int[] bikes) {
        return Math.abs(workers[0] - bikes[0]) + Math.abs(workers[1] - bikes[1]);
    }
}