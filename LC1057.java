// TC: (m*n*log(m*n))
// SC: O(m*n)
// where m is the number of workers and n is the number of bikes

import java.util.*;

public class LC1057 {
    private int getDist(int w[], int b[]){
        return Math.abs(w[0]-b[0]) + Math.abs(w[1]-b[1]);
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int ans[] = new int[n];
        Map<Integer, List<int[]>> map = new TreeMap();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int dist = getDist(workers[i], bikes[j]);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i, j});
            }
        }
        int wVis[] = new int[n];
        int bVis[] = new int[m];
        for(int d: map.keySet()){
            for(int[] i : map.get(d)){
                if(wVis[i[0]] == 1 || bVis[i[1]] == 1) continue;
                ans[i[0]] = i[1];
                wVis[i[0]] = 1;
                bVis[i[1]] = 1;
            }
        }
        return ans;
    }
}
