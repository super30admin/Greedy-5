import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TC: O(m*n)
//SC: O(m*n)
class CampusBikes {
  public int[] assignBikes(int[][] workers, int[][] bikes) {
      int n=workers.length;
      int m=bikes.length;
      boolean[] w=new boolean[n];  //workers
      boolean[] b=new boolean[m]; //bikes
      int[] res=new int[n];
      Map<Integer,List<int[]>> map=new HashMap<>();
      int min=Integer.MAX_VALUE;
      int max=0;
      for(int i=0;i<n;i++){ //m*n
          for(int j=0;j<m;j++){
              int dist=Math.abs(workers[i][0]-bikes[j][0])+Math.abs(workers[i][1]-bikes[j][1]);
              min=Math.min(min,dist);
              max=Math.max(max,dist);
              int[] arr=new int[]{i,j};
              if(!map.containsKey(dist)){
                  map.put(dist,new ArrayList<>());
              }
              map.get(dist).add(arr);
          }
      }
      int count=0;
      for(int i=min;i<=max && count<n;i++){ //m*n
          List<int[]> pairs = map.get(i);
          if(pairs==null){
              continue;
          }
          for(int[] pair:pairs){
              int worker=pair[0];
              int bike=pair[1];
              if(!w[worker] && !b[bike]){
                  w[worker]=true;
                  b[bike]=true;
                  res[worker]=bike;
                  count++;
              }
          }
      }
      return res;
  }
}
