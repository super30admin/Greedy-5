//Time - O(m*n) 
//Space - O(m*n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //BucketSort instead of TreeMap 
        int[] result = new int[workers.length];
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<workers.length; i++){
            int[] worker = workers[i];
            for(int j=0; j<bikes.length; j++){
                int[] bike = bikes[j];
                int dist = distance(worker, bike);
                min = Math.min(min,dist);
                max = Math.max(max,dist);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});

            }
        }
        boolean[] vWorker  = new boolean[workers.length];
        boolean[] vBike  = new boolean[bikes.length];


        for(int i=min; i<=max; i++){
            List<int[]> pairs = map.get(i);
            if(pairs == null) continue;
            for(int[] wb : pairs){
                if(!vWorker[wb[0]] && !vBike[wb[1]]){
                    result[wb[0]] = wb[1];
                    vWorker[wb[0]] =true;
                    vBike[wb[1]] = true;
                }
            }
        }
        return result;

    }

    private int distance(int[] work, int[]bike){
        return Math.abs(work[0]-bike[0]) + Math.abs(work[1]-bike[1]);
    }

}