import java.util.ArrayList;
import java.util.List;

//Time complexity is O(mn))
// space complexity is O(n)
// This solution is not submitted on leetcode

public class BigN163CampusBikeSolutionUsingListArray {

	public int[] assignBikes(int[][] workers, int[][] bikes) {
		// edge case
		if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0)
			return new int[0];
		List<int []> [] liArray = new List [2000];
		int m = workers.length;
		int n = bikes.length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int dist = calculateDist(workers[i], bikes[j]);
				if(liArray[dist]==null) {
					liArray[dist] = new ArrayList<>();
				}
				liArray[dist].add(new int [] {i,j});
			}
		}

		boolean[] w = new boolean[m];
		boolean[] b = new boolean[n];
		int[] result = new int[m];
		for (List<int []> li : liArray) {
			if(li == null) continue;
			for (int[] wk : li) {
				int wo = wk[0];
				int bi = wk[1];
				if (!w[wo] && !b[bi]) {
					w[wo] = true;
					b[bi] = true;
					result[wo] = bi;
				}
			}
		}
		return result;
	}

	private int calculateDist(int[] work, int[] bik) {
		return Math.abs(work[0] - bik[0]) + Math.abs(work[1] - bik[1]);
	}

	public static void main(String args[]) {
		BigN163CampusBikeSolutionUsingListArray cam = new BigN163CampusBikeSolutionUsingListArray();
		int [] [] workers = {{0,0},{2,1}};
		int [][] bikes = {{1,2},{3,3}};
		// int[][] workers = { { 0, 0 }, { 1, 1 }, { 2, 0 } };
		// int[][] bikes = { { 1, 0 }, { 2, 2 }, { 2, 1 } };
		int[] res = cam.assignBikes(workers, bikes);
		for (int i : res) {
			System.out.println(i);
		}
	}
}