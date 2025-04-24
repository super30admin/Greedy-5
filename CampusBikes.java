class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int n= workers.length;
        int m = bikes.length;
        int min = Integer.MAX_VALUE;
        int max=0;
        for(int i=0;i<n;i++)
        {
            int [] w = workers[i];
            for(int j=0;j<m;j++)
            {
                int [] b = bikes[j];
                int dist = Math.abs(w[0]-b[0])+ Math.abs(w[1]-b[1]);
                max = Math.max(dist,max);
                min = Math.min(dist,min);
                if(!map.containsKey(dist))
                {
                    map.put(dist, new ArrayList<>());
                }
                
                map.get(dist).add(new int[]{i,j});
            }
        }
        int [] res = new int[n];
        boolean [] assigned = new boolean[n]; //for workers
        boolean [] occupied = new boolean[m]; //for bikes
        int cnt=0;
        for(int dist=min;dist<=max && cnt <n ;dist++)
        {
            List<int[]> li = map.get(dist);
            if(li!= null)
            {
            for(int[] wb: li)
            {
            
                int w = wb[0];
                int b= wb[1];
                if(!assigned[w] && !occupied[b])
                {
                    assigned[w]=true;
                    occupied[b] = true;
                    res[w]=b;
                    cnt++;
                }
            }
            }
        }
        return res;
    }
}

//tc : O(n X m)
//sc: O(n X m)