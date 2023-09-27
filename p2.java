// Time Complexity : O(mm*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        //min max values in hashmap keys
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        //Get the hashmap keys which are distanc and values which are worker and bike
        for(int i=0; i<n ; i++){
            for(int j=0; j<m; j++){
                int dist = distance(workers[i], bikes[j]);
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }

        boolean[] w = new boolean[n];
        boolean[] b = new boolean[m];
        int[] result = new int[n];

        //Go over each and every entry in hashmap
        //
        for(int i=min; i<=max; i++){
            if(!map.containsKey(i)) continue;
            List<int[]> list = map.get(i);
            for(int j=0; j<list.size(); j++){
                int[] wb = list.get(j);
                if(!w[wb[0]] && !b[wb[1]]){
                    w[wb[0]] = true;
                    b[wb[1]] = true;
                    result[wb[0]] = wb[1];
                }
            }
        }

        return result;
    }

    private int distance(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}