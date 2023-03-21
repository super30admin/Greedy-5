public class BikesInACampusLC1057 {
    //Approach 2

    //Time Complexity: O(m*n), m = no of workers, n = no of bikes, here we have used bucketSort(maintaned min and max value) so that we will get value(distance) in sorting manner

    //Space Complexity: O(m*n)

    public static int[] assignBikes(int[][] workers, int[][] bikes) {

        // write your code here

        if(workers == null || bikes == null || workers.length == 0 || bikes.length == 0) return new int[]{};            //check for null case

        Map<Integer, List<int[]>> map = new HashMap<>();                //create a map to store the distance (distance between worker and bike) as key and value is List of pair of worker index and bike index

        int min = Integer.MAX_VALUE;                                    //here we are using bucket sort, thats why we have to maintain the minimum and maximum distance
        int max = 0;

        for(int i = 0; i<workers.length; i++){                              //iterate through worker array
            for(int j=0; j<bikes.length; j++){                              //for each worker iterate through bike array

                int dist = calculateDistance(workers[i], bikes[j]);         //calculateDistance between worker and bike

                min = Math.min(min, dist);                                  //update the min if distance is smaller than the old min value
                max = Math.max(max, dist);                                  //update the max if distance is larger than the old max value

                if(!map.containsKey(dist)){                                 //check if that distance is in map or not, if not, then add a key to the map
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i, j});                         //add the worker and bike index pair as a value into the map
            }
        }

        int[] result = new int[workers.length];                             //to store result

        boolean[] workerAssigned = new boolean[workers.length];             //to keep track of worker are assigned or not

        boolean[] bikesOccupied = new boolean[bikes.length];                //to keep track of bikes are assigned or not


        int count = 0;                                                      //to keep track of how many workers in assigned till now

        for(int dist = min; dist <= max; dist++){                                        //iterate from min to max, so we are getting distance in sorted manner

            if(!map.containsKey(dist)){                                     //it might be possible that some distance is not available in map, to handle the case written the if block
                continue;
            }

            List<int[]> list = map.get(dist);                               //take all the value for that distance

            for(int[] wb: list){                                            //iterate through list

                int worker = wb[0];                                         //take the worker id
                int bike = wb[1];                                           //take the bike id

                if(!workerAssigned[worker] && !bikesOccupied[bike]){        //check if bike and worker is still available

                    workerAssigned[worker] = true;                          //if it is, then mark that worker and bike as true(means occupied)

                    bikesOccupied[bike] = true;

                    result[worker] = bike;                                  //add into result
                    count++;                                                //increment the count

                    if(count == workers.length){                            //check if count == workers.length means all the workers are assigned
                        return result;                                      //if so, then return true
                    }
                }
            }
        }
        return result;
    }





    //Approach 1

    //Time Complexity: O(m*n*log(m*n)), m = no of workers, n = no of bikes, here we have used TreeMap so that, whenever we put or get the distance(key), we will get in sorting manner and for that internal sorting reason we have this much time Complexity

    //Space Complexity: O(m*n)


    // public static int[] assignBikes(int[][] workers, int[][] bikes) {

    //     // write your code here

    //     if(workers == null || bikes == null || workers.length == 0 || bikes.length == 0) return new int[]{};            //check for null case

    //     TreeMap<Integer, List<int[]>> map = new TreeMap<>();                //create a map to store the distance (distance between worker and bike) as key and value is List of pair of worker index and bike index

    //     for(int i = 0; i<workers.length; i++){                              //iterate through worker array
    //         for(int j=0; j<bikes.length; j++){                              //for each worker iterate through bike array

    //             int dist = calculateDistance(workers[i], bikes[j]);         //calculateDistance between worker and bike

    //             if(!map.containsKey(dist)){                                 //check if that distance is in map or not, if not, then add a key to the map
    //                 map.put(dist, new ArrayList<>());
    //             }
    //             map.get(dist).add(new int[]{i, j});                         //add the worker and bike index pair as a value into the map
    //         }
    //     }

    //     int[] result = new int[workers.length];                             //to store result
    //     boolean[] workerAssigned = new boolean[workers.length];             //to keep track of worker are assigned or not
    //     boolean[] bikesOccupied = new boolean[bikes.length];                //to keep track of bikes are assigned or not
    //     int count = 0;                                                      //to keep track of how many workers in assigned till now

    //     for(int dist: map.keySet()){                                        //iterate through map, we have used TreeMap so we are getting distance in sorted manner

    //         List<int[]> list = map.get(dist);                               //take all the value for that distance

    //         for(int[] wb: list){                                            //iterate through list

    //             int worker = wb[0];                                         //take the worker id
    //             int bike = wb[1];                                           //take the bike id

    //             if(!workerAssigned[worker] && !bikesOccupied[bike]){        //check if bike and worker is still available

    //                 workerAssigned[worker] = true;                          //if it is, then mark that worker and bike as true(means occupied)
    //                 bikesOccupied[bike] = true;

    //                 result[worker] = bike;                                  //add into result
    //                 count++;                                                //increment the count

    //                 if(count == workers.length){                            //check if count == workers.length means all the workers are assigned
    //                     return result;                                      //if so, then return true
    //                 }
    //             }
    //         }
    //     }
    //     return result;
    // }


    private static int calculateDistance(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);       //to calculate the distance between worker and bike
    }
}
