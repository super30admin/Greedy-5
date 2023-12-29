// Time Complexity : O(n*m)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length;
        int n = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++){
            int[] w = workers[i];
            for(int j = 0; j < n; j++){
                int[] b = bikes[j];
                int dist = calculateDistance(w,b);
                min = Math.min(min,dist);
                max = Math.max(max,dist);
                if(!map.containsKey(dist))
                    map.put(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i,j});
                
            }
        }
        boolean[] wvis = new boolean[m];
        boolean[] bvis = new boolean[n];
        int[] res = new int[m];
        int cnt = 0;
        for(int i = min; i <= max; i++){
            if(map.containsKey(i)){
                List<int[]> li = map.get(i);
                for(int[] wb : li){
                    int w = wb[0];
                    int b = wb[1];
                    if(!wvis[w] && !bvis[b]){
                        res[w] = b;
                        wvis[w] = true;
                        bvis[b] = true;
                        cnt++;
                        if(cnt == m)
                            return res;
                    }
                }
            }
        }
        return res;
    }
    private int calculateDistance(int[] w, int[] b){
        return Math.abs(w[0]-b[0])+Math.abs(w[1]-b[1]);
    }
}