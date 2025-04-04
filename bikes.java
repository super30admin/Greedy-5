// Online Java Compiler
// Use this editor to write, compile and run your Java code online

// TC:O(n*m)
//sc:O(n+m)
import java.util.*;
class Main {
    
    public static int[] assignBikes(int[][]workers, int[][]bikes){
        int m = workers.length;
        int n = bikes.length;
        TreeMap<Integer,List<int[]>> map = new TreeMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int[] w= workers[i];
                int[] b =bikes[j];
                int dist=distance(w,b);
                if(!map.containsKey(dist)){
                    map.put(dist,new ArrayList<>());
                }
                map.get(dist).add(new int[] {i,j});
            }
        }
        int[] result = new int[m];
        boolean[] assigned = new boolean[m];
        boolean[] occupied = new boolean[n];
        for(int dist:map.keySet()){
            List<int[]> pairs = map.get(dist);
            for(int[]wb:pairs){
               int w = wb[0];
            int b =wb[1];
            if(!assigned[w]&&!occupied[b]){
                assigned[w]=true;
                occupied[b]=true;
            }
            result[w]=b; 
            }
        }
        
        return result;
    }
    
    private static int distance(int[]w, int[]b){
        return Math.abs(w[0]-b[0])+Math.abs(w[1]-b[1]);
    }
    public static void main(String[] args) {
        // Example test case
        int[][] workers = {{0, 0}, {2, 1}};
        int[][] bikes = {{1, 2}, {3, 3}};
        
        int[] result = assignBikes(workers, bikes);
        System.out.println(Arrays.toString(result)); // Expected output: [1, 0]
    }
}