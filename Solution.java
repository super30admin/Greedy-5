package Q_1057_Campus_Bikes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
//
//Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
//
//The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
//
//Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
//
// 
//
//Example 1:
//
//	Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
//			Output: [1,0]
//			Explanation: 
//			Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
//			Example 2:
//				
//				Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
//						Output: [0,2,1]
//						Explanation: 
//						Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
//						 
//
//						Note:
//
//						0 <= workers[i][j], bikes[i][j] < 1000
//						All worker and bike locations are distinct.
//						1 <= workers.length <= bikes.length <= 1000

/// workers length = m  
//bikes length = n 
//O(m*n) 		[running for loops to fond distance to each worker with each bike]
//O(log(m*n))	[TreeMap]
//k [unique distances]

//Time Complexity  : O(m*n)  + O(log(m*n))
//Space Complexity : k * O(m*n)

public class Solution {


	public int[] assignBikes(int[][] workers, int[][] bikes) {
		//edge case
		if(workers == null || workers.length ==0 || bikes == null || bikes.length ==0) {
			return new int[0];
		}
		
		//logic
		int wl = workers.length;
		int bl = bikes.length;
		Map<Integer, List<int []>> map = new TreeMap<>();
		for(int i=0; i < wl ;i++) {
			for(int j =0 ; j< bl ;j++) {
				int dist = calculateDist(workers[i], bikes[j]);
				if(!map.containsKey(dist)) {
					map.put(dist, new ArrayList<>());
				}
				map.get(dist).add(new int[]{i,j});
			}
		}
		
		boolean[] wAssigned = new boolean[wl];
		boolean[] bOccupied = new boolean[bl];
		int[] result = new int[wl];
		for(int di : map.keySet()) {
			List<int []> WnBnList = map.get(di);
			for(int[] wbp: WnBnList ) {
				int workerNo = wbp[0];
				int bikeNo = wbp[1];
				
				if(!wAssigned[workerNo] && !bOccupied[bikeNo]) {
					wAssigned[workerNo] = true;
					bOccupied[bikeNo] = true;
					result[workerNo] = bikeNo;
				}
			}
//			System.out.println(map.get(di).get(0)[0] + "," + map.get(di).get(0)[1]);
		}
		return result;

	}

	
	public int calculateDist(int[] worker, int[] bike) {
		return  Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
//		int[][] workers =  {{0,0},{2,1}};
//		int[][] bikes = {{1,2},{3,3}};
		
		int[][] workers =  {{0,0},{1,1},{2,0}};
		int[][] bikes = {{1,0},{2,2},{2,1}};
		
//		System.out.println(s.assignBikes(workers, bikes));
		int[] ans = s.assignBikes(workers, bikes);
		for(int val : ans) {
			System.out.println(val);
		}

	}

}
