// Time Complexity: O(M*N), where M is the number of workers, N is the number of bikes
// Space Complexity: O(1)
class Solution {
	public int[] assignBikes(int[][] workers, int[][] bikes) {
	  // base cases
		if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
			return new int[0];
		}

		int W = workers.length;
		int B = bikes.length;

		Map<Integer, List<int[]>> map = new TreeMap<>();

		for(int w = 0; w < W; w++) {
			for(int b = 0; b < B; b++) {
				int dist = compute(workers[w], bikes[b]);

				if(!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}
				map.get(dist).add(new int[] {w, b});
			}
		}

		// assignments
		boolean[] workerStatus = new boolean[W];
		boolean[] bikeStatus = new boolean[B];

		int[] result = new int[W];

		// 1: {(0,0), (0,1), ...}
		for(Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
			List<int[]> list = entry.getValue();
			for(int[] workerBikePair : list) {
				int w = workerBikePair[0];
				int b = workerBikePair[1];

				if(workerStatus[w] == false && bikeStatus[b] == false) {
					workerStatus[w] = true;
					bikeStatus[b] = true;

					result[w] = b;
				}
			}
		}


	}

	private int compute(int[] worker, int[] bike) {
		return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
	}
}