// tc :  O(n*m)
// sc :  O(n*m)

// problem link : https://leetcode.com/problems/campus-bikes/description/

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m  = bikes.length; 

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[1] == b[1])
                if(a[2]==b[2])
                    return a[0]-b[0];
                else 
                    return a[2]-b[2];
            return a[1]-b[1];
        });

        for(int i = 0 ; i<n ; i++){
            int[] w = workers[i];
            for(int j =  0 ; j < m ; j++){
                int[] b = bikes[j];
                int dist = Math.abs(b[0] - w[0]) + 
                           Math.abs(b[1] - w[1]);
                pq.add(new int[] {j , dist, i});
            }
        }

        int[] result = new int[n];
        Set<Integer> seen = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        while(set.size()<n){
            int[] curr = pq.poll();
            if(seen.contains(curr[0]))
                continue;
            if(set.contains(curr[2]))
                continue;
            result[curr[2]] = curr[0];
            seen.add(curr[0]);
            set.add(curr[2]);
        }
        return result;
    }
}
