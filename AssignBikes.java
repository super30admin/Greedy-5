// Time Complexity : The time complexity is O(m*n) where m is the length of the workers and n is the length of bikes
// Space Complexity : Te space complexity is O(m*n) where m is the length of the workers and n is the length of bikes
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        Map<Integer,List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){

                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);

                //group by distance
                List<int[]> li = map.getOrDefault(dist,new ArrayList<>());
                li.add(new int[]{i,j});
                map.put(dist,li);

                min = Math.min(min,dist);
                max = Math.max(max,dist);
            }
        }

        int[] res = new int[workers.length];
        Arrays.fill(res,-1);
        boolean[] assigned = new boolean[bikes.length];
        int count = 0;

        for(int i=min;i<=max;i++){
            if(map.containsKey(i)){
                List<int[]> li = map.get(i);
                for(int[] each:li){
                    //first workers gets the first bike
                    if(res[each[0]] == -1 && !assigned[each[1]]){
                        res[each[0]] = each[1];
                        assigned[each[1]] = true;
                        count++;

                        if(count == workers.length){
                            return res;
                        }
                    }
                }
            }
        }
        return res;
    }
}