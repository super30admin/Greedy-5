class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        TreeMap<Integer, List<int[]>> tm = new TreeMap<>();
        int[] result = new int[n];
        int count =n;
        boolean[] assignedworkers = new boolean[n];
        boolean[] assignedbikes = new boolean[m];
        for (int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                int distance = findDistance(workers[i],bikes[j]);
                if (!tm.containsKey(distance)){
                    List<int[]> l = new ArrayList<>();
                    l.add(new int[]{i,j});
                    tm.put(distance, l);
                } else {
                    tm.get(distance).add(new int[]{i,j});
                }
            }
        }
        for (int key:tm.keySet()) {
            List<int[]> pair = tm.get(key);
            for (int i=0;i<pair.size();i++) {
                int[] a= pair.get(i);
                if (count == 0) {
                    break;
                }
                if (assignedworkers[a[0]] == false) {
                    if (assignedbikes[a[1]] == false) {
                        assignedworkers[a[0]] = true;
                        assignedbikes[a[1]] = true;
                        result[a[0]] = a[1];
                        count--;
                    }
                }
            }
        }
        return result;
    }
    private int findDistance(int[] worker, int[] bike) {
        int distance = Math.abs(worker[0] - bike[0])  + Math.abs(worker[1] - bike[1]);
        return distance;
    }
}
