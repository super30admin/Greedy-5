// 1057.
// time - O(mn log(mn))
// space - O(mn) for treemap
//given condition - 0 <= workers[i][j], bikes[i][j] < 1000
//worst case - 1 worker at (0,0) and 1 bike at (1000, 1000) to get manhattan distance of 2000
// so max manhattan distance possible is 2000
//thus instead of treemap used a list of int[] with size of list = 2000 to reduce run time to O(mn)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //edge
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0)
        {
            return new int[0];
        }
        
        //for each worker, find the distance between the current worker and all bikes
        TreeMap<Integer, List<int[]>> support = new TreeMap<>(); //distance maps to list of worker,bike pairs
        //treemap holds the distances in increasing order
        for(int i = 0; i < workers.length; i++)
        {
            for(int j = 0; j < bikes.length; j++)
            {
                int[] worker = workers[i];
                int[] bike = bikes[j];
                //manhattan distance between current worker and current bike (|x1 - x2| + |y1 - y2|)
                int distance = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                //for each distance found insert distance key as key and (worker, bike) pair as value into treemap
                if(!support.containsKey(distance))
                {
                    support.put(distance, new ArrayList<>());
                }
                support.get(distance).add(new int[] {i, j});
            }
        }
        
        int[] result = new int[workers.length]; //return list
        //boolean[] to track whether a bike or a worker is already assigned
        boolean[] assignedWorkers = new boolean[workers.length]; 
        boolean[] assignedBikes = new boolean[bikes.length];
        
        for(int distance : support.keySet())
        {
            List<int[]> pairs = support.get(distance);
            for(int[] currentPair : pairs)
            {
                int currentWorker = currentPair[0];
                int currentBike = currentPair[1];
                if(!assignedWorkers[currentWorker] && !assignedBikes[currentBike])
                {
                    //both current worker and current bike are not assigned
                    //assign current worker to current bike
                    assignedWorkers[currentWorker] = true;
                    assignedBikes[currentBike] = true;
                    result[currentWorker] = currentBike;
                }
            }
        }
        
        return result;
    }
}
