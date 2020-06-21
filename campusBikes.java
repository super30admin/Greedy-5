// Time Complexity :O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : Figuring out the way to remmeber the indices. 


// Your code here along with comments explaining your approach
class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]> [] dist = new List[2000]; // keep track of pairs with distance
        for(int i =0;i<workers.length;i++)
        {
            for (int j =0; j <bikes.length;j++)
            {
                int d = distance(workers[i],bikes[j]);
                if(dist[d]==null)
                {
                    dist[d]=new ArrayList<>();
                }
                dist[d].add(new int[]{i,j});
            }
        }
        boolean [] w = new boolean[workers.length]; // check the status of workers
        boolean [] b = new boolean[bikes.length]; // check the stattus of bikes
        int[] result = new int[workers.length];
        for(int i =0; i <dist.length;i++)
        {
            if(dist[i]!=null)
            {
                for(int[] pair:dist[i])
                {
                    if(!w[pair[0]] && !b[pair[1]])
                    {
                        w[pair[0]] = true;
                        b[pair[1]] =true;
                        result[pair[0]] = pair[1];
                    }
                }
            }
        }
        return result;
    }
    private int distance(int[] point1, int[] point2)
    {
        //manhattan. distance
        return Math.abs(point1[0]-point2[0]) + Math.abs(point1[1]-point2[1]);
    }
}