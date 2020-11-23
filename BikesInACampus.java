// Time Complexity : O(n^2)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // int[]: worker, bike, dis
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int  compare(int[] a, int[] b) {
                if (a[2] < b[2]) return -1; // compare by distance
                if (a[2] == b[2]) { // if dis the same
                    if (a[0] != b[0]) {
                        return a[0] - b[0]; // if worker num smaller, then result smaller.
                    } else {
                        return a[1] - b[1]; // if dis and worker both same, then compare bike.
                    }
                }
                return 1; 
            }
        });
        
        for (int i = 0; i < workers.length; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                int dis = Math.abs(worker[0] - bike[0]) + 
                          Math.abs(worker[1] - bike[1]);
                int[] unit = new int[]{i, j, dis};
                pq.offer(unit);
            }
        }
        
        int[] res = new int[workers.length];
        Set<Integer> visw = new HashSet<>();
        Set<Integer> visb = new HashSet<>();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (!visw.contains(cur[0]) && !visb.contains(cur[1])) {
                res[cur[0]] = cur[1];
                visw.add(cur[0]);
                visb.add(cur[1]);
            }

        }
        return res;
    }
}