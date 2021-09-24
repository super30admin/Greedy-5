// Time Complexity : O(mn), m -> Number of bikes, n -> Number of workers
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CampusBikes {
	/********************* TreeMap *********************/
	// Time Complexity : O(mn log(mn)), m -> Number of bikes, n -> Number of workers
	// Space Complexity : O(mn)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int[] assignBikesTreeMap(int[][] workers, int[][] bikes) {
		if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
			return new int[0];
		}

		Map<Integer, List<int[]>> map = new TreeMap<>();

		int wLen = workers.length;
		int bLen = bikes.length;

		int[] res = new int[wLen];

		for (int i = 0; i < wLen; i++) {
			for (int j = 0; j < bLen; j++) {
				int dist = calculateDistance(workers[i], bikes[j]);

				if (!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}

				map.get(dist).add(new int[] { i, j });
			}
		}

		boolean[] assignedWorkers = new boolean[wLen];
		boolean[] occupiedBikes = new boolean[bLen];
		int totalAssigned = 0;

		for (int dist : map.keySet()) {
			List<int[]> distList = map.get(dist);

			for (int[] pair : distList) {
				if (!assignedWorkers[pair[0]] && !occupiedBikes[pair[1]]) {
					res[pair[0]] = pair[1];
					assignedWorkers[pair[0]] = true;
					occupiedBikes[pair[1]] = true;
					totalAssigned++;
				}

				if (totalAssigned == wLen) {
					return res;
				}
			}
		}
		return res;
	}

	// Time Complexity : O(mn), m -> Number of bikes, n -> Number of workers
	// Space Complexity : O(mn)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
			return new int[0];
		}

		Map<Integer, List<int[]>> map = new HashMap<>();

		int wLen = workers.length;
		int bLen = bikes.length;

		int[] res = new int[wLen];

		int minDist = Integer.MAX_VALUE;
		int maxDist = 0;

		for (int i = 0; i < wLen; i++) {
			for (int j = 0; j < bLen; j++) {
				int dist = calculateDistance(workers[i], bikes[j]);

				minDist = Math.min(minDist, dist);
				maxDist = Math.max(maxDist, dist);
				if (!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}

				map.get(dist).add(new int[] { i, j });
			}
		}

		boolean[] assignedWorkers = new boolean[wLen];
		boolean[] occupiedBikes = new boolean[bLen];
		int totalAssigned = 0;

		for (int dist = minDist; dist <= maxDist; dist++) {
			List<int[]> distList = map.get(dist);

			if (distList != null) {
				for (int[] pair : distList) {
					if (!assignedWorkers[pair[0]] && !occupiedBikes[pair[1]]) {
						res[pair[0]] = pair[1];
						assignedWorkers[pair[0]] = true;
						occupiedBikes[pair[1]] = true;
						totalAssigned++;
					}

					if (totalAssigned == wLen) {
						return res;
					}
				}
			}
		}
		return res;
	}

	private int calculateDistance(int[] worker, int[] bike) {
		return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
	}

	private void print(int[] arr) {
		for (int ele : arr) {
			System.out.print(ele + " ");
		}
	}

	public static void main(String[] args) {
		CampusBikes obj = new CampusBikes();
		int[][] workers = { { 0, 0 }, { 2, 1 } };
		int[][] bikes = { { 1, 2 }, { 3, 3 } };

		int[] ans = obj.assignBikes(workers, bikes);
		System.out.println("Bike Assignment: ");
		obj.print(ans);
	}

}
