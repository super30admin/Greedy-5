/*

https://leetcode.com/problems/wildcard-matching/

Did it run leetcode: TLE
Did you face any problem: empty string and * is the pattern

Time complexity: exponential
Space compelxity: 0(MxN)+ recursion stack

Algorithm:

- dp[i][j] can we match 0..ith string with 0...jth pattern
- if s.charAt(i)==p.charAt(j) then we recursively try to get reult for remaining string
- if there is * in pattern then there are three possibilities
    - matching seq with *
    - matching * with empty string
    - end matching seq with * and inc both

*/

class Solution {
    
    Integer[][] dp;
    
    public boolean isMatch(String s, String p) {
        
        dp = new Integer[s.length()+1][p.length()+1];
        
        dp[s.length()][p.length()] = 1;
        
        for(int i=p.length()-1;i>=0;--i){
            if(p.charAt(i)=='*'){
                dp[s.length()][i] = dp[s.length()][i+1];
            }else{
                dp[s.length()][i] = 0;
            }
            
        }
        
        for(int j=0;j<s.length();++j){  dp[j][p.length()] = 0; }
        
        dfsHelper(s,p,0,0);
        
        printDP(dp);
        return dp[0][0]==1;
    }
    
    private int dfsHelper(String s,String p,int sp,int pp){
        
        System.out.println("SP:"+sp+", PP:"+pp);
        if(dp[sp][pp]!=null){
            return dp[sp][pp];
        }
        
        if(s.charAt(sp)==p.charAt(pp) || p.charAt(pp)=='?'){
            dp[sp][pp] = dfsHelper(s,p,sp+1,pp+1);
        }
        
        else if(p.charAt(pp)=='*'){
            
            boolean resultOne = dfsHelper(s,p,sp+1,pp)==1; // matching seq with *
            boolean resultTwo = dfsHelper(s,p,sp,pp+1)==1; // matching * with empty string
            boolean resultThree = dfsHelper(s,p,sp+1,pp+1)==1; // end matching seq with * and inc both
            
            dp[sp][pp] = (resultOne || resultTwo || resultThree) ? 1:0;
            
        }else{
            dp[sp][pp] = 0;
        }
        
        return dp[sp][pp];
        
    }
    
    private void printDP(Integer[][] arr){
        for(int i=0;i<arr.length;++i){
            System.out.println("");
            for(int j=0;j<arr[i].length;++j){
                System.out.print(arr[i][j]+",");
            }
        }
    }
    
}