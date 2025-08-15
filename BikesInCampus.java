// TC: O(m * n) m = len of workers, n = len of bikes
// SC: O(m * n) all pairs has unique dists
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < bikes.length; i++){
            for(int j = 0; j < workers.length; j++){
                int dist = calculateDistance(workers[j], bikes[i]);

                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add( new int[] {j, i});

                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        int[] result = new int[workers.length];
        int count = 0;
        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];

        for(int i=min; i<= max; i++){

            if(count == workers.length) {
                break;
            }
            if(!map.containsKey(i)){
                continue;
            }

            for(int[] pair : map.get(i)){
                if(!workersAssigned[pair[0]] && !bikesAssigned[pair[1]]){
                    result[pair[0]] = pair[1];
                    workersAssigned[pair[0]] = true;
                    bikesAssigned[pair[1]] = true;
                    count++;
                }
            }
        }

        return result;
    }

    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}
