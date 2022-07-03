//Using TreeMap
import java.util.ArrayList;
import java.util.TreeMap;

public class Solution{

    //Time Complexity : 0(nmlognm) where n is the length of workers array and m is the length of bikes array. lognm is for addition to a treemap as addition in treemap is log n
    //Space Complexity: 0(nm)

    public int [] assignBikes(int [][] workers, int [][] bikes){
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int []{};
        }
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();  //a treemap to store the distance such as 1,2,3 and the worker bike combo whose manhattan distance comes up to be that. I am using a treemap because treemap stores the keys in a sorted order and while assigning in result, I will assign for distance 1 before assigning for distance 2
        for(int i = 0; i < workers.length; i++){    //going over workers array
            for(int j = 0; j < bikes.length; j++){  //going over bikes array
                int [] worker = workers[i]; //storing workers coordinates to calculate manhattan distance
                int [] bike = bikes[j]; //storing bikes coordinates to calculate manhattan distance. Each bike's manhattan distance is calculated from 1 worker
                int distance = FindManhattanDistance(worker, bike); //Finding manhattan distance of a bike from ith worker
                if(!map.containsKey(distance)){ //if map does not contain the calculated distance
                    map.put(distance, new ArrayList<>());   //then adding distance as key to the map and declaring an arraylist
                }
                map.get(distance).put(new int[]{i, j}); //then adding the combo of worker bike for that particular distance
            }
        }
        int [] result = new int[workers.length];    //to return the result
        Arrays.fill(result, -1);    //Initially filling it with -1 to keep a track of the workers that are assigned bikes or not
        boolean[] AllocatedBikes = new int[bikes.length];   //keeping a visited array
        int count = 0;  //for optimization
        for(int distance: map.keySet()){    //going over the keyset in treemap
            List<int[]> workerBike = map.get(distance); //getting the list of worker bike combo
            for(int i = 0; i < workerBike.length; i++){//going over it
                int worker = workerBike.get(i)[0];//getting the worker
                int bike = workerBike.get(i)[1];    //getting the bike
                if(result[worker] == -1 && AllocatedBike[bike] == false){//if the worker is not assigned a bike and also the bike is not assigned
                    result[worker] = bike;  //then assigning that worker the nearest bike. The visited array keeps track of assigning w1 a bike closest to its distance before w2 also it marks as visited
                    AllocatedBikes[bike] = true;    //marking bike array as visited
                    count++;  //1 worker is assigned a bike
                    if(count == workers.length){    //for optimization as the array will run the length of entire keyset, so if all workers are assigned bikes, I return the result
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public int FindManhattanDistance(int[] worker, int [] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);   //calculating the manhattan distance of worker to bike
    }
}

//Using Hashmap

public class Solution{

    //Time Complexity : 0(m*n) where n is the length of workers array and m is the length of bikes array.
    //Space Complexity: 0(n*m)

    public int [] assignBikes(int [][] workers, int [][] bikes){
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0){
            return new int []{};
        }
        HashMap<Integer, List<Integer>> map = new TreeMap<>();  //a hashmap will save on the log factor. I can maintain a minimum and maximum and iterate over it
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < workers.length; i++){    //going over workers array
            for(int j = 0; j < bikes.length; j++){  //going over bikes array
                int [] worker = workers[i]; //storing workers coordinates to calculate manhattan distance
                int [] bike = bikes[j]; //storing bikes coordinates to calculate manhattan distance. Each bike's manhattan distance is calculated from 1 worker
                int distance = FindManhattanDistance(worker, bike); //Finding manhattan distance of a bike from ith worker
                min = Math.min(min, distance);  //Updating min and max values
                max = Math.max(max, distance);
                if(!map.containsKey(distance)){ //if map does not contain the calculated distance
                    map.put(distance, new ArrayList<>());   //then adding distance as key to the map and declaring an arraylist
                }
                map.get(distance).put(new int[]{i, j}); //then adding the combo of worker bike for that particular distance
            }
        }
        int [] result = new int[workers.length];    //to return the result
        Arrays.fill(result, -1);    //Initially filling it with -1 to keep a track of the workers that are assigned bikes or not
        boolean[] AllocatedBikes = new int[bikes.length];   //keeping a visited array
        int count = 0;  //for optimization
        for(int j = minl j <= max; j++){    //going over the keyset in treemap
            List<int[]> workerBike = map.get(j); //getting the list of worker bike combo
            if(workerBike == null){
                continue;
            }
            for(int i = 0; i < workerBike.length; i++){//going over it
                int worker = workerBike.get(i)[0];//getting the worker
                int bike = workerBike.get(i)[1];    //getting the bike
                if(result[worker] == -1 && AllocatedBike[bike] == false){//if the worker is not assigned a bike and also the bike is not assigned
                    result[worker] = bike;  //then assigning that worker the nearest bike. The visited array keeps track of assigning w1 a bike closest to its distance before w2 also it marks as visited
                    AllocatedBikes[bike] = true;    //marking bike array as visited
                    count++;  //1 worker is assigned a bike
                    if(count == workers.length){    //for optimization as the array will run the length of entire keyset, so if all workers are assigned bikes, I return the result
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public int FindManhattanDistance(int[] worker, int [] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);   //calculating the manhattan distance of worker to bike
    }
}