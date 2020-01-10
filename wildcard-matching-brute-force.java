/*

https://leetcode.com/problems/wildcard-matching/

Did it run on leetcode: TLE
Did you face any problem:
- How to increment at *
- How to deal with * at the last of pattern, pattern pointern recahed end

Time Complexity: exponential

Algorithm:
- have two pointers sp,pp for input and pattern
- if both characters are same of at pattern is equal to `?`, increment both pointers
- if its pattern equal to star, then you have two cases 
    - increment sp pointer
    - increment pp pointer


*/




class Solution {
    public boolean isMatch(String s, String p) {
        
        int sp = 0;
        int pp = 0;
        return dfsHelper(s,p,sp,pp);
        
    }
    
    public boolean dfsHelper(String input,String pattern,int sp,int pp){
        
        // base cases
        
        //means no more to match, reached end of both pattern and string
        if(input.length()==sp && pattern.length()==pp){
            return true;
        }
        
        // end of pattern so this is a false case
        if(pattern.length()==pp){
            return false;
        }
        
        if(input.length()==sp){
           return pattern.charAt(pp)=='*' && dfsHelper(input,pattern,sp,pp+1) ;
        }
        
        // recursive cases
        if(input.charAt(sp)==pattern.charAt(pp)){
            return dfsHelper(input,pattern,sp+1,pp+1);
        }
        else if(pattern.charAt(pp)=='?'){
            return dfsHelper(input,pattern,sp+1,pp+1);
        }
        else if(pattern.charAt(pp)=='*'){
            return dfsHelper(input,pattern,sp+1,pp) || dfsHelper(input,pattern,sp,pp+1);
        }
        else{
            return false;
        }
        
        
    }
}