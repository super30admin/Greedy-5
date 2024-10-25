// Problem 1057. Campus Bikes
// Time Complexity : O(mnlog(mn))
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length;
        int n = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] w = workers[i];
                int[] b = bikes[j];
                int dist = calculate(w, b);
                max = Math.max(max, dist);
                min = Math.min(min, dist);
                map.computeIfAbsent(dist, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        boolean[] assigned = new boolean[m];
        boolean[] occupied = new boolean[n];
        int[] result = new int[m];
        int count = 0; 
        for (int i = min; i <= max; i++) {
            List<int[]> list = map.get(i);
            if (list != null) {
                for (int[] wb : list) {
                    int w = wb[0];
                    int b = wb[1];
                    if (!assigned[w] && !occupied[b]) {
                        assigned[w] = true;
                        occupied[b] = true;
                        result[w] = b;
                        count++;
                        if (count == m) return result;
                    }
                }
            }
        }
        return result;
    }
    private int calculate(int[] w, int[] b) {
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }
}