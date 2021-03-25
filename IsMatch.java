// Time Complexity : The time complexity is O(min(m,n)) where m is the length of the s and n is the length of p
// Space Complexity : Te space complexity is O(1) where m is the length of the s and n is the length of p
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public boolean isMatch(String s, String p) {

        if(s.equals(p) || p.equals("*")) return true;

        int pl = p.length();
        int sl = s.length();

        int sp = 0;
        int pp = 0;
        int sStar = -1;
        int pStar = -1;

        while(sp < s.length()){
            // if characters match
            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++; pp++;
            }
            // if character is *
            else if(pp < p.length() && p.charAt(pp) == '*'){
                sStar = sp;
                pStar = pp;
                pp++;
            }
            // if characters dont match
            else if(pStar == -1){
                return false;
            }
            // characters dont match, try to match * with letters
            else{
                sStar++;
                sp = sStar;
                pp = pStar+1;
            }
        }

        while(pp < p.length()){
            if(p.charAt(pp) != '*'){
                return false;
            }
            pp++;
        }
        return true;
    }
}