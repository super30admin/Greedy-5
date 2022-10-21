import java.util.ArrayList;
import java.util.List;

// Time Complexity : O(m*n) where m = number of workers, n = number of bikes
// Space Complexity : O(m) + O(n) where m = number of workers, n = number of bikes
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//1057. Campus Bikes (Medium) - https://leetcode.com/problems/campus-bikes/
// Time Complexity : O(m*n log(m*n)) where m = number of workers, n = number of bikes
// Space Complexity : O(m) + O(n) where m = number of workers, n = number of bikes
//class Solution {
//	public int[] assignBikes(int[][] workers, int[][] bikes) {
//		if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
//			return new int[] {};
//		}
//
//		TreeMap<Integer, List<int[]>> map = new TreeMap<>();
//
//		int m = workers.length, n = bikes.length;
//
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++) {
//				int[] worker = workers[i];
//				int[] bike = bikes[j];
//
//				int dist = calculateDist(worker, bike);
//
//				if (!map.containsKey(dist)) {
//					map.put(dist, new ArrayList<>());
//				}
//				map.get(dist).add(new int[] { i, j });
//			}
//		}
//
//		boolean[] assigned = new boolean[m];
//		boolean[] occupied = new boolean[n];
//		int[] result = new int[m];
//		int count = 0;
//
//		for (int dist : map.keySet()) {
//			List<int[]> li = map.get(dist);
//
//			for (int[] wb : li) {
//				int w = wb[0], b = wb[1];
//
//				if (!assigned[w] && !occupied[b]) {
//					assigned[w] = true;
//					occupied[b] = true;
//					result[w] = b;
//					count++;
//
//					if (count == m)
//						return result;
//				}
//			}
//		}
//
//		return result;
//	}
//
//	private int calculateDist(int[] worker, int[] bike) {
//		return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
//	}
//}

//Time Complexity : O(mn) where m = number of workers, n = number of bikes
//Space Complexity : O(m) + O(n) where m = number of workers, n = number of bikes
class Solution {
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
			return new int[] {};
		}

		HashMap<Integer, List<int[]>> map = new HashMap<>();

		int m = workers.length, n = bikes.length, max = 0, min = 2000;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int[] worker = workers[i];
				int[] bike = bikes[j];

				int dist = calculateDist(worker, bike);
				max = Math.max(max, dist);
				min = Math.min(min, dist);

				if (!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}
				map.get(dist).add(new int[] { i, j });
			}
		}

		boolean[] assigned = new boolean[m];
		boolean[] occupied = new boolean[n];
		int[] result = new int[m];
		int count = 0;

		for (int dist = min; dist <= max; dist++) {
			List<int[]> li = map.get(dist);

			if (li == null)
				continue;

			for (int[] wb : li) {
				int w = wb[0], b = wb[1];

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

		return new int[m];
	}

	private int calculateDist(int[] worker, int[] bike) {
		return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
	}
}