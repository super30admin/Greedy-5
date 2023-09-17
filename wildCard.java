// tc : O(ls*lp)
// sc : o(ls)  memory stack

// problem link : https://leetcode.com/problems/wildcard-matching/


class Solution {
    String s;
    String p;
    int ls;
    int lp;
    int[][] memo;
    public boolean isMatch(String s, String p) {
        // base case

        this.s = s ;
        this.p = p ;
        this.ls = s.length();
        this.lp = p.length();
        this.memo = new int[ls+1][lp+1];

        // intialize memoization
        for(int[] mem : memo)
            Arrays.fill(mem,-1);

        
        return dp(0,0);
    }

    private boolean dp(int is, int ip){

        // check memoization
        if(memo[is][ip] != -1)
            return memo[is][ip] == 1;
        
        // base cases
        if(is == ls && ip == lp)
            return true;
        else if((is == ls || ip == lp) && !(ip<lp && p.charAt(ip) == '*'))
            return false;

        // logic 
        char cp = p.charAt(ip);
        char cs = is<ls? s.charAt(is) : '1';
        if(cp == '?'){
            memo[is][ip] = dp(is+1, ip+1)? 1 : 0;
            return memo[is][ip] == 1;
        }
        else if(cp == '*'){
            for(int i = is ; i<= ls ; i++){
                if(dp(i, ip+1)){
                    memo[is][ip] = 1;
                    return true;
                }
            }
            memo[is][ip] = 0;
            return false;
        }
        else{
            if(cp == cs)
                memo[is][ip] = dp(is+1, ip+1)? 1 : 0;
            else      
                memo[is][ip] = 0;
            return memo[is][ip] == 1;
        }
    }
}
