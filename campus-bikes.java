//APPROACH 2: instead of a treemap,  took array of list of size 2000
//Time Complexity: O(mn), lookup time is O(1) in the array
//Space Complexity: O(mn), all the worker bike pairs go in array. worstcase all the worker bike pairs have unique distance value
//Running on leetcode:yes
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length==0) return new int[] {};
        int wl = workers.length;
        int bl = bikes.length;
        int[] result = new int[wl];
        boolean[] allottedBikes = new boolean[bl];
        boolean[] allottedWorkers = new boolean[wl];
        //treemap contains distance between worker and bike as key and list of pair of worker and bike as the value
        //TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        List<int[]> [] lists = new List[2000];
        int min = 2000;
        int max = 0;
        //compute the distance between all the bikes and the workers
        for(int i=0; i<wl; i++) {
            for(int j=0; j<bl; j++){
                int dist = findDistance(workers[i], bikes[j]);
                //check the map for the distance
                //if(!map.containsKey(dist)) {
                    //map.put(dist, new ArrayList<>());
                //}
                if(lists[dist] == null) {
                    lists[dist] = new ArrayList<>();
                }
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                lists[dist].add(new int[] {i, j});
                //map.get(dist).add(new int[] {i, j});
            }
        }
        //for(int dist : map.keySet()) {
        for(int i=min; i <= max; i++){
            List<int[]> list = lists[i];
            if(list != null) {
            for(int[] wb : list) {
                int worker = wb[0];
                int bike = wb[1];
                if(!allottedWorkers[worker] && !allottedBikes[bike]) {
                    result[worker] = bike;
                    allottedWorkers[worker] = true;
                    allottedBikes[bike] = true;
                }
            }
                }
        }
        return result;
    }
    //the bike and worker at each index is coordinates (array of integers)
    private int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}
