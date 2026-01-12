
// Time Complexity : O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : I am confused on the logic

/*
Approach
*/
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int m = workers.length;
        int n = bikes.length;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dist = findDistance(workers, bikes, i, j);
                min = Math.min(dist, min);
                max = Math.max(dist, max);
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[] { i, j });
            }
        }
        int count = 0;
        boolean[] assigned = new boolean[m];
        boolean[] occupied = new boolean[n];
        int[] result = new int[m];

        for (int dist = min; dist <= max; dist++) {
            List<int[]> li = map.get(dist);
            if (li != null) {
                for (int[] wb : li) {
                    int w = wb[0];
                    int b = wb[1];

                    if (!assigned[w] && !occupied[b]) {
                        assigned[w] = true;
                        occupied[b] = true;
                        result[w] = b;
                        count++;
                        if (count == m)
                            return result;
                    }
                }
            }
        }
        return result;
    }

    private int findDistance(int[][] workers, int[][] bikes, int i, int j) {
        return Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
    }
}