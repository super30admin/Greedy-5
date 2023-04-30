import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Bikes in a Campus
approach: we have to keep track distance from each worker to each bike, and we have to select nearest bike for each worker,
so we have to keep track "with each distance how many workers are there"
time: 1. O((mxn) log (mxn)) using treemap 2. O(mxn)
space: both: O(mxn)
 */
public class Problem2 {
    private int[] campusBikes(int[][] workers, int[][] bikes) {
        int m = workers.length, n = bikes.length;
        int[] res = new int[m];
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i=0;i<m;i++) {
            int[] curr = workers[i];
            for (int j=0;j<n;j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(curr[0]-bike[0])+Math.abs(curr[1]-bike[1]);
                if (!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }
        boolean[] wrkrs = new boolean[m];
        boolean[] bks = new boolean[n];
        int count = 0;
        for (int i=min;i<=max;i++) {
            ArrayList<int[]> list = map.get(i);
            for (int j=0;j<list.size();j++) {
                int w = list.get(j)[0], b = list.get(j)[1];
                if (!wrkrs[w] && !bks[b]) {
                    wrkrs[w] = true;
                    bks[b] = true;
                    res[w] = b;
                    count++;
                    if (count==m) return res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();
    }
}
