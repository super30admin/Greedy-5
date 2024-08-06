//Time Complexity : O(m*n) + O(k), k is the range of values in hashmap
//Space Complexity : O(m*n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length;
        int n = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int dist = calculateDist(workers[i], bikes[j]);
                if(!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        boolean[] workersAssigned = new boolean[workers.length];
        boolean[] bikesAssigned = new boolean[bikes.length];
        int[] result = new int[m];
        int count = 0;
        for(int i = min; i <= max; i++) {
            if(!map.containsKey(i)) continue;
            List<int[]> li = map.get(i);
            for(int[] l : li) {
                if(!workersAssigned[l[0]] && !bikesAssigned[l[1]]) {
                    workersAssigned[l[0]] = true;
                    bikesAssigned[l[1]] = true;
                    result[l[0]] = l[1];
                    count++;
                    if(count == m) return result;
                }
            }
            
        }
        return result;
    }
    private int calculateDist(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0])+Math.abs(worker[1]-bike[1]);
    } 
}