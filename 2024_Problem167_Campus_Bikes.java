//1057. Campus Bikes - https://leetcode.com/problems/campus-bikes/description/
//Time Complexity: O(m*n)
//Space Complexity: O(m*n)
//bucket sorting
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MAX_VALUE;

        for(int i=0; i<workers.length; i++){
            for(int j=0; j<bikes.length; j++){
                int dist = calculateDist(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i,j}); //worker, bikes
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }
        //visited arrays
        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];

        int[] result = new int[workers.length];
        int count=0;
        for(int i=min; i<=max; i++){
            if(!map.containsKey(i)) continue;
            List<int[]> wbPair = map.get(i);//get all the list of that distance
            for(int[] wb: wbPair){
                int worker = wb[0];
                int bike = wb[1];
                //if not assigned, assign
                if(!workersAssigned[worker] && !bikesAssigned[bike]){
                    result[worker] = bike;
                    workersAssigned[worker] = true;
                    bikesAssigned[bike] = true;
                    count++;
                    if(count == workers.length) return result;
                }
            }
        }
        return result;
    }

    private int calculateDist(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}