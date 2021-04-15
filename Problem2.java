//time complexity-O(n^2 *logn)
//Space complexity-O(w*b)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        Map<Integer, List<int[]>>map= new TreeMap<>();
        int dist=0;
        int[] output= new int[workers.length];//findign distance for each bike for each worker
        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                dist=calculateDistance(workers[i],bikes[j]);
                
                map.putIfAbsent(dist,new ArrayList<>());//addingt the value to TreeMap 
                map.get(dist).add(new int[]{i,j});
            }
        }
        boolean[] Wvisited=new boolean[workers.length];
        boolean[] Bvisited=new boolean[bikes.length];//maintian already assigned bikes and workers
        
        for(List<int[]> p : map.values()){
            for(int[] pair: p){
                int worker= pair[0];
                int bike= pair[1];
                if(!Wvisited[worker] && !Bvisited[bike]){
                    Wvisited[worker]=true;
                    Bvisited[bike]=true;
                    
                    output[worker]=bike;
                }
            }
        }
        
        return output;
    }
    
    public int calculateDistance(int[] workers,int[] bikes){
        return Math.abs(workers[0]-bikes[0]) + Math.abs(workers[1]-bikes[1]);
    }
}