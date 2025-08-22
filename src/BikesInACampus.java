//time complexity- O(m*n) where m is bikers and n is workers
//space complexity- O(m*n)
import java.util.*;
public class BikesInACampus {
    static int[]  assignBikes(int[][] workers, int[][] bikes) {
        boolean[] b = new boolean [bikes.length];
        boolean [] w = new  boolean[workers.length];
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < bikes.length; i++) {
            for(int j = 0; j < workers.length; j++) {
               int  dis =  distance(bikes[i], workers[j]);
               if(!map.containsKey(dis)) {
                   map.put(dis, new ArrayList<>());
               }
               map.get(dis).add(new int[] {i, j});
               min = Math.min(min, dis);
               max = Math.max(max, dis);
            }
        }
        int count = 0;
        int[] assign = new int[workers.length];
        for(int i = min; i <= max; i++) {
            if(!map.containsKey(i)) continue;;
            ArrayList<int[]> temp = map.get(i);
            for(int[] d : temp) {
                int bike = d[0];
                int worker = d[1];
                if(b[bike] == false && w[worker] == false) {
                    assign[worker] = bike;
                    b[bike] = true;
                    w[worker] = true;
                    count++;
                }
            }
            if(count == workers.length) break;
        }
        return assign;
    }

    static private int  distance(int[] x1, int[] x2) {
        int res = 0;
        res += Math.abs(x1[0] - x2[0]) + Math.abs(x1[1] - x2[1]);
        return res;
    }
    public static void main(String[] args) {
        int[][] workers = {{0,0}, {2,1}};
        int[][] bikes = {{1,2}, {3,3}};

        System.out.println("Workers Coordinates:" + Arrays.toString(workers));
        System.out.println("Bikes Coordinates:" + Arrays.toString(bikes));
        System.out.println("Bikes Assigned to Workers Are: ");
        int[] res = assignBikes(workers, bikes);
        for(int i = 0; i < res.length; i++) {
            System.out.println("Worker "+ (i)+ " -> Bike " + res[i]);
        }

    }
}
