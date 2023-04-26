import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
//Time Complexity : O(MN log MN)
//Space Complexity : O(MN)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Calculate the distances and put the pair of bike and worker in the map for
 * that particular distance. Then take a boolean array to confirm if the
 * respective bike or worker is occupied. Iterate over the map values and mark
 * the respective bike and worker as true and store in result. Finally return
 * the result.
 *
 */
class Solution {
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		if (workers == null || bikes == null || workers.length == 0 || bikes.length == 0)
			return new int[0];
		int n = workers.length;
		int m = bikes.length;
		int[] result = new int[n];
		// need sorted map by distance
		Map<Integer, List<int[]>> map = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int d = calculateDist(workers[i], bikes[j]);
				if (!map.containsKey(d)) {
					map.put(d, new ArrayList<>());
				}
				map.get(d).add(new int[] { i, j });
			}
		}
		boolean[] assigned = new boolean[n];
		boolean[] occupied = new boolean[m];
		for (List<int[]> li : map.values()) {
			// wbp --> worker bike pair
			for (int[] wbp : li) {
				int w = wbp[0];
				int b = wbp[1];
				if (!assigned[w] && !occupied[b]) {
					assigned[w] = true;
					occupied[b] = true;
					result[w] = b;
				}
			}
		}
		return result;
	}

	private int calculateDist(int[] worker, int[] bike) {

		return Math.abs(worker[1] - bike[1]) + Math.abs(worker[0] - bike[0]);

	}

}
