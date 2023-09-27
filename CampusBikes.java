import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// Time Complexity : O(m*n)  m is the number of bikes and n is the number of workers
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes

public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {

        int n = workers.length;
        int m = bikes.length;
        int max =Integer.MIN_VALUE;
        int min = 0;
        int result[] = new int[n];
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int dist = calculateDistance(workers[i],bikes[j]);
                max = Math.max(max,dist);
                min = Math.min(min,dist);
                if(!map.containsKey(dist)){
                    map.put(dist,new ArrayList<int[]>());
                }
                map.get(dist).add(new int[]{i,j}); // worker bike pair
            }
        }
        HashSet<Integer> visitedBikes = new HashSet<>();
        HashSet<Integer> visitedWorkers = new HashSet<>();
        int count=0;
        for(int d=min; d<= max; d++){
            if(!map.containsKey(d)) continue;
            List<int[]> li = map.get(d);

            for(int[] wb:li){
                int worker = wb[0];
                int bike = wb[1];
                if(!visitedBikes.contains(bike) && !visitedWorkers.contains(worker)){
                    result[worker] = bike;
                    count++;
                    visitedBikes.add(bike); visitedWorkers.add(worker);
                    if(count == n) return result;
                }
            }
        }

        return result;
    }


    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
