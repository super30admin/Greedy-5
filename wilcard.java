// Time Complexity :O(mn)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : Figuring out the way to remmeber the indices. 


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        if(s.isEmpty() || p.isEmpty()) return false;
        int S=0;
        int P=0;
        int S_star = -1;
        int P_star = -1;
        while(S<s.length())
        {
            if(P<p.length() && (s.charAt(S)==p.charAt(P) || p.charAt(P)=='?')) {
                S++;
                P++;
            }
            else if(P<p.length() && p.charAt(P)=='*') //null case
            {
                S_star = S;
                P_star = P;
                P++;
            }
            else if(P_star == -1) return false; // mismatch case
            else // more than one star case
            {
                S = S_star+1;
                P = P_star+1;
                S_star = S;
            }
        }
        while(P<p.length()) // check if there is any character in the pattern
        {
            if(p.charAt(P)!='*') return false;
            P++;
        }
        return true;
        
    }
}