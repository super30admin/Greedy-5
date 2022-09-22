//Time Complexity: O(m*n)
//Space Complexity: O(m*n)
//Code run successfully on LeetCode.

public class Problem2 {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        int n = workers.length;
        int m = bikes.length;
        
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        boolean[] worker = new boolean[n];
        boolean[] bike = new boolean[m];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[] result = new int[n];
        int count = 0;
        
        for(int i =0; i <n; i++)
        {
            for(int j =0; j < m; j++)
            {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                
                max = Math.max(max, dist);
                min = Math.min(min, dist);
                
                if(!map.containsKey(dist))
                    map.put(dist, new ArrayList<>());
                
                map.get(dist).add(new ArrayList<>(Arrays.asList(i,j)));
                
            }
        }
        
        for(int i =min; i <= max; i++)
        {
            List<List<Integer>> list = map.get(i);
            
            if(list == null)
                continue;
            
            for(List<Integer> l : list)
            {
                int w = l.get(0);
                int b = l.get(1);
                
                if(!worker[w] && !bike[b])
                {
                    worker[w] = true;
                    bike[b] = true;
                    count ++;
                    result[w] = b;
                    
                    if(count == n)
                        return result;
                }
            }
        }
            
       return result; 
    }
}
