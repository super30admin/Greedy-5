package week14.day1;

//TC - O(m*n)
//https://leetcode.com/problems/campus-bikes/description/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CampusBikes {
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		if (workers.length == 0 || bikes.length == 0)
			return new int[] {};

		int min = Integer.MAX_VALUE; // for bucket sort
		int max = Integer.MIN_VALUE;
		HashMap<Integer, List<int[]>> map = new HashMap<>();
		for (int i = 0; i < workers.length; i++) {
			for (int j = 0; j < bikes.length; j++) {
				int dist = findDistance(workers[i], bikes[j]);
				min = Math.min(min, dist);
				max = Math.max(max, dist);

				if (!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}
				map.get(dist).add(new int[] { i, j }); // added all distances = {w,b} to map
			}
		}

		boolean[] assignedWorkers = new boolean[workers.length];
		boolean[] assignedBikes = new boolean[bikes.length];
		int[] pairedBikes = new int[bikes.length];
		int count = 0;

		for (int d = min; d < max; d++) {
			List<int[]> li = map.get(d);
			if (li == null)
				continue;
			for (int[] wb : li) {
				int w = wb[0];
				int b = wb[1];
				if (!assignedWorkers[w] && !assignedBikes[b]) {
					assignedWorkers[w] = true;
					assignedBikes[b] = true;
					pairedBikes[w] = b;
					count++;
					if (count == workers.length)
						return pairedBikes;
				}
			}
		}
		return pairedBikes;

	}

	private int findDistance(int[] y, int[] x) {
		// TODO Auto-generated method stub
		return Math.abs(y[0] - x[0]) + Math.abs(y[1] - x[1]);
	}

	public static void main(String[] args) {
		int[][] workers = { { 0, 0 }, { 1, 1 }, { 2, 0 } };
		int[][] bikes = { { 1, 0 }, { 2, 2 }, { 2, 1 } };
		CampusBikes c = new CampusBikes();
		int[] out = c.assignBikes(workers, bikes);
		System.out.println(Arrays.toString(out));
	}

}
