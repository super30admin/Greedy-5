//time o(mn log mn)
//space o(mn)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null || workers.length ==0 || bikes.length ==0) {
            return new int[0];
        }
        int n = workers.length;
        int m = bikes.length;
        int[] res = new int[n];
        boolean[] assigned = new boolean[n]; // check if the worker is already allotted
        boolean[] occupied = new boolean[m]; //check if the bikes are already occupied
        
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        
        for(int i=0; i<n ; i++) {
            for(int j=0; j<m; j++) {
                int dist = calDist(workers[i], bikes[j]);
                if(!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }
        //iterate through the treemap
        for(int key : map.keySet()) {
            List<int[]> list = map.get(key);
            for(int[] wab: list) {
                int worker = wab[0];
                int bike = wab[1];
                if(!assigned[worker] && !occupied[bike]) {
                    assigned[worker] = true;
                    occupied[bike] = true;
                    res[worker] = bike;
                }
            }
        }
        return res;
    }
    
    private int calDist(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}

//improvised solution
//time o(mn)
//space o(n)
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if(workers == null || bikes == null || workers.length ==0 || bikes.length ==0) {
            return new int[0];
        }
        int n = workers.length;
        int m = bikes.length;
        int[] res = new int[n];
        boolean[] assigned = new boolean[n]; // check if the worker is already allotted
        boolean[] occupied = new boolean[m]; //check if the bikes are already occupied
        
        List<int[]> [] listArr = new List[2000];
        
        for(int i=0; i<n ; i++) {
            for(int j=0; j<m; j++) {
                int dist = calDist(workers[i], bikes[j]);
                if(listArr[dist] == null) {
                    listArr[dist] = new ArrayList<>();
                }
                listArr[dist].add(new int[]{i,j});
            }
        }
        
        //iterate through the treemap
        for(int k=0; k<listArr.length; k++) {
            if(listArr[k] == null) continue;
            for(int[] wab: listArr[k]) {
                int worker = wab[0];
                int bike = wab[1];
                if(!assigned[worker] && !occupied[bike]) {
                    assigned[worker] = true;
                    occupied[bike] = true;
                    res[worker] = bike;
                }
            }
        }
        return res;
    }
    
    private int calDist(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }
}