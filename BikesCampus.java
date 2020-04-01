import java.util.*;

/*
Time Complexity: O(MN Log(MN)) for Approach 1,O(MN) for Approach 2
Space Complexity: O(MN) for Both Approaches
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
*/

/**
 * 
 *  Algos Steps
 *  1. Calculate Manhattan Distance between every workers and bike pairs
 *  2. Create HashMap of Integer,List of Integer Array storing the distance and 
 * list of integer array of indexes of bikes and workers pairs
 *  3. Create two boolean array of assigned and occupied to processing allocation of bikes
 * to workers
 *  4. do following steps
 *    a.) Pop out bike and worker pair index.Let indexes be i and j
 *     b.) Check these index in assigned and occupied for allocation
 *     c.) If values are not present,mark them as allocated and occupied,
 *       Assign given bike to worker and updated in result
 *    d.) IF values are present,check for other slots which are not assigned and occupied
 *    e.) Repeat above steps until all workers are allocated bikes
 *  
 * 
 *  Fundamental difference between two pairs is use of TreeMap and Arrays of List of Integer Array for Storing
 * Distance and (Worker,Bike) pair index.
 *   Time Complexity for HashMap is O(MN Log(MN))
 *   Time Complexity for Arrays of List of Integer Array is O(MN)
 * 
 */

public class BikesCampus {

    private static boolean shouldUseHashMap = false;
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int[0];
        }
        int size1 = workers.length;
        int size2 = bikes.length;

        int result[] = new int[size1];

        Map<Integer,List<int []>> map = new TreeMap<>();
        List<int []> [] listArray = new List[2000];

        if(shouldUseHashMap){
            for(int i = 0; i < size1; i++){
                for(int j = 0; j < size2; j++){
                    int distance = calculateDistance(workers[i], bikes[j]);
                    if(!map.containsKey(distance)){
                        map.put(distance,new ArrayList<>());
                    }
                    map.get(distance).add(new int[]{i,j});
                }
            }
    
            boolean assigned[] = new boolean[size1];
            boolean occupied[] = new boolean[size2];
    
            for(int key : map.keySet()){
                List<int []> dList = map.get(key);
                for(int d[] : dList){
                    int worker = d[0];
                    int bike = d[1];
                    if(!assigned[worker] && !occupied[bike]){
                        assigned[worker] = true;
                        occupied[bike] = true;
                        result[worker] = bike;
                    }
                }
            }
        }
        else {
            for(int i = 0; i < size1; i++){
                for(int j = 0; j < size2; j++){
                    int dist = calculateDistance(workers[i], bikes[j]);
                    if(listArray[dist] == null){
                       listArray[dist] = new ArrayList<>(); 
                    }
                    listArray[dist].add(new int[]{i,j}); 
                    
                }
            }
    
            boolean assigned[] = new boolean[size1];
            boolean occupied[] = new boolean[size2];
    
            for(List<int []> dist : listArray){
                if(dist != null) {
                    for(int d[] : dist){
                        int worker = d[0];
                        int bike = d[1];
                        if(!assigned[worker] && !occupied[bike]){
                            assigned[worker] = true;
                            occupied[bike] = true;
                            result[worker] = bike;
                        }
                    }
                }
            }
        }
      
        return result;
    }
    
    public int calculateDistance(int [] workers,int bikes[]){
        return Math.abs(workers[0] - bikes[0]) + Math.abs(workers[1] - bikes[1]);
    }
}