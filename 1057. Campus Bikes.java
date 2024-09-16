//1057. Campus Bikes
//Time Complexity:mnlogmn
//Space Complexity:mn

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
		
		//create a treemap so we can access it in the sorted manner

		int m  = workers.length;
		int n  = bikes.length;  
        
        Map<Integer,List<int[]>> map = new TreeMap<>();
        //create map
        for(int i = 0; i < m; ++i){
            
            for(int j = 0; j < n; ++j){
                int dist = calculateDist(workers[i],bikes[j]);        
                
                //check if this dist already in the map 
                //else initialize the list
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<int[]>());
                }
                List<int[]> tempList = map.get(dist);
                tempList.add(new int[] {i,j});
                map.put(dist,tempList);
            }
        }
    
    int result[] = new int[m];
    HashSet<Integer> workersDone = new HashSet();
    HashSet<Integer> bikesDone = new HashSet();
    //done with the creation of the hashmap
    //iterate through the hashmap and perform the greedy choice
    for(int key: map.keySet()){
        //for the given distance go through all the pairs
        for( int[] pair: map.get(key)){
            
            int worker = pair[0];
            int bike = pair[1];
            if(!bikesDone.contains(bike) && !workersDone.contains(worker)){
                //if non of them are processed/used yet
                result[worker] = bike;
                workersDone.add(worker);
                bikesDone.add(bike);
            }   
            
        }
        
    }
    return result;
    }
    
    private int calculateDist(int[] worker, int[] bike){
        return (Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]));
    }
}
