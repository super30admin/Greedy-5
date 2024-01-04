// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class BikesInCampus {
    class Solution {
        public int[] assignBikes(int[][] workers, int[][] bikes) {
            int m = workers.length, n = bikes.length;
            int result[] = new int[m];

            if(m == 0 || n == 0 || workers == null || bikes == null)
                return result;

        /*Greedy
        Go through weach worker and find the manhattan dist and store it in map
        maintain min and max dist and loop through it : bucket sort
        maitain boolean arrays of assigned and occupied for tracking bikes nad workers.
        assign shortest pair first and so on..*/

            Map<Integer, List<int[]>> map = new HashMap<>();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

            for(int i = 0; i < m; i++){
                int[] w = workers[i];
                for(int j = 0; j < n; j++){
                    int[] b = bikes[j];
                    int dist = calManDist(w, b);
                    min = Math.min(min, dist);
                    max = Math.max(max, dist);
                    if(!map.containsKey(dist)){
                        map.put(dist, new ArrayList<>());
                    }
                    map.get(dist).add(new int[]{i, j});
                }
            }

            boolean[] assigned = new boolean[m];
            boolean[] occupied = new boolean[n];
            int count = 0;

            for(int dist = min; dist <= max; dist++){
                List<int []> list = map.get(dist);
                if(list == null)
                    continue;
                for(int[] wb : list){
                    int w = wb[0];
                    int b = wb[1];

                    if(!assigned[w] && !occupied[b]){
                        assigned[w] = true;
                        occupied[b] = true;
                        result[w] = b;
                        count++;
                        if(count == m)
                            return result;
                    }
                }
            }
            return result;
        }

        private int calManDist(int[] w, int[] b){
            return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
        }
    }
}
