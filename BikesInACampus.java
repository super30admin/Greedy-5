import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/*
Time Complexity: O(m*n+klogk)~ m is the length of workers array and n is the length of bikes
Space Complexity: O(k) size of priority queue
Any difficulties: no
Run on leetcode: TLE for one test case, apart from that every test case was passed

Approach:
1. Attempted once discussed in the class
 */
public class BikesInACampus {
    public static int[] assignBikes(int[][] workers, int[][] bikes) {

        List<Pairing> assign = new ArrayList<>();
        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                Pairing p = new Pairing(i,j,calDistance(workers[i],bikes[j]));
                assign.add(p);
            }
        }


        PriorityQueue<Pairing> pq = new PriorityQueue<>((a, b) -> {
            if (a.distance != b.distance) return a.distance - b.distance;
            else if (a.worker != b.worker) return a.worker - b.worker;
            else return a.bike - b.bike;
        });

        pq.addAll(assign);

        boolean[] bikesCheck = new boolean[bikes.length];
        int[] result = new int[workers.length];
        Arrays.fill(result,-1);
        int i = 0;
        while(!pq.isEmpty() &&  i<bikes.length){
            Pairing person = pq.poll();

            if(!bikesCheck[person.bike] && result[person.worker] == -1){
                bikesCheck[person.bike] = true;
                result[person.worker] = person.bike;
                i++;
            }
        }
        return result;
    }

    // Calculating Manhattan distance
    public static int calDistance(int[] worker, int[] bike){
        return (Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]));
    }

    public static void main(String[] args){
        int[][] workers = {
                {0,0},
                {1,1},
                {2,0}
        };

        int[][] bikes = {
                {1,0},
                {2,2},
                {2,1}
        };
        System.out.println("Bikes in campus: "+ Arrays.toString(assignBikes(workers, bikes)));
    }
}
