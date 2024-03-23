public class Solution {
    /**
     * @param workers: workers' location
     * @param bikes: bikes' location
     * @return: assign bikes
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // write your code here
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) return new int[] {};
        int m = workers.length;
        int n = bikes.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, List<int []>> map = new HashMap<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int dist = absoluteDistance(workers[i],bikes[j]);
                min = Math.min(min,dist);
                max = Math.max(max,dist);
                if(!map.containsKey(dist)){
                    map.put(dist,new ArrayList<>());
                }
                map.get(dist).add(new int[] {i,j});
            }
        }
        boolean [] assignedW = new boolean[m];
        boolean [] ocuupiedB = new boolean[n];

        int [] result = new int[m];
        int count = 0;

        for(int dist = min; dist <= max; dist++){
            List<int []> li = map.get(dist);
            if( li != null){
                for(int [] wb : li){
                    int w = wb[0], b= wb[1];
                    if(!assignedW[w] && !ocuupiedB[b]){
                        count++;
                        assignedW[w] = true;
                        ocuupiedB[b] = true;
                        result[w] = b;
                        if(count == m) return result;
                    }
                }
            }
        }
        return result;
    }

    private int absoluteDistance(int [] worker, int [] bike){
        return (Math.abs(worker[0] - bike[0]) + Math.abs(worker[1]-bike[1]));
    }
}
