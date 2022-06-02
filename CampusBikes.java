import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//Time Complexity : O(m.n); where n=number of workers and m= number of bikes
//Space Complexity : O(m.n)
public class CampusBikes { 
	/**Approach: HashMap + Greedy*/
	public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        //Prepare map of distance and worker, bike pair.
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min= 1000; int max= -1000;        
        for(int i=0; i<n; i++){ //O(n)
            for(int j=0; j<m; j++){ //(O(m)
                int[] workerPos = workers[i];
                int[] bikePos = bikes[j];
                int dist = getManhattanDist(workerPos, bikePos);
                min= Math.min(min, dist);
                max= Math.max(max, dist);
                map.putIfAbsent(dist, new ArrayList<>());
                map.get(dist).add(new int[] {i, j});
            }
        }        
        
        int[] res= new int[n];
        boolean[] assigned = new boolean[n];
        boolean[] occupied = new boolean[m];
        int count=0;        
        for(int k=min; k<=max; k++){ // O(1); as K is constant i.e. 1998 in worst case.
            List<int[]> list = map.get(k);
            if(list != null){
                for(int[] wb : list){
                    int w = wb[0]; int b = wb[1];                    
                    if(!assigned[w] && !occupied[b]){
                        assigned[w] = true; occupied[b] = true;
                        res[w]= b;
                        count++;
                    }
                    if(count == n) return res;                    
                }
            }
        }     
        return res;
    } 
	
	private int getManhattanDist(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
	 
    
	// Driver code to test above
	public static void main (String[] args) {	
		CampusBikes ob  = new CampusBikes();	
		int[][] workers = {{0,0},{1,1},{2,0}}; //{{0,0},{2,1}};
		int[][] bikes = {{1,0},{2,2},{2,1}}; //{{1,2},{3,3}};

		int[] arr= ob.assignBikes(workers, bikes);
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		System.out.println("Bikes assigned to worker based on their manhattan distance:"+ list);         
	}
}
