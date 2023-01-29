// Time Complexity : O(n*m)
// Space Complexity : O(1)  
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//44. Wildcard Matching

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        int m = p.length();
        int n = s.length();
        int sp = 0, pp = 0;
        int sstar = -1, pstar = -1;
        while(sp < n){
            if(pp < m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                pp++;
                sp++;
            }
            else if(pp < m && p.charAt(pp) == '*'){
                pstar = pp;
                sstar = sp;
                pp++;
            }
            else if(pstar == -1){
                return false;
            }
            else{
                pp = pstar;
                sp = sstar + 1;
                sstar = sp;
            }
        }
        while(pp < m){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        return true;
    }
}