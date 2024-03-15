class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) return new int[]{};

        int n = workers.length, m = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap();

        int minimum = Integer.MAX_VALUE, maximum = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                int distance = findManhattanDistance(workers[i], bikes[j]);

                // Let's keep a track of minimum and maximum values. With the help of these variables we could eliminate 
                // TreeMap and use a Hashmap

                minimum = Math.min(distance, minimum);
                maximum = Math.max(maximum, distance);

                if(!map.containsKey(distance)) map.put(distance, new ArrayList());

                // We store the worker and bike pair as indices
                map.get(distance).add(new int[]{i, j});
            }
        }

        // We are creating these arrays to be sure what bike is assigned to which worker
        int[] result = new int[n];
        Arrays.fill(result, -1);

        int count = 0;

        boolean[] bikesAssigned = new boolean[m];

        // Let's iterate the loop from minimum till maximum
        for(int i = minimum; i <= maximum; i++)
        {
            List<int[]> pairs = map.get(i);

            // We might have a key but it's value might be null
            if(pairs == null) continue;
            
            // Traverse through these pairs
            for(int[] pair : pairs)
            {
                int worker = pair[0];
                int bike = pair[1];

                // The worker is not yet assigned a bike
                if(result[worker] == -1)
                {  
                    // Bike is not yet assigned
                    if(bikesAssigned[bike] == false)
                    {
                        result[worker] = bike;
                        bikesAssigned[bike] = true;
                        
                        // A worker is assigned a bike
                        ++count;

                        // It means if all the workers are assigned with bikes there it is not required to traverse the map.
                        if(count == n) return result;
                    }
                }
            }
        }

        return result;
    }

    private int findManhattanDistance(int[] worker, int[] bike)
    {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}