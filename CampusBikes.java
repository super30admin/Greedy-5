//Hashmap with bucket sort
// Since we need the pair of {worker, bike} with the minimum distance and if the distances are the same, then we need to take the worker/bike with the lowest index, we need a way to maintain all the worker/bike pairs for each distances. so, iterate through each worker and then calculate the distance of that worker to each bike. Store each possible distance and the associated list of worker-bike pairs in a hashmap. Since we need the keys of the hashmap to be in order, we can either use a treemap or we can use buck sorting. to do bucket sorting, while calculating the distances of each worker bike pair, maintain the minimum and maximum distance encountered. that way, you know the range of numbers and then if you run a for loop for this range and check if each key exists in the hashmap, you are guaranteed to get the distances in order. Now iterate over each worker-bike pair for a particular distance. Check if that worker has been assigned a bike,  also check if the current bike has already been assigned to somebody. Both of this is checked by maintaining two boolean arrays - workerUsed and bikeUsed. if both the worker and bike are currently unassigned, then assign the current bike to the current worker. increment the assigned count. As soon as the assigned count becomes equal to the workers array length, you know that all the workers have been assigned a bike. You can break then and return the result array.

//Time O(mn)
//Space O(mn)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<List<Integer>>> t = new HashMap<>();
        int minDist= Integer.MAX_VALUE; int maxDist = Integer.MIN_VALUE;
        for(int i =0 ;i<workers.length;i++)
        {
            int [] w = workers[i];
            for(int j =0;j<bikes.length;j++)
            {
                int [] b = bikes[j];
                
                int dist = Math.abs(w[0]-b[0]) + Math.abs(w[1]-b[1]);
                minDist = Math.min(minDist, dist);
                maxDist = Math.max(maxDist,dist);
                List<List<Integer>> curr = t.getOrDefault(dist, new ArrayList<>());
                List<Integer> temp = new ArrayList<>();
                temp.add(i); temp.add(j);
                curr.add(temp);
                t.put(dist,curr);
            }
        }
       int [] result =new int[workers.length];
       boolean [] workerUsed =new boolean[workers.length];
       boolean [] bikeUsed =new boolean [bikes.length];
       int assigned = 0;
       for(int k=minDist;k<=maxDist;k++)
       {
           List<List<Integer>> curr = t.get(k);
           if(curr!=null)
           {    
           for(List<Integer> l : curr)
            {
               if(!workerUsed[l.get(0)] && !bikeUsed[l.get(1)])
                {
                   result[l.get(0)] = l.get(1);
                   workerUsed[l.get(0)]=true;
                   bikeUsed[l.get(1)]=true;
                   assigned++;
                }
               if(assigned==workers.length)
                   break;
            }
           
           }
       }
        
        return result;
    }
}