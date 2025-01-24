class Problem2 {
    //TC: O(M*N) + O(M*N)
    //SC : O(M*N)
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer,ArrayList<int[]> > map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int [] result = new int[workers.length];
        boolean[] usedBikes = new boolean [bikes.length];
        boolean[] allocatedWorker = new boolean [bikes.length];
        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                int[] worker =  workers[i];
                int[] bike = bikes[j];
                int dist = manhattenDist(worker,bike);

                map.putIfAbsent(dist, new ArrayList<int[]>());
                map.get(dist).add(new int[]{i,j});
                min = Math.min(min,dist);
                max = Math.max(max,dist);

            }


        }
        int count = 0;
        for(int k = min;k<=max ;k++){
            if(!map.containsKey(k)) continue;
            System.out.println(k);
            List<int[]> workerBikeList =  map.get(k);

            for(int[] wb : workerBikeList){
                int worker = wb[0];
                int bike = wb[1];

                if(!usedBikes[bike] && !allocatedWorker[worker]){
                    usedBikes[bike] = true;
                    allocatedWorker[worker] = true;
                    result[worker] = bike;
                    if(count == workers.length){
                        return result;
                    }
                    count ++;

                }
            }

        }

        return result;

    }


    private int manhattenDist(int[]a , int b[]){
        return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
    }
}