/*
 * class Solution {
 * 
 * public int[] assignBikes(int[][] workers, int[][] bikes) {
 * // Tc: O((W×B)log(W×B)) Sc: O(W×B)
 * if (workers == null || bikes == null || workers.length == 0 || bikes.length
 * == 0) {
 * return new int[] {};
 * }
 * 
 * List<int[]> distances = new ArrayList<>();
 * for (int i = 0; i < workers.length; i++) {
 * for (int j = 0; j < bikes.length; j++) {
 * int dist = calcDist(workers[i], bikes[j]);
 * distances.add(new int[] { dist, i, j });
 * }
 * }
 * 
 * Collections.sort(distances, (a, b) -> {
 * if (a[0] != b[0])
 * return a[0] - b[0];
 * if (a[1] != b[1])
 * return a[1] - b[1];
 * return a[2] - b[2];
 * });
 * 
 * boolean[] assigned = new boolean[workers.length];
 * boolean[] occupied = new boolean[bikes.length];
 * int[] res = new int[workers.length];
 * 
 * int cnt = 0;
 * for (int[] distance : distances) {
 * int w = distance[1];
 * int b = distance[2];
 * if (!assigned[w] && !occupied[b]) {
 * assigned[w] = true;
 * occupied[b] = true;
 * res[w] = b;
 * cnt++;
 * if (cnt == workers.length)
 * break;
 * }
 * }
 * 
 * return res;
 * }
 * 
 * private int calcDist(int[] worker, int[] bike) {
 * return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
 * }
 * 
 * }
 */

/*
 * class Solution {
 * public int[] assignBikes(int[][] workers, int[][] bikes) {
 * //Tc: O(W*B) Sc: O(W*B)
 * if (workers == null || bikes == null || bikes.length == 0 || workers.length
 * == 0) {
 * return new int[] {};
 * }
 * 
 * TreeMap<Integer, List<int[]>> map = new TreeMap<>();
 * for (int i = 0; i < workers.length; i++) {
 * for (int j = 0; j < bikes.length; j++) {
 * int w[] = workers[i];
 * int b[] = bikes[j];
 * 
 * int dist = calcDist(w, b);
 * if (!map.containsKey(dist)) {
 * map.put(dist, new ArrayList<>());
 * }
 * map.get(dist).add(new int[] { i, j });
 * }
 * }
 * 
 * boolean[] assigned = new boolean[workers.length];
 * boolean[] occupied = new boolean[bikes.length];
 * 
 * int[] res = new int[workers.length];
 * 
 * int cnt = 0;
 * 
 * for (int dist : map.keySet()) {
 * List<int[]> li = map.get(dist);
 * for (int[] wb : li) {
 * int w = wb[0];
 * int b = wb[1];
 * if (!assigned[w] && !occupied[b]) {
 * assigned[w] = true;
 * occupied[b] = true;
 * res[w] = b;
 * cnt++;
 * if (cnt == workers.length)
 * return res;
 * }
 * 
 * }
 * 
 * }
 * return res;
 * }
 * 
 * private int calcDist(int[] worker, int[] bike) {
 * return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
 * }
 * 
 * }
 */