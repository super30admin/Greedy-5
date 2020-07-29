// Time Complexity : 
/*                      Approach 1 : Using List of Arrays : O(bikes length * workers length + distance length array traverse)
                        Approach 2: Using TreeMap : O((bikes length * workers length) log (bikes length * workers length))
// Space Complexity :
/*                      Approach 1 : Using List of Arrays : O(distance length array traverse + worker bike pairs at each index)
                        Approach 2: Using TreeMap : O((bikes length * workers length)) Map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Understanding the point its workers to bike mapping not the reverse.
/* Your code here along with comments explaining your approach: In approach 1, we are creating an array of list where we are ordering the
pairs of bike and workers by calculating the manhattan distance between each bike and worker pair and storing the same in a array index that maintains
a list of pairs having the same distance as index. In approach 2, we use TreeMap as the storage and its stored in nlogn order and in a sorted order. 
Hence, here in treemap, if we traverse through keys one by one, we are checking the pairs and if the bike and worker both are free to be alloted an
assignment then make it true. In approach 1, we are starting from the initial index to maintain the sorted order and checking if the bike and worker
are free by traversing through the pairs.
*/
// APPROACH 1 : USING LIST OF ARRAYS
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null) return new int[0];
        int[] result = new int[workers.length];
        List<int[]>[] dist = new List[2000];                                                                        // List of arrays
        boolean[] bikes_free = new boolean[bikes.length];                                                           // Boolean are the bikes free
        boolean[] worker_free = new boolean[workers.length];                                                        // Boolean are the worker not got bike yet
        for(int i = 0; i < workers.length; i++){
            for(int j  = 0; j < bikes.length; j++){
    int distance = calManhattanDist(workers[i][0], workers[i][1], bikes[j][0], bikes[j][1]);                                // Calculate Manhattan dist between each worker bike pair
                if(dist[distance] == null){
                    dist[distance] = new ArrayList<>();
                } 
                    dist[distance].add(new int[]{i,j});                                                     // Add the worker bike distance as index
        }
    }
        for(int k = 0; k < dist.length; k++){
            List<int[]> rel = dist[k];                                                                                  // Traverse through each list pair of bike-worker in array
            if(rel == null) continue;
            for(int[] p : rel){
                if(!worker_free[p[0]] && !bikes_free[p[1]]){                                                        // If both the bike and the worker is free
                    worker_free[p[0]] = true;
                    bikes_free[p[1]] = true;                                                                    
                    result[p[0]] = p[1];                                                                        // Assign the worker the corresponding bike
                }
            }
        }
        return result;
    }
    private int calManhattanDist(int p1, int p2, int x, int y){
        return Math.abs(p1 - x) + Math.abs(p2 - y);                                                                 // Distance formula
    }
}

// APPROACH 2 : USING TREEMAP
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null) return new int[0];
        int[] result = new int[workers.length];
        boolean[] bikes_free = new boolean[bikes.length];
        boolean[] worker_free = new boolean[workers.length];                                                       // Boolean arrays for checking if the worker and bike free
        TreeMap<Integer, List<int[]>> store = new TreeMap<>();
        for(int i = 0; i < workers.length; i++){
            for(int j  = 0; j < bikes.length; j++){
    int distance = calManhattanDist(workers[i][0], workers[i][1], bikes[j][0], bikes[j][1]);                        // Calculate manhattan distance between worker and bike
                if(!store.containsKey(distance)){
                    List<int[]> p = new ArrayList<>();
                    p.add(new int[]{i, j});
                    store.put(distance, p);
                } else {
                    store.get(distance).add(new int[]{i, j});                                                   // Put the bike and worker pair in treemap and distance as key
                }
            }
        }
        for(Map.Entry<Integer, List<int[]>> e : store.entrySet()){
            List<int[]> rel = e.getValue();
            for(int[] p : rel){
                if(!worker_free[p[0]] && !bikes_free[p[1]]) {                                                   // If the worker and bike both free
                    worker_free[p[0]] = true;                                                                   // Worker is free
                    bikes_free[p[1]] = true;                                                                    // Bike is free
                    result[p[0]] = p[1];                                                                            // Assign the worker the bike
                }
            }
        }
        return result;
    }
    private int calManhattanDist(int p1, int p2, int x, int y){
        return Math.abs(p1 - x) + Math.abs(p2 - y);                                                         // Distance Formula
    }
}
