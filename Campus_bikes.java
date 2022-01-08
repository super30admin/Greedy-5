class Solution{
  public int[] assignBikes(int[][] workers, int[][] bikes) {
      if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
          return new int[] {};
      }
    
      TreeMap<Integer, List<int []>> map = new TreeMap<>();
    
      for(int i = 0; i < workers.length; i++){
          for(int j = 0; j < bikes.length; j++){
              int dist = calculateDist(workers[i], bikes[j]);
              List<int []> li = map.getOrDefault(dist, new ArrayList<>());
              li.add(new int[] {i, j});
              map.put(dist, li);
          }
      }
    
      int [] result = new int[workers.length];
      boolean [] assigned = new boolean[workers.length];
      boolean [] ocuupied = new boolean[bikes.length];
      int count = 0;
    
      //iterating over keys of treeMap
      for(int key: map.keySet()){
          List<int []> li = map.get(key);
          for(int [] wb: li){
              int worker = wb[0];
              int bike = wb[1];
              
              if(!assigned[worker] && !oocupied[bike]){
                  assigned[worker] = true;
                  occupied[bike] = true;
                  result[worker] = bike;
                  count++;
              }
            if(workers.length == count) break;
          }
      }
    return result;
  }
  
  private int calculateDist(int[] worker, int[] bike){
      return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }
}


//TC: O(m * n)
//SC: O(m+n)
