// Time Complexity : O(M*N) 

// Space Complexity : O(N)

// Did this code successfully run on Leetcode : YES

// Appoarch: HashMap - find the manhattan dist and store it in a hashmap with the dist and correspoding
// [w,b] details. Iterate the map and find the how many bikes can be assigned and return array.

// 1057. Campus Bikes

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    private int ManhattanDist(int [] w,int [] b){
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]); 
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int[] {};
        }
        int m = workers.length;
        int n = bikes.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer,List<int[]>> map = new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int dist = ManhattanDist(workers[i],bikes[j]);
                min = Math.min(min,dist);
                max = Math.max(max,dist);
                if(!map.containsKey(dist)){
                    map.put(dist,new ArrayList<>());
                }
                map.get(dist).add(new int[] {i,j});
            }
        }
        boolean [] workersAssigned = new boolean[m];
        boolean [] bikesAssigned = new boolean[n];
        int [] result = new int[m];
        int count = 0;
        for(int dist=min;dist<=max;dist++){
            List<int []> li = map.get(dist);
            if(li != null){
                for(int [] wb : li){
                    int w = wb[0];
                    int b = wb[1];
                    if(!bikesAssigned[b] && !workersAssigned[w]){
                        count++;
                        bikesAssigned[b] = true;
                        workersAssigned[w] = true;
                        result[w] = b;
                        if(count == m) return result;
                    }
                }
            }
        }
        return result;
    }
}