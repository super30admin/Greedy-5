// Time: O(mn)
// Space: O(mn)

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || bikes == null || workers.length == 0 || bikes.length == 0){
            return new int[0];
        }

        HashMap <Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int m = workers.length;
        int n = bikes.length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int val = calcDist(workers[i], bikes[j]);
                min = Math.min(min, val);
                max = Math.max(max, val);
                if (!map.containsKey(val)){
                    map.put(val, new ArrayList<>());
                }
                map.get(val).add(new int[] {i,j});
                // map[val].add(new int[] {i,j});
            }
        }
        
        int[] result = new int[m];
        int[] assignworkers = new int[m];
        int[] assignbikes = new int[n];
        int count = 0;
        for(int i = min; i <= max; i++){
            List<int []> li = map.get(i);
            if (li == null) continue;
            for(int j = 0; j < li.size(); j++){
                int [] arr = li.get(j);
                int w = arr[0];
                int b = arr[1];
                
                if (assignworkers[w] == 0 && assignbikes[b] == 0){
                // if ((assignworkers[w] == 0) && (assignbikes[b] == 0)){
                    assignworkers[w] = 1;
                    assignbikes[b] = 1;
                    result[w] = b;
                    count++;
                }
                
                if (count == m){
                    return result;
                }
            }
        }
        
        return result;
        
    }
    private int calcDist(int[] w, int[] b){
        return Math.abs(w[0]-b[0]) + Math.abs(w[1]-b[1]);
    }
}
