//TC: O(mnlog(mn))
// SC: O(mn)
class Solution {
    private int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);    
    }
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int m = bikes.length;
        int n = workers.length;
        int output[] = new int[n];
        Arrays.fill(output, -1);
        boolean[] bikesAssigned = new boolean[m];
        //[distance, workerIdx, bikeIdx]
        int arr[][] = new int[n*m][3];
        int c = 0;
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                
                arr[c][0] = getDistance(bikes[j], workers[i]);
                arr[c][1] = i;
                arr[c][2] = j;
                
                c++;
            }
        }
        Arrays.sort(arr, (a,b) -> (a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]): a[0]-b[0]));
        for(int i = 0;i<m*n;i++) {
            // System.out.println(Arrays.toString(arr[i]));
            int dis = arr[i][0], workerId = arr[i][1], bikeId = arr[i][2];
            if(!bikesAssigned[bikeId] && output[workerId] == -1) {
                output[workerId] = bikeId;
                bikesAssigned[bikeId] = true;
                
            }
        }
        
        return output;
    }
}
