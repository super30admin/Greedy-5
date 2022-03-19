// Time Complexity : O(mn log mn); m - length of workers and n - length of bikes
// Space Complexity : O(m)
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        //null case
        if(workers == null || workers.length == 0
                || bikes == null || bikes.length == 0){
            return new int[]{};
        }
        Map<Integer, List<int[]>> map = new TreeMap<>();
        int[] result = new int[workers.length];

        //calculate Manhattan distance
        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
                int dist = calculateDistance(workers[i], bikes[j]);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }
        boolean[] assignedWorkers = new boolean[workers.length];
        boolean[] occupiedBikes = new boolean[bikes.length];
        int count = 0;
        for(int dist: map.keySet()){
            List<int[]> li = map.get(dist);
            //worker bike pair
            for(int[] wb: li){
                int worker = wb[0];
                int bike = wb[1];

                if(!assignedWorkers[worker] && !occupiedBikes[bike]){
                    assignedWorkers[worker] = true;
                    occupiedBikes[bike] = true;
                    result[worker] = bike;
                    count++;
                    if(count == workers.length) return result;
                }
            }
        }   
        return result;
    }

    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
