// Time Complexity : O(nm log nm) - because of TreeMap which stores keys in natural sorted order
// Space Complexity : O(nm) - map stores key which are dist, so max dist can be nm in the worst case
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//Approach 1 - Using TreeMap
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();

        int count = 0;
        int len1 = workers.length;
        int len2 = bikes.length;
        boolean[] workersAssigned = new boolean[len1];
        boolean[] bikesAssigned = new boolean[len2];
        int[] result = new int[len1];

        for(int i = 0; i < len1; i++) {
            for(int j = 0; j < len2; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if(!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }

        for(int d : map.keySet()) {
            List<int[]> list = map.get(d);
            for(int[] li : list) {
                if(!workersAssigned[li[0]] && !bikesAssigned[li[1]]) {
                    result[li[0]] = li[1];
                    count++;
                    workersAssigned[li[0]] = true;
                    bikesAssigned[li[1]] = true;
                    if(count == len1) return result;
                }
            }
        }
        return result;
    }
}

// Time Complexity : O(nm)
// Space Complexity : O(nm) - map stores key which are dist, so max dist can be nm in the worst case
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//Approach 2 - Using Counting Sort
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int maxDist = Integer.MIN_VALUE;
        int minDist = Integer.MAX_VALUE;
        int count = 0;
        int len1 = workers.length;
        int len2 = bikes.length;
        boolean[] workersAssigned = new boolean[len1];
        boolean[] bikesAssigned = new boolean[len2];
        int[] result = new int[len1];

        for(int i = 0; i < len1; i++) {
            for(int j = 0; j < len2; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if(maxDist < dist) maxDist = dist;
                if(minDist > dist) minDist = dist;
                if(!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }

        for(int d = minDist; d <= maxDist; d++) {
            List<int[]> list = map.get(d);
            if(null != list) {
                for(int[] li : list) {
                    if(!workersAssigned[li[0]] && !bikesAssigned[li[1]]) {
                        result[li[0]] = li[1];
                        count++;
                        workersAssigned[li[0]] = true;
                        bikesAssigned[li[1]] = true;
                        if(count == len1) return result;
                    }
                }
            }
        }
        return result;
    }
}