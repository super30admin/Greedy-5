//Problem 1: WildCard Matching
// Time Complexity : O(mlogn)Avg  O(mn)worst O(1)best
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//DP-> O(mn)
//Two pointers -> O(mlogn)
//keep note when star happens in pattern string and store pointers at both strings for each star check all possibilities and check if we exhaust our source string pointer
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        int sl=s.length(), pl=p.length();
        // boolean[][] dp= new boolean[pl+1][sl+1];
        // dp[0][0]=true;
        // //top row
        // for(int i=1;i<=pl;i++){
        //     for(int j=0;j<=sl;j++){
        //         char pChar=p.charAt(i-1);

        //         if(pChar!='*'){
        //             if(j>0 && (pChar==s.charAt(j-1) || pChar=='?')){
        //                 dp[i][j]=dp[i-1][j-1];
        //             }else{
        //                 dp[i][j]=false;
        //             }
        //         }else{
        //             if(j==0){
        //                 dp[i][j]=dp[i-1][j];
        //             }else{
        //                 dp[i][j]=dp[i-1][j] || dp[i][j-1];
        //             }
        //         }
        //     }
        // }
        // return dp[pl][sl]; 

        //O(mn) O(m+n)
        // boolean[] dp= new boolean[sl+1];
        // dp[0]=true;
        // //top row
        // for(int i=1;i<=pl;i++){
        //     boolean diagUp=dp[0];
        //     for(int j=0;j<=sl;j++){
        //         char pChar=p.charAt(i-1);
        //         boolean temp=dp[j];
        //         if(pChar!='*'){
        //             if(j>0 && (pChar==s.charAt(j-1) || pChar=='?')){
        //                 dp[j]=diagUp;
        //             }else{
        //                 dp[j]=false;
        //             }
        //         }else{
        //             if(j==0){
        //                 dp[j]=dp[j];
        //             }else{
        //                 dp[j]=dp[j] || dp[j-1];
        //             }
        //         }
        //         diagUp=temp;
        //     }
        // }
        // return dp[sl]; 


        //Two Pointers -> Karl & Barton Thm
        //O(mlogn) O(1)
        int pidx=0,sidx=0,pStar=-1,sStar=-1;

        while(sidx<sl){
            if(pidx<pl && 
                (s.charAt(sidx)==p.charAt(pidx) || p.charAt(pidx)=='?')){
                sidx++;
                pidx++;
            }else if(pidx<pl && p.charAt(pidx)=='*'){
                pStar=pidx;
                sStar=sidx;
                pidx++;
            }else if(pStar==-1){
                return false;
            }
            else{
                sStar++;
                sidx=sStar;
                pidx=pStar+1;
            }
        }

        while(pidx<pl){
            if(p.charAt(pidx)!='*') return false;
            pidx++;
        }

        return true;
    }
}

//Problem 2: Bikes in Campus
// Time Complexity : O(MN)
// Space Complexity : O(MN)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : tough one


// Your code here along with comments explaining your approach
//Use bucket sort for linear sorting, then form distance:bike worker pairs in hashmap. iterate these pairs until all bikes are occupied with workers. 
class Solution {
    //O(MN) O(MN)
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        int n=workers.length;
        int m=bikes.length;
        int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        HashMap<Integer, List<int[]>> map= new HashMap<>();
        //for loop to find out minimum/max
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int dist=calcDistance(workers[i], bikes[j]);
                min=Math.min(min, dist);
                max=Math.max(max, dist);
                if(!map.containsKey(dist))
                    map.put(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i, j});

            }
        }
        int count=0;//to check number of workers assigned
        int[] res=new int[n];
        boolean[] assignedbikes=new boolean[m];
        boolean[] assignedworkers=new boolean[n];
        for(int key=min;key<=max;key++){
            if(!map.containsKey(key))
                continue;
                List<int[]> wbpair=map.get(key);
                for(int[] arr: wbpair){
                    int worker=arr[0];
                    int  bike=arr[1];

                    if(!assignedbikes[bike] && !assignedworkers[worker]){
                        assignedbikes[bike]=true;
                        assignedworkers[worker]=true;
                        res[worker]=bike;
                        count++;
                        if(count==m) return res;
                    }
                }
        }
        return res;
    }

    int calcDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}