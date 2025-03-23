// Time Complexity : 0(nxm)
// Space Complexity : O(n + m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[] res = new int[n];
        boolean[] assigned = new boolean[n];
        boolean[] occupied = new boolean[m];
        List<int[]>[] list = new List[2001]; /// max distance is 2000

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int dist = dist( workers[i], bikes[j] ); // distance between worker i and bike j

                if (list[dist] == null) {
                    list[dist] = new ArrayList<>(); //  create a new list
                }

                list[dist].add(new int[]{i, j});
            }
        }

        for (int i = 0; i < 2001; i++) {
            if (list[i] == null) continue;
            int size = list[i].size();
            for (int j = 0; j < size; j++) {
                 int w = list[i].get(j)[0];
                 int b = list[i].get(j)[1];

                if (!assigned[w] && !occupied[b]) {
                    res[w] = b;
                     assigned[w] = true;
                    occupied[b] = true;
                }
            }
        }

        return res;
    }

    private int dist(int[] w, int[] b) {
        return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
    }
}