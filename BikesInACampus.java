//TC: O(m*n) where m is length of workers array , n is length of bikes array
//SC: O(m*n)

//CODE:

class solution {
    public int[] assignBikes(int[][]workers , int[][]bikes){
        //null case
        if(workers == null || workers.length==0 || bikes == null || bikes.length == 0) return new int[]{};

        int m = workers.length;
        int n = bikes.length;
        HashMap<Integer, List<int []>> map = new HashMap<>();
        int min = 1000 ;   //taking min and max 
        int max = -1000;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int[] worker = workers[i];
                int[] bike = bikes[j];
                int dist = calculateDist(worker,bike);
                min = Math.min(min,dist);
                max = Math.max(max,dist);
                if(!map.containsKey(dist)){   //if map doest not contains that distance , then add it to the map 
                    map.put(dist , new ArrayList<>());
                }
                List<int[]> li = map.get(dist); // getting the list of that distance and then adding new pair of worker and bike to list
                li.add(new int[] {i,j});
            }
        }
        int count =0;
        //now checking if worker is available to be assigned and bike is available
        boolean[] assignedWorker = new boolean[m];
        boolean[] occupiedBike = new boolean[n];
        int[] result = new int[m];
        for(int k=min;k<=max;k++){   //traversing from min distnace to max distnace
            List<int[]> li = map.get(k);
            if(li!= null){
                for(int[] wb : li){  //checking for each worker-bike pair in list
                    int w = wb[0];
                    int bi = wb[1];
                    if(!assignedWorker[w] && !occupiedBike[bi]){  //if worker and bike are availble then mark them as true and add it to the result
                        assignedWorker[w] = true;
                        occupiedBike[bi]= true;
                        result[w] =bi;
                        count++;
                    }
                    if(count == m) return result;
                }
            }
        }
        return result;
    }

    public static int calculateDist(int[] worker ; int[] bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]- bike[1]);
    }
}
