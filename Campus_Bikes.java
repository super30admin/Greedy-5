class Solution{


    public int[] assignBikes(int [][]workers, int [][]bikes) {
        if(workers == null || workers.length==0 || bikes == null || bikes.length == 0) {
            return new int[]{};
        }

        HashMap<Integer, List<int []>> map  = new HashMap<>();
        int minimum = Integer.MAX_VALUE;
        int maximum = 0;
        for(int i =0; i< workers.length;i++) {
            for(int j = 0; j<bikes.length;j++) {
                int distance = calculateDistance(workers[i], bikes[j]);
                minimum = Math.min(distance, minimum);
                maximum = Math.max(distance, maximum);
                if(!map.containsKey(distance)) {
                    map.put(distance, new ArrayList<>());
                }
                map.get(distance).add(new int []{i,j});
            }
        }

        boolean []assigned = new boolean[workers.length];
        boolean []available = new boolean[bikes.length];
        int []result = new int[workers.length];
        for(int dist = minimum ; dist <= maximum;dist++) {
            List<int []> li = map.get(dist);
            if(li == null) {
                continue;
            }

            for(int []wb:li) {
                int w = wb[0];
                int b = wb[1];
                if(!assigned[w] && !available[b]) {
                    assigned[w] = true;
                    available[b] = true;
                    result[w] = b;
                    count++;
                    if(count == workers.length) {
                        return result;
                    }

                }
            }
        }
        return result;
    }
}