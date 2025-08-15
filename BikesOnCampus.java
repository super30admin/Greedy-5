// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : no(needs premium)
// Any problem you faced while coding this : no
// Approach : Find distance b/w all possible worker and bikes pairs. Sort them using bucket sort. Assign bikes to closest workers

public class Main {
    public static void main(String[] args) {
        int workers[][] = new int[][]{{1,2}, {1,1}, {3,2}};
        int bikes[][] = new int[][]{{2,3}, {7, 5}, {5,1}};
        int workerAssg[] = getWorkerBikeAssignment(workers, bikes);
        for(int i=0;i<workerAssg.length;i++){
            System.out.println("Worker "+(i+1)+" assigned to "+(workerAssg[i]+1));
        }
    }
    public static int[] getWorkerBikeAssignment(int workers[][], int bikes[][]){
        int n = workers.length, m = bikes.length;
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max= Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int w[] = workers[i];
                int b[] = bikes[j];
                int dist = manhattanDist(w, b);
                min = Math.min(min, dist);
                max = Math.max(max, dist);
                if(!map.containsKey(dist)){
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i, j});
            }
        }
        boolean opworkers[] = new boolean[n];
        boolean opbikes[] = new boolean[m];
        int count=0;
        int res[] = new int[n];
        for(int i=min;i<=max;i++){
            if(map.containsKey(i)){
                List<int[]> li = map.get(i);
                for(int arr[]: li){
                    int w = arr[0];
                    int b = arr[1];
                    if(!opworkers[w] && !opbikes[b]){
                        opworkers[w] = true;
                        opbikes[b] = true;
                        res[w] = b;
                    }
                }
            }
        }
        return res;
    }
    private static int manhattanDist(int x[], int y[]){
        return Math.abs(x[0]- y[0])+Math.abs(x[1]-y[1]);
    }
}