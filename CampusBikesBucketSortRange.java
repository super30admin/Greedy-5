import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class CampusBikesBucketSortRange {

        // Greedy - Tree Map - Time O(m*n) + O(b) and Space O(m*n)
        // Use only bucket sort in interviews, not tree map

        public int[] assignBikes(int[][] workers, int[][] bikes) {

            //
            int m = workers.length;
            int n = bikes.length;

            // null case
            if(m == 0 || n == 0) {
                return null;
            }

            // range of buckets
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            // Hash map instead od tree map when using bucket sort range
            HashMap<Integer, List<int[]>> pairsAtDist = new HashMap<>();

            // nested loop to calculate manhattan distances
            for(int i = 0; i < m; i++) {        // O(m*n)
                for(int j = 0; j < n; j++) {

                    // call manhattan distance method
                    int dist = manhattanDist(workers[i], bikes[j]);

                    // update range of buckets
                    min = Math.min(min, dist);
                    max = Math.max(max, dist);

                    if(!pairsAtDist.containsKey(dist)) {

                        // add dist - empty list pair, if new distance
                        pairsAtDist.put(dist, new ArrayList<>());
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

            // Greedily iterate over pairs at distances in range starting from minimum distance until maximum distance
            for(int dist = min; dist <= max; dist++) {     // O(b)

                // get list at a distance
                List<int[]> pairs = pairsAtDist.get(dist);

                // list be non-null, avoids null pointer exception
                if(pairs != null) {

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
            }

            // output
            return result;
        }

        public int manhattanDist(int[] worker, int[] bike) {

            // calculate manhattan distance between worker and bike
            return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
        }


    public static void main(String[] args) {

        CampusBikesBucketSortRange obj = new CampusBikesBucketSortRange();

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
Time Complexity = O(m*n) + O(b)
b = bucket sort range
Space Complexity = O(m*n)

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
*/