/*

https://leetcode.com/problems/campus-bikes/
Did it run on leetcode: Yes

Time Complexity: 0(N*M*logN)
Space Complexity: 0(N+M)

Algorithm:
- We calculate distance for every worker with every bike, and for same distance worker with less index value
will get priority.
- We create Map where `key` with be distance value will (ith worker, jth bike). ie
map.put(distance,[(i,j)]. ie distance of ith worker with jth bike. Since there can be multiple pairs with same distance,
we will use ArrayList as our value.
- We have used tree map, because it can give us keys in ordered format ie sorted procedure
- and then greedily for every distance 
    - for ith,jth pair
    we will try to assign bike for each worker.
    ith worker has to be satisfied before satisfying (i+1)th worker.



*/


class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        TreeMap<Integer,List<int[]>> map = new TreeMap<>();
        int wLen = workers.length;
        int bLen = bikes.length;
        
        for(int i=0;i<wLen;++i){
            for(int j=0;j<bLen;++j){
                
                int[] worker = workers[i];
                int[] bike = bikes[j];
                int distance = calculateDistance(worker,bike);
                
                if(!map.containsKey(distance)){ map.put(distance,new ArrayList<int[]>());}
                map.get(distance).add(new int[]{i,j});
            }
        }
        
        int[] result = new int[wLen];
        boolean[] workerTaken = new boolean[wLen];
        boolean[] bikeTaken = new boolean[bLen];
        
        for(Integer key: map.keySet()){
            for(int[] arr : map.get(key)){
                int worker=arr[0],bike = arr[1];
                if(!workerTaken[worker] && !bikeTaken[bike]){
                    result[worker] = bike;
                    workerTaken[worker] = true;
                    bikeTaken[bike] = true;
                }
                
            }
        }
        
        return result;
    }
    
    private int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}