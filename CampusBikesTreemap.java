import java.util.Scanner;
import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;

public class CampusBikesTreemap {

        // Greedy - Tree Map - Time O(m*n + log(m*n)) and Space O(m*n)
        // Use only bucket sort in interviews, not tree map

        public int[] assignBikes(int[][] workers, int[][] bikes) {

            //
            int m = workers.length;
            int n = bikes.length;

            // null case
            if(m == 0 || n == 0) {
                return null;
            }

            // build tree map
            TreeMap<Integer, List<int[]>> pairsAtDist = new TreeMap<>();    // O(m*n) space

            // nested loop to calculate manhattan distances
            for(int i = 0; i < m; i++) {        // O(m*n)
                for(int j = 0; j < n; j++) {

                    // call manhattan distance method
                    int dist = manhattanDist(workers[i], bikes[j]);

                    if(!pairsAtDist.containsKey(dist)) {

                        // add dist - empty list pair, if new distance
                        pairsAtDist.put(dist, new ArrayList<>());    // O(log(m*n))
                    }

                    // add pair to appropriate distance list
                    pairsAtDist.get(dist).add(new int[] {i, j});
                }
            }

            //availability boolean arrays
            boolean[] unavailableWorker = new boolean[m];    // O(m) space
            boolean[] unavailableBike = new boolean[n];      // O(n) space

            // result array
            int[] result = new int[m];                      // O(m) space
            int assignCount = 0;

            // Greedily iterate over pairs at distances starting from minimum distance
            // sorted distances is important
            for(int dist: pairsAtDist.keySet()) {       // O(m*n)

                // get list at a distance
                List<int[]> pairs = pairsAtDist.get(dist);

                // local optimal (minimal) distance pair matches leading to global optimal(minimal) assignment
                for(int[] pair: pairs) {

                    int workerIdx = pair[0];
                    int bikeIdx = pair[1];

                    // if both worker and bike in a pair are available
                    if(!unavailableWorker[workerIdx] && !unavailableBike[bikeIdx]) {

                        // assign and increase count of assigned workers
                        result[workerIdx] = bikeIdx;
                        assignCount++;

                        // make them available for future
                        unavailableWorker[workerIdx] = true;
                        unavailableBike[bikeIdx] = true;

                        // if all bikes assigned
                        if(assignCount == n) {

                            // output
                            return result;
                        }
                    }
                }
            }

            // output
            return result;
        }

        public int manhattanDist(int[] worker, int[] bike) {

            // calculate manhattan distance between worker and bike
            return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
        }

        public static void main(String[] args) {

            CampusBikesTreemap obj = new CampusBikesTreemap();

            Scanner scanner = new Scanner(System.in);

            System.out.println("number of workers");
            int m = scanner.nextInt();
            int[][] workers = new int[m][2];

            System.out.println("number of bikes");
            int n = scanner.nextInt();
            int[][] bikes = new int[n][2];

            System.out.println("positions of workers");
            for(int[] worker: workers) {

                worker[0] = scanner.nextInt();
                worker[1] = scanner.nextInt();

                System.out.println(" ");
            }

            System.out.println("positions of bikes");
            for(int[] bike: bikes) {

                bike[0] = scanner.nextInt();
                bike[1] = scanner.nextInt();

                System.out.println(" ");
            }

            int[] answer = obj.assignBikes(workers, bikes);

            System.out.println("Assigned Campus bikes are ");
            for(int bike: answer) {

                System.out.print(bike + " ");
            }


        }

}

/*
Time Complexity = O(m*n + log(m*n))
Space Complexity = O(m*n)

Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
*/