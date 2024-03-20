import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusBikes {

    // TC: O(M * N) where M is length of workers and N is length of bikes
    // SC: O(M * N) where M is length of workers and N is length of bikes
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int[] res = new int[n];
        Map<Integer, List<int[]>> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE, maxDistance = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = getDistance(workers[i], bikes[j]);
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i, j});
                minDistance = Math.min(minDistance, dist);
                maxDistance = Math.max(maxDistance, dist);
            }
        }
        boolean[] wAssigned = new boolean[n];
        boolean[] bAssigned = new boolean[bikes.length];
        for (int i = minDistance; i <= maxDistance; i++) {
            if (map.containsKey(i)) {
                List<int[]> list = map.get(i);
                for (int[] p : list) {
                    int wIndex = p[0];
                    int bIndex = p[1];
                    if (!wAssigned[wIndex] && !bAssigned[bIndex]) {
                        res[wIndex] = bIndex;
                        wAssigned[wIndex] = true;
                        bAssigned[bIndex] = true;
                    }
                }
            }
        }
        return res;
    }

    private int getDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
