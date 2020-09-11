
//TC - O(MNlogMN)
//SC - O(M + N)
import java.util.*;

class Solution1 {
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		if (workers == null || workers.length == 0 || bikes.length == 0 || bikes == null) {
			return new int[0];
		}
		TreeMap<Integer, List<int[]>> map = new TreeMap<>();
		int m = workers.length;
		int n = bikes.length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int dist = getDistance(workers[i], bikes[j]);
				if (!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}
				map.get(dist).add(new int[] { i, j });
			}
		}
		boolean[] assigned = new boolean[m];
		boolean[] occupied = new boolean[n];
		int[] result = new int[m];
		for (int dist : map.keySet()) {
			List<int[]> list = map.get(dist);
			for (int[] wb : list) {
				int worker = wb[0];
				int bike = wb[1];
				if (!assigned[worker] && !occupied[bike]) {
					assigned[worker] = true;
					occupied[bike] = true;
					result[worker] = bike;
				}
			}
		}
		return result;
	}

	private int getDistance(int[] worker, int[] bike) {
		return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
	}
}
