// Time Complexity : O(m*n log(m*n))
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : No // premium question
// Any problem you faced while coding this : Yes

    public int[] assignBikes(int[][] workers, int[][] bikes){
        if(workers == null || workers.length == 0 || bikes == null || bikes.length ==0){
            return new int[]{};
        }

        int w = workers.length;
        int b = bikes.length;

        TreeMap<Integer, List<List<Integer>>> map = new TreeMap<>();
        int result[] = new int[w];
        Boolean assignedWorkers[] = new Boolean[w];
        Boolean assignedBikes[] = new Boolean[b];

        int count = 0;

        for(int i=0; i< w; i++){
            for(int j =0; j< b; j++){
                int distance = findManhattanDistance(workers[i], bikes[j]);

                if(!map.containsKey(distance)){
                    map.put(distance, new ArrayList<>());
                }
                map.get(distance).add(Arrays.asList(i,j));
            }
        }
        for(int key : map.keySet()){
            List<List<Integer>> workerBikes = map.get(key);
            for(List<Integer> wb : workerBikes){
                int worker = wb.get(0);
                int bike = wb.get(1);
                if(assignedWorkers[worker]==false){
                    if(assignedBikes[bike]==false){
                        assignedWorkers[worker] = true;
                        assignedBikes[bike] = true;
                        result[worker] = bike;
                        count++;
                        if(count == b){
                            break;
                        }
                    }
                }
            }
        }
        return result;

    }
    private int findManhattanDistance(int [] worker, int [] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }